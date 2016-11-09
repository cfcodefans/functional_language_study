"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var tsUnit = require("../../node_modules/tsunit.external/tsUnit");
var InterfaceTests = (function (_super) {
    __extends(InterfaceTests, _super);
    function InterfaceTests() {
        _super.call(this);
        console.info("constructor:\t" + this);
    }
    InterfaceTests.prototype.setUp = function () {
        console.info("\nsetUp\t");
    };
    InterfaceTests.prototype.tearDown = function () {
        console.info("tearDown\n");
    };
    InterfaceTests.prototype.testFirstInf = function () {
        function printLabel(labelledObj) {
            console.log(labelledObj.label);
        }
        var myObj = { size: 10, label: "Size 10 Object" };
        printLabel(myObj);
    };
    InterfaceTests.prototype.testSecondInf = function () {
        function printLabel(labelledObj) {
            console.log(labelledObj.label);
        }
        var myObj = { size: 10, label: "Size 10 Object" };
        printLabel(myObj);
    };
    InterfaceTests.prototype.testOptionalProperties = function () {
        function createSquare(config) {
            var newSquare = { color: "white", area: 100 };
            if (config.color) {
                newSquare.color = config.color;
            }
            else {
                console.warn("color doesn't exist");
            }
            if (config.width) {
                newSquare.area = config.width * config.width;
            }
            else {
                console.warn("width doesn't exist");
            }
            return newSquare;
        }
        var mySquare = createSquare({ color: "red" });
        this.isTrue(mySquare.color === "red" && mySquare.area == 100);
        mySquare = createSquare({ width: 100, opacity: 0.5 });
    };
    InterfaceTests.prototype.testRandomProperites = function () {
        function printSquareConfig(squareConfig) {
            for (var p in squareConfig) {
                console.info("%s:\t%s", p, squareConfig[p]);
            }
        }
        printSquareConfig({ color: "blue", width: 100, height: 200, name: "test", created: new Date() });
    };
    InterfaceTests.prototype.testFuncType = function () {
        var mySearch = function (source, subString) {
            return source.search(subString) != -1;
        };
        this.isTrue(mySearch("abcdef", "cde"));
    };
    InterfaceTests.prototype.testIndexableType = function () {
        var myArray = ["Bob", "Fred"];
        // myArray.forEach((el:string, i:number, array) => console.info(el, i, array));
        myArray.forEach(function (el, i, array) { return console.info(el, i, array); });
        var intArray = [3, 1, 4, 1, 5, 9];
        intArray.forEach(function (el, i, array) { return console.info(el, i, array); });
        var Animal = (function () {
            function Animal() {
            }
            return Animal;
        }());
        var Dog = (function (_super) {
            __extends(Dog, _super);
            function Dog() {
                _super.apply(this, arguments);
            }
            return Dog;
        }(Animal));
        var clock = {
            currentTime: new Date()
        };
    };
    InterfaceTests.prototype.testConstructorInterface = function () {
        function createClock(cf) {
            var now = new Date();
            return new cf(now.getHours(), now.getMinutes());
        }
        var DigitalClock = (function () {
            function DigitalClock(h, m) {
                this.currentTime = new Date();
                this.currentTime.setHours(h);
                this.currentTime.setMinutes(h);
            }
            DigitalClock.prototype.tick = function () { console.info("beep beep"); };
            return DigitalClock;
        }());
        var AnalogClock = (function () {
            function AnalogClock(h, m) {
                this.currentTime = new Date();
                this.currentTime.setHours(h);
                this.currentTime.setMinutes(h);
            }
            AnalogClock.prototype.tick = function () { console.info("tick tock"); };
            return AnalogClock;
        }());
        var dc = createClock(DigitalClock);
        dc.tick();
        var ac = createClock(AnalogClock);
        ac.tick();
    };
    InterfaceTests.prototype.testClassType = function () {
        {
            var Clock = (function () {
                function Clock(h, m) {
                }
                return Clock;
            }());
        }
        {
            var Clock = (function () {
                function Clock() {
                }
                Clock.prototype.setCurrentTime = function (d) {
                    this.currentTime = d;
                };
                return Clock;
            }());
        }
    };
    InterfaceTests.prototype.testExtendInterface = function () {
        {
            var square = {};
            square.color = "blue";
            square.sideLength = 10;
            console.info(square);
        }
        {
            var square = {};
            square.color = "blue";
            square.sideLength = 10;
            square.penWidth = 5.0;
            console.info(square);
        }
    };
    InterfaceTests.prototype.testHybirdTypes = function () {
        function getCounter() {
            var counter = function (start) { console.info("(start = %d: number)", start); };
            counter.interval = 123;
            counter.reset = function () { };
            return counter;
        }
        var c = getCounter();
        c(10);
        c.reset();
        c.interval = 5.0;
        console.info(c);
        c(15);
    };
    InterfaceTests.prototype.testInterfaceExtendingClasses = function () {
        var Control = (function () {
            function Control() {
            }
            return Control;
        }());
        var Button = (function (_super) {
            __extends(Button, _super);
            function Button() {
                _super.apply(this, arguments);
            }
            Button.prototype.select = function () { console.info(this + ".select()"); };
            return Button;
        }(Control));
        var TextBox = (function (_super) {
            __extends(TextBox, _super);
            function TextBox() {
                _super.apply(this, arguments);
            }
            TextBox.prototype.select = function () { console.info(this + ".select()"); };
            return TextBox;
        }(Control));
        var Image = (function (_super) {
            __extends(Image, _super);
            function Image() {
                _super.apply(this, arguments);
            }
            return Image;
        }(Control));
        var Location = (function () {
            function Location() {
            }
            Location.prototype.select = function () { console.info(this + ".select()"); };
            return Location;
        }());
        var sc = new Button();
        sc.select();
        sc = new TextBox();
        sc.select();
        // sc = new Image();
        // sc.select();
        // sc = new Location();
        // sc.select();
    };
    return InterfaceTests;
}(tsUnit.TestClass));
exports.InterfaceTests = InterfaceTests;
