define(["app", "apps/disciplines/common/views"], function(CDSCeunes, CommonViews) {
  CDSCeunes.module("DisciplinesApp.Edit.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
    View.Discipline = CommonViews.Form.extend({
      title: "Editar disciplina",
      onRender: function() {
        this.$(".js-submit-discipline").text("Atualizar");
      },

    });
  });
  return CDSCeunes.DisciplinesApp.Edit.View;
});
