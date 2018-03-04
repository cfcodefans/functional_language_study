export interface Cat {
    age: number
    name: string
}

type ReadOnlyCat = {
    readonly [P in keyof Cat]: Cat[P]
}

type GenericReadOnly<T> = {
    readonly [P in keyof T]: T[P]
}

type ReadOnlyCat2 = GenericReadOnly<Cat>

type GenericPartial<T> = {
    [P in keyof T]?: T[P]
}
type PartialCat = GenericPartial<Cat>
type ReadOnlyPartialCat = GenericReadOnly<GenericPartial<Cat>>

type GenericNullable<T> = {
    [P in keyof T]: T[P] | null
}

type NullableCat = GenericNullable<Cat>

interface Proxy<T> {
    get(): T
    set(value: T): void
}

type Proxied<T> = {
    [P in keyof T]: Proxy<T>
}
type ProxiedCat = Proxied<Cat>

const readOnlyCat: ReadOnlyCat = { name: "tom", age: 2 }
const readOnlyCat2: ReadOnlyCat2 = { name: "jack", age: 4 }
// readOnlyCat.age++
const partialCat: PartialCat = { name: "peter" }
partialCat.age = 4
const partialReadOnlyCat: ReadOnlyPartialCat = { name: "john" }
// partialReadOnlyCat.age = 5
const nullableCat: NullableCat = { name: "nick", age: null } // can't omit age
// const proxyCat: ProxiedCat = { name: "prank", age: 4 }