import * as bn from "./binance-api"
import { IExchangeInfo } from "./defs"

async function main() {
    console.info("test")

    // console.info(bn.BASE)
    // let exchangeInfo: IExchangeInfo = await bn.exchangeInfo()
    // exchangeInfo.symbols.map(s => s.symbol).forEach(console.info)

    // console.info(await bn.time())
    // console.info(await bn.depth("ETHBTC", 5))
    // console.info(await bn.price("ETHBTC"))
    // console.info(await bn.price("BTCETH"))

    // console.info(await bn.ticker24H("ETHBTC"))
    // console.info(await bn.recentTrades("ETHBTC", 10))
    console.info(await bn.trades("ETHBTC", 10))
}

main()