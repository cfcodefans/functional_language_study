import * as bn from "./binance-node"
import { IExchangeInfo } from "./defs"

async function main() {
    console.info("test")

    // console.info(bn.BASE)
    // let exchangeInfo: IExchangeInfo = await bn.exchangeInfo()
    // exchangeInfo.symbols.map(s => s.symbol).forEach(console.info)

    console.info(await bn.time())
    console.info(await bn.depth("ETHBTC", 5))
}

main()