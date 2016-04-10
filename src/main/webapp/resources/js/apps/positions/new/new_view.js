define(["app", "apps/positions/common/views"], function(CDSCeunes, CommonViews) {
  CDSCeunes.module("PositionsApp.New.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
    View.Positions = CommonViews.Form.extend({
      title: "Novo cargo",

      onRender: function() {
        this.$(".js-submit-position").text("Enviar");
      }
    });
  });

  return CDSCeunes.PositionsApp.New.View;
});
