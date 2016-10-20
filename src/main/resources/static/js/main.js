requirejs.config({
  baseUrl: "js/",
  paths: {
    underscore: "vendor/underscore",
    "backbone.radio": "vendor/backbone.radio",
    "backbone.syphon": "vendor/backbone.syphon",
    backbone: "vendor/backbone",
    handlebars: "vendor/handlebars.amd",
    nunjucks: "vendor/nunjucks",
    jquery: "vendor/jquery-2.2.0",
    "jquery-ui": "vendor/jquery-ui",
    json2: "vendor/json2",
    localstorage: "vendor/backbone.localStorage",
    marionette: "vendor/backbone.marionette",
    text: "vendor/text",
    foundation: "vendor/foundation.min",
    "coffee-script": "vendor/coffee-script",
    cs: "vendor/cs",
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
      deps: ["backbone", "backbone.radio"],
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


require([
  "cs!app",
  "cs!apps/routers/login_router",
  "cs!apps/routers/teacher_router",
  "cs!apps/routers/distribution_router",
  "cs!apps/routers/department_router"
], function(CDSCeunes){
  CDSCeunes.start();
});
