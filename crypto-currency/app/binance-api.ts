import * as Req from 'request'
import * as RP from 'request-promise'
import * as _ from 'lodash'

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

export function opt(): Options {
    return new Options
}

export async function publicReq(_url: string, data: any, _method: string = 'GET'): Promise<any> {
    if (!(data && !_.isEmpty(_url))) return Promise.resolve(null);

    return RP({
        url: _url,
        method: _method,
        qs: data,
        timeout: opt().recvWindow,
        headers: {
            'User-agent': USER_AGENT,
            'Content-type': CONTENT_TYPE
        }
    })
}

