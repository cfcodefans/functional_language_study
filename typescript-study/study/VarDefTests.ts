import tsUnit = require("../node_modules/tsunit.external/tsUnit");

export class VarDefTests extends tsUnit.TestClass {
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

	testVarDef() {
		var a = 10;
		this.areIdentical(a, 10, `var a = ${a};`);
	}


	testVarDefInFunc() {
		function f() {
			var message = "Hello, World!";
			return message;
		}
	}

	testVarReferredInFunc() {
		function f() {
			var a = 10;
			return function g() {
				var b = a + 1;
				return b;
			}
		}

		var g = f();
		this.areIdentical(11, g(), "a = 10 insides f() {...}")
	}

	testModifyVarReferredInFunc() {
		function f() {
			var a = 1;
			a = 2;
			var b = g();
			a = 3;
			return b;

			function g() { return a;}
		}
		this.areIdentical(f(), 2, "a = 3, but g(){...} was called first");
	}

	testConditionalVarDef() {
		function f(shouldInit: boolean) {
			if (shouldInit) {
				var x = 10;
			}
			return x;
		}

		console.info(f(true));
		console.info(f(false));
	}

	testVarCapture() {
		for (var i: number = 0; i < 10; i++) {
			((_i:number) => {
				setTimeout(() => { console.info(_i); }, 100 * _i);
			})(i);
		}
	}

	testConditionalLet() {
		function f(input:boolean) {
			let a = 100;
			if (input) {
				let b = a + 1;
				return b;
			}
			// return b;
		}
	}

	testLetCapture() {
		for (let i: number = 0; i < 10; i++) {
			setTimeout(() => { console.info(i); }, 100 * i);
		}
	}

	testArrayDestructuring() {
		let input = [1, 2];
		let [first, second] = input;

		this.areIdentical(1, first);
		this.areIdentical(2, second);

		[first, second] = [second, first];

		this.areIdentical(2, first);
		this.areIdentical(1, second);

		function f([first, second] : [number, number]) {
			console.log("first:\t" + first);
			console.log("second:\t" + second);
		}

		f([3, 4]);

		[first] = [4, 3, 2, 1];
		this.areIdentical(first, 4);

		let [head, ...rest] = [0, 1, 2, 3, 4];
		console.info(rest, "let [head, ...rest] = [0, 1, 2, 3, 4];");

		let [a, , c] = [4, 3, 2, 1];
		this.areIdentical(a, 4);
		this.areIdentical(c, 2);
	}

	testObjectDestructuring() {
		let coordinate = { x: 100, y: 200 };
		let {x, y} = coordinate;

		this.areIdentical(x, coordinate.x);
		this.areIdentical(y, coordinate.y);

		//TODO
	}

}