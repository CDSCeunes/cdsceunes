define(["app", "apps/teachers/common/views"], function(CDSCeunes, CommonViews) {
  CDSCeunes.module("TeachersApp.Edit.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
    View.Teacher = CommonViews.Form.extend({
      title: "Editar professor",

      onRender: function() {
        this.$(".js-submit-teacher").text("Atualizar");
      },

      templateHelpers: {
        isDepartment: function(id) {
          return this.model.department.id === id;
        }
      }
    });
  });
  return CDSCeunes.TeachersApp.Edit.View;
});