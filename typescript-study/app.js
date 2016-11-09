"use strict";
var tsUnit = require("./node_modules/tsunit.external/tsUnit");
var TypeCompatibilityTests = require("./study/lang/TypeCompatibilityTests");
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
    TypeCompatibilityTests);
    //Run the tests
    try {
        var result = tests.run();
        //Show the test results (TAP output)
        console.info(result.getTapResults());
    }
    catch (e) {
        console.error(e);
    }
}
main();
