requirejs.config({
  baseUrl: "resources/js",
  paths: {
    underscore: "vendor/underscore",
    lodash: "vendor/lodash",
    "backbone.babysitter": "vendor/backbone.babysitter",
    "backbone.wreqr": "vendor/backbone.wreqr",
    backbone: "vendor/backbone",
    jquery: "vendor/jquery-2.2.0",
    "jquery-ui": "vendor/jquery-ui",
    json2: "vendor/json2",
    localstorage: "vendor/backbone.localStorage",
    marionette: "vendor/backbone.marionette",
    text: "vendor/text"
  },

  shim: {
    underscore: {
      exports: "_"
    },
    backbone: {
      deps: ["jquery", "underscore", "json2"],
      exports: "Backbone"
    },
    marionette: {
      deps: ["backbone"],
      exports: "Marionette"
    },
    "jquery-ui": ["jquery"],
    localstorage: ["backbone"]
  }
});

require(["app", "apps/login/login_app"], function(CDSCeunes){
  CDSCeunes.start();
});