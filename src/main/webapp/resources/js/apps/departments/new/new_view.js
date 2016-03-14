define(["app", "apps/departments/common/views"], function(CDSCeunes, CommonViews) {
  CDSCeunes.module("DepartmentsApp.New.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
    View.Department = CommonViews.Form.extend({
      title: "Novo Departamento",

      onRender: function() {
        this.$(".js-submit-department").text("Enviar");
      }
    });
  });

  return CDSCeunes.DepartmentsApp.New.View;
});
