"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
const mocha_typescript_1 = require("mocha-typescript");
const chai_1 = require("chai");
const utils_1 = require("./utils");
let BasicTypesStudy = class BasicTypesStudy {
    _boolean() {
        let isDone = false;
        let isNotDone = true;
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
        utils_1.tell("true || 1");
        utils_1.tell("1 || true");
        utils_1.tell("true || 0");
        utils_1.tell("0 || true");
        utils_1.tell("true || null");
        utils_1.tell("null || true");
        utils_1.tell("true || undefined");
        utils_1.tell("undefined || true");
        utils_1.tell("NaN || true");
        utils_1.tell("true || NaN");
        utils_1.tell("null || NaN");
        utils_1.tell("NaN || null");
        utils_1.tell("null || undefined");
        utils_1.tell("undefined || null");
        utils_1.tell("NaN || undefined");
        utils_1.tell("undefined || NaN");
        utils_1.tell("false || 1");
        utils_1.tell("1 || false");
        utils_1.tell("false || 0");
        utils_1.tell("0 || false");
        utils_1.tell("true && 1");
        utils_1.tell("1 && true");
        utils_1.tell("true && 0");
        utils_1.tell("0 && true");
        utils_1.tell("true && null");
        utils_1.tell("null && true");
        utils_1.tell("true && undefined");
        utils_1.tell("undefined && true");
        utils_1.tell("NaN && true");
        utils_1.tell("true && NaN");
        utils_1.tell("null && NaN");
        utils_1.tell("NaN && null");
        utils_1.tell("null && undefined");
        utils_1.tell("undefined && null");
        utils_1.tell("NaN && undefined");
        utils_1.tell("undefined && NaN");
        utils_1.tell("false && 1");
        utils_1.tell("1 && false");
        utils_1.tell("false && 0");
        utils_1.tell("0 && false");
        // let _true: boolean = true
        // console.info("_true.valueOf()", _true.valueOf())
    }
    _number() {
        let decimal = 6;
        console.info(decimal);
        let hex = 0xf00d;
        console.info(hex);
        let binary = 0b1010;
        console.info(binary);
        let octal = 0o744;
        console.info(octal);
        let _number = 1;
        let _Number = 1;
        chai_1.expect(_number == _Number);
        chai_1.expect(_number === _Number);
        let pi = Math.PI;
        utils_1.tell("pi.toExponential(5)");
        utils_1.tell("(1/4).toFixed(4)");
        utils_1.tell('pi.toLocaleString("zh")');
        utils_1.tell("0b100 >> 2");
        utils_1.tell("1 << 2");
        utils_1.tell("1 >> 2");
        utils_1.tell("-1 << 2");
        utils_1.tell("-1 >> 2");
        utils_1.tell("3 % 5");
        utils_1.tell("-3 % 5");
        utils_1.tell("3 % -5");
        utils_1.tell("-3 % -5");
        utils_1.tell("! 0b1010");
        utils_1.tell("~ 0b1010");
        utils_1.tell("0b101 | 0b10");
        utils_1.tell("0b101 & 0b10");
        utils_1.tell("0b101 ^ 0b10");
        utils_1.tell("0b101 ^ 0b11");
        utils_1.tell("0b1010 ^ 0b1111");
        utils_1.tell("0b1001 ^ 0b1111");
        utils_1.tell("0b110 ^ 0b1111");
        utils_1.tell("~ 0");
        utils_1.tell("~ -1");
        utils_1.tell("0b110 ^ 0b110");
        utils_1.tell("(~0b110) ^ 0b110");
    }
    // @skip
    _string() {
        let color = "blue";
        console.info(color);
        color = "red";
        console.info(color);
        let age = 36;
        // console.info(`I am ${age} years old`)
        utils_1.tell("JSON.stringify({})");
        utils_1.tell("JSON.stringify(null)");
        utils_1.tell("JSON.stringify(undefined)");
        utils_1.tell("'abcdef'[0]");
        utils_1.tell("'abcdef'[1]");
        utils_1.tell("'abcdef'[6]");
        utils_1.tell("'abcdef'[-1]");
        utils_1.tell("'abcdef'.anchor()");
        utils_1.tell("'abcdef'.anchor('test')");
        utils_1.tell("''.anchor('test')");
        utils_1.tell("'<a/>'.anchor('test')");
        utils_1.tell("'abcdef'.big()");
        utils_1.tell("'abcdef'.blink()");
        utils_1.tell("'abcdef'.bold()");
        utils_1.tell("'abcdef'.charAt(1)");
        utils_1.tell("'abcdef'.charAt(10)");
        utils_1.tell("'abcdef'.charAt(-1)");
        utils_1.tell("'abcdef'.charAt(-10)");
        utils_1.tell("'abcdef'.charCodeAt(1)");
        utils_1.tell("'abcdef'.charCodeAt(10)");
        utils_1.tell("'abcdef'.charCodeAt(-1)");
        utils_1.tell("'abcdef'.codePointAt(1)");
        utils_1.tell("'abcdef'.codePointAt(10)");
        utils_1.tell("'abcdef'.codePointAt(-1)");
        utils_1.tell("'abcdef'.concat('12345')");
        utils_1.tell("'abcdef'.concat(12345)");
        utils_1.tell("'abcdef'.concat(new Date())");
        utils_1.tell("'abcdef'.concat('')");
        utils_1.tell("'abcdef'.concat(null)");
        utils_1.tell("'abcdef'.concat(undefined)");
        utils_1.tell("'abcdef'.endsWith('f')");
        utils_1.tell("'abcdef'.endsWith('1')");
        utils_1.tell("'abcdef'.endsWith('')");
        utils_1.tell("'123456'.endsWith(6)");
        utils_1.tell("'abcdef'.endsWith(null)");
        utils_1.tell("'abcdefnull'.endsWith(null)");
        utils_1.tell("'abcdefundefined'.endsWith(undefined)");
        utils_1.tell("'abcdef'.fixed()");
        utils_1.tell("'abcdef'.fontcolor()");
        utils_1.tell("'abcdef'.fontcolor('red')");
        utils_1.tell("'abcdef'.fontsize()");
        utils_1.tell("'abcdef'.fontsize('10')");
        utils_1.tell("'abcdef'.fontsize('0')");
        utils_1.tell("'abcdef'.fontsize('')");
        utils_1.tell("'abcdef'.fontsize(null)");
        utils_1.tell("'abcdef'.fontsize(10)");
        utils_1.tell("'abcdef'.fontsize(0)");
        utils_1.tell("'abcdef'.includes()");
        utils_1.tell("'abcdef'.includes(null)");
        utils_1.tell("'abc_null_def'.includes(null)");
        utils_1.tell("'abcdef'.includes(undefined)");
        utils_1.tell("'abc_undefined_def'.includes(undefined)");
        utils_1.tell("'abcdef'.includes('bcd')");
        utils_1.tell("'abcdef'.includes('bcd', 0)");
        utils_1.tell("'abcdef'.includes('bcd', 1)");
        utils_1.tell("'abcdef'.includes('bcd', 2)");
        utils_1.tell("'abcdef'.includes('bcd', -1)");
        utils_1.tell("'abcdef'.includes('bcd', -2)");
        utils_1.tell("'abcdef'.includes('ef', -3)");
        utils_1.tell("'abcdef'.includes('ef', 10)");
        utils_1.tell("'abcdef'.indexOf('d')");
        utils_1.tell("'abcdef'.indexOf('1')");
        utils_1.tell("'abcdef'.indexOf('')");
        utils_1.tell("'abcdef'.indexOf('d', 2)");
        utils_1.tell("'abcdef'.indexOf('d', 3)");
        utils_1.tell("'abcdef'.indexOf('d', 4)");
        utils_1.tell("'abcdef'.indexOf('d', 5)");
        utils_1.tell("'abcdef'.indexOf('d', 10)");
        utils_1.tell("'abcdef'.indexOf('d', -3)");
        utils_1.tell("'abc_null_def'.indexOf(null)");
        utils_1.tell("'abc_undefined_def'.indexOf(undefined)");
        utils_1.tell("'abcdef'.italics()");
        utils_1.tell("'abcdef'.lastIndexOf('d')");
        utils_1.tell("'abcdef'.lastIndexOf('1')");
        utils_1.tell("'abcdef'.lastIndexOf('')");
        utils_1.tell("'abcdef'.lastIndexOf('d', 2)");
        utils_1.tell("'abcdef'.lastIndexOf('d', 3)");
        utils_1.tell("'abcdef'.lastIndexOf('d', 4)");
        utils_1.tell("'abcdef'.lastIndexOf('d', 5)");
        utils_1.tell("'abcdef'.lastIndexOf('d', 10)");
        utils_1.tell("'abcdef'.lastIndexOf('d', -3)");
        utils_1.tell("'abc_null_def'.lastIndexOf(null)");
        utils_1.tell("'abc_undefined_def'.lastIndexOf(undefined)");
        utils_1.tell("'abcdef'.length");
        utils_1.tell("'abcdef'.link()");
        utils_1.tell("'abcdef'.localeCompare()");
        utils_1.tell("'abcdef'.localeCompare(null)");
        utils_1.tell("'abcdef'.localeCompare('null')");
        utils_1.tell("'abcdef'.localeCompare(undefined)");
        utils_1.tell("'abcdef'.localeCompare('undefined')");
        utils_1.tell("'abcdef'.localeCompare('a')");
        utils_1.tell("'abcdef'.localeCompare('abcdef')");
        utils_1.tell("'abcdef'.localeCompare('abcdefg')");
        utils_1.tell("'abcdef'.localeCompare('aacdef')");
        utils_1.tell("'abcdef'.localeCompare('accdef')");
        utils_1.tell("'abcdef'.match()");
        utils_1.tell("'abcdef'.match(/./)");
        utils_1.tell("'abcdef'.match(/cde/)");
        utils_1.tell("'abcdef'.match(/(b|d)/)");
        utils_1.tell("'abcdef'.match(null)");
        utils_1.tell("'abc_null_def'.match(null)");
        utils_1.tell("'abcdef'.match(undefined)");
        utils_1.tell("'abc_undefined_def'.match(undefined)");
        //normalize(form: "NFC" | "NFD" | "NFKC" | "NFKD"): string;
        utils_1.tell("'abcdef'.normalize()");
        // tryIt("'abcdef'.normalize(null)") //抛异常
        // tryIt("'abcdef'.normalize(undefined)") //抛异常
        //https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/normalize
        utils_1.tell("'abcdef'.normalize('NFC')");
        utils_1.tell("'abcdef'.normalize('NFD')");
        utils_1.tell("'abcdef'.normalize('NFKC')");
        utils_1.tell("'abcdef'.normalize('NFKD')");
        utils_1.tell("'123456'.normalize('NFC')");
        utils_1.tell("'123456'.normalize('NFD')");
        utils_1.tell("'123456'.normalize('NFKC')");
        utils_1.tell("'123456'.normalize('NFKD')");
        utils_1.tell("'\u1E9B\u0323'.normalize('NFC')");
        utils_1.tell("'\u1E9B\u0323'.normalize('NFD')");
        utils_1.tell("'\u1E9B\u0323'.normalize('NFKC')");
        utils_1.tell("'\u1E9B\u0323'.normalize('NFKD')");
        utils_1.tell("'abcdef'.padEnd(20)");
        utils_1.tell("'abcdef'.padEnd(10)");
        utils_1.tell("'abcdef'.padEnd(0)");
        utils_1.tell("'abcdef'.padEnd(1)");
        utils_1.tell("'abcdef'.padEnd(20, '-')");
        utils_1.tell("'abcdef'.padEnd(15, '-')");
        utils_1.tell("'abcdef'.padEnd(10, '-')");
        utils_1.tell("'abcdef'.padEnd(10.5, '-')");
        utils_1.tell("'abcdef'.padEnd(0, '-')");
        utils_1.tell("'abcdef'.padEnd(-1, '-')");
        utils_1.tell("'abcdef'.padEnd(10, '123456')");
        utils_1.tell("'abcdef'.padEnd(10, null)");
        utils_1.tell("'abcdef'.padEnd(10, undefined)");
        utils_1.tell("'abcdef'.padStart(20)");
        utils_1.tell("'abcdef'.padStart(10)");
        utils_1.tell("'abcdef'.padStart(0)");
        utils_1.tell("'abcdef'.padStart(1)");
        utils_1.tell("'abcdef'.padStart(20, '-')");
        utils_1.tell("'abcdef'.padStart(15, '-')");
        utils_1.tell("'abcdef'.padStart(10, '-')");
        utils_1.tell("'abcdef'.padStart(10.5, '-')");
        utils_1.tell("'abcdef'.padStart(0, '-')");
        utils_1.tell("'abcdef'.padStart(-1, '-')");
        utils_1.tell("'abcdef'.padStart(10, '123456')");
        utils_1.tell("'abcdef'.padStart(10, null)");
        utils_1.tell("'abcdef'.padStart(10, undefined)");
        utils_1.tell("'abcdef'.slice(2, 2)");
        utils_1.tell("'abcdef'.slice(2, 3)");
        utils_1.tell("'abcdef'.slice(2, 4)");
        utils_1.tell("'abcdef'.slice(4, 2)");
        utils_1.tell("'abcdef'.slice(0, -2)");
        utils_1.tell("'abcdef'.slice(-2, 0)");
        utils_1.tell("'abcdef'.slice(-4, -2)");
        utils_1.tell("'abcdef'.substr(2)");
        utils_1.tell("'abcdef'.substr(2, 0)");
        utils_1.tell("'abcdef'.substr(2, -1)");
        utils_1.tell("'abcdef'.substr(2, -2)");
        utils_1.tell("'abcdef'.substr(2, 10)");
        utils_1.tell("'abcdef'.substring(2, 2)");
        utils_1.tell("'abcdef'.substring(2, 3)");
        utils_1.tell("'abcdef'.substring(2, 4)");
        utils_1.tell("'abcdef'.substring(4, 2)");
        utils_1.tell("'abcdef'.substring(0, -2)");
        utils_1.tell("'abcdef'.substring(-2, 0)");
        utils_1.tell("'abcdef'.substring(-4, -2)");
        utils_1.tell("1 + '0'");
        utils_1.tell("1 + '2'");
        utils_1.tell("1 * '0'");
        utils_1.tell("1 * '2'");
        utils_1.tell("'2' * 2");
        utils_1.tell("'4' / 2");
        utils_1.tell("4 / '2'");
        utils_1.tell("'4' / '2'");
        utils_1.tell("'4' / ' 2'");
        utils_1.tell("'3' * '2'");
        utils_1.tell("'3' * 'a'");
        utils_1.tell("'a' * '2'");
        utils_1.tell("'abcdef'.split(/./)");
        utils_1.tell("'abcdef'.split(/../)");
        utils_1.tell("'abcdef'.split(/cd/)");
        utils_1.tell("'abcdef'.split(/(b|d)/)");
    }
};
__decorate([
    mocha_typescript_1.skip,
    mocha_typescript_1.test
], BasicTypesStudy.prototype, "_boolean", null);
__decorate([
    mocha_typescript_1.skip,
    mocha_typescript_1.test
], BasicTypesStudy.prototype, "_number", null);
__decorate([
    mocha_typescript_1.test
], BasicTypesStudy.prototype, "_string", null);
BasicTypesStudy = __decorate([
    mocha_typescript_1.suite
], BasicTypesStudy);
