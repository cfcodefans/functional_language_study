import { observable, autorun, IReactionDisposer, IReactionPublic, Reaction, IObservableValue, when } from "mobx"
import { trace } from "console";

function _test() {
    const map = observable.map({ key: "value" })

    const list = observable([1, 2, 4])

    const person = observable({
        name: {
            firstName: "Clive Staples",
            lastName: "Lewis"
        },
        age: 36
    });

    console.info("type of person", typeof (person), JSON.stringify(person))

    const temperature = observable.box(20)

    let count: number = 0

    function _react(r: Reaction): void {
        console.info(count, "_react", person, "\n")
        // trace()
        // count++
        // if (count > 2)
        //     r.dispose()
    }

    function __react(r: Reaction): void {
        console.info(count, "__react", person.age, "\n")
        // trace()
        // count++
        // if (count > 2)
        //     r.dispose()
    }

    let ird: IReactionDisposer = autorun(_react)
    autorun(__react)
    console.info("type of person", typeof (person), JSON.stringify(person))

    // let ird: IReactionDisposer = autorun((r: Reaction) => {
    // console.info("r", JSON.stringify(r), "\n")
    // console.info("r.diffValue", JSON.stringify(r.diffValue), "\n")
    // console.info("r.newObserving", JSON.stringify(r.newObserving), "\n")
    // console.info(count, "autorun", JSON.stringify(person), "\n")
    // console.info(count, "autorun", person, "\n")
    // trace()
    // count++
    // if (count > 2)
    //     r.dispose()
    // console.info(JSON.stringify(list))
    // console.info(JSON.stringify(person))
    // console.info(JSON.stringify(temperature))
    // })

    // console.info(JSON.stringify(ird))

    // temperature.set(25)
    person.age = 37
    // map.set("key", "new value")
    // list[2] = 3
    // person.name.firstName = "C.S."
    // person.name.firstName = "C.S.1"
}

function test_1(): void {
    let i: IObservableValue<number> = observable.box(4)
    console.info("before autorun", i.get())

    autorun((r: IReactionPublic) => {
        console.info("during autorun", i.get())
    })

    for (let a: number = 0; a < 5; a++) {
        i.set(a)
    }
}

function test_2(): void {
    class TestCls {
        @observable.ref arr: number[]
        constructor(arr: number[]) {
            this.arr = arr
        }
    }

    let t: TestCls = new TestCls([0, 1, 2, 3, 4, 5])
    let t1: TestCls = new TestCls([])
    console.info("before autorun", t.arr)
    autorun((r: IReactionPublic) => {
        t1.arr = t.arr.map(el => el + 1)
        console.info("during autorun", t1.arr)
    })
    t.arr = [0, 2, 4]
}

function main(): void {
    test_1()
}
main()