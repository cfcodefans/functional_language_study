// import * as Req from 'request'
// import * as RP from 'request-promise'
import * as _ from 'lodash'
import * as crypto from 'crypto'
import * as d from './defs'
import { publicReq } from './page-util'


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