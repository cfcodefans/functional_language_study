"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function tell(statement, expected, msg) {
    let re = null;
    try {
        re = eval(statement);
    }
    catch (e) {
        console.error(e);
        re = e;
    }
    console.info([statement, "=", "" + JSON.stringify(re)].join("\t"));
}
exports.tell = tell;
exports.default = tell;
