"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var tsUnit = require("../../node_modules/tsunit.external/tsUnit");
var BasicTypeTests = (function (_super) {
    __extends(BasicTypeTests, _super);
    function BasicTypeTests() {
        _super.call(this);
        console.info("constructor:\t" + this);
    }
    BasicTypeTests.prototype.setUp = function () {
        console.info("\nsetUp");
    };
    BasicTypeTests.prototype.tearDown = function () {
        console.info("tearDown\n");
    };
    BasicTypeTests.prototype.isTrue = function (val, msg) {
        if (msg === void 0) { msg = ''; }
        console.info(val, msg);
        _super.prototype.isTrue.call(this, val, msg);
    };
    BasicTypeTests.prototype.testObj = function () {
        var obj = new Object();
        console.info(obj);
        this.isTrue(obj != null);
        for (var p in obj) {
            console.info(p, ":\t", +obj[p]);
        }
        obj = { x: 1, y: 2 };
        for (var p in obj) {
            console.info(p, ":\t", +obj[p]);
        }
        console.info(obj);
    };
    BasicTypeTests.prototype.testArray = function () {
        var array = new Array(0, 1, 2, 3, 4, 5);
        console.info(array);
        array.forEach(function (el, idx, _array) { return console.info(el, _array.length - idx); });
        this.areIdentical(array.pop(), 5);
        this.isFalse(array.every(function (el) { return el > 0; }));
        this.isTrue(array.slice(1).every(function (el) { return el > 0; }));
        var list = [1, 2, 3, 4];
        var _list = [1, 2, 3, 4];
    };
    BasicTypeTests.prototype.testEquality = function () {
        this.isTrue(1 == 1, "1 == 1");
        this.isTrue(1 === 1, "1 === 1");
        var a = 1;
        this.isTrue(a == a, "a == a");
        this.isTrue(a === a, "a === a");
        var b = 1;
        this.isTrue(b == a, "b == a");
        this.isTrue(b === a, "b === a");
        this.isTrue([b] != [a], "[b] != [a]");
        this.isTrue([b] !== [a], "[b] !== [a]");
        this.isTrue({ x: b } != { x: a }, "{ x: b } != { x: a }");
        this.isTrue({ x: b } !== { x: a }, "{ x: b } !== { x: a }");
        a = 2;
        this.isTrue({ x: b } != { x: a }, "{ x: b } != { x: a }");
        this.isTrue({ x: b } !== { x: a }, "{ x: b } !== { x: a }");
        this.isTrue(b != a, b + " != " + a);
        this.isTrue(b !== a, b + " !== " + a);
    };
    BasicTypeTests.prototype.testBoolean = function () {
        var bool = true;
        this.isTrue(bool);
        this.isFalse(!bool);
        this.isTrue(bool || true);
        this.isFalse(bool && false);
        this.isFalse(bool !== bool);
    };
    BasicTypeTests.prototype.testNumber = function () {
        var decimal = 6;
        var hex = 0xf00d;
        var binary = 10;
        var octal = 484;
        console.info(decimal.toExponential());
    };
    BasicTypeTests.prototype.testString = function () {
        var color = "blue";
        color = 'red';
        var fullName = "Bob Bobbington";
        var age = 37;
        var sentence = "Hello, my name is " + fullName + ".\n\t\tI'll be " + (age + 1) + " years old next month.";
        console.info(sentence);
    };
    BasicTypeTests.prototype.testTuple = function () {
        var x = ["hello", 10];
        console.info(typeof (x));
        console.info(x.length);
        x.forEach(function (el, idx, array) {
            console.info(el + ": " + typeof (el) + " = [" + array + "][" + idx + "]");
        });
    };
    BasicTypeTests.prototype.testEnum = function () {
        var Season;
        (function (Season) {
            Season[Season["Spring"] = 0] = "Spring";
            Season[Season["Summer"] = 1] = "Summer";
            Season[Season["Autumn"] = 2] = "Autumn";
            Season[Season["Winter"] = 3] = "Winter";
        })(Season || (Season = {}));
        ;
        var first = Season.Spring;
        console.info(first, typeof (first));
        var _first = Season[0];
        console.info(_first, typeof (_first));
        console.info(Season);
        console.info(typeof (Season));
        var _season = Season;
        console.info(_season[first]);
        var R;
        (function (R) {
            R[R["a"] = 2] = "a";
            R[R["b"] = 3] = "b";
            R[R["c"] = 4] = "c";
        })(R || (R = {}));
        ;
        console.info(R);
    };
    BasicTypeTests.prototype.testAny = function () {
        {
            var notSure = 4;
            notSure = "maybe a string instead";
            notSure = false;
        }
        {
            var notSure = 4;
            notSure.ifItExists();
            notSure.toFixed();
        }
    };
    BasicTypeTests.prototype.testVoid = function () {
        function warnUser() {
            console.warn("This is my warning message");
        }
        warnUser();
        var unusable = undefined;
        console.info(unusable);
    };
    BasicTypeTests.prototype.testCast = function () {
        var someValue = "this is a string";
        // let strLength: number = ((string)someValue).length;
        var strLength = someValue.length;
        strLength = someValue.length;
    };
    return BasicTypeTests;
}(tsUnit.TestClass));
exports.BasicTypeTests = BasicTypeTests;
