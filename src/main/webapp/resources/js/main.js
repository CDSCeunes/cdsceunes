requirejs.config({
  baseUrl: "resources/js",
  paths: {
    lodash: "vendor/lodash",
    underscore: "vendor/underscore",
    q: "vendor/q",
    "backbone.babysitter": "vendor/backbone.babysitter",
    "backbone.wreqr": "vendor/backbone.wreqr",
    backbone: "vendor/backbone",
    handlebars: "vendor/handlebars.amd",
    jquery: "vendor/jquery-2.2.0",
    "jquery-ui": "vendor/jquery-ui",
    json2: "vendor/json2",
    localstorage: "vendor/backbone.localStorage",
    marionette: "vendor/backbone.marionette",
    text: "vendor/text",
    foundation: "vendor/foundation.min"
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
  }
});

require(["app", "apps/login/login_app", "apps/teachers/teachers_app"], function(CDSCeunes){
  CDSCeunes.start();
});