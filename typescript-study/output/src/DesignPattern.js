class Singleton {
    constructor() {
        console.info("Singleton.constructor");
    }
    static instance() {
        return this._instance || (this._instance = new Singleton);
    }
}
// let s:Singleton = new Singleton()
let s = Singleton.instance();
