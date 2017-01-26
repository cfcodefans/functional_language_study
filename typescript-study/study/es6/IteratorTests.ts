import tsUnit = require("../../node_modules/tsunit.external/tsUnit");


export class IteratorTests extends tsUnit.TestClass {
	
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

	testForOf() {
		let nums:number[] = [0, 1, 2, 3, 4, 5];
		for (let i of nums) {
			console.info(i);
		}

		for (let c of "Hello world") {
			console.info(c);
		}


	}
}