import * as bn from "./binance-api"
import * as RP from 'request-promise'
import * as _ from "lodash"
import { IExchangeInfo } from "./defs";

async function main() {
    console.info("test")

    // console.info(bn.BASE)
    // let exchangeInfo: IExchangeInfo = await bn.exchangeInfo()
    // exchangeInfo.symbols.map(s => s.symbol).forEach(console.info)

    console.info(await bn.time())
}

main()