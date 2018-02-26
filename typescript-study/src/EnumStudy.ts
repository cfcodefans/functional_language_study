import * as console from 'console';
import { suite, test, slow, timeout, skip } from "mocha-typescript"
import { expect } from "chai"

import { tell } from './utils';

@suite class EnumStudy {
    @test tryEnum(): void {
        enum Orientation {
            EAST, NORTH, WEST, SOUTH
        }
        console.info(Orientation)
        console.info(Orientation[0])

        enum Month {
            Janurary, February, March, Apail, May, June, July, August, September, October, November, December
        }
        type SpringMonth = Month.Janurary | Month.February | Month.March
    }
}