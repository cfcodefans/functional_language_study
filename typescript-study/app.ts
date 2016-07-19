import tsUnit = require("./node_modules/tsunit.external/tsUnit");
import BasicTypeTests = require("./study/lang/BasicTypeTests");
import VarDefTests = require("./study/lang/VarDefTests");
import InterfaceTests = require("./study/lang/InterfaceTests");
import ClassTests = require("./study/lang/ClassTests");
import FunctionTests = require("./study/lang/FunctionTests");
import GenericTests = require("./study/lang/GenericTests");
import EnumTests = require("./study/lang/EnumTests");
import TypeInferenceTests = require("./study/lang/TypeInferenceTests");
import TypeCompatibilityTests = require("./study/lang/TypeCompatibilityTests");
import CallbackTests = require("./study/async/CallbackTests");

// import readline = require("readline");


function main() {
	//Instantiate tsUnit and pass in modules that contain tests
	var tests = new tsUnit.Test(
		// BasicTypeTests,
		// VarDefTests,
		// InterfaceTests, 
		// ClassTests,
		// FunctionTests,
		// GenericTests,
		// EnumTests,
		// TypeInferenceTests,
		TypeCompatibilityTests
		// CallbackTests
		);

	//Run the tests
	try {
		var result = tests.run();
		//Show the test results (TAP output)
		console.info(result.getTapResults());
	} catch(e) {
		console.error(e);
	}
}

main();
