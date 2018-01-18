// import * as Req from 'request'
// import * as RP from 'request-promise'
import * as _ from 'lodash'
import * as crypto from 'crypto'
import * as d from './defs'



export async function publicReq(_url: string, data: any, _method: string = 'GET'): Promise<any> {
    if (!(data && !_.isEmpty(_url))) return Promise.resolve(null);

    console.info(`${_method} ${_url}`)

    return fetch(new Request(
        _url, {
            method: _method,
            body: data,
            window: d.OPTS.recvWindow,
            // resolveWithFullResponse: true,
            headers: {
                'User-agent': d.USER_AGENT,
                'Content-type': d.CONTENT_TYPE
            }
        })).then((resp: Response) => resp.json())
        .catch(console.error)
}

export async function apiReq(_url: string, _method: string = 'GET'): Promise<any> {
    if (_.isEmpty(d.OPTS.apiKey)) throw 'apiReq: Invalid API Key';
    return fetch(new Request(_url,
        {
            method: _method,
            window: d.OPTS.recvWindow,
            // resolveWithFullResponse: true,
            // agent:false,
            headers: {
                'User-agent': d.USER_AGENT,
                'Content-type': d.CONTENT_TYPE,
                'X-MBX-APIKEY': d.OPTS.apiKey
            }
        })).then((resp: Response) => resp.json())
        .catch(console.error)
}

export async function signedReq(_url: string, data: any, _method: string = 'GET'): Promise<any> {
    if (_.isEmpty(d.OPTS.apiSecret)) throw 'signedReq: Invalid API Secret'
    data = data ? data : {}
    data.timestamp = new Date().getTime()
    if (typeof data.symbol !== 'undefined') data.symbol = data.symbol.replace('_', '')
    if (typeof data.recvWindow === 'undefined') data.recvWindow = d.OPTS.recvWindow

    let query: string = Object.keys(data)
        .map((key: string) => `${key}=${encodeURIComponent(data[key])}`)
        .join('&')

    let signature: string = crypto.createHmac('sha256', d.OPTS.apiSecret).update(query).digest('hex')
    return apiReq(`${_url}?${query}&signature=${signature}`)
}

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