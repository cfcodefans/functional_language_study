import { suite, test, slow, timeout, skip } from "mocha-typescript"
import { expect } from "chai"

import {tell} from './utils'

@suite class BasicTypesStudy {
    @skip
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

        tell("true || 1")
        tell("1 || true")
        tell("true || 0")
        tell("0 || true")
        tell("true || null")
        tell("null || true")
        tell("true || undefined")
        tell("undefined || true")
        tell("NaN || true")
        tell("true || NaN")

        tell("null || NaN")
        tell("NaN || null")
        tell("null || undefined")
        tell("undefined || null")
        tell("NaN || undefined")
        tell("undefined || NaN")


        tell("false || 1")
        tell("1 || false")
        tell("false || 0")
        tell("0 || false")


        tell("true && 1")
        tell("1 && true")
        tell("true && 0")
        tell("0 && true")
        tell("true && null")
        tell("null && true")
        tell("true && undefined")
        tell("undefined && true")
        tell("NaN && true")
        tell("true && NaN")

        tell("null && NaN")
        tell("NaN && null")
        tell("null && undefined")
        tell("undefined && null")
        tell("NaN && undefined")
        tell("undefined && NaN")


        tell("false && 1")
        tell("1 && false")
        tell("false && 0")
        tell("0 && false")

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
        tell("pi.toExponential(5)")
        tell("(1/4).toFixed(4)")
        tell('pi.toLocaleString("zh")')

        tell("0b100 >> 2")
        tell("1 << 2")
        tell("1 >> 2")
        tell("-1 << 2")
        tell("-1 >> 2")
        tell("3 % 5")
        tell("-3 % 5")
        tell("3 % -5")
        tell("-3 % -5")

        tell("! 0b1010")
        tell("~ 0b1010")

        tell("0b101 | 0b10")
        tell("0b101 & 0b10")
        tell("0b101 ^ 0b10")
        tell("0b101 ^ 0b11")

        tell("0b1010 ^ 0b1111")
        tell("0b1001 ^ 0b1111")
        tell("0b110 ^ 0b1111")

