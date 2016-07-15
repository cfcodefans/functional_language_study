import tsUnit = require("../../node_modules/tsunit.external/tsUnit");

export class BasicTypeTests extends tsUnit.TestClass {
	constructor() {
		super();
		console.info("constructor:\t" + this);
	}

	setUp() {
		console.info("\nsetUp");
	}

	tearDown() {
		console.info("tearDown\n");	
	}

	isTrue(val: boolean, msg: string = '') {
		console.info(val, msg);
		super.isTrue(val, msg);
	}

	testObj() {
		var obj: Object = new Object();
		console.info(obj);
		this.isTrue(obj != null);

		for (var p in obj) {
			console.info(p, ":\t", + obj[p]);
		}

		obj = { x: 1, y: 2 };

		for (var p in obj) {
			console.info(p, ":\t", + obj[p]);
		}

		console.info(obj);
	}

	testArray() {
		var array: Array<Number> = new Array(0, 1, 2, 3, 4, 5);
		console.info(array);

		array.forEach((el, idx, _array) => console.info(el, _array.length - idx));

		this.areIdentical(array.pop(), 5);

		this.isFalse(array.every((el) => el > 0));
		this.isTrue(array.slice(1).every((el) => el > 0));

		let list: number[] = [1, 2, 3, 4];
		let _list: Array<number> = [1, 2, 3, 4];

	}

	testEquality() {
		this.isTrue(1 == 1, "1 == 1");
		this.isTrue(1 === 1, "1 === 1");
		
		var a: number = 1;
		this.isTrue(a == a, "a == a");
		this.isTrue(a === a, "a === a");
		var b: number = 1;
		this.isTrue(b == a, "b == a");		
		this.isTrue(b === a, "b === a");


		this.isTrue([b] != [a], "[b] != [a]");
		this.isTrue([b] !== [a], "[b] !== [a]");


		this.isTrue({ x: b } != { x: a }, "{ x: b } != { x: a }");
		this.isTrue({ x: b } !== { x: a }, "{ x: b } !== { x: a }");

		a = 2;		
		this.isTrue({ x: b } != { x: a }, "{ x: b } != { x: a }");
		this.isTrue({ x: b } !== { x: a }, "{ x: b } !== { x: a }");

		this.isTrue(b != a, `${b} != ${a}`);
		this.isTrue(b !== a, `${b} !== ${a}`);
	}

	testBoolean() {
		let bool: boolean = true;
		this.isTrue(bool);
		this.isFalse(!bool);
		this.isTrue(bool || true);
		this.isFalse(bool && false);
		this.isFalse(bool !== bool);
	}

	testNumber() {
		let decimal: Number = 6;
		let hex: Number = 0xf00d;
		let binary: Number = 0b1010;
		let octal: Number = 0o744;

		console.info(decimal.toExponential());
	}

	testString() {
		let color: String = "blue";
		color = 'red';

		let fullName: String = `Bob Bobbington`;
		let age: number = 37;
		let sentence: String = `Hello, my name is ${fullName}.
		I'll be ${age + 1} years old next month.`;

		console.info(sentence);
	}

	testTuple() {
		let x: [string, number] = ["hello", 10];
		console.info(typeof(x));
		console.info(x.length);

		x.forEach((el:string | number, idx: number, array: (string | number)[]) => {
			console.info(`${el}: ${typeof (el)} = [${array}][${idx}]`);
		});
	}

	testEnum() {
		enum Season { Spring, Summer, Autumn, Winter };

		let first: Season = Season.Spring;
		console.info(first, typeof (first));

		let _first = Season[0];
		console.info(_first, typeof (_first));		

		console.info(Season);
		console.info(typeof(Season));

		let _season = Season;
		console.info(_season[first])

		enum R { a = 2, b, c };
		console.info(R)
	}

	testAny() {
		{
			let notSure: any = 4;
			notSure = "maybe a string instead";
			notSure = false;
		}

		{
			let notSure: any = 4;
			notSure.ifItExists();
			notSure.toFixed();
		}
	}

	testVoid() {
		function warnUser(): void {
			console.warn("This is my warning message");
		}
		warnUser();
		let unusable: void = undefined;
		console.info(unusable);
	}

	testCast() {
		let someValue: any = "this is a string";
		// let strLength: number = ((string)someValue).length;
		let strLength: number = (<string>someValue).length;
		strLength = (someValue as string).length;
	}

} 
