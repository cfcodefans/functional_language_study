import tsUnit = require("../../node_modules/tsunit.external/tsUnit");

export class TypeInferenceTests extends tsUnit.TestClass {
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


	testBasics() {
		let x:any = 3;
		console.info(typeof(x));
		x = "3";
		console.info(typeof(x));
		x = new Date();
		console.info(typeof(x));
	}

	testCommonType() {
		let x = [0, 1, null];
		console.info(typeof(x), "x[0]", x[0]);
		let a:number[] = x; //common type inference
		
		class Animal {}
		class Rhino extends Animal {}
		class Elephant extends Animal {}
		class Snake extends Animal {}
		let zoo = [new Rhino(), new Elephant(), new Snake()];
		console.info(typeof(zoo), zoo);
		let as: Animal[] = zoo; //common type inferences

		let y = ["0", 1, [2], {v:3}];
		// a = y; //can't assign because diverse types
		let _a:any[] = y;
	}

	testContextualType() {
		//?
	}
}