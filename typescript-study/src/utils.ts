import { expect } from "chai"

export function tell(statement: string, expected?: any, msg?: string): void {
    let re: any = null
    try {
        re = eval(statement)
    } catch (e) {
        console.error(e)
        re = e
    }
    console.info([statement, "=", "" + JSON.stringify(re)].join("\t"))
}

export default tell