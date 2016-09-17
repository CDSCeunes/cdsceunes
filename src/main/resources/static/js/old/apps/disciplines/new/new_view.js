define(["app", "apps/disciplines/common/views"], function(CDSCeunes, CommonViews) {
  CDSCeunes.module("DisciplinesApp.New.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
    View.Discipline = CommonViews.Form.extend({
      title: "Nova disciplina",

      onRender: function() {
        this.$(".js-submit-discipline").text("Enviar");
      }
    });
  });
  return CDSCeunes.DisciplinesApp.New.View;
});
