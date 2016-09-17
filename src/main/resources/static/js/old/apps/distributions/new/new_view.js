define(["app", "apps/distributions/common/views"], function(CDSCeunes, CommonViews) {
  CDSCeunes.module("DistributionsApp.New.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
    View.Distribution = CommonViews.Form.extend({
      title: "Nova Distribuição",

      onRender: function() {
        this.$(".js-submit-distribution").text("Enviar");
      }
    });
  });

  return CDSCeunes.DistributionsApp.New.View;
});
