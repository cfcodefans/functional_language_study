"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var tsUnit = require("../../node_modules/tsunit.external/tsUnit");
var TypeInferenceTests = (function (_super) {
    __extends(TypeInferenceTests, _super);
    function TypeInferenceTests() {
        _super.call(this);
        console.info("constructor:\t" + this);
    }
    TypeInferenceTests.prototype.setUp = function () {
        console.info("\nsetUp\t");
    };
    TypeInferenceTests.prototype.tearDown = function () {
        console.info("tearDown\n");
    };
    TypeInferenceTests.prototype.testBasics = function () {
        var x = 3;
        console.info(typeof (x));
        x = "3";
        console.info(typeof (x));
        x = new Date();
        console.info(typeof (x));
    };
    TypeInferenceTests.prototype.testCommonType = function () {
        var x = [0, 1, null];
        console.info(typeof (x), "x[0]", x[0]);
        var a = x; //common type inference
        var Animal = (function () {
            function Animal() {
            }
            return Animal;
        }());
        var Rhino = (function (_super) {
            __extends(Rhino, _super);
            function Rhino() {
                _super.apply(this, arguments);
            }
            return Rhino;
        }(Animal));
        var Elephant = (function (_super) {
            __extends(Elephant, _super);
            function Elephant() {
                _super.apply(this, arguments);
            }
            return Elephant;
        }(Animal));
        var Snake = (function (_super) {
            __extends(Snake, _super);
            function Snake() {
                _super.apply(this, arguments);
            }
            return Snake;
        }(Animal));
        var zoo = [new Rhino(), new Elephant(), new Snake()];
        console.info(typeof (zoo), zoo);
        var as = zoo; //common type inferences
        var y = ["0", 1, [2], { v: 3 }];
        // a = y; //can't assign because diverse types
        var _a = y;
    };
    TypeInferenceTests.prototype.testContextualType = function () {
        //?
    };
    return TypeInferenceTests;
}(tsUnit.TestClass));
exports.TypeInferenceTests = TypeInferenceTests;
