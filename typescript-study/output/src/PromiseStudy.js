var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
function sleep(ms, name) {
    return __awaiter(this, void 0, void 0, function* () {
        return new Promise(done => setTimeout(() => {
            while (true) {
                console.info(name + " after sleep for " + ms);
                task1("4");
            }
            done();
        }, ms));
    });
}
function sendRequest(name) {
    return __awaiter(this, void 0, void 0, function* () {
        console.log('Request sent.');
        yield sleep(3000, name);
        console.log('request finished.');
        return "response";
    });
}
function task1(name) {
    return __awaiter(this, void 0, void 0, function* () {
        // await sleep(3000)
        // console.info("sleep(3000)")
        const request = sendRequest(name);
        console.log("after sending request");
        // Doing some stuff
        const response = yield request;
        console.log('Get response: ' + response);
    });
}
function main() {
    return __awaiter(this, void 0, void 0, function* () {
        Promise.all([
            task1("1"),
            task1("2"),
            task1("3")
        ]).then((v) => console.info(v));
        // while (true) {
        //     console.info('\t main')
        // }
        return 1;
    });
}
main().then(() => {
});
