import tsUnit = require("../node_modules/tsunit.external/tsUnit");


export class InterfaceTests extends tsUnit.TestClass {
	
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


	testFirstInf() {
		function printLabel(labelledObj: {label:string}) {
			console.log(labelledObj.label);
		}

		let myObj = {size: 10, label: "Size 10 Object"};
		printLabel(myObj);
	}	

	testSecondInf() {
		interface LabelledValue {
			label: string;
		}
		function printLabel(labelledObj: LabelledValue) {
			console.log(labelledObj.label);
		}		
		let myObj = {size: 10, label: "Size 10 Object"};
		printLabel(myObj);
	}
}
