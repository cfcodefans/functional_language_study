import * as bn from "./binance-api"
import * as RP from 'request-promise'
import * as _ from "lodash"

async function main() {
    console.info("test")

    // console.info(bn.BASE)
    let req = await RP("http://baidu.com")
    console.info(req)

    let d = {}
    _.update(d, "symbol", (symbol: string) => symbol)
    console.info(d)
}

main()