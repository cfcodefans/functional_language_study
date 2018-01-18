import * as wp from 'webpack'

const config: wp.Configuration = {
    entry: "./app/main.ts",
    output: {
        filename: "main.js", path: __dirname + "/target", publicPath: "assets"
    },
    target: "node",
    resolve: {
        extensions: ['.ts', '.js']
    }, module: {
        rules: [
            { test: /\.ts$/, use: 'ts-loader', exclude: /node_modules/ }
        ]
    }, node: {
        fs: "empty", net: "empty", tls: "empty"
    }, externals: {
        "lodash": "_"
    }
}

export default config
