(function(global) {
  const NG_PACKAGES = [
  "core",
  "common",
  "compiler",
  // "forms",
  "http",
  "router",
  "platform-browser",
  "platform-browser-dynamic",
    // "upgrade"
    ];

    const NG_VERSION = "2.0.0-rc.4";
    const RESPOSITORY = "//npmcdn.com";

    function MAP() {
      var map = {
        "app": "app",
        "rxjs": "//npmcdn.com/rxjs@5.0.0-beta.6"
      };

      NG_PACKAGES.forEach((_package) => map[`@angular/${_package}`] = `${RESPOSITORY}/@angular/${_package}@${NG_VERSION}`);
      map["@angular/router"] = `${RESPOSITORY}/@angular/router@3.0.0-alpha.7`;
      return map;
    }

    function PACKAGES() {
      var packages = {
        "app" : {main: "main.ts", defaultExtension: "ts"},
        "rxjs": {main: "index.js"},
      };
      NG_PACKAGES.forEach((_package) => packages[`@angular/${_package}`] = { main: 'index.js' });
      return packages;
    }

    var cfg = {
     transpiler: "typescript",
     typescriptOptions: {emitDecoratorMetadata: true},
     map: MAP(),
     packages: PACKAGES() 
   };
   System.config(cfg);
 })(this);
