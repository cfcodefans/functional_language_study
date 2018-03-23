import * as bn from "./binance-api"
import { IExchangeInfo, OPTS } from "./defs"

async function main() {
    console.info("test")

    // console.info(bn.BASE)
    // let exchangeInfo: IExchangeInfo = await bn.exchangeInfo()
    // exchangeInfo.symbols.map(s => s.symbol).forEach(console.info)

    // console.info(await bn.time())
    // console.info(await bn.depth("ETHBTC", 5))
    // console.info(await bn.price("ETHBTC"))
    // console.info(await bn.price("BTCETH"))

    console.info(await bn.ticker24H("ETHBTC"))
    console.info(await bn.recentTrades("ETHBTC", 10))


    // OPTS.apiKey="vmPUZE6mv9SD5VNHk4HlWFsOr6aKE2zvsw0MuIgwCIPy6utIco14y7Ju91duEh8A"
    // OPTS.apiSecret="NhqPtmdSJYdKjVHjA7PZj4Mge3R5YNiP1e3UZjInClVN65XAbvqqM6A7H5fATj0j"

    // console.info(await bn.trades("ETHBTC", 10))
}

main()