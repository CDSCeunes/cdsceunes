define(["app",
        "handlebars",
        "text!apps/preferences/list/templates/layout.hbs",
        "text!apps/preferences/list/templates/panel.hbs",
        "text!apps/preferences/list/templates/discipline_layout.hbs",
        "text!apps/preferences/list/templates/discipline_view.hbs",
        "text!apps/preferences/list/templates/discipline_item.hbs"],
  function(CDSCeunes, Handlebars, layoutTpl, panelTpl, disciplineLayoutTpl, disciplineViewTpl, disciplineItemTpl) {
  CDSCeunes.module("PreferencesApp.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
    
    View.Layout = Marionette.LayoutView.extend({
      template: Handlebars.compile(layoutTpl),

      regions: {
        panel: "#panel-region",
        main: "#preferences-region"
      }
    });

    View.Panel = Marionette.ItemView.extend({
      template: Handlebars.compile(panelTpl)
    });

    View.DisciplinesContainer = Marionette.CompositeView.extend({
      template: Handlebars.compile(disciplineLayoutTpl),
      childView: View.Discipline,
      childViewContainer: "#disciplines-container"
    });

    View.Discipline = Marionette.CompositeView.extend({
      template: Handlebars.compile(disciplineViewTpl),
      childView: View.Preferences
    });

    View.Preferences = Marionette.ItemView.extend({
      template: Handlebars.compile(disciplineItemTpl),
    });
  });

  return CDSCeunes.PreferencesApp.View;
});