import tsUnit = require("../../node_modules/tsunit.external/tsUnit");

export class EnumTests extends tsUnit.TestClass {
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

	testEnumOpers() {
		enum Direction {
			Up = 1, Down, Left, Right
		}

		console.info("Direction: ", Direction);
		console.info("Direction.Up", Direction.Up);

		let dir:Direction = Direction.Up;
		console.info("Up + 1", dir + 1, Direction[dir + 1]);

		console.info("Direction[Direction.Up]", Direction[Direction.Up]);
		console.info("Direction[Direction[Direction.Up]]", Direction[Direction[Direction.Up]]);
		console.info("Direction[Direction[Direction[Direction.Up]]]", Direction[Direction[Direction[Direction.Up]]]);
	}
}