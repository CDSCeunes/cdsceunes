define(["app","apps/header/list/list_controller"], function(CDSCeunes, HeaderController) {
  CDSCeunes.module("HeaderApp", function(Header, CDSCeunes, Backbone, Marionette, $, _) {
    var API = {
      listHeader: function() {
        HeaderController.listHeader();
      }
    };

    CDSCeunes.commands.setHandler("set:active:header", function(name) {
      HeaderController.setActiveHeader(name);
    });

    Header.on("start", function() {
      console.log("header inicializado");
      API.listHeader();
    });

  });
  return CDSCeunes.HeaderApp;
});