const sym1 = Symbol()
const sym2 = Symbol("I am a symbol")
const sym3 = Symbol("the third symbol")
const sym4 = Symbol("I am a symbol")

console.info(sym1, sym2, sym3, sym4)

const propSym = Symbol()
const funcSym = Symbol()

class SomeClass {
    constructor() {
        this[propSym] = 12
    }
    [funcSym](): string {
        return "I am a function value"
    }
}

const someObj = new SomeClass
console.info(someObj[propSym])
console.info(someObj[funcSym]())
console.info(Symbol.hasInstance)
console.info(SomeClass[Symbol.hasInstance](someObj))