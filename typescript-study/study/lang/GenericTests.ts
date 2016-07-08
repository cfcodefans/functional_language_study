import tsUnit = require("../../node_modules/tsunit.external/tsUnit");


export class GenericTests extends tsUnit.TestClass {
	
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

	testGenerics() {
		function identity<T>(arg: T): T {
			return arg;
		}

		console.info(identity<string>("myString"));
		// console.info(identity<number>("myNumber"));
	}

	testGenericArray() {
		function loggingIdentity<T>(arg: T[]): T[] {
			console.info(arg.length);
			return arg;
		}
		console.info(loggingIdentity([]));
	}

	testGenericFuncs() {
		function identity<T>(arg: T): T {
			return arg;
		}
		let myIdentity: {<T>(arg: T): T} = identity;

		interface GenericIdentityFn {
			<T>(arg: T): T;
		}
		let myIdentityFn: GenericIdentityFn = identity;

		interface GenericIdentityFns<T> {
			(arg: T): T;
		}
		let myIdentityFns: GenericIdentityFns<number> = identity;
	}

	testGenericClass() {
		class GenericNumber<T> {
			zeroValue: T;
			// add: (x: T, y: T) => T;
			add: (x: T, y: T)=>T;
		}
		let myGenericNumber = new GenericNumber<number>();
		myGenericNumber.zeroValue = 0;
		myGenericNumber.add = (x:number, y:number) => x + y;
		console.info(myGenericNumber.add(2, 3));

		let stringNumber = new GenericNumber<string>();
		stringNumber.zeroValue = "";
		stringNumber.add = function(x:string, y:string):string {return x + y;};
		console.info(stringNumber.add("a", "z"));
	}

	testGenericConstraints() {
		interface Sizeable {
			length: number;
		}
		function logIdentity<T extends Sizeable>(arg: T): T {
			console.info(`length:\t${arg.length}\t${arg}`);
			return arg;
		}
		logIdentity("string");
		// logIdentity(3); //Error, number doesn't have a .length property
		logIdentity([3]);

		function copyFields<T extends U, U>(target: T, source: U): T {
			for (let id in source) {
				target[id] = source[id];
			}
			return target;
		}

		let x = {a: 1, b: 2, c: 3, d: 4};
	}
}