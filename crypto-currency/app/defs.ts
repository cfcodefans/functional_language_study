

export type RateLimitType = 'REQUEST' | 'ORDERS'
export type LimitIntervalType = 'MINUTE' | 'SECOND' | 'DAY'

export interface IRateLimit {
    rateLimitType: RateLimitType
    interval: LimitIntervalType
    limit: number
}

export type FilterType = "PRICE_FILTER" | "LOT_SIZE" | "MIN_NOTIONAL"

export type OrderType = "LIMIT" | "LIMIT_MAKER" | "MARKET" | "STOP_LOSS" | "STOP_LOSS_LIMIT" | "TAKE_PROFIT" | "TAKE_PROFIT_LIMIT"

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