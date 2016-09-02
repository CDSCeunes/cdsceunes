define(["app", "handlebars", "text!apps/distributions/list/templates/layout.hbs", "text!apps/distributions/list/templates/panel.hbs",
  "text!apps/distributions/list/templates/list.hbs", "text!apps/distributions/list/templates/list_item.hbs"],
  function(CDSCeunes, Handlebars, layoutTpl, panelTpl, listTpl, listItemTpl) {
    CDSCeunes.module("DistributionsApp.List.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
      var LockEdit = false;

      View.Layout = Marionette.LayoutView.extend({
        template: Handlebars.compile(layoutTpl),

        regions: {
          panelRegion: "#panel-region",
          distributionsRegion: "#distributions-region"
        }
      });

      View.Panel = Marionette.ItemView.extend({
        template: Handlebars.compile(panelTpl),

        triggers: {
          //"click button.js-new-distribution": "distribution:new"
        }
      });

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
  return CDSCeunes.DistributionsApp.List.View;
});
