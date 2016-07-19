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

	testFunctionCompatibility() {
		let xf = (a: number) => -a;
		let yf = (a: number, s: string) => +a;
		let uf = (a: number, s: string) => s+a;
		let zf = (a: number, s: string): string => {
			for (let i:number = 0, j:number = Math.abs(a), _s:string = s; 
				i < j; 
				i++) {
				s += _s;
		}
		return s;
	}

		//zf = yf //return values dismatch
		// zf = uf; //ok
		// yf = uf

		yf = xf;
		console.info(yf(5, "?"));
		console.info(zf(5, "?"));
	}

	testIgnoreExtraParams() {
		let items = [1, 2, 3];
		//not need to force these extra parameters
		items.forEach((item, idx, items) => console.info(item, idx));
		// also good
		items.forEach((item, idx) => console.info(item, idx));
	}

	testFunctionParamBivariance() {
		enum EventType {Mouse, Keyboard}
		interface Event {timestamp: number;}
		interface MouseEvent extends Event {x: number, y: number}
		interface KeyEvent extends Event { keyCode: number }

		function listenEvent(eventType: EventType, handler: (ev: Event) => void) {
			//
		}

		//unsound, but useful and common
		listenEvent(EventType.Mouse, 
			//a specific event type for (ev: Event)
			(me: MouseEvent) => console.info(JSON.stringify(me)));

		//Undesirable alternatives in presence of soundness
		listenEvent(EventType.Mouse, 
			(ev: Event) => console.info((<MouseEvent>ev)));

		listenEvent(EventType.Mouse, 
			<(ev: Event) => void>((me: MouseEvent) => console.info(JSON.stringify(me))));		

		// listenEvent(EventType.Mouse, (e:number)=>console.log(e));
		// listenEvent(EventType.Mouse, (e:{x: number; y: number;})=>console.log(e));
		listenEvent(EventType.Mouse, 
			(e:{x: number; y: number; timestamp: number}) => console.log(e));
	}

	testOptionalParams() {
		function _func(args: any[], callback: (...args: any[]) => void) {

		}

		//Unsound - _func "might" provide any number of arguments
		_func([1,2], (x, y) => console.info(x, y));

		// Confusing (x and y are actually required) and undiscoverable
		_func([1, 2], (x?, y?) => console.info(x, y));
	}

	testEnumIncompitable() {
		enum Status {Ready, Waiting}
		enum Color {Red, Blue, Green}
		let status = Status.Ready;
		// status = Color.Green; //error
	}

	testClassCompatibility() {
		class Animal {
			feet: number;
			constructor(name: string, numFeet: number) {
				console.info(name, numFeet);
			}
		}
		class Size {
			feet: number;
			constructor(numFeet: number) {
				console.info(numFeet);
			}
		}
		let a: Animal;
		let s: Size;
		a = s; //OK
		s = a; 
	}

	testGenerics() {
		interface Empty<T> {}
		let x: Empty<number>;
		let y: Empty<string>;
		x = y; //okay, y matches struture of x because Empty<T> doesn't use T

		interface NotEmpty<T> { data: T;}
		let u: NotEmpty<number>;
		let v: NotEmpty<string>;
		// u = v;//error, x and y are not compatible
	}
}