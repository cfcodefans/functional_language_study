System.config({
	transpiler: "typescript",
	typescriptOptions: {emitDecoratorMetadata: true},
	map: {
		"app": "app",
		"rxjs": "//npmcdn.com/rxjs@5.0.0-beta.6",

		"@angular/core": "//npmcdn.com/@angular/core@2.0.0-rc.4",
		"@angular/common": "//npmcdn.com/@angular/common@2.0.0-rc.4",
		"@angular/compiler": "//npmcdn.com/@angular/compiler@2.0.0-rc.4",
		"@angular/router": "//npmcdn.com/@angular/router@3.0.0-alpha.7",
		"@angular/platform-browser": "//npmcdn.com/@angular/platform-browser@2.0.0-rc.4",
		"@angular/platform-browser-dynamic": "//npmcdn.com/@angular/platform-browser-dynamic@2.0.0-rc.4" 
	},
	packages: {
		'app' : {main: 'main.ts', defaultExtension: 'ts'},
		"rxjs": {main: "index.js"},
		"@angular/core": {main: "index.js"},
		"@angular/common": {main: "index.js"},
		"@angular/compiler": {main: "index.js"},
		"@angular/router": {main: "index.js"},
		"@angular/platform-browser": {main: "index.js"},
		"@angular/platform-browser-dynamic": {main: "index.js"},
	}
});