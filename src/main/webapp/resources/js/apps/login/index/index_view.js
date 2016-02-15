define(["app",
        "handlebars",
        "text!apps/login/index/templates/index.hbs"],
          function(CDSCeunes, Handlebars, indexTpl) {
  CDSCeunes.module("LoginApp.Index.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
    View.Layout = Marionette.LayoutView.extend({
      template: Handlebars.compile(indexTpl)
      /*events: {

      }*/

    });
  });


  return CDSCeunes.LoginApp.Index.View;
}); 