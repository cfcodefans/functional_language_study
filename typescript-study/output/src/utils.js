"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function tryIt(statement, expected, msg) {
    let re = null;
    try {
        re = eval(statement);
    }
    catch (e) {
        console.error(e);
        re = e;
    }
    // console.info([statement, re, expected || "", msg || `${statement} = ${re}`].join("\t"))
    console.info([statement, "=", "" + re].join("\t"));
    // expect(re).to.eq(expected, msg || `${statement} eq ${re}`)
}
exports.tryIt = tryIt;
