import { observable, autorun, IReactionDisposer, IReactionPublic, Reaction } from "mobx"
import { trace } from "console";

function main() {
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
main()