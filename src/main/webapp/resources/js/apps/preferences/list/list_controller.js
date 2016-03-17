define(["app", "apps/preferences/list/list_view", "q"], function(CDSCeunes, View, Q) {
  CDSCeunes.module("PreferencesApp.List", function(List, CDSCeunes, Backbone, Marionette, $, _) {
    List.Controller = {
     listPreferences: function() {
        var layoutView = new View.Layout();
        var panelView = new View.Panel();
        var disciplinesLayoutView;
        require(["entities/preferences"], function() {
          Q.all(CDSCeunes.request("preferences:entities"), function(preferences) {
            disciplinesLayoutView = new View.DisciplinesContainer({
              collection: preferences
            });
          });

          layoutView.on("show", function() {
            layoutView.main.show(disciplinesLayoutView);
          });
        });
      

      CDSCeunes.regions.main.show(layoutView);
      }
    };
  });

  return CDSCeunes.PreferencesApp.List.Controller;
});