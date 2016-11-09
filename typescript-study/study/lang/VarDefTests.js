"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var tsUnit = require("../../node_modules/tsunit.external/tsUnit");
var VarDefTests = (function (_super) {
    __extends(VarDefTests, _super);
    function VarDefTests() {
        _super.call(this);
        console.info("constructor:\t" + this);
    }
    VarDefTests.prototype.setUp = function () {
        console.info("\nsetUp\t");
    };
    VarDefTests.prototype.tearDown = function () {
        console.info("tearDown\n");
    };
    VarDefTests.prototype.testVarDef = function () {
        var a = 10;
        this.areIdentical(a, 10, "var a = " + a + ";");
    };
    VarDefTests.prototype.testVarDefInFunc = function () {
        function f() {
            var message = "Hello, World!";
            return message;
        }
    };
    VarDefTests.prototype.testVarReferredInFunc = function () {
        function f() {
            var a = 10;
            return function g() {
                var b = a + 1;
                return b;
            };
        }
        var g = f();
        this.areIdentical(11, g(), "a = 10 insides f() {...}");
    };
    VarDefTests.prototype.testModifyVarReferredInFunc = function () {
        function f() {
            var a = 1;
            a = 2;
            var b = g();
            a = 3;
            return b;
            function g() { return a; }
        }
        this.areIdentical(f(), 2, "a = 3, but g(){...} was called first");
    };
    VarDefTests.prototype.testConditionalVarDef = function () {
        function f(shouldInit) {
            if (shouldInit) {
                var x = 10;
            }
            return x;
        }
        console.info(f(true));
        console.info(f(false));
    };
    VarDefTests.prototype.testVarCapture = function () {
        for (var i = 0; i < 10; i++) {
            (function (_i) {
                setTimeout(function () { console.info(_i); }, 100 * _i);
            })(i);
        }
    };
    VarDefTests.prototype.testConditionalLet = function () {
        function f(input) {
            var a = 100;
            if (input) {
                var b = a + 1;
                return b;
            }
            // return b;
        }
    };
    VarDefTests.prototype.testLetCapture = function () {
        var _loop_1 = function(i) {
            setTimeout(function () { console.info(i); }, 100 * i);
        };
        for (var i = 0; i < 10; i++) {
            _loop_1(i);
        }
    };
    VarDefTests.prototype.testArrayDestructuring = function () {
        var input = [1, 2];
        var first = input[0], second = input[1];
        this.areIdentical(1, first);
        this.areIdentical(2, second);
        _a = [second, first], first = _a[0], second = _a[1];
        this.areIdentical(2, first);
        this.areIdentical(1, second);
        function f(_a) {
            var first = _a[0], second = _a[1];
            console.log("first:\t" + first);
            console.log("second:\t" + second);
        }
        f([3, 4]);
        first = [4, 3, 2, 1][0];
        this.areIdentical(first, 4);
        var _b = [0, 1, 2, 3, 4], head = _b[0], rest = _b.slice(1);
        console.info(rest, "let [head, ...rest] = [0, 1, 2, 3, 4];");
        var _c = [4, 3, 2, 1], a = _c[0], c = _c[2];
        this.areIdentical(a, 4);
        this.areIdentical(c, 2);
        var _a;
    };
    VarDefTests.prototype.testObjectDestructuring = function () {
        var coordinate = { x: 100, y: 200 };
        var x = coordinate.x, y = coordinate.y;
        this.areIdentical(x, coordinate.x);
        this.areIdentical(y, coordinate.y);
        //TODO
    };
    return VarDefTests;
}(tsUnit.TestClass));
exports.VarDefTests = VarDefTests;
