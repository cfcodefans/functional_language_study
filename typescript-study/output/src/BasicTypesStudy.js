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
        utils_1.tryIt("true || 1");
        utils_1.tryIt("1 || true");
        utils_1.tryIt("true || 0");
        utils_1.tryIt("0 || true");
        utils_1.tryIt("true || null");
        utils_1.tryIt("null || true");
        utils_1.tryIt("true || undefined");
        utils_1.tryIt("undefined || true");
        utils_1.tryIt("NaN || true");
        utils_1.tryIt("true || NaN");
        utils_1.tryIt("null || NaN");
        utils_1.tryIt("NaN || null");
        utils_1.tryIt("null || undefined");
        utils_1.tryIt("undefined || null");
        utils_1.tryIt("NaN || undefined");
        utils_1.tryIt("undefined || NaN");
        utils_1.tryIt("false || 1");
        utils_1.tryIt("1 || false");
        utils_1.tryIt("false || 0");
        utils_1.tryIt("0 || false");
        utils_1.tryIt("true && 1");
        utils_1.tryIt("1 && true");
        utils_1.tryIt("true && 0");
        utils_1.tryIt("0 && true");
        utils_1.tryIt("true && null");
        utils_1.tryIt("null && true");
        utils_1.tryIt("true && undefined");
        utils_1.tryIt("undefined && true");
        utils_1.tryIt("NaN && true");
        utils_1.tryIt("true && NaN");
        utils_1.tryIt("null && NaN");
        utils_1.tryIt("NaN && null");
        utils_1.tryIt("null && undefined");
        utils_1.tryIt("undefined && null");
        utils_1.tryIt("NaN && undefined");
        utils_1.tryIt("undefined && NaN");
        utils_1.tryIt("false && 1");
        utils_1.tryIt("1 && false");
        utils_1.tryIt("false && 0");
        utils_1.tryIt("0 && false");
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
        utils_1.tryIt("pi.toExponential(5)");
        utils_1.tryIt("(1/4).toFixed(4)");
        utils_1.tryIt('pi.toLocaleString("zh")');
        utils_1.tryIt("0b100 >> 2");
        utils_1.tryIt("1 << 2");
        utils_1.tryIt("1 >> 2");
        utils_1.tryIt("-1 << 2");
        utils_1.tryIt("-1 >> 2");
        utils_1.tryIt("3 % 5");
        utils_1.tryIt("-3 % 5");
        utils_1.tryIt("3 % -5");
        utils_1.tryIt("-3 % -5");
        utils_1.tryIt("! 0b1010");
        utils_1.tryIt("~ 0b1010");
        utils_1.tryIt("0b101 | 0b10");
        utils_1.tryIt("0b101 & 0b10");
        utils_1.tryIt("0b101 ^ 0b10");
        utils_1.tryIt("0b101 ^ 0b11");
        utils_1.tryIt("0b1010 ^ 0b1111");
        utils_1.tryIt("0b1001 ^ 0b1111");
        utils_1.tryIt("0b110 ^ 0b1111");
        utils_1.tryIt("~ 0");
        utils_1.tryIt("~ -1");
        utils_1.tryIt("0b110 ^ 0b110");
        utils_1.tryIt("(~0b110) ^ 0b110");
    }
    // @skip
    _string() {
        let color = "blue";
        console.info(color);
        color = "red";
        console.info(color);
        let age = 36;
        console.info(`I am ${age} years old`);
        utils_1.tryIt("JSON.stringify({})");
        utils_1.tryIt("JSON.stringify(null)");
        utils_1.tryIt("JSON.stringify(undefined)");
        utils_1.tryIt("'abcdef'[0]");
        utils_1.tryIt("'abcdef'[1]");
        utils_1.tryIt("'abcdef'[6]");
        utils_1.tryIt("'abcdef'[-1]");
        utils_1.tryIt("'abcdef'.anchor()");
        utils_1.tryIt("'abcdef'.anchor('test')");
        utils_1.tryIt("''.anchor('test')");
        utils_1.tryIt("'<a/>'.anchor('test')");
        utils_1.tryIt("'abcdef'.big()");
        utils_1.tryIt("'abcdef'.blink()");
        utils_1.tryIt("'abcdef'.bold()");
        "abcdef".chatAt(1);
        utils_1.tryIt("'abcdef'.chatAt(1)");
        utils_1.tryIt("'abcdef'.chatAt(10)");
        utils_1.tryIt("'abcdef'.chatAt(-1)");
        utils_1.tryIt("'abcdef'.chatAt(-10)");
        utils_1.tryIt("'abcdef'.slice(2, 2)");
        utils_1.tryIt("'abcdef'.slice(2, 3)");
        utils_1.tryIt("'abcdef'.slice(2, 4)");
        utils_1.tryIt("'abcdef'.slice(4, 2)");
        utils_1.tryIt("'abcdef'.slice(0, -2)");
        utils_1.tryIt("'abcdef'.slice(-2, 0)");
        utils_1.tryIt("'abcdef'.slice(-4, -2)");
        utils_1.tryIt("'abcdef'.substr(2)");
        utils_1.tryIt("'abcdef'.substr(2, 0)");
        utils_1.tryIt("'abcdef'.substr(2, -1)");
        utils_1.tryIt("'abcdef'.substr(2, -2)");
        utils_1.tryIt("'abcdef'.substr(2, 10)");
        utils_1.tryIt("'abcdef'.substring(2, 2)");
        utils_1.tryIt("'abcdef'.substring(2, 3)");
        utils_1.tryIt("'abcdef'.substring(2, 4)");
        utils_1.tryIt("'abcdef'.substring(4, 2)");
        utils_1.tryIt("'abcdef'.substring(0, -2)");
        utils_1.tryIt("'abcdef'.substring(-2, 0)");
        utils_1.tryIt("'abcdef'.substring(-4, -2)");
        utils_1.tryIt("1 + '0'");
        utils_1.tryIt("1 + '2'");
        utils_1.tryIt("1 * '0'");
        utils_1.tryIt("1 * '2'");
        utils_1.tryIt("'2' * 2");
        utils_1.tryIt("'4' / 2");
        utils_1.tryIt("4 / '2'");
        utils_1.tryIt("'4' / '2'");
        utils_1.tryIt("'4' / ' 2'");
        utils_1.tryIt("'3' * '2'");
        utils_1.tryIt("'3' * 'a'");
        utils_1.tryIt("'a' * '2'");
        utils_1.tryIt("'abcdef'.split(/./)");
        utils_1.tryIt("'abcdef'.split(/../)");
        utils_1.tryIt("'abcdef'.split(/cd/)");
        utils_1.tryIt("'abcdef'.split(/(b|d)/)");
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
