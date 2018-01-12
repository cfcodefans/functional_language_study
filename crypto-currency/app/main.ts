import * as bn from "./binance-api"
import * as RP from 'request-promise'

async function main() {
    console.info("test")

    // console.info(bn.BASE)
    let req = await RP("http://baidu.com")
    console.info(req)
}

main()