        tell("~ 0")
        tell("~ -1")
        tell("0b110 ^ 0b110")
        tell("(~0b110) ^ 0b110")
    }

    // @skip
    @test _string(): void {
        let color: string = "blue"
        console.info(color)
        color = "red"
        console.info(color)
        let age: number = 36
        // console.info(`I am ${age} years old`)

        tell("JSON.stringify({})")
        tell("JSON.stringify(null)")
        tell("JSON.stringify(undefined)")

        tell("'abcdef'[0]")
        tell("'abcdef'[1]")
        tell("'abcdef'[6]")
        tell("'abcdef'[-1]")

        tell("'abcdef'.anchor()")
        tell("'abcdef'.anchor('test')")
        tell("''.anchor('test')")
        tell("'<a/>'.anchor('test')")
        tell("'abcdef'.big()")
        tell("'abcdef'.blink()")
        tell("'abcdef'.bold()")


        tell("'abcdef'.charAt(1)")
        tell("'abcdef'.charAt(10)")
        tell("'abcdef'.charAt(-1)")
        tell("'abcdef'.charAt(-10)")

        tell("'abcdef'.charCodeAt(1)")
        tell("'abcdef'.charCodeAt(10)")
        tell("'abcdef'.charCodeAt(-1)")

        tell("'abcdef'.codePointAt(1)")
        tell("'abcdef'.codePointAt(10)")
        tell("'abcdef'.codePointAt(-1)")

        tell("'abcdef'.concat('12345')")
        tell("'abcdef'.concat(12345)")
        tell("'abcdef'.concat(new Date())")
        tell("'abcdef'.concat('')")
        tell("'abcdef'.concat(null)")
        tell("'abcdef'.concat(undefined)")

        tell("'abcdef'.endsWith('f')")
        tell("'abcdef'.endsWith('1')")
        tell("'abcdef'.endsWith('')")
        tell("'123456'.endsWith(6)")
        tell("'abcdef'.endsWith(null)")
        tell("'abcdefnull'.endsWith(null)")
        tell("'abcdefundefined'.endsWith(undefined)")

        tell("'abcdef'.fixed()")

        tell("'abcdef'.fontcolor()")
        tell("'abcdef'.fontcolor('red')")

        tell("'abcdef'.fontsize()")
        tell("'abcdef'.fontsize('10')")
        tell("'abcdef'.fontsize('0')")
        tell("'abcdef'.fontsize('')")
        tell("'abcdef'.fontsize(null)")
        tell("'abcdef'.fontsize(10)")
        tell("'abcdef'.fontsize(0)")
        
        tell("'abcdef'.includes()")
        tell("'abcdef'.includes(null)")
        tell("'abc_null_def'.includes(null)")
        tell("'abcdef'.includes(undefined)")
        tell("'abc_undefined_def'.includes(undefined)")
        tell("'abcdef'.includes('bcd')")
        tell("'abcdef'.includes('bcd', 0)")
        tell("'abcdef'.includes('bcd', 1)")
        tell("'abcdef'.includes('bcd', 2)")
        tell("'abcdef'.includes('bcd', -1)")
        tell("'abcdef'.includes('bcd', -2)")
        tell("'abcdef'.includes('ef', -3)")
        tell("'abcdef'.includes('ef', 10)")

        tell("'abcdef'.indexOf('d')")
        tell("'abcdef'.indexOf('1')")
        tell("'abcdef'.indexOf('')")
        tell("'abcdef'.indexOf('d', 2)")
        tell("'abcdef'.indexOf('d', 3)")
        tell("'abcdef'.indexOf('d', 4)")
        tell("'abcdef'.indexOf('d', 5)")
        tell("'abcdef'.indexOf('d', 10)")
        tell("'abcdef'.indexOf('d', -3)")
        tell("'abc_null_def'.indexOf(null)")
        tell("'abc_undefined_def'.indexOf(undefined)")

        tell("'abcdef'.italics()")

        tell("'abcdef'.lastIndexOf('d')")
        tell("'abcdef'.lastIndexOf('1')")
        tell("'abcdef'.lastIndexOf('')")
        tell("'abcdef'.lastIndexOf('d', 2)")
        tell("'abcdef'.lastIndexOf('d', 3)")
        tell("'abcdef'.lastIndexOf('d', 4)")
        tell("'abcdef'.lastIndexOf('d', 5)")
        tell("'abcdef'.lastIndexOf('d', 10)")
        tell("'abcdef'.lastIndexOf('d', -3)")
        tell("'abc_null_def'.lastIndexOf(null)")
        tell("'abc_undefined_def'.lastIndexOf(undefined)")

        tell("'abcdef'.length")

        tell("'abcdef'.link()")

        tell("'abcdef'.localeCompare()")
        tell("'abcdef'.localeCompare(null)")
        tell("'abcdef'.localeCompare('null')")
        tell("'abcdef'.localeCompare(undefined)")
        tell("'abcdef'.localeCompare('undefined')")
        tell("'abcdef'.localeCompare('a')")
        tell("'abcdef'.localeCompare('abcdef')")
        tell("'abcdef'.localeCompare('abcdefg')")
        tell("'abcdef'.localeCompare('aacdef')")
        tell("'abcdef'.localeCompare('accdef')")

        tell("'abcdef'.match()")
        tell("'abcdef'.match(/./)")
        tell("'abcdef'.match(/cde/)")
        tell("'abcdef'.match(/(b|d)/)")
        tell("'abcdef'.match(null)")
        tell("'abc_null_def'.match(null)")
        tell("'abcdef'.match(undefined)")
        tell("'abc_undefined_def'.match(undefined)")
        
        //normalize(form: "NFC" | "NFD" | "NFKC" | "NFKD"): string;
        tell("'abcdef'.normalize()")
        // tryIt("'abcdef'.normalize(null)") //抛异常
        // tryIt("'abcdef'.normalize(undefined)") //抛异常
        //https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/normalize
        tell("'abcdef'.normalize('NFC')")
        tell("'abcdef'.normalize('NFD')")
        tell("'abcdef'.normalize('NFKC')")
        tell("'abcdef'.normalize('NFKD')")
        tell("'123456'.normalize('NFC')")
        tell("'123456'.normalize('NFD')")
        tell("'123456'.normalize('NFKC')")
        tell("'123456'.normalize('NFKD')")
        tell("'\u1E9B\u0323'.normalize('NFC')")
        tell("'\u1E9B\u0323'.normalize('NFD')")
        tell("'\u1E9B\u0323'.normalize('NFKC')")
        tell("'\u1E9B\u0323'.normalize('NFKD')")

        tell("'abcdef'.padEnd(20)")
        tell("'abcdef'.padEnd(10)")
        tell("'abcdef'.padEnd(0)")
        tell("'abcdef'.padEnd(1)")
        tell("'abcdef'.padEnd(20, '-')")
        tell("'abcdef'.padEnd(15, '-')")
        tell("'abcdef'.padEnd(10, '-')")
        tell("'abcdef'.padEnd(10.5, '-')")
        tell("'abcdef'.padEnd(0, '-')")
        tell("'abcdef'.padEnd(-1, '-')")
        tell("'abcdef'.padEnd(10, '123456')")
        tell("'abcdef'.padEnd(10, null)")
        tell("'abcdef'.padEnd(10, undefined)")


        
        tell("'abcdef'.padStart(20)")
        tell("'abcdef'.padStart(10)")
        tell("'abcdef'.padStart(0)")
        tell("'abcdef'.padStart(1)")
        tell("'abcdef'.padStart(20, '-')")
        tell("'abcdef'.padStart(15, '-')")
        tell("'abcdef'.padStart(10, '-')")
        tell("'abcdef'.padStart(10.5, '-')")
        tell("'abcdef'.padStart(0, '-')")
        tell("'abcdef'.padStart(-1, '-')")
        tell("'abcdef'.padStart(10, '123456')")
        tell("'abcdef'.padStart(10, null)")
        tell("'abcdef'.padStart(10, undefined)")
        
        tell("'abcdef'.slice(2, 2)")
        tell("'abcdef'.slice(2, 3)")
        tell("'abcdef'.slice(2, 4)")
        tell("'abcdef'.slice(4, 2)")
        tell("'abcdef'.slice(0, -2)")
        tell("'abcdef'.slice(-2, 0)")
        tell("'abcdef'.slice(-4, -2)")

        tell("'abcdef'.substr(2)")
        tell("'abcdef'.substr(2, 0)")
        tell("'abcdef'.substr(2, -1)")
        tell("'abcdef'.substr(2, -2)")
        tell("'abcdef'.substr(2, 10)")

        tell("'abcdef'.substring(2, 2)")
        tell("'abcdef'.substring(2, 3)")
        tell("'abcdef'.substring(2, 4)")
        tell("'abcdef'.substring(4, 2)")
        tell("'abcdef'.substring(0, -2)")
        tell("'abcdef'.substring(-2, 0)")
        tell("'abcdef'.substring(-4, -2)")

        tell("1 + '0'")
        tell("1 + '2'")
        tell("1 * '0'")
        tell("1 * '2'")
        tell("'2' * 2")
        tell("'4' / 2")
        tell("4 / '2'")
        tell("'4' / '2'")
        tell("'4' / ' 2'")
        tell("'3' * '2'")
        tell("'3' * 'a'")
        tell("'a' * '2'")

        tell("'abcdef'.split(/./)")
        tell("'abcdef'.split(/../)")
        tell("'abcdef'.split(/cd/)")
        tell("'abcdef'.split(/(b|d)/)")
    }
}
