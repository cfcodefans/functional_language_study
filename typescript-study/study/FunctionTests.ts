import tsUnit = require("../node_modules/tsunit.external/tsUnit");


export class FunctionTests extends tsUnit.TestClass {
	
	constructor() {
		super();
		console.info("constructor:\t" + this);
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
		function buildName(firstName: string, lastName: string = "Smith"): string {
			return `${firstName} ${lastName}`;
		}

		console.info(buildName("Bob"));
		console.info(buildName("Bob", undefined));
		// console.info(buildName("Bob", "Adams", "Sr")); //error, too many parameters
		console.info(buildName("Bob", "Adams"));
	}
}
