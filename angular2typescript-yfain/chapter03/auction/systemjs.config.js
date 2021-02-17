System.config({
	transpiler: "typescript",
	typescriptOptions: {emitDecoratorMetadata: true},
	map: {
		"app": "app",
		"rxjs": "//unpkg.com/rxjs@5.0.0-beta.6",

		"@angular/core": "//unpkg.com/@angular/core@2.0.0-rc.4",
		"@angular/common": "//unpkg.com/@angular/common@2.0.0-rc.4",
		"@angular/compiler": "//unpkg.com/@angular/compiler@2.0.0-rc.4",
		"@angular/router": "//unpkg.com/@angular/router@3.0.0-alpha.7",
		"@angular/platform-browser": "//unpkg.com/@angular/platform-browser@2.0.0-rc.4",
		"@angular/platform-browser-dynamic": "//unpkg.com/@angular/platform-browser-dynamic@2.0.0-rc.4" 
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