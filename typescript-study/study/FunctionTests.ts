import tsUnit = require("../node_modules/tsunit.external/tsUnit");


export class FunctionTests extends tsUnit.TestClass {
	
	constructor() {
		super();
		console.info("constructor:\t");
	}

	setUp() {
		console.info("\nsetUp\t");		
	}

	tearDown() {
		console.info("tearDown\n");
	}

	testFunction() {
		function add(x, y) {return x + y;}
		let myAdd = function(x, y) {return x + y;}
		let myAdd1 = (x, y) => {return x + y;}

		console.info(`1 + 2 = ${add(1, 2)}`);
		console.info(`1 + true = ${add(1, true)}`);
		console.info(`1 + \"a\" = ${add(1, "a")}`);
		console.info(`1 + null = ${add(1, null)}`);

		console.info(`2 + 1 = ${add(2, 1)}`);
		console.info(`true + 1 = ${add(true, 1)}`);
		console.info(`\"a\" + 1 = ${add("a", 1)}`);
		console.info(`null + 1 = ${add(null, 1)}`);
	}

	testReferenceToExternalVar() {
		let z = 100;
		function addToZ(x, y) {
			return x + y + z;
		}
		console.info(addToZ(1, 2));
		z = 1;
		console.info(addToZ(1, 2));

		function keepZ(_z) {
			return function(x, y) {return x + y + _z};
		}

		var _addToZ = keepZ(z);
		console.info(_addToZ(1, 2));
		z = 9;
		console.info(_addToZ(1, 2));
	}

	testFunctionType() {
		function invoker(binOper: (x: number, y: number) => number, x: number, y: number) {
			console.info(`binOper(${x}, ${y}) = ${binOper(x, y)}`);
		}

		function add(x, y) {return x + y;}
		invoker(add, 10, 20);

		function sub(x: number, y: number): number {
			return x - y;
		}
		invoker(sub, 10, 20);
	}

	testFunctionParams() {
		function buildName(firstName: string, lastName: string): string {
			return `${firstName} ${lastName}`;
		}

		// console.info(buildName("Bob")); //error too few parameters
		// console.info(buildName("Bob", "Adams", "Sr.")); //error, too many parameters
		console.info(buildName("Bob", "Adams"));
	}

	testOptionalParams() {
		function buildName(firstName: string, lastName?: string): string {
			return `${firstName} ${lastName}`;	
		}
		console.info(buildName("Bob")); 
		// console.info(buildName("Bob", "Adams", "Sr.")); //error, too many parameters
		console.info(buildName("Bob", "Adams"));	

		function max(a: number, b?: number, c?:number): number {
			return Math.max(a, Math.max(b, c));
		}
		console.info(max(4));	//NAN
		console.info(max(4, 5));	//NAN
		console.info(max(4, 5, 6));	//6
	}

	testDefaultParams() {
		function buildName(firstName: string, lastName: string = "Default"): string {
			return `${firstName} ${lastName}`;
		}

		console.info(buildName("Bob"));
		console.info(buildName("Bob", undefined));
		// console.info(buildName("Bob", "Adams", "Sr")); //error, too many parameters
		console.info(buildName("Bob", "Adams"));

		function _buildName(firstName = "Default", lastName: string) :string {
			return `${firstName} ${lastName}`;	
		}

		// console.info(_buildName("Bob")); //Error, too few parameters
		// console.info(_buildName("Bob", "Adams", "Sr")); //error, too many parameters
		console.info(_buildName("Jimmy", undefined));
		console.info(_buildName("Jimmy", "Adams"));		
		console.info(_buildName(undefined, "Adams"));		
	}

	testRestParameters() {
		function buildName(firstName: string, ...restOfName: string[]): string {
			restOfName.unshift(firstName);
			return restOfName.join(" ");
		}

		console.info(buildName("Joseph", "Samuel", "Lucas", "Mackinzie"));
		console.info(buildName("fan"));
	}

