"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var tsUnit = require("../../node_modules/tsunit.external/tsUnit");
var EnumTests = (function (_super) {
    __extends(EnumTests, _super);
    function EnumTests() {
        _super.call(this);
        console.info("constructor:\t" + this);
    }
    EnumTests.prototype.setUp = function () {
        console.info("\nsetUp\t");
    };
    EnumTests.prototype.tearDown = function () {
        console.info("tearDown\n");
    };
    EnumTests.prototype.testEnumOpers = function () {
        var Direction;
        (function (Direction) {
            Direction[Direction["Up"] = 1] = "Up";
            Direction[Direction["Down"] = 2] = "Down";
            Direction[Direction["Left"] = 3] = "Left";
            Direction[Direction["Right"] = 4] = "Right";
        })(Direction || (Direction = {}));
        console.info("Direction: ", Direction);
        console.info("Direction.Up", Direction.Up);
        var dir = Direction.Up;
        console.info("Up + 1", dir + 1, Direction[dir + 1]);
        console.info("Direction[Direction.Up]", Direction[Direction.Up]);
        console.info("Direction[Direction[Direction.Up]]", Direction[Direction[Direction.Up]]);
        console.info("Direction[Direction[Direction[Direction.Up]]]", Direction[Direction[Direction[Direction.Up]]]);
    };
    return EnumTests;
}(tsUnit.TestClass));
exports.EnumTests = EnumTests;
