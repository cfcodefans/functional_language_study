import { suite, test, slow, timeout, skip } from "mocha-typescript"
import { expect } from "chai"

import { tryIt } from "./utils"

@suite class BasicTypesStudy {
    @skip 
    @test _boolean(): void {
        let isDone: boolean = false
        let isNotDone: Boolean = true

        expect(1).not.to.eq(true, "expect 1 eq true")
        expect(1 ? true : false).eq(true, "expect 1 eq true")
        expect(-1).not.to.eq(true, "expect -1 eq true")
        expect(-1 ? true : false).eq(true, "expect 1 eq true")
        expect(2).not.to.eq(true, "expect 2 eq true")
        expect("a").not.to.eq(true, "expect 'a' eq true")
        expect("a" ? true : false).eq(true, "expect 'a' eq true")
        expect({}).not.to.eq(true, "expect {} eq true")
        expect({} ? true : false).eq(true, "expect {} eq true")

        expect(0).not.to.eq(false, "expect 0 eq false")
        expect(0 ? true : false).eq(false, "expect 0 eq false")
        expect(null).not.to.eq(false, "expect null eq false")
        expect(null ? true : false).eq(false, "expect null eq false")
        expect(undefined).not.to.eq(false, "expect undefined eq false")
        expect(undefined ? true : false).eq(false, "expect undefined eq false")

        let _true: boolean = true
        console.info("_true.valueOf()", _true.valueOf())
        // console.info("true + 1", true + 1)
        // console.info("true - 1", true - 1)
        // console.info("true | 1", true | 1)
        // console.info("1 | true", 1 | true)
        // console.info("true | true", true | true)
        tryIt("true || 1", true)
        tryIt("true || 0", true)
        tryIt("true && 1", true)
        tryIt("true && 0", true) // it is not false
        tryIt("0 && 0", false)
    }

    // @skip
    @test _number(): void {
        let decimal: number = 6
        console.info(decimal)
        let hex: number = 0xf00d
        console.info(hex)
        let binary: number = 0b1010
        console.info(binary)
        let octal: number = 0o744
        console.info(octal)

        let _number: number = 1
        let _Number: Number = 1

        expect(_number == _Number)
        expect(_number === _Number)

        let pi: number = Math.PI
        console.info("pi.toExponential(5)", pi.toExponential(5))
        console.info("(1/4).toFixed(4)", (1 / 4).toFixed(4))
        console.info(pi.toLocaleString("zh"))

        console.info("0b100 >> 2", 0b100 >> 2)
        console.info("1 << 2", 0b1 << 2)
        console.info("1 >> 2", 0b1 >> 2)
        console.info("-1 << 2", -1 << 2)
        console.info("-1 >> 2", -1 >> 2)
        console.info("3 % 5", 3 % 5)
        console.info("-3 % 5", -3 % 5)
        console.info("3 % -5", 3 % -5)
        console.info("-3 % -5", -3 % -5)
    }

    @skip
    @test _string(): void {
        let color: string = "blue"
        console.info(color)
        color = "red"
        console.info(color)
        let age: number = 36
        console.info(`I am ${age} years old`)
    }
}