	testArguments() {
		function inspectArguments(...params: any[]):void {
			console.info(`typeof(arguments) = ${typeof(arguments)}`);
			console.info(`length: ${arguments.length}`);
			for (var i: number = 0, j: number = arguments.length; i < j; i++) {
				console.info(i, arguments[i]);
			}
		}

		inspectArguments(1, 2, 3);
		inspectArguments("a", "b", "c");
		inspectArguments();
	}

	testThis() {
		let deck = {
			suits: ["hearts", "spades", "clubs", "diamonds"],
			cards: Array(52),
			createCardPicker: function() {
				return function() {
					let pickedCard = Math.floor(Math.random() * 52);
					let pickedSuit = Math.floor(pickedCard / 13);
					//need to bind "this" to deck
					return {suit: this.suits[pickedSuit], card: pickedCard % 13};
				};
			}
		}

		let cardPicker = deck.createCardPicker().bind(deck); 
		let pickedCard = cardPicker();

		console.info(`card: ${pickedCard.card} of ${pickedCard.suit}`);
	}

	testLambdaWitThis() {
		let deck = {
			suits: ["hearts", "spades", "clubs", "diamonds"],
			cards: Array(52),
			createCardPicker: function() {
				return () => {
					let pickedCard = Math.floor(Math.random() * 52);
					let pickedSuit = Math.floor(pickedCard / 13);
					return {suit: this.suits[pickedSuit], card: pickedCard % 13};
				};
			}
		};

		let cardPicker = deck.createCardPicker();
		let pickedCard = cardPicker();
		console.info(`card: ${pickedCard.card} of ${pickedCard.suit}`);
	}

	testJavascriptOverloads() {
		let suits = ["hearts", "spades", "clubs", "diamonds"];

		function pickCard(x): any {
			// Check to see if we're working with an object/array
			// if so, they gave us the deck and we'll pick the card
			if (typeof(x) == "object") {
				let pickedCard = Math.floor(Math.random() * x.length);
				return pickedCard;
			}
			// Otherwise just let them pick the card
			else if (typeof(x) == "number") {
				let pickedSuit = Math.floor(x / 13);
				return {suit: suits[pickedSuit], card: x % 13};
			}
		}

		let myDeck = [{suit: "diamonds", card: 2},
						{suit: "spades", card: 10},
						{suit: "hearts", card: 4},];
		let pickedCard1 = myDeck[pickCard(myDeck)];
		console.info(`card: ${pickedCard1.card} of ${pickedCard1.suit}`);

		let pickedCard2 = pickCard(15);
		console.info(`card: ${pickedCard2.card} of ${pickedCard2.suit}`);		
	}

	testTypescriptOverloads() {
		let suits = ["hearts", "spades", "clubs", "diamonds"];
		function pickCard(x: {suit: string; card: number;}[]): number;
		function pickCard(x: number): {suit: string; card: number;};
		function pickCard(x): any {
			// Check to see if we're working with an object/array
			// if so, they gave us the deck and we'll pick the card
			if (typeof(x) == "object") {
				let pickedCard = Math.floor(Math.random() * x.length);
				return pickedCard;
			}
			// Otherwise just let them pick the card
			else if (typeof(x) == "number") {
				let pickedSuit = Math.floor(x / 13);
				return {suit: suits[pickedSuit], card: x % 13};
			}
		}

		let myDeck = [{suit: "diamonds", card: 2},
						{suit: "spades", card: 10},
						{suit: "hearts", card: 4},];
		let pickedCard1 = myDeck[pickCard(myDeck)];
		console.info(`card: ${pickedCard1.card} of ${pickedCard1.suit}`);

		let pickedCard2 = pickCard(15);
		console.info(`card: ${pickedCard2.card} of ${pickedCard2.suit}`);

		// let pickedCard3 = pickCard("spade"); // violate type checks
	}
}
