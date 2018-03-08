class Car {
    // public brandName: string

    // constructor(brandName: string) {
    //     this.brandName = brandName
    // }

    constructor(public brandName: string) {
        Car.SERIES_NUMBER++
        this._seriesNumber = Car.SERIES_NUMBER
    }

    public startCar(): void {
        this._startEngine()
    }

    private _startEngine(): void {

    }

    private _seriesNumber: number

    private static SERIES_NUMBER: number = 0

    public get seriesNumber(): number {
        return this._seriesNumber
    }

    private _ownerName: string
    public get ownerName(): string {
        return this._ownerName
    }

    public set ownerName(name: string) {
        //Do some validation on the name
        this._ownerName = name
    }

    public static BuyCar(brandName: string, ownerName: string): Car {
        let car: Car = new Car(brandName)
        car.ownerName = ownerName
        return car
    }
}

let car: Car = new Car("Mazda")
car.startCar()
console.info("Brand name:", car.brandName)
// car._startEngine()

console.info("Series number", car.seriesNumber)
// car.seriesNumber = 2
car.ownerName = "Slava"
console.info(car.ownerName)

let car1: Car = new Car("Mazda")
console.info("Brand name:", car1.brandName)
// car._startEngine()
console.info("Series number", car1.seriesNumber)
console.info(car1.ownerName)

let car2:Car = Car.BuyCar("Mazda", "Slava")
console.info("Brand name:", car2.brandName)
// car._startEngine()
console.info("Series number", car2.seriesNumber)
console.info(car2.ownerName)