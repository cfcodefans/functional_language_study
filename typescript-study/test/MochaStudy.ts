import { suite, test, slow, timeout } from "mocha-typescript"
import { expect } from "chai"

@suite class Hello {
    @test world() {
        expect(1).not.to.equal(2, "Expected one not to equal two.")
        expect(1).to.equal(2, "Expected one to equal two.")
    }
}