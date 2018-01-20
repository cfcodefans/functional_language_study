// import * as Req from 'request'
// import * as RP from 'request-promise'
import * as _ from 'lodash'
import * as crypto from 'crypto'
import * as d from './defs'
import { publicReq } from './node-util'

export async function exchangeInfo(): Promise<d.IExchangeInfo> {
    return await publicReq(d.BASE + "v1/exchangeInfo", {}) as d.IExchangeInfo
}

export async function ping(): Promise<boolean> {
    let resp: any = await publicReq(d.BASE + "v1/ping", {})
    console.info(resp)
    return !_.isNull(resp)
}

export async function time(): Promise<number> {
    let resp: any = await publicReq(d.BASE + "v1/time", {})
    console.info(resp)
    return parseInt(_.get(resp, "serverTime") as string)
}

export async function depth(symbol: string, limit: number = 100): Promise<d.IDepth> {
    if (_.isEmpty(symbol))
        throw `symbol is empty`

    if (!d.intervalLimits.includes(limit))
        throw `invalid limit ${limit} is not in ${d.intervalLimits}`

    return publicReq(d.BASE + "v1/depth", { symbol: symbol, limit: limit })
        .then(result => {
            let _bids: any[][] = _.get(result, "bids", []) as any[][]
            let _asks: any[][] = _.get(result, "asks", []) as any[][]
            return {
                lastUpdateId: _.get(result, "lastUpdateId") as number,
                bids: _bids.map(bid => { return { price: bid[0], qty: bid[1] } }),
                asks: _asks.map(ask => { return { price: ask[0], qty: ask[1] } })
            }
        })
}