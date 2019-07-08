"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
const mobx_1 = require("mobx");
function _test() {
    const map = mobx_1.observable.map({ key: "value" });
    const list = mobx_1.observable([1, 2, 4]);
    const person = mobx_1.observable({
        name: {
            firstName: "Clive Staples",
            lastName: "Lewis"
        },
        age: 36
    });
    console.info("type of person", typeof (person), JSON.stringify(person));
    const temperature = mobx_1.observable.box(20);
    let count = 0;
    function _react(r) {
        console.info(count, "_react", person, "\n");
        // trace()
        // count++
        // if (count > 2)
        //     r.dispose()
    }
    function __react(r) {
        console.info(count, "__react", person.age, "\n");
        // trace()
        // count++
        // if (count > 2)
        //     r.dispose()
    }
    let ird = mobx_1.autorun(_react);
    mobx_1.autorun(__react);
    console.info("type of person", typeof (person), JSON.stringify(person));
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
    person.age = 37;
    // map.set("key", "new value")
    // list[2] = 3
    // person.name.firstName = "C.S."
    // person.name.firstName = "C.S.1"
}
function test_1() {
    let i = mobx_1.observable.box(4);
    console.info("before autorun", i.get());
    mobx_1.autorun((r) => {
        console.info("during autorun", i.get());
    });
    for (let a = 0; a < 5; a++) {
        i.set(a);
    }
}
function test_2() {
    class TestCls {
        constructor(arr) {
            this.arr = arr;
        }
    }
    __decorate([
        mobx_1.observable.ref
    ], TestCls.prototype, "arr", void 0);
    let t = new TestCls([0, 1, 2, 3, 4, 5]);
    let t1 = new TestCls([]);
    console.info("before autorun", t.arr);
    mobx_1.autorun((r) => {
        t1.arr = t.arr.map(el => el + 1);
        console.info("during autorun", t1.arr);
    });
    t.arr = [0, 2, 4];
}
function main() {
    test_1();
}
main();
