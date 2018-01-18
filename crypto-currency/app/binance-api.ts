import * as Req from 'request'
import * as RP from 'request-promise'
import * as _ from 'lodash'
import * as crypto from 'crypto'
import { IExchangeInfo } from './defs';

export const BASE: string = "https://api.binance.com/api/"
export const WS: string = 'https://api.binance.com/wapi/'
export const WS_BASE: string = 'wss://stream.binance.com:9443/ws/'
export const USER_AGENT: string = 'Mozilla/4.0 (compatible; Node Binance API)'
const CONTENT_TYPE: string = 'application/x-www-form-urlencoded'

export class Options {
    recvWindow: number = 60000
    reconnect: boolean = false
    test: boolean = false
    apiKey: string = ''
    apiSecret: string = ''
}

export const OPTS: Options = new Options()

export async function publicReq(_url: string, data: any, _method: string = 'GET'): Promise<any> {
    if (!(data && !_.isEmpty(_url))) return Promise.resolve(null);

    console.info(`${_method} ${_url}`)

    return RP({
        url: _url,
        method: _method,
        qs: data,
        timeout: OPTS.recvWindow,
        resolveWithFullResponse: true,
        headers: {
            'User-agent': USER_AGENT,
            'Content-type': CONTENT_TYPE
        }
    }).then((resp: Req.RequestResponse) => resp.body)
        .then(JSON.parse)
        .catch(console.error)
}

export async function apiReq(_url: string, _method: string = 'GET'): Promise<any> {
    if (_.isEmpty(OPTS.apiKey)) throw 'apiReq: Invalid API Key';
    return RP({
        url: _url,
        method: _method,
        timeout: OPTS.recvWindow,
        resolveWithFullResponse: true,
        // agent:false,
        headers: {
            'User-agent': USER_AGENT,
            'Content-type': CONTENT_TYPE,
            'X-MBX-APIKEY': OPTS.apiKey
        }
    }).then((resp: Req.RequestResponse) => resp.body)
        .then(JSON.parse)
        .catch(console.error)
}

export async function signedReq(_url: string, data: any, _method: string = 'GET'): Promise<any> {
    if (_.isEmpty(OPTS.apiSecret)) throw 'signedReq: Invalid API Secret'
    data = data ? data : {}
    data.timestamp = new Date().getTime()
    if (typeof data.symbol !== 'undefined') data.symbol = data.symbol.replace('_', '')
    if (typeof data.recvWindow === 'undefined') data.recvWindow = OPTS.recvWindow

    let query: string = Object.keys(data)
        .map((key: string) => `${key}=${encodeURIComponent(data[key])}`)
        .join('&')

    let signature: string = crypto.createHmac('sha256', OPTS.apiSecret).update(query).digest('hex')
    return apiReq(`${_url}?${query}&signature=${signature}`)
}

export async function exchangeInfo(): Promise<IExchangeInfo> {
    return await publicReq(BASE + "v1/exchangeInfo", {}) as IExchangeInfo
}

export async function ping(): Promise<boolean> {
    let resp: any = await publicReq(BASE + "v1/ping", {})
    console.info(resp)
    return !_.isNull(resp)
}

export async function time(): Promise<number> {
    let resp: any = await publicReq(BASE + "v1/time", {})
    console.info(resp)
    return parseInt(_.get(resp, "serverTime") as string)
}