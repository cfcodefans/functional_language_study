import tsUnit = require("../../node_modules/tsunit.external/tsUnit");
import restful = require("./restful")

export class ClassTests extends tsUnit.TestClass {
    testParams() {
        let ia: number[] = [1, 3, 5];
        let params: restful.IParam[] = [new restful.Param('a', 'a', restful.Source.QUERY)];
    }

    testMethodReassign() {
        class Bar {
            foo() {
                console.info("foo");
            }
        }

        let bar: Bar = new Bar();
        bar.foo = () => { console.info("no"); }
        bar.foo();

        interface IBar {
            value: number;
            foo();
        }
        let _bar: IBar = <IBar> {
            value: 10,
        }
        _bar.foo = () => { console.info("no"); }
        _bar.foo();
    }


}

