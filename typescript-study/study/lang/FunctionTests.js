"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var tsUnit = require("../../node_modules/tsunit.external/tsUnit");
var FunctionTests = (function (_super) {
    __extends(FunctionTests, _super);
    function FunctionTests() {
        _super.call(this);
        console.info("constructor:\t");
    }
    FunctionTests.prototype.setUp = function () {
        console.info("\nsetUp\t");
    };
    FunctionTests.prototype.tearDown = function () {
        console.info("tearDown\n");
    };
    FunctionTests.prototype.testFunction = function () {
        function add(x, y) { return x + y; }
        var myAdd = function (x, y) { return x + y; };
        var myAdd1 = function (x, y) { return x + y; };
        console.info("1 + 2 = " + add(1, 2));
        console.info("1 + true = " + add(1, true));
        console.info("1 + \"a\" = " + add(1, "a"));
        console.info("1 + null = " + add(1, null));
        console.info("2 + 1 = " + add(2, 1));
        console.info("true + 1 = " + add(true, 1));
        console.info("\"a\" + 1 = " + add("a", 1));
        console.info("null + 1 = " + add(null, 1));
    };
    FunctionTests.prototype.testReferenceToExternalVar = function () {
        var z = 100;
        function addToZ(x, y) {
            return x + y + z;
        }
        console.info(addToZ(1, 2));
        z = 1;
        console.info(addToZ(1, 2));
        function keepZ(_z) {
            return function (x, y) { return x + y + _z; };
        }
        var _addToZ = keepZ(z);
        console.info(_addToZ(1, 2));
        z = 9;
        console.info(_addToZ(1, 2));
    };
    FunctionTests.prototype.testFunctionType = function () {
        function invoker(binOper, x, y) {
            console.info("binOper(" + x + ", " + y + ") = " + binOper(x, y));
        }
        function add(x, y) { return x + y; }
        invoker(add, 10, 20);
        function sub(x, y) {
            return x - y;
        }
        invoker(sub, 10, 20);
    };
    FunctionTests.prototype.testFunctionParams = function () {
        function buildName(firstName, lastName) {
            return firstName + " " + lastName;
        }
        // console.info(buildName("Bob")); //error too few parameters
        // console.info(buildName("Bob", "Adams", "Sr.")); //error, too many parameters
        console.info(buildName("Bob", "Adams"));
    };
    FunctionTests.prototype.testOptionalParams = function () {
        function buildName(firstName, lastName) {
            return firstName + " " + lastName;
        }
        console.info(buildName("Bob"));
        // console.info(buildName("Bob", "Adams", "Sr.")); //error, too many parameters
        console.info(buildName("Bob", "Adams"));
        function max(a, b, c) {
            return Math.max(a, Math.max(b, c));
        }
        console.info(max(4)); //NAN
        console.info(max(4, 5)); //NAN
        console.info(max(4, 5, 6)); //6
    };
    FunctionTests.prototype.testDefaultParams = function () {
        function buildName(firstName, lastName) {
            if (lastName === void 0) { lastName = "Default"; }
            return firstName + " " + lastName;
        }
        console.info(buildName("Bob"));
        console.info(buildName("Bob", undefined));
        // console.info(buildName("Bob", "Adams", "Sr")); //error, too many parameters
        console.info(buildName("Bob", "Adams"));
        function _buildName(firstName, lastName) {
            if (firstName === void 0) { firstName = "Default"; }
            return firstName + " " + lastName;
        }
        // console.info(_buildName("Bob")); //Error, too few parameters
        // console.info(_buildName("Bob", "Adams", "Sr")); //error, too many parameters
        console.info(_buildName("Jimmy", undefined));
        console.info(_buildName("Jimmy", "Adams"));
        console.info(_buildName(undefined, "Adams"));
    };
    FunctionTests.prototype.testRestParameters = function () {
        function buildName(firstName) {
            var restOfName = [];
            for (var _i = 1; _i < arguments.length; _i++) {
                restOfName[_i - 1] = arguments[_i];
            }
            restOfName.unshift(firstName);
            return restOfName.join(" ");
        }
        console.info(buildName("Joseph", "Samuel", "Lucas", "Mackinzie"));
        console.info(buildName("fan"));
    };
    FunctionTests.prototype.testArguments = function () {
        function inspectArguments() {
            var params = [];
            for (var _i = 0; _i < arguments.length; _i++) {
                params[_i - 0] = arguments[_i];
            }
            console.info("typeof(arguments) = " + typeof (arguments));
            console.info("length: " + arguments.length);
            for (var i = 0, j = arguments.length; i < j; i++) {
                console.info(i, arguments[i]);
            }
        }
        inspectArguments(1, 2, 3);
        inspectArguments("a", "b", "c");
        inspectArguments();
    };
    FunctionTests.prototype.testThis = function () {
        var deck = {
            suits: ["hearts", "spades", "clubs", "diamonds"],
            cards: Array(52),
            createCardPicker: function () {
                return function () {
                    var pickedCard = Math.floor(Math.random() * 52);
                    var pickedSuit = Math.floor(pickedCard / 13);
                    //need to bind "this" to deck
                    return { suit: this.suits[pickedSuit], card: pickedCard % 13 };
                };
            }
        };
        var cardPicker = deck.createCardPicker().bind(deck);
        var pickedCard = cardPicker();
        console.info("card: " + pickedCard.card + " of " + pickedCard.suit);
    };
    FunctionTests.prototype.testLambdaWitThis = function () {
        var deck = {
            suits: ["hearts", "spades", "clubs", "diamonds"],
            cards: Array(52),
            createCardPicker: function () {
                var _this = this;
                return function () {
                    var pickedCard = Math.floor(Math.random() * 52);
                    var pickedSuit = Math.floor(pickedCard / 13);
                    return { suit: _this.suits[pickedSuit], card: pickedCard % 13 };
                };
            }
        };
        var cardPicker = deck.createCardPicker();
        var pickedCard = cardPicker();
        console.info("card: " + pickedCard.card + " of " + pickedCard.suit);
    };
    FunctionTests.prototype.testJavascriptOverloads = function () {
        var suits = ["hearts", "spades", "clubs", "diamonds"];
        function pickCard(x) {
            // Check to see if we're working with an object/array
            // if so, they gave us the deck and we'll pick the card
            if (typeof (x) == "object") {
                var pickedCard = Math.floor(Math.random() * x.length);
                return pickedCard;
            }
            else if (typeof (x) == "number") {
                var pickedSuit = Math.floor(x / 13);
                return { suit: suits[pickedSuit], card: x % 13 };
            }
        }
        var myDeck = [{ suit: "diamonds", card: 2 },
            { suit: "spades", card: 10 },
            { suit: "hearts", card: 4 },];
        var pickedCard1 = myDeck[pickCard(myDeck)];
        console.info("card: " + pickedCard1.card + " of " + pickedCard1.suit);
        var pickedCard2 = pickCard(15);
        console.info("card: " + pickedCard2.card + " of " + pickedCard2.suit);
    };
    FunctionTests.prototype.testTypescriptOverloads = function () {
        var suits = ["hearts", "spades", "clubs", "diamonds"];
        function pickCard(x) {
            // Check to see if we're working with an object/array
            // if so, they gave us the deck and we'll pick the card
            if (typeof (x) == "object") {
                var pickedCard = Math.floor(Math.random() * x.length);
                return pickedCard;
            }
            else if (typeof (x) == "number") {
                var pickedSuit = Math.floor(x / 13);
                return { suit: suits[pickedSuit], card: x % 13 };
            }
        }
        var myDeck = [{ suit: "diamonds", card: 2 },
            { suit: "spades", card: 10 },
            { suit: "hearts", card: 4 },];
        var pickedCard1 = myDeck[pickCard(myDeck)];
        console.info("card: " + pickedCard1.card + " of " + pickedCard1.suit);
        var pickedCard2 = pickCard(15);
        console.info("card: " + pickedCard2.card + " of " + pickedCard2.suit);
        // let pickedCard3 = pickCard("spade"); // violate type checks
    };
    return FunctionTests;
}(tsUnit.TestClass));
exports.FunctionTests = FunctionTests;
