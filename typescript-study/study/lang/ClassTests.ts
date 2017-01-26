import tsUnit = require("../../node_modules/tsunit.external/tsUnit");

abstract class Mammal {
	abstract makeSound(): void;
}

export class ClassTests extends tsUnit.TestClass {
	setUp() {
		console.info("\nsetUp\t");		
	}

	tearDown() {
		console.info("tearDown\n");
	}

	testGreeter() {
		class Greeter {
			greeting: string;
			constructor(message: string) {
				this.greeting = message;
			}
			greet() {
				return "Hello, " + this.greeting;
			}
		}

		let greeter:Greeter = new Greeter("world");
		console.info(greeter.greet());
		console.info(greeter);
		console.info(greeter.greeting);
		console.info(greeter.greet);
	}

	testInheritance() {
		class Animal {
			name: string;
			constructor(theName: string) {this.name = theName;}
			move(distanceInMeters: number = 0) {
				console.info(`${this.name} moved ${distanceInMeters}m.`);
			}
		}

		class Snake extends Animal {
			constructor(name:string) {super(name);}
			move(distanceInMeters = 5) {
				console.info("Slithering...");
				super.move(distanceInMeters);
			}
		}

		class Horse extends Animal {
			constructor(name:string) {super(name);}
			move(distanceInMeters = 45) {
				console.info("Galloping...");
				super.move(distanceInMeters);
			}
		}

		let sam = new Snake("Sammy the Python");
		let tom: Animal = new Horse("Tommy the Palomino");

		sam.move();
		tom.move(34);
	}

	testPublicCompatibility() {
		class Animal {
			name:string;
			constructor(theName: string) {this.name = theName;}
			act(distanceInMeters: number = 0) {
				console.info(`${this.name} moved ${distanceInMeters}m.`);
			}
		}

		class Employee {
			name:string;
			constructor(theName: string) {this.name = theName;}
			act(distanceInMeters: number = 0) {
				console.info(`${this.name} worked ${distanceInMeters}m.`);
			}
		}

		let cat = new Animal("Kitty");
		let coder:Employee = cat;
		console.info(coder.name);
		coder.act(10);
	}

	testPrivate() {
		class Animal {
			private name: string;
			constructor(theName: string) {this.name = theName;}
			getName():string {return this.name;}
		}
		// new Animal("cat").name;
		console.info(new Animal("cat").getName());

		class Rhino extends Animal {
			constructor() {super("Rhino");}
		}

		class Employee {
			private name: string;
			constructor(theName: string) {this.name = theName;}
		}

		let animal = new Animal("Goat");
		let rhino = new Rhino();
		let employee = new Employee("Bob");

		animal = rhino;
		// animal = employee; //private definitions for name collide
	}

	testProtected() {
		class Person {
			protected name: string;
			constructor(name: string) {this.name = name;}
		}

		class Employee extends Person {
			private department: string;
			constructor(name: string, department: string) {
				super(name);
				this.department = department;
			}
			public getElevatorPitch() {
				return `Hello, my name is ${this.name} and I work in ${this.department}`;
			}
		}

		let howard = new Employee("Howard", "Sales");
		console.info(howard.getElevatorPitch());
		// console.info(howard.name); //error
	}

	testParamProperties() {
		class Animal {
			constructor(private name:string) {}
			move(distanceInMeters: number) {
				console.info(`${this.name} moved ${distanceInMeters}`);
			}
		}

		new Animal("name").move(20);
	}

	testAccessors() {
		let passcode = "secret passcode";
		class Employee {
			private _fullName: string;
			get fullName(): string {return this._fullName;}
			set fullName(newName: string) {
				if (passcode && passcode == "secret passcode") {
					this._fullName = newName;
				} else {
					console.warn("Error: Unauthorized update of employee!");
				}
			}
		}

		let employee = new Employee();
		employee.fullName = "Bob Smith";
		if (employee.fullName) {
			console.info(employee.fullName);
		}
	}

	testStatic() {
		interface Point {
			x: number; 
			y: number;
		}
		class Grid {
			static origin: Point = {x: 0, y: 0};

			constructor(public scale: number) {}
			calculateDistanceFromOrigin(point: Point) {
				let xDist = point.x - Grid.origin.x;
				let yDist = point.y - Grid.origin.y;
				return Math.sqrt(xDist * xDist + yDist * yDist) / this.scale;
			}
		}
		let g1 = new Grid(1.0);
		let g2 = new Grid(5.0);

		console.info(g1.calculateDistanceFromOrigin({x: 10, y:10}));
		console.info(g2.calculateDistanceFromOrigin({x: 10, y:10}));
	}

	testAbstractClass() {
		class Dog extends Mammal {
			makeSound()	{
				console.info("bark");
			}
		}

		let cat = <Mammal>{
			makeSound(){console.info("meaw");}
		}

		new Dog().makeSound();
		cat.makeSound();
	}

	testPropertyWithValue() {
		class AjaxRes {
			url:string = "abc";
		}	
		let res = new AjaxRes();
		console.info(res.url);
	}
}


