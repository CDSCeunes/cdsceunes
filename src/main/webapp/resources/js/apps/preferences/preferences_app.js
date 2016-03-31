define(["app"], function(CDSCeunes) {
  CDSCeunes.module("Routers.PreferencesApp", function(PreferencesAppRouter, CDSCeunes, Backbone, Marionette, $, _) {
    PreferencesAppRouter.Router = Marionette.AppRouter.extend({
      appRoutes: {
        "preferences": "listPreferences"
      }
    });

    var executeAction = function(action, args) {
      CDSCeunes.startSubApp("PreferencesApp");
      action(args); 
    };

    var API = {
      listPreferences: function() {
        console.log("executando");
        require(["apps/preferences/list/list_controller"], function(ListController) {
          executeAction(ListController.listPreferences, undefined);
        });
      }
    };


    CDSCeunes.Routers.on("start", function() {
      console.log("Preferences Router");
      new PreferencesAppRouter.Router({
        controller: API
      });
    });

  });
});