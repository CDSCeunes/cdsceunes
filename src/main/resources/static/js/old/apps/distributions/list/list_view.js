define(["app",
        "handlebars",
        "text!apps/distributions/list/templates/list.hbs",
        "text!apps/distributions/list/templates/list_item.hbs"],
  function(CDSCeunes, Handlebars, layoutTpl, panelTpl, listTpl, listItemTpl) {
    CDSCeunes.module("DistributionsApp.List.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
      var LockEdit = false;

      View.Distribution = Marionette.ItemView.extend({
        className: "row",
        template: Handlebars.compile(listItemTpl),
        triggers: {
          "click a.js-edit-distribution": "distribution:edit",
          "click a.js-show-distribution": "distribution:show",
          "click button.js-delete-distribution": "distribution:delete"
        },

        modelEvents: {
          "change": "fieldChanged"
        },

      });

      View.Distributions = Marionette.CompositeView.extend({
        template: Handlebars.compile(listTpl),
        childView: View.Distribution,
        childViewContainer: "#list-item-distribution",
      });
  });
            console.log("-1");
  return CDSCeunes.DistributionsApp.List.View;
});
