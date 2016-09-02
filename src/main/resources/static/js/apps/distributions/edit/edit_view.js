define(["app", "apps/distributions/common/views"], function(CDSCeunes, CommonViews) {
  CDSCeunes.module("DistributionsApp.Edit.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
    View.Distribution = CommonViews.Form.extend({
      title: "Editar distribuição",

      onRender: function() {
        this.$(".js-submit-distribution").text("Atualizar");
      },

      templateHelpers: {
        isTeacher: function(id) {
          return this.model.teacher.id === id;
        },
        isDiscipline: function(id) {
          return this.model.discipline.id === id;
        }
      }
    });
  });
  return CDSCeunes.DistributionsApp.Edit.View;
});
