"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var tsUnit = require("../../node_modules/tsunit.external/tsUnit");
var GenericTests = (function (_super) {
    __extends(GenericTests, _super);
    function GenericTests() {
        _super.call(this);
        console.info("constructor:\t" + this);
    }
    GenericTests.prototype.setUp = function () {
        console.info("\nsetUp\t");
    };
    GenericTests.prototype.tearDown = function () {
        console.info("tearDown\n");
    };
    GenericTests.prototype.testGenerics = function () {
        function identity(arg) {
            return arg;
        }
        console.info(identity("myString"));
        // console.info(identity<number>("myNumber"));
    };
    GenericTests.prototype.testGenericArray = function () {
        function loggingIdentity(arg) {
            console.info(arg.length);
            return arg;
        }
        console.info(loggingIdentity([]));
    };
    GenericTests.prototype.testGenericFuncs = function () {
        function identity(arg) {
            return arg;
        }
        var myIdentity = identity;
        var myIdentityFn = identity;
        var myIdentityFns = identity;
    };
    GenericTests.prototype.testGenericClass = function () {
        var GenericNumber = (function () {
            function GenericNumber() {
            }
            return GenericNumber;
        }());
        var myGenericNumber = new GenericNumber();
        myGenericNumber.zeroValue = 0;
        myGenericNumber.add = function (x, y) { return x + y; };
        console.info(myGenericNumber.add(2, 3));
        var stringNumber = new GenericNumber();
        stringNumber.zeroValue = "";
        stringNumber.add = function (x, y) { return x + y; };
        console.info(stringNumber.add("a", "z"));
    };
    GenericTests.prototype.testGenericConstraints = function () {
        function logIdentity(arg) {
            console.info("length:\t" + arg.length + "\t" + arg);
            return arg;
        }
        logIdentity("string");
        // logIdentity(3); //Error, number doesn't have a .length property
        logIdentity([3]);
        function copyFields(target, source) {
            for (var id in source) {
                target[id] = source[id];
            }
            return target;
        }
        var x = { a: 1, b: 2, c: 3, d: 4 };
        copyFields(x, { b: 10, d: 20 });
        // copyFields(x, {Q: 90});//error, property 'Q' isn't declared in 'x'
    };
    GenericTests.prototype.testGenericsWithClassType = function () {
        function create(c) {
            return new c();
        }
        var BeeKeeper = (function () {
            function BeeKeeper() {
            }
            return BeeKeeper;
        }());
        var ZooKeeper = (function () {
            function ZooKeeper() {
            }
            return ZooKeeper;
        }());
        var Animal = (function () {
            function Animal() {
            }
            return Animal;
        }());
        var Bee = (function (_super) {
            __extends(Bee, _super);
            function Bee() {
                _super.apply(this, arguments);
            }
            return Bee;
        }(Animal));
        var Lion = (function (_super) {
            __extends(Lion, _super);
            function Lion() {
                _super.apply(this, arguments);
            }
            return Lion;
        }(Animal));
        function findKeeper(a) {
            return a.prototype.keeper;
        }
        findKeeper(Lion).nametag;
        findKeeper(Bee).hasMask;
    };
    return GenericTests;
}(tsUnit.TestClass));
exports.GenericTests = GenericTests;
