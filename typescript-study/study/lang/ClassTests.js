"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var tsUnit = require("../../node_modules/tsunit.external/tsUnit");
var Mammal = (function () {
    function Mammal() {
    }
    return Mammal;
}());
var ClassTests = (function (_super) {
    __extends(ClassTests, _super);
    function ClassTests() {
        _super.apply(this, arguments);
    }
    ClassTests.prototype.setUp = function () {
        console.info("\nsetUp\t");
    };
    ClassTests.prototype.tearDown = function () {
        console.info("tearDown\n");
    };
    ClassTests.prototype.testGreeter = function () {
        var Greeter = (function () {
            function Greeter(message) {
                this.greeting = message;
            }
            Greeter.prototype.greet = function () {
                return "Hello, " + this.greeting;
            };
            return Greeter;
        }());
        var greeter = new Greeter("world");
        console.info(greeter.greet());
        console.info(greeter);
        console.info(greeter.greeting);
        console.info(greeter.greet);
    };
    ClassTests.prototype.testInheritance = function () {
        var Animal = (function () {
            function Animal(theName) {
                this.name = theName;
            }
            Animal.prototype.move = function (distanceInMeters) {
                if (distanceInMeters === void 0) { distanceInMeters = 0; }
                console.info(this.name + " moved " + distanceInMeters + "m.");
            };
            return Animal;
        }());
        var Snake = (function (_super) {
            __extends(Snake, _super);
            function Snake(name) {
                _super.call(this, name);
            }
            Snake.prototype.move = function (distanceInMeters) {
                if (distanceInMeters === void 0) { distanceInMeters = 5; }
                console.info("Slithering...");
                _super.prototype.move.call(this, distanceInMeters);
            };
            return Snake;
        }(Animal));
        var Horse = (function (_super) {
            __extends(Horse, _super);
            function Horse(name) {
                _super.call(this, name);
            }
            Horse.prototype.move = function (distanceInMeters) {
                if (distanceInMeters === void 0) { distanceInMeters = 45; }
                console.info("Galloping...");
                _super.prototype.move.call(this, distanceInMeters);
            };
            return Horse;
        }(Animal));
        var sam = new Snake("Sammy the Python");
        var tom = new Horse("Tommy the Palomino");
        sam.move();
        tom.move(34);
    };
    ClassTests.prototype.testPublicCompatibility = function () {
        var Animal = (function () {
            function Animal(theName) {
                this.name = theName;
            }
            Animal.prototype.act = function (distanceInMeters) {
                if (distanceInMeters === void 0) { distanceInMeters = 0; }
                console.info(this.name + " moved " + distanceInMeters + "m.");
            };
            return Animal;
        }());
        var Employee = (function () {
            function Employee(theName) {
                this.name = theName;
            }
            Employee.prototype.act = function (distanceInMeters) {
                if (distanceInMeters === void 0) { distanceInMeters = 0; }
                console.info(this.name + " worked " + distanceInMeters + "m.");
            };
            return Employee;
        }());
        var cat = new Animal("Kitty");
        var coder = cat;
        console.info(coder.name);
        coder.act(10);
    };
    ClassTests.prototype.testPrivate = function () {
        var Animal = (function () {
            function Animal(theName) {
                this.name = theName;
            }
            Animal.prototype.getName = function () { return this.name; };
            return Animal;
        }());
        // new Animal("cat").name;
        console.info(new Animal("cat").getName());
        var Rhino = (function (_super) {
            __extends(Rhino, _super);
            function Rhino() {
                _super.call(this, "Rhino");
            }
            return Rhino;
        }(Animal));
        var Employee = (function () {
            function Employee(theName) {
                this.name = theName;
            }
            return Employee;
        }());
        var animal = new Animal("Goat");
        var rhino = new Rhino();
        var employee = new Employee("Bob");
        animal = rhino;
        // animal = employee; //private definitions for name collide
    };
    ClassTests.prototype.testProtected = function () {
        var Person = (function () {
            function Person(name) {
                this.name = name;
            }
            return Person;
        }());
        var Employee = (function (_super) {
            __extends(Employee, _super);
            function Employee(name, department) {
                _super.call(this, name);
                this.department = department;
            }
            Employee.prototype.getElevatorPitch = function () {
                return "Hello, my name is " + this.name + " and I work in " + this.department;
            };
            return Employee;
        }(Person));
        var howard = new Employee("Howard", "Sales");
        console.info(howard.getElevatorPitch());
        // console.info(howard.name); //error
    };
    ClassTests.prototype.testParamProperties = function () {
        var Animal = (function () {
            function Animal(name) {
                this.name = name;
            }
            Animal.prototype.move = function (distanceInMeters) {
                console.info(this.name + " moved " + distanceInMeters);
            };
            return Animal;
        }());
        new Animal("name").move(20);
    };
    ClassTests.prototype.testAccessors = function () {
        var passcode = "secret passcode";
        var Employee = (function () {
            function Employee() {
            }
            Object.defineProperty(Employee.prototype, "fullName", {
                get: function () { return this._fullName; },
                set: function (newName) {
                    if (passcode && passcode == "secret passcode") {
                        this._fullName = newName;
                    }
                    else {
                        console.warn("Error: Unauthorized update of employee!");
                    }
                },
                enumerable: true,
                configurable: true
            });
            return Employee;
        }());
        var employee = new Employee();
        employee.fullName = "Bob Smith";
        if (employee.fullName) {
            console.info(employee.fullName);
        }
    };
    ClassTests.prototype.testStatic = function () {
        var Grid = (function () {
            function Grid(scale) {
                this.scale = scale;
            }
            Grid.prototype.calculateDistanceFromOrigin = function (point) {
                var xDist = point.x - Grid.origin.x;
                var yDist = point.y - Grid.origin.y;
                return Math.sqrt(xDist * xDist + yDist * yDist) / this.scale;
            };
            Grid.origin = { x: 0, y: 0 };
            return Grid;
        }());
        var g1 = new Grid(1.0);
        var g2 = new Grid(5.0);
        console.info(g1.calculateDistanceFromOrigin({ x: 10, y: 10 }));
        console.info(g2.calculateDistanceFromOrigin({ x: 10, y: 10 }));
    };
    ClassTests.prototype.testAbstractClass = function () {
        var Dog = (function (_super) {
            __extends(Dog, _super);
            function Dog() {
                _super.apply(this, arguments);
            }
            Dog.prototype.makeSound = function () {
                console.info("bark");
            };
            return Dog;
        }(Mammal));
        var cat = {
            makeSound: function () { console.info("meaw"); }
        };
        new Dog().makeSound();
        cat.makeSound();
    };
    return ClassTests;
}(tsUnit.TestClass));
exports.ClassTests = ClassTests;
