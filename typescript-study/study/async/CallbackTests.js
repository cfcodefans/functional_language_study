"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var tsUnit = require("../../node_modules/tsunit.external/tsUnit");
var CallbackTests = (function (_super) {
    __extends(CallbackTests, _super);
    function CallbackTests() {
        _super.call(this);
        console.info("constructor:\t" + this);
    }
    CallbackTests.prototype.setUp = function () {
        console.info("\nsetUp\t");
    };
    CallbackTests.prototype.tearDown = function () {
        console.info("tearDown\n");
    };
    CallbackTests.prototype.testSetTimeout = function () {
        function f1(callback) {
            console.info(new Date());
            setTimeout(callback, 1000);
        }
        f1(function () { console.info(new Date()); });
    };
    CallbackTests.prototype.testSetInterval = function () {
        function f1(callback) {
            console.info(new Date());
            setInterval(callback, 1000);
        }
        f1(function () { console.info(new Date()); });
    };
    return CallbackTests;
}(tsUnit.TestClass));
exports.CallbackTests = CallbackTests;
