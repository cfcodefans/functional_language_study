import tsUnit = require("../../node_modules/tsunit.external/tsUnit");

export class CallbackTests extends tsUnit.TestClass {
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

	testSetTimeout() {
		function f1(callback: Function) {
			console.info(new Date());
			setTimeout(callback, 1000);
		}

		f1(()=>{console.info(new Date());})
	}

	testSetInterval() {
		function f1(callback: Function) {
			console.info(new Date());
			setInterval(callback, 1000);
		}

		f1(()=>{console.info(new Date());})	
	}

}