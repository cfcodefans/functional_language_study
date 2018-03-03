
// export const BASE: string = "https://api.binance.com/api/"
// export const WS: string = 'https://api.binance.com/wapi/'
// export const WS_BASE: string = 'wss://stream.binance.com:9443/ws/'
// export const USER_AGENT: string = 'Mozilla/4.0 (compatible; Node Binance API)'
// export const CONTENT_TYPE: string = 'application/x-www-form-urlencoded'



// class Options {
//     recvWindow: number = 60000
//     reconnect: boolean = false
//     test: boolean = false
//     apiKey: string = ''
//     apiSecret: string = ''
// }

export = d;
export as namespace d;

declare namespace d {
    type LimitIntervalType = 'MINUTE' | 'SECOND' | 'DAY'
    type RateLimitType = 'REQUEST' | 'ORDERS'
    type FilterType = "PRICE_FILTER" | "LOT_SIZE" | "MIN_NOTIONAL"

    interface IFilter {
        filterType: FilterType

        maxPrice?: number
        minPrice?: number
        tickSize?: number

        maxQty?: number
        minQty?: number
        stepSize?: number

        minNotional?: number
    }

    interface ISymbol {
        symbol: string
        status: SymbolStatus
        baseAsset: string
        baseAssetPrecision: number,
        quoteAsset: string,
        quotePrecision: number,
        orderTypes: OrderType[],
        icebergAllowed: boolean,
        filters: IFilter[]
    }

    interface IExchangeInfo {
        timezone: string
        serverTime: number
        rateLimits: IRateLimit[]
        symbols: ISymbol[]
    }

    interface IPriceAndQty {
        price: number
        qty: number
    }

    interface IDataResp {
        lastUpdateId: number
    }

    interface IDepth extends IDataResp {
        bids: IPriceAndQty[],
        asks: IPriceAndQty[]
    }

    interface IRateLimit {
        rateLimitType: RateLimitType
        interval: LimitIntervalType
        limit: number
    }

    type OrderType = "LIMIT" | "LIMIT_MAKER" | "MARKET" | "STOP_LOSS" | "STOP_LOSS_LIMIT" | "TAKE_PROFIT" | "TAKE_PROFIT_LIMIT"

    type OrderStatus = "NEW" |
        "PARTIALLY_FILLED" |
        "FILLED" |
        "CANCELED" |
        "PENDING_CANCEL" | // (currently unused)
        "REJECTED" |
        "EXPIRED"

    type SymbolStatus = 'PRE_TRADING' |
        'TRADING' |
        'POST_TRADING' |
        'END_OF_DAY' |
        'HALT' |
        'AUCTION_MATCH' |
        'BREAK'

    type OrderSide = "BUY" | "SELL"

    type TimeInForce = "GTC" | "IOC" | "FOK"
}

// const OPTS: Options = new Options()

// const intervalLimits = [5, 10, 20, 50, 100, 500, 1000]
