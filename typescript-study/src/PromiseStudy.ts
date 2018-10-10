

async function sleep(ms: number, name: string) {
    return new Promise(done => setTimeout(
        () => {
            while (true) {
                console.info(name + " after sleep for " + ms); 
                task1("4")
            } done()
        }
        ,
        ms))
}

async function sendRequest(name: string) {
    console.log('Request sent.');
    await sleep(3000, name);
    console.log('request finished.');
    return "response";
}

async function task1(name: string) {
    // await sleep(3000)
    // console.info("sleep(3000)")
    const request = sendRequest(name);
    console.log("after sending request");
    // Doing some stuff
    const response = await request;
    console.log('Get response: ' + response);
}

async function main() {

    Promise.all([
        task1("1"),
        task1("2"),
        task1("3")
    ]).then((v) => console.info(v))
    // while (true) {
    //     console.info('\t main')
    // }
    return 1;
}

main().then(() => {

})
