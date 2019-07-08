class Car {
    // public brandName: string
    // constructor(brandName: string) {
    //     this.brandName = brandName
    // }
    constructor(brandName) {
        this.brandName = brandName;
        Car.SERIES_NUMBER++;
        this._seriesNumber = Car.SERIES_NUMBER;
    }
    startCar() {
        this._startEngine();
    }
    _startEngine() {
    }
    get seriesNumber() {
        return this._seriesNumber;
    }
    get ownerName() {
        return this._ownerName;
    }
    set ownerName(name) {
        //Do some validation on the name
        this._ownerName = name;
    }
    static BuyCar(brandName, ownerName) {
        let car = new Car(brandName);
        car.ownerName = ownerName;
        return car;
    }
}
Car.SERIES_NUMBER = 0;
let car = new Car("Mazda");
car.startCar();
console.info("Brand name:", car.brandName);
// car._startEngine()
console.info("Series number", car.seriesNumber);
// car.seriesNumber = 2
car.ownerName = "Slava";
console.info(car.ownerName);
let car1 = new Car("Mazda");
console.info("Brand name:", car1.brandName);
// car._startEngine()
console.info("Series number", car1.seriesNumber);
console.info(car1.ownerName);
let car2 = Car.BuyCar("Mazda", "Slava");
console.info("Brand name:", car2.brandName);
// car._startEngine()
console.info("Series number", car2.seriesNumber);
console.info(car2.ownerName);
