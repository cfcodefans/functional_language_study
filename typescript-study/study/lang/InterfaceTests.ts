import tsUnit = require("../../node_modules/tsunit.external/tsUnit");


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

	testOptionalProperties() {
		interface SquareConfig {
			color?: string; width?: number;
		}
		function createSquare(config: SquareConfig): {color: string, area: number} {
			let newSquare = {color: "white", area: 100};
			if (config.color) {
				newSquare.color = config.color;
			} else {
				console.warn("color doesn't exist");
			}
			if (config.width) {
				newSquare.area = config.width * config.width;
			} else {
				console.warn("width doesn't exist");
			}
			return newSquare;
		}
		let mySquare = createSquare({color: "red"});
		this.isTrue(mySquare.color === "red" && mySquare.area == 100);
		mySquare = createSquare({width: 100, opacity: 0.5} as SquareConfig);
	}

	testRandomProperites() {
		interface SquareConfig {
			color?: string;
			width?: number;
			[propertyName: string]: any;
		}

		function printSquareConfig(squareConfig: SquareConfig) {
			for (let p in squareConfig) {
				console.info("%s:\t%s", p, squareConfig[p]);
			}
		}

		printSquareConfig({color:"blue", width:100, height:200, name:"test",created:new Date()});
	}

	testFuncType() {
		interface SearchFunc {
			(source: string, subString: string): boolean;
		}
		let mySearch = (source:string, subString:string):boolean => {
			return source.search(subString) != -1;
		}

		this.isTrue(mySearch("abcdef", "cde"));
	}

	testIndexableType() {
		interface StringArray {
			[index: number]: string;
		}

		let myArray: StringArray = ["Bob", "Fred"];
		// myArray.forEach((el:string, i:number, array) => console.info(el, i, array));
		(myArray as Array<string>).forEach((el:string, i:number, array) => console.info(el, i, array));

		interface NumberArray extends Array<number> {
			[index: number]: number;
		}
		let intArray: NumberArray = [3, 1, 4, 1, 5, 9];
		intArray.forEach((el:number, i:number, array) => console.info(el, i, array));

		class Animal {name:string;}
		class Dog extends Animal {breed:string;}

		//Error: indexing with a 'string' will sometimes get you a dog
		interface NotOkay {
			// [x: number]: Animal;//Numeric index type Animal can not be assigned to Dog
			[x: string]: Dog;
		}

		interface NumberDictionary {
			[index: string]: number;
			length: number; //okay, length is a number
			// name: string;//error, the type of 'name' is not a subtype of the indexer, string is not assignable to number
		}	

		interface IClock {
			currentTime: Date;
		}
		let clock:IClock = {
			currentTime: new Date()
		};
	}

	testConstructorInterface() {
		interface IClockConstructor {
			new (hour:number, minute:number);//:IClockConstructor;
		}
		// doesn't work
		// class ClockConstructor implements IClockConstructor {
		// 	constructor(hour:number, minute:number)	{}
		// }
		// let clock = new ClockConstructor(10, 11);
		interface IClock {
			currentTime: Date;
			tick();
		}
		interface IClockFactory {
			new (hour:number, minute:number): IClock;	
		}

		function createClock(cf: IClockFactory): IClock {
			let now: Date = new Date();
			return new cf(now.getHours(), now.getMinutes());
		}

		class DigitalClock implements IClock {
			currentTime: Date = new Date();
			constructor(h: number, m: number) {
				this.currentTime.setHours(h);
				this.currentTime.setMinutes(h);
			}
			tick() {console.info("beep beep");}
		}

		class AnalogClock implements IClock {
			currentTime: Date = new Date();
			constructor(h: number, m: number) {
				this.currentTime.setHours(h);
				this.currentTime.setMinutes(h);
			}
			tick() {console.info("tick tock");}
		}

		let dc:IClock = createClock(DigitalClock);
		dc.tick();
		let ac:IClock = createClock(AnalogClock);
		ac.tick();
	}

	testClassType() {
		{

			interface IClock {
				currentTime: Date;
			}

			class Clock implements IClock {
				currentTime: Date;
				constructor(h: number, m: number) {}
			}
		}

		{
			interface IClock {
				currentTime: Date;
				setCurrentTime(d: Date);
			}

			class Clock implements IClock {
				currentTime: Date;
				setCurrentTime(d: Date) {
					this.currentTime = d;
				}
			}
		}
	}

	testExtendInterface() {
		interface Shape {
			color: string;
		}

	 	{
			interface Square extends Shape {
				sideLength: number;
			}

			let square = <Square>{};
			square.color = "blue";
			square.sideLength = 10;

			console.info(square);
		}

		{
			interface PenStroke {
				penWidth: number;
			}

			interface Square extends Shape, PenStroke {
				sideLength: number;
			}

			let square = <Square>{};
			square.color = "blue";
			square.sideLength = 10;
			square.penWidth = 5.0;

			console.info(square);
		}
	}

	testHybirdTypes() {
		interface Counter {
			(start: number): string;
			interval: number;
			reset(): void;
		}

		function getCounter(): Counter {
			let counter = <Counter>function (start: number) {console.info("(start = %d: number)", start);};
			counter.interval = 123;
			counter.reset = function() {};
			return counter;
		}

		let c:Counter = getCounter();
		c(10);
		c.reset();
		c.interval = 5.0;

		console.info(c);

		c(15);
	}

	testInterfaceExtendingClasses() {
		class Control {
			private state: any;
		}

		interface SelectableControl extends Control {
			select(): void;
		}

		class Button extends Control {
			select() {console.info(this + ".select()");}
		}

		class TextBox extends Control {
			select() {console.info(this + ".select()");}	
		}

		class Image extends Control {

		}

		class Location {
			select() {console.info(this + ".select()");}
		}

		let sc: SelectableControl = new Button();
		sc.select();
		sc = new TextBox();
		sc.select();
		// sc = new Image();
		// sc.select();
		// sc = new Location();
		// sc.select();
	}
}
