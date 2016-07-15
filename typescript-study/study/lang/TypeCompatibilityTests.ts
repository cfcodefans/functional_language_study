import tsUnit = require("../../node_modules/tsunit.external/tsUnit");

export class TypeCompatibilityTests extends tsUnit.TestClass {
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

	testTypeCompatibility() {
		interface Named {name: string;}
		interface Nameable {name: string;}
		class Person {name: string;}

		let p: Named = new Person();
		console.info(typeof(p));
		let n: Nameable = p;
		console.info(typeof(n));
	}

	testSameMembers() {
		interface Named {name: string;}
		let x: Named;
		let y = {name: "Fan", location: "Seattle"};
		x = y;
		function greet(n: Named): void {
			console.info("hi there! " + n.name);
		}
		greet(y);
	}

	
}