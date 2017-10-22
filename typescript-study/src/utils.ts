import { expect } from "chai"

export function tryIt(statement: string, expected?: any, msg?: string): void {
    let re: any = null
    try {
        re = eval(statement)
    } catch (e) {
        console.error(e)
        re = e
    }
    // console.info([statement, re, expected || "", msg || `${statement} = ${re}`].join("\t"))
    console.info([statement, "=", "" + JSON.stringify(re)].join("\t"))
    // expect(re).to.eq(expected, msg || `${statement} eq ${re}`)
}