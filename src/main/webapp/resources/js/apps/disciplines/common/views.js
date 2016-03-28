define(["app", "handlebars", "text!apps/disciplines/common/templates/form.hbs", "backbone.syphon", "jquery-ui"],
  function(CDSCeunes, Handlebars, formTpl) {
    CDSCeunes.module("DisciplinesApp.Common.Views", function(Views, CDSCeunes, Backbone, Marionette, $, _) {
      Views.Form = Marionette.ItemView.extend({
        template: Handlebars.compile(formTpl),

        ui: {
          "submitData": ".js-submit-discipline"
        },

        events: {
          "click @ui.submitData": "submitData"
        },

        submitData: function(e) {
          e.preventDefault();
          var data = Backbone.Syphon.serialize(this);
          this.trigger("discipline:form:submit", data);
        }
      });
    });
    return CDSCeunes.DisciplinesApp.Common.Views;
  });
