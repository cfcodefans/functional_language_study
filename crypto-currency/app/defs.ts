
export const BASE: string = "https://api.binance.com/api/"
export const WS: string = 'https://api.binance.com/wapi/'
export const WS_BASE: string = 'wss://stream.binance.com:9443/ws/'
export const USER_AGENT: string = 'Mozilla/4.0 (compatible; Node Binance API)'
export const  CONTENT_TYPE: string = 'application/x-www-form-urlencoded'

//https://github.com/binance-exchange/binance-official-api-docs/blob/master/errors.md
export interface Error {
    code: number
    msg: string
}

export class Options {
    recvWindow: number = 60000
    reconnect: boolean = false
    test: boolean = false
    apiKey: string = ''
    apiSecret: string = ''
}

export const OPTS: Options = new Options()

export type RateLimitType = 'REQUEST' | 'ORDERS'
export type LimitIntervalType = 'MINUTE' | 'SECOND' | 'DAY'

export interface IRateLimit {
    rateLimitType: RateLimitType
    interval: LimitIntervalType
    limit: number
}


export type FilterType = "PRICE_FILTER" | "LOT_SIZE" | "MIN_NOTIONAL"

export type OrderType = "LIMIT" | "LIMIT_MAKER" | "MARKET" | "STOP_LOSS" | "STOP_LOSS_LIMIT" | "TAKE_PROFIT" | "TAKE_PROFIT_LIMIT"

export type OrderStatus = "NEW" |
    "PARTIALLY_FILLED" |
    "FILLED" |
    "CANCELED" |
    "PENDING_CANCEL" | // (currently unused)
    "REJECTED" |
    "EXPIRED"

export type OrderSide = "BUY" | "SELL"

export type TimeInForce = "GTC" | "IOC" | "FOK"

export interface IFilter {
    filterType: FilterType

    maxPrice?: number
    minPrice?: number
    tickSize?: number

    maxQty?: number
    minQty?: number
    stepSize?: number

    minNotional?: number
}

export type SymbolStatus = 'PRE_TRADING' |
    'TRADING' |
    'POST_TRADING' |
    'END_OF_DAY' |
    'HALT' |
    'AUCTION_MATCH' |
    'BREAK'

export interface ISymbol {
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

export interface IExchangeInfo {
    timezone: string
    serverTime: number
    rateLimits: IRateLimit[]
    symbols: ISymbol[]
}

export const intervalLimits = [5, 10, 20, 50, 100, 500, 1000]

export interface IPriceAndQty {
    price: number
    qty: number
}

export interface IDataResp {
    lastUpdateId: number
}

export interface IDepth extends IDataResp {
    symbol: string
    bids: IPriceAndQty[],
    asks: IPriceAndQty[]
}

export interface ISymbolPrice {
    symbol: string
    price: string
}

export interface ISymbolTicker {
    symbol: string
    priceChange: string
    priceChangePercent: string
    weightedAvgPrice: string
    prevClosePrice: string
    lastPrice: string
    lastQty: string
    bidPrice: string
    askPrice: string
    openPrice: string
    highPrice: string
    lowPrice: string
    volume: string
    quoteVolume: string
    openTime: number
    closeTime: number
    fristId: number
    lastId: number
    count: number
}

export interface IRecentTradesResp {
    symbol: string
    transactions: ITransaction[]
}

export interface ITransaction {
    symbol: string
    id: number
    price: string
    qty: string
    time: number
    isBuyerMaker: boolean
    isBestMatch: boolean
}