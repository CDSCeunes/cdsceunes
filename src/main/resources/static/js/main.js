requirejs.config({
  baseUrl: "js/",
  paths: {
    lodash: "vendor/lodash",
    underscore: "vendor/underscore",
    q: "vendor/q",
    "backbone.babysitter": "vendor/backbone.babysitter",
    "backbone.wreqr": "vendor/backbone.wreqr",
    "backbone.syphon": "vendor/backbone.syphon",
    backbone: "vendor/backbone",
    handlebars: "vendor/handlebars.amd",
    jquery: "vendor/jquery-2.2.0",
    "jquery-ui": "vendor/jquery-ui",
    json2: "vendor/json2",
    localstorage: "vendor/backbone.localStorage",
    marionette: "vendor/backbone.marionette",
    text: "vendor/text",
    foundation: "vendor/foundation.min",
    "coffee-script": "vendor/coffee-script",
    cs: "vendor/cs",
    lockr: "vendor/lockr"
  },

  shim: {
    lodash: {
      exports: "_"
    },
    backbone: {
      deps: ["jquery", "lodash", "json2"],
      exports: "Backbone"
    },
    marionette: {
      deps: ["backbone"],
      exports: "Marionette"
    },
    "jquery-ui": ["jquery"],
    localstorage: ["backbone"],
    handlebars: {
      exports: "handlebars"
    },
    foundation: {
      dep: ["jquery"]
    }
  },
  packages: [
    {
      name: 'cs',
      location: '.',
      main: 'vendor/cs'
    },
    {
      name: 'coffee-script',
      location: '.',
      main: 'vendor/coffee-script'
    }
  ]
});


require(["cs!app", "cs!apps/login/login_app", "cs!apps/teachers/teachers_app", "cs!apps/preferences/preferences_app",
  "cs!apps/departments/departments_app", "cs!apps/disciplines/disciplines_app",
  "cs!apps/positions/positions_app", "cs!apps/distributions/distributions_app"], function(CDSCeunes){
    CDSCeunes.start();
});
