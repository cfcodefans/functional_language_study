"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var tsUnit = require("../../node_modules/tsunit.external/tsUnit");
var TypeCompatibilityTests = (function (_super) {
    __extends(TypeCompatibilityTests, _super);
    function TypeCompatibilityTests() {
        _super.call(this);
        console.info("constructor:\t" + this);
    }
    TypeCompatibilityTests.prototype.setUp = function () {
        console.info("\nsetUp\t");
    };
    TypeCompatibilityTests.prototype.tearDown = function () {
        console.info("tearDown\n");
    };
    TypeCompatibilityTests.prototype.testTypeCompatibility = function () {
        var Person = (function () {
            function Person() {
            }
            return Person;
        }());
        var p = new Person();
        console.info(typeof (p));
        var n = p;
        console.info(typeof (n));
    };
    TypeCompatibilityTests.prototype.testSameMembers = function () {
        var x;
        var y = { name: "Fan", location: "Seattle" };
        x = y;
        function greet(n) {
            console.info("hi there! " + n.name);
        }
        greet(y);
    };
    TypeCompatibilityTests.prototype.testFunctionCompatibility = function () {
        var xf = function (a) { return -a; };
        var yf = function (a, s) { return +a; };
        var uf = function (a, s) { return s + a; };
        var zf = function (a, s) {
            for (var i = 0, j = Math.abs(a), _s = s; i < j; i++) {
                s += _s;
            }
            return s;
        };
        //zf = yf //return values dismatch
        // zf = uf; //ok
        // yf = uf
        yf = xf;
        console.info(yf(5, "?"));
        console.info(zf(5, "?"));
    };
    TypeCompatibilityTests.prototype.testIgnoreExtraParams = function () {
        var items = [1, 2, 3];
        //not need to force these extra parameters
        items.forEach(function (item, idx, items) { return console.info(item, idx); });
        // also good
        items.forEach(function (item, idx) { return console.info(item, idx); });
    };
    TypeCompatibilityTests.prototype.testFunctionParamBivariance = function () {
        var EventType;
        (function (EventType) {
            EventType[EventType["Mouse"] = 0] = "Mouse";
            EventType[EventType["Keyboard"] = 1] = "Keyboard";
        })(EventType || (EventType = {}));
        function listenEvent(eventType, handler) {
            //
        }
        //unsound, but useful and common
        listenEvent(EventType.Mouse, 
        //a specific event type for (ev: Event)
        function (me) { return console.info(JSON.stringify(me)); });
        //Undesirable alternatives in presence of soundness
        listenEvent(EventType.Mouse, function (ev) { return console.info(ev); });
        listenEvent(EventType.Mouse, (function (me) { return console.info(JSON.stringify(me)); }));
        // listenEvent(EventType.Mouse, (e:number)=>console.log(e));
        // listenEvent(EventType.Mouse, (e:{x: number; y: number;})=>console.log(e));
        listenEvent(EventType.Mouse, function (e) { return console.log(e); });
    };
    TypeCompatibilityTests.prototype.testOptionalParams = function () {
        function _func(args, callback) {
        }
        //Unsound - _func "might" provide any number of arguments
        _func([1, 2], function (x, y) { return console.info(x, y); });
        // Confusing (x and y are actually required) and undiscoverable
        _func([1, 2], function (x, y) { return console.info(x, y); });
    };
    TypeCompatibilityTests.prototype.testEnumIncompitable = function () {
        var Status;
        (function (Status) {
            Status[Status["Ready"] = 0] = "Ready";
            Status[Status["Waiting"] = 1] = "Waiting";
        })(Status || (Status = {}));
        var Color;
        (function (Color) {
            Color[Color["Red"] = 0] = "Red";
            Color[Color["Blue"] = 1] = "Blue";
            Color[Color["Green"] = 2] = "Green";
        })(Color || (Color = {}));
        var status = Status.Ready;
        // status = Color.Green; //error
    };
    TypeCompatibilityTests.prototype.testClassCompatibility = function () {
        var Animal = (function () {
            function Animal(name, numFeet) {
                console.info(name, numFeet);
            }
            return Animal;
        }());
        var Size = (function () {
            function Size(numFeet) {
                console.info(numFeet);
            }
            return Size;
        }());
        var a;
        var s;
        a = s; //OK
        s = a;
    };
    TypeCompatibilityTests.prototype.testGenerics = function () {
        var x;
        var y;
        x = y; //okay, y matches struture of x because Empty<T> doesn't use T
        var u;
        var v;
        // u = v;//error, x and y are not compatible
    };
    return TypeCompatibilityTests;
}(tsUnit.TestClass));
exports.TypeCompatibilityTests = TypeCompatibilityTests;
