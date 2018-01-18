import * as _ from 'lodash'
import * as crypto from 'crypto'
import * as d from './defs'
import * as Req from 'request'
import * as RP from 'request-promise'

function checkResp(resp: Req.RequestResponse): any {
    if (resp.statusCode == 429) {
        console.error("GOT 429! exits")
        process.exit(1)
    }
    return resp.body
}

export async function publicReq(_url: string, data: any, _method: string = 'GET'): Promise<any> {
    if (!(data && !_.isEmpty(_url))) return Promise.resolve(null);

    console.info(`${_method} ${_url}`)

    return RP({
        url: _url,
        method: _method,
        qs: data,
        timeout: d.OPTS.recvWindow,
        resolveWithFullResponse: true,
        headers: {
            'User-agent': d.USER_AGENT,
            'Content-type': d.CONTENT_TYPE
        }
    }).then(checkResp)
        .then(JSON.parse)
        .catch(console.error)
}

export async function apiReq(_url: string, _method: string = 'GET'): Promise<any> {
    if (_.isEmpty(d.OPTS.apiKey)) throw 'apiReq: Invalid API Key';
    return RP({
        url: _url,
        method: _method,
        timeout: d.OPTS.recvWindow,
        resolveWithFullResponse: true,
        // agent:false,
        headers: {
            'User-agent': d.USER_AGENT,
            'Content-type': d.CONTENT_TYPE,
            'X-MBX-APIKEY': d.OPTS.apiKey
        }
    }).then(checkResp)
        .then(JSON.parse)
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