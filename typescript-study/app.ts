import tsUnit = require("./node_modules/tsunit.external/tsUnit");
import BasicTypeTests = require("./study/BasicTypeTests");
// import readline = require("readline");


function main() {
	//Instantiate tsUnit and pass in modules that contain tests
	var tests = new tsUnit.Test(BasicTypeTests);

	//Run the tests
	var result = tests.run();

	//Show the test results (TAP output)
	console.info(result.getTapResults());
}

main();