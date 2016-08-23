System.config({
  transpiler: "typescript",
  typescriptOptions: {emitDecoratorMetadata: true},
  map: {
    "app": "app",
    "rxjs": "//code.angularjs.org/2.0.0-beta.5/",

    "@angular/core": "//code.angularjs.org/2.0.0-beta.5/",
    "@angular/common": "//code.angularjs.org/2.0.0-beta.5/",
      "@angular/compiler": "//code.angularjs.org/2.0.0-beta.5/",
    "@angular/router": "//code.angularjs.org/2.0.0-beta.5/",
    "@angular/platform-browser": "//code.angularjs.org/2.0.0-beta.5/",
    "@angular/platform-browser-dynamic": "//code.angularjs.org/2.0.0-beta.5/" 
  },
  packages: {
    'app' : {main: 'main.ts', defaultExtension: 'ts'},
    "rxjs": {main: "Rx.js"},
    "@angular/core": {main: "angular2.js"},
    "@angular/common": {main: "angular2.js"},
    "@angular/compiler": {main: "angular2.js"},
    "@angular/router": {main: "angular2.js"},
    "@angular/platform-browser": {main: "angular2.js"},
    "@angular/platform-browser-dynamic": {main: "angular2.js"},
  }
});
