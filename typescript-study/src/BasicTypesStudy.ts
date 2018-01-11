import { suite, test, slow, timeout, skip } from "mocha-typescript"
import { expect } from "chai"

import { tryIt } from './utils';

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

    // @skip
    @test _string(): void {
        let color: string = "blue"
        console.info(color)
        color = "red"
        console.info(color)
        let age: number = 36
        // console.info(`I am ${age} years old`)

        tryIt("JSON.stringify({})")
        tryIt("JSON.stringify(null)")
        tryIt("JSON.stringify(undefined)")

        tryIt("'abcdef'[0]")
        tryIt("'abcdef'[1]")
        tryIt("'abcdef'[6]")
        tryIt("'abcdef'[-1]")

        tryIt("'abcdef'.anchor()")
        tryIt("'abcdef'.anchor('test')")
        tryIt("''.anchor('test')")
        tryIt("'<a/>'.anchor('test')")
        tryIt("'abcdef'.big()")
        tryIt("'abcdef'.blink()")
        tryIt("'abcdef'.bold()")


        tryIt("'abcdef'.charAt(1)")
        tryIt("'abcdef'.charAt(10)")
        tryIt("'abcdef'.charAt(-1)")
        tryIt("'abcdef'.charAt(-10)")

        tryIt("'abcdef'.charCodeAt(1)")
        tryIt("'abcdef'.charCodeAt(10)")
        tryIt("'abcdef'.charCodeAt(-1)")

        tryIt("'abcdef'.codePointAt(1)")
        tryIt("'abcdef'.codePointAt(10)")
        tryIt("'abcdef'.codePointAt(-1)")

        tryIt("'abcdef'.concat('12345')")
        tryIt("'abcdef'.concat(12345)")
        tryIt("'abcdef'.concat(new Date())")
        tryIt("'abcdef'.concat('')")
        tryIt("'abcdef'.concat(null)")
        tryIt("'abcdef'.concat(undefined)")

        tryIt("'abcdef'.endsWith('f')")
        tryIt("'abcdef'.endsWith('1')")
        tryIt("'abcdef'.endsWith('')")
        tryIt("'123456'.endsWith(6)")
        tryIt("'abcdef'.endsWith(null)")
        tryIt("'abcdefnull'.endsWith(null)")
        tryIt("'abcdefundefined'.endsWith(undefined)")

        tryIt("'abcdef'.fixed()")

        tryIt("'abcdef'.fontcolor()")
        tryIt("'abcdef'.fontcolor('red')")

        tryIt("'abcdef'.fontsize()")
        tryIt("'abcdef'.fontsize('10')")
        tryIt("'abcdef'.fontsize('0')")
        tryIt("'abcdef'.fontsize('')")
        tryIt("'abcdef'.fontsize(null)")
        tryIt("'abcdef'.fontsize(10)")
        tryIt("'abcdef'.fontsize(0)")
        
        tryIt("'abcdef'.includes()")
        tryIt("'abcdef'.includes(null)")
        tryIt("'abc_null_def'.includes(null)")
        tryIt("'abcdef'.includes(undefined)")
        tryIt("'abc_undefined_def'.includes(undefined)")
        tryIt("'abcdef'.includes('bcd')")
        tryIt("'abcdef'.includes('bcd', 0)")
        tryIt("'abcdef'.includes('bcd', 1)")
        tryIt("'abcdef'.includes('bcd', 2)")
        tryIt("'abcdef'.includes('bcd', -1)")
        tryIt("'abcdef'.includes('bcd', -2)")
        tryIt("'abcdef'.includes('ef', -3)")
        tryIt("'abcdef'.includes('ef', 10)")

        tryIt("'abcdef'.indexOf('d')")
        tryIt("'abcdef'.indexOf('1')")
        tryIt("'abcdef'.indexOf('')")
        tryIt("'abcdef'.indexOf('d', 2)")
        tryIt("'abcdef'.indexOf('d', 3)")
        tryIt("'abcdef'.indexOf('d', 4)")
        tryIt("'abcdef'.indexOf('d', 5)")
        tryIt("'abcdef'.indexOf('d', 10)")
        tryIt("'abcdef'.indexOf('d', -3)")
        tryIt("'abc_null_def'.indexOf(null)")
        tryIt("'abc_undefined_def'.indexOf(undefined)")

        tryIt("'abcdef'.italics()")

        tryIt("'abcdef'.lastIndexOf('d')")
        tryIt("'abcdef'.lastIndexOf('1')")
        tryIt("'abcdef'.lastIndexOf('')")
        tryIt("'abcdef'.lastIndexOf('d', 2)")
        tryIt("'abcdef'.lastIndexOf('d', 3)")
        tryIt("'abcdef'.lastIndexOf('d', 4)")
        tryIt("'abcdef'.lastIndexOf('d', 5)")
        tryIt("'abcdef'.lastIndexOf('d', 10)")
        tryIt("'abcdef'.lastIndexOf('d', -3)")
        tryIt("'abc_null_def'.lastIndexOf(null)")
        tryIt("'abc_undefined_def'.lastIndexOf(undefined)")

        tryIt("'abcdef'.length")

        tryIt("'abcdef'.link()")

        tryIt("'abcdef'.localeCompare()")
        tryIt("'abcdef'.localeCompare(null)")
        tryIt("'abcdef'.localeCompare('null')")
        tryIt("'abcdef'.localeCompare(undefined)")
        tryIt("'abcdef'.localeCompare('undefined')")
        tryIt("'abcdef'.localeCompare('a')")
        tryIt("'abcdef'.localeCompare('abcdef')")
        tryIt("'abcdef'.localeCompare('abcdefg')")
        tryIt("'abcdef'.localeCompare('aacdef')")
        tryIt("'abcdef'.localeCompare('accdef')")

        tryIt("'abcdef'.match()")
        tryIt("'abcdef'.match(/./)")
        tryIt("'abcdef'.match(/cde/)")
        tryIt("'abcdef'.match(/(b|d)/)")
        tryIt("'abcdef'.match(null)")
        tryIt("'abc_null_def'.match(null)")
        tryIt("'abcdef'.match(undefined)")
        tryIt("'abc_undefined_def'.match(undefined)")
        
        //normalize(form: "NFC" | "NFD" | "NFKC" | "NFKD"): string;
        tryIt("'abcdef'.normalize()")
        // tryIt("'abcdef'.normalize(null)") //抛异常
        // tryIt("'abcdef'.normalize(undefined)") //抛异常
        //https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/normalize
        tryIt("'abcdef'.normalize('NFC')")
        tryIt("'abcdef'.normalize('NFD')")
        tryIt("'abcdef'.normalize('NFKC')")
        tryIt("'abcdef'.normalize('NFKD')")
        tryIt("'123456'.normalize('NFC')")
        tryIt("'123456'.normalize('NFD')")
        tryIt("'123456'.normalize('NFKC')")
        tryIt("'123456'.normalize('NFKD')")
        tryIt("'\u1E9B\u0323'.normalize('NFC')")
        tryIt("'\u1E9B\u0323'.normalize('NFD')")
        tryIt("'\u1E9B\u0323'.normalize('NFKC')")
        tryIt("'\u1E9B\u0323'.normalize('NFKD')")

        tryIt("'abcdef'.padEnd(20)")
        tryIt("'abcdef'.padEnd(10)")
        tryIt("'abcdef'.padEnd(0)")
        tryIt("'abcdef'.padEnd(1)")
        tryIt("'abcdef'.padEnd(20, '-')")
        tryIt("'abcdef'.padEnd(15, '-')")
        tryIt("'abcdef'.padEnd(10, '-')")
        tryIt("'abcdef'.padEnd(10.5, '-')")
        tryIt("'abcdef'.padEnd(0, '-')")
        tryIt("'abcdef'.padEnd(-1, '-')")
        tryIt("'abcdef'.padEnd(10, '123456')")
        tryIt("'abcdef'.padEnd(10, null)")
        tryIt("'abcdef'.padEnd(10, undefined)")


        
        tryIt("'abcdef'.padStart(20)")
        tryIt("'abcdef'.padStart(10)")
        tryIt("'abcdef'.padStart(0)")
        tryIt("'abcdef'.padStart(1)")
        tryIt("'abcdef'.padStart(20, '-')")
        tryIt("'abcdef'.padStart(15, '-')")
        tryIt("'abcdef'.padStart(10, '-')")
        tryIt("'abcdef'.padStart(10.5, '-')")
        tryIt("'abcdef'.padStart(0, '-')")
        tryIt("'abcdef'.padStart(-1, '-')")
        tryIt("'abcdef'.padStart(10, '123456')")
        tryIt("'abcdef'.padStart(10, null)")
        tryIt("'abcdef'.padStart(10, undefined)")
        
        

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
        tryIt("'4' / 2")
        tryIt("4 / '2'")
        tryIt("'4' / '2'")
        tryIt("'4' / ' 2'")
        tryIt("'3' * '2'")
        tryIt("'3' * 'a'")
        tryIt("'a' * '2'")

        tryIt("'abcdef'.split(/./)")
        tryIt("'abcdef'.split(/../)")
        tryIt("'abcdef'.split(/cd/)")
        tryIt("'abcdef'.split(/(b|d)/)")


    }
}
