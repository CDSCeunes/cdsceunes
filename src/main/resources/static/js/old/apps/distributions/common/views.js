define(["app",
        "handlebars",
        "text!apps/distributions/common/templates/form.hbs",
        "text!apps/distributions/common/templates/layout.hbs",
        "text!apps/distributions/common/templates/panel.hbs",
        "backbone.syphon", "jquery-ui"],
  function(CDSCeunes, Handlebars, formTpl, layoutTpl, panelTpl) {
    CDSCeunes.module("DistributionsApp.Common.Views", function(Views, CDSCeunes, Backbone, Marionette, $, _) {
      Views.Form = Marionette.ItemView.extend({
        template: Handlebars.compile(formTpl),

        ui: {
          "submitData": ".js-submit-distribution"
        },

        events: {
          "click @ui.submitData": "submitData"
        },

        serializeData: function() {
          return {
            model: this.model.toJSON(),
            teachers: this.options.teachers.toJSON(),
            disciplines: this.option.disciplines.toJSON()
          };
        },

        submitData: function(e) {
          e.preventDefault();
          var data = Backbone.Syphon.serialize(this);
          console.log(data);
          this.trigger("distribution:form:submit", data);
        }
      });

      Views.Layout = Marionette.LayoutView.extend({
        template: Handlebars.compile(layoutTpl),

        regions: {
          panelRegion: "#panel-region",
          distributionsRegion: "#distributions-region"
        }
      });

      Views.Panel = Marionette.ItemView.extend({
        template: Handlebars.compile(panelTpl),
      });
    });
    return CDSCeunes.DistributionsApp.Common.Views;
  });
