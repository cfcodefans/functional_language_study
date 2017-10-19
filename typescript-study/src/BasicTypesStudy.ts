import { suite, test, slow, timeout, skip } from "mocha-typescript"
import { expect } from "chai"

import { tryIt } from './utils';

@suite class BasicTypesStudy {
    // @skip
    @test _boolean(): void {
        let isDone: boolean = false
        let isNotDone: Boolean = true

        // expect(1).not.to.eq(true, "expect 1 eq true")
        // expect(1 ? true : false).eq(true, "expect 1 eq true")
        // expect(-1).not.to.eq(true, "expect -1 eq true")
        // expect(-1 ? true : false).eq(true, "expect 1 eq true")
        // expect(2).not.to.eq(true, "expect 2 eq true")
        // expect("a").not.to.eq(true, "expect 'a' eq true")
        // expect("a" ? true : false).eq(true, "expect 'a' eq true")
        // expect({}).not.to.eq(true, "expect {} eq true")
        // expect({} ? true : false).eq(true, "expect {} eq true")

        // expect(0).not.to.eq(false, "expect 0 eq false")
        // expect(0 ? true : false).eq(false, "expect 0 eq false")
        // expect(null).not.to.eq(false, "expect null eq false")
        // expect(null ? true : false).eq(false, "expect null eq false")
        // expect(undefined).not.to.eq(false, "expect undefined eq false")
        // expect(undefined ? true : false).eq(false, "expect undefined eq false")

        // tryIt("1 == true")
        // tryIt("1 ? true : false")
        // tryIt("0 == false")
        // tryIt("0 ? true : false")
        // tryIt("'' == false")
        // tryIt("'' ? true : false")
        // // tryIt("{} == false")
        // // tryIt("{} ? true : false")
        // tryIt("null == false")
        // tryIt("null ? true : false")
        // tryIt("undefined == false")
        // tryIt("undefined ? true : false")
        // tryIt("NaN == false")
        // tryIt("NaN ? true : false")

        tryIt("true || 1")
        tryIt("1 || true")
        tryIt("true || 0")
        tryIt("0 || true")
        tryIt("true || null")
        tryIt("null || true")
        tryIt("true || undefined")
        tryIt("undefined || true")
        tryIt("NaN || true")
        tryIt("true || NaN")

        tryIt("null || NaN")
        tryIt("NaN || null")
        tryIt("null || undefined")
        tryIt("undefined || null")
        tryIt("NaN || undefined")
        tryIt("undefined || NaN")

        
        tryIt("false || 1")
        tryIt("1 || false")
        tryIt("false || 0")
        tryIt("0 || false")

        
        tryIt("true && 1")
        tryIt("1 && true")
        tryIt("true && 0")
        tryIt("0 && true")
        tryIt("true && null")
        tryIt("null && true")
        tryIt("true && undefined")
        tryIt("undefined && true")
        tryIt("NaN && true")
        tryIt("true && NaN")

        tryIt("null && NaN")
        tryIt("NaN && null")
        tryIt("null && undefined")
        tryIt("undefined && null")
        tryIt("NaN && undefined")
        tryIt("undefined && NaN")

        
        tryIt("false && 1")
        tryIt("1 && false")
        tryIt("false && 0")
        tryIt("0 && false")
  
        // let _true: boolean = true
        // console.info("_true.valueOf()", _true.valueOf())
    }

    @skip
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
        tryIt("pi.toExponential(5)")
        tryIt("(1/4).toFixed(4)")
        tryIt('pi.toLocaleString("zh")')

        tryIt("0b100 >> 2")
        tryIt("1 << 2")
        tryIt("1 >> 2")
        tryIt("-1 << 2")
        tryIt("-1 >> 2")
        tryIt("3 % 5")
        tryIt("-3 % 5")
        tryIt("3 % -5")
        tryIt("-3 % -5")

        tryIt("! 0b1010")
        tryIt("~ 0b1010")

        tryIt("0b101 | 0b10")
        tryIt("0b101 & 0b10")
        tryIt("0b101 ^ 0b10")
        tryIt("0b101 ^ 0b11")

        tryIt("0b1010 ^ 0b1111")
        tryIt("0b1001 ^ 0b1111")
        tryIt("0b110 ^ 0b1111")

        tryIt("~ 0")
        tryIt("~ -1")
        tryIt("0b110 ^ 0b110")
        tryIt("(~0b110) ^ 0b110")
    }

    @skip
    @test _string(): void {
        let color: string = "blue"
        console.info(color)
        color = "red"
        console.info(color)
        let age: number = 36
        console.info(`I am ${age} years old`)

        tryIt("'abcdef'[0]")
        tryIt("'abcdef'[1]")
        tryIt("'abcdef'[6]")
        tryIt("'abcdef'[-1]")

        tryIt("'abcdef'.slice(2, 2)")
        tryIt("'abcdef'.slice(2, 3)")
        tryIt("'abcdef'.slice(2, 4)")
        tryIt("'abcdef'.slice(4, 2)")
        tryIt("'abcdef'.slice(0, -2)")
        tryIt("'abcdef'.slice(-2, 0)")
        tryIt("'abcdef'.slice(-4, -2)")

        tryIt("'abcdef'.substr(2)")
        tryIt("'abcdef'.substr(2, 0)")
        tryIt("'abcdef'.substr(2, -1)")
        tryIt("'abcdef'.substr(2, -2)")
        tryIt("'abcdef'.substr(2, 10)")

        tryIt("'abcdef'.substring(2, 2)")
        tryIt("'abcdef'.substring(2, 3)")
        tryIt("'abcdef'.substring(2, 4)")
        tryIt("'abcdef'.substring(4, 2)")
        tryIt("'abcdef'.substring(0, -2)")
        tryIt("'abcdef'.substring(-2, 0)")
        tryIt("'abcdef'.substring(-4, -2)")

        tryIt("1 + '0'")
        tryIt("1 + '2'")
        tryIt("1 * '0'")
        tryIt("1 * '2'")
        tryIt("'2' * 2")
        tryIt("'3' * '2'")
        tryIt("'3' * 'a'")
        tryIt("'a' * '2'")

        tryIt("'abcdef'.split(/./)")
        tryIt("'abcdef'.split(/../)")
        tryIt("'abcdef'.split(/cd/)")
        tryIt("'abcdef'.split(/(b|d)/)")
    }
}