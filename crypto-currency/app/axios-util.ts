import * as _ from 'lodash'
import * as crypto from 'crypto'
import * as d from './defs'

import * as ax from "axios"

export async function publicReq(_url: string, _params: any, _method: string = 'get'): Promise<any> {
    if (_.isEmpty(_url)) return Promise.resolve(null);

    console.info(`${_method} ${_url}`)

    const reqCfgs = {
        url: _url,
        method: _method,
        params: _params,
        timeout: d.OPTS.recvWindow,
        headers: {
            'User-agent': d.USER_AGENT,
            'Content-type': d.CONTENT_TYPE
        }
    };

    if (!_params)
        delete reqCfgs.params

    return ax.default.create()(reqCfgs)
        .then((resp: ax.AxiosResponse) => {
            return resp.data
        }).catch(reason => {
            console.error(reason)
        })
}

export async function apiReq(_url: string, _params: any, _method: string = 'get'): Promise<any> {
    if (_.isEmpty(d.OPTS.apiKey)) throw 'apiReq: Invalid API Key';

    return ax.default.create()({
        url: _url,
        method: _method,
        timeout: d.OPTS.recvWindow,
        params: _params,
        headers: {
            'User-agent': d.USER_AGENT,
            'Content-type': d.CONTENT_TYPE,
            'X-MBX-APIKEY': d.OPTS.apiKey
        }
    }).then((resp: ax.AxiosResponse) => resp.data)
        .catch(console.error)
}

export async function signedReq(_url: string, data: any, _method: string = 'get'): Promise<any> {
    if (_.isEmpty(d.OPTS.apiSecret)) throw 'signedReq: Invalid API Secret'
    data = data ? data : {}
    data.timestamp = new Date().getTime()
    if (typeof data.symbol !== 'undefined') data.symbol = data.symbol.replace('_', '')
    if (typeof data.recvWindow === 'undefined') data.recvWindow = d.OPTS.recvWindow

    let query: string = Object.keys(data)
        // .filter((key: string) => !_.isEmpty(data[key]))
        .filter((key: string) => data[key])
        .map((key: string) => `${key}=${encodeURIComponent(data[key])}`)
        .join('&')

    let signature: string = crypto.createHmac('sha256', d.OPTS.apiSecret).update(query).digest('hex')
    data.signature = signature
    return apiReq(_url, data)
}


