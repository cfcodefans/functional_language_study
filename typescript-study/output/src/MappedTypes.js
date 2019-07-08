"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const readOnlyCat = { name: "tom", age: 2 };
const readOnlyCat2 = { name: "jack", age: 4 };
// readOnlyCat.age++
const partialCat = { name: "peter" };
partialCat.age = 4;
const partialReadOnlyCat = { name: "john" };
// partialReadOnlyCat.age = 5
const nullableCat = { name: "nick", age: null }; // can't omit age
// const proxyCat: ProxiedCat = { name: "prank", age: 4 }
