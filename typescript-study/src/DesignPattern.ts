

class Singleton {
    private static _instance: Singleton
    private constructor() {
        console.info("Singleton.constructor")
    }
    public static instance(): Singleton {
        return this._instance || (this._instance = new Singleton)
    }
}

// let s:Singleton = new Singleton()
let s: Singleton = Singleton.instance()