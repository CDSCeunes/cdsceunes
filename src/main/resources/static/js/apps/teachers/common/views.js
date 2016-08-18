define(["app",
        "handlebars",
        "text!apps/teachers/common/templates/form.hbs",
        "backbone.syphon",
        "jquery-ui"],
      function(CDSCeunes, Handlebars, formTpl) {
  CDSCeunes.module("TeachersApp.Common.Views", function(Views, CDSCeunes, Backbone, Marionette, $, _) {
    Views.Form = Marionette.LayoutView.extend({
      template: Handlebars.compile(formTpl),

      ui: {
        "admissionDate": ".datepicker",
        "submitData": ".js-submit-teacher"
      },

      events: {
        "click @ui.submitData": "submitData"
      },

      serializeData: function() {
        return {
          model: this.model.toJSON(),
          departments: this.options.departments.toJSON()
        }
      },

      onShow: function() {
        this.ui.admissionDate.datepicker({
          dateFormat: "yy-mm-dd",
          defaultDate: 0,
          showAnim: "drop"
        });
      },

      submitData: function(e) {
        e.preventDefault();
        var data = Backbone.Syphon.serialize(this);
        console.log(data);
        this.trigger("teacher:form:submit", data);
      }
    });

  });

  return CDSCeunes.TeachersApp.Common.Views;
});
