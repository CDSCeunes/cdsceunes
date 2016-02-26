define(["app", "apps/departments/common/views"], function(CDSCeunes, CommonViews) {
  CDSCeunes.module("TeachersApp.New.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
    View.Teacher = CommonViews.Form.extend({
      title: "Novo Departamento",

      onRender: function() {
        this.$(".js-submit-department").text("Enviar");
      }
    });
  });

  return CDSCeunes.DepartmentsApp.New.View;
});