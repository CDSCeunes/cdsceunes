define(["app", "apps/teachers/common/views"], function(CDSCeunes, CommonViews) {
  CDSCeunes.module("TeachersApp.New.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
    View.Teacher = CommonViews.Form.extend({
      title: "Novo Professor",

      onRender: function() {
        this.$(".js-submit-teacher").text("Enviar");
      }
    });
  });

  return CDSCeunes.TeachersApp.New.View;
});