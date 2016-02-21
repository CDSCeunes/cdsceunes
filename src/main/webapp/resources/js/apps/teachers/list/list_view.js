define(["app",
        "handlebars",
        "text!apps/teachers/list/templates/layout.hbs",
        "text!apps/teachers/list/templates/panel.hbs",
        "text!apps/teachers/list/templates/list.hbs",
        "text!apps/teachers/list/templates/list_item.hbs"],
          function(CDSCeunes, Handlebars, layoutTpl, panelTpl, listTpl, listItemTpl) {
  CDSCeunes.module("TeachersApp.List.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
    
    View.Layout = Marionette.LayoutView.extend({
      template: Handlebars.compile(layoutTpl),

      regions: {
        panelRegion: "#panel-region",
        teachersRegion: "#teachers-region"
      }

    });

    View.Panel = Marionette.ItemView.extend({
      template: Handlebars.compile(panelTpl)
    });

    View.Teacher = Marionette.ItemView.extend({
      className: "row",
      template: Handlebars.compile(listItemTpl)
    });

    View.Teachers = Marionette.CompositeView.extend({
      template: Handlebars.compile(listTpl),
      childView: View.Teacher,
      childViewContainer: "#list-item-teacher",


    });

  });

  return CDSCeunes.TeachersApp.List.View;
});