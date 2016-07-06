import tsUnit = require("./node_modules/tsunit.external/tsUnit");
import BasicTypeTests = require("./study/BasicTypeTests");
import VarDefTests = require("./study/VarDefTests");
import InterfaceTests = require("./study/InterfaceTests");
import ClassTests = require("./study/ClassTests");
import FunctionTests = require("./study/FunctionTests");

// import readline = require("readline");


function main() {
	//Instantiate tsUnit and pass in modules that contain tests
	var tests = new tsUnit.Test(
		// BasicTypeTests,
		// VarDefTests,
		// InterfaceTests,
		// ClassTests,
		FunctionTests
		);

	//Run the tests
	var result = tests.run();

	//Show the test results (TAP output)
	console.info(result.getTapResults());
}

main();
