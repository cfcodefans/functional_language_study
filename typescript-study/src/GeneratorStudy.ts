import { suite, test, slow, timeout, skip } from "mocha-typescript"
import { expect } from "chai"

// console.info(iterator.next())
// console.info(iterator.next())

// const it2: Iterator<number> = iAmAGenerator()
// console.info(it2.next())
// console.info(it2.next())
// console.info(it2.next())
// console.info(it2.next())

// @suite 
class GenStudy {
    // @skip
    // @test
    tryYield(): void {
        function* iAmAGenerator(): Iterator<number> {
            for (let i: number = 0; i < 10; i++) {
                console.info("\nbefore yield", i)
                yield i
                console.info("after yield", i)
            }
        }
        const iterator = iAmAGenerator()

        console.info(iterator.next())
        console.info(iterator.next())
        console.info(iterator.next())
        console.info(iterator.return(5))
        console.info(iterator.next())
    }

    // @test
    tryInside(): void {
        function* insider(): Iterator<number> {
            for (let i: number = 0; i < 10; i++) {
                try {
                    console.info("\nbefore yield", i)
                    const inside = yield i
                    console.info("after yield", i, inside)
                } catch(e) {
                    console.error(e)
                }
            }
        }
        const it = insider()

        console.info(it.next(10))
        console.info(it.next(20))
        // console.info(iterator.next(10))
        console.info(it.next(30))
        console.info(it.next(40))
    }
}

new GenStudy().tryYield()