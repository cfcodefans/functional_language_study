// import * as Req from 'request'
// import * as RP from 'request-promise'
import * as _ from 'lodash'
import * as crypto from 'crypto'
import * as d from './defs'
import { publicReq, signedReq, apiReq } from './axios-util'

export async function exchangeInfo(): Promise<d.IExchangeInfo> {
    return await publicReq(d.BASE + "v1/exchangeInfo", null) as d.IExchangeInfo
}

export async function ping(): Promise<boolean> {
    let resp: any = await publicReq(d.BASE + "v1/ping", null)
    console.info(resp)
    return !_.isNull(resp)
}

export async function time(): Promise<number> {
    let resp: any = await publicReq(d.BASE + "v1/time", {})
    console.info(resp)
    return parseInt(_.get(resp, "serverTime") as string)
}

export async function depth(_symbol: string, limit: number = 100): Promise<d.IDepth> {
    if (_.isEmpty(_symbol))
        throw `symbol is empty`

    if (!d.intervalLimits.includes(limit))
        throw `invalid limit ${limit} is not in ${d.intervalLimits}`

    return publicReq(d.BASE + "v1/depth", { symbol: _symbol, limit: limit })
        .then(result => {
            let _bids: any[][] = _.get(result, "bids", []) as any[][]
            let _asks: any[][] = _.get(result, "asks", []) as any[][]
            return {
                symbol: _symbol,
                lastUpdateId: _.get(result, "lastUpdateId") as number,
                bids: _bids.map(bid => { return { price: bid[0], qty: bid[1] } }),
                asks: _asks.map(ask => { return { price: ask[0], qty: ask[1] } })
            }
        })
}

export async function price(_symbol: string): Promise<d.ISymbolPrice[]> {
    return publicReq(d.BASE + "v3/ticker/price", _symbol ? { symbol: _symbol } : null)
        .then(result => {
            if (_.isArray(result))
                return result as d.ISymbolPrice[]
            else
                return [result as d.ISymbolPrice]
        })
}

export async function ticker24H(_symbol?: string): Promise<d.ISymbolTicker> {
    return publicReq(d.BASE + "v1/ticker/24hr", _symbol ? { symbol: _symbol } : null)
        .then(result => result as d.ISymbolTicker)
}

export async function recentTrades(symbol: string, limit: number = 500): Promise<d.IRecentTradesResp> {
    limit = (limit < 1 || limit > 500) ? 500 : limit
    if (_.isEmpty(symbol))
        throw `symbol is empty`

    return publicReq(d.BASE + "v1/trades", symbol ? { symbol, limit } : null)
        .then(result => {
            let resp: d.IRecentTradesResp = {
                'symbol': symbol,
                transactions: (result as d.ITransaction[])
            }
            resp.transactions.forEach(t => t.symbol = symbol)
            return resp
        })
}

export async function trades(symbol: string, limit: number = 500, fromId?: number): Promise<d.IRecentTradesResp> {
    limit = (limit < 1 || limit > 500) ? 500 : limit
    if (_.isEmpty(symbol))
        throw `symbol is empty`

    return apiReq(d.BASE + "v1/historicalTrades", symbol ? { symbol, limit, fromId } : null)
        .then(result => {
            let resp: d.IRecentTradesResp = {
                'symbol': symbol,
                transactions: (result as d.ITransaction[])
            }
            resp.transactions.forEach(t => t.symbol = symbol)
            return resp
        }).catch((reason: any) => {
            console.error(reason)
            return null
        })
}