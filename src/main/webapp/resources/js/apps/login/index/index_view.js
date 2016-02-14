define(["app",
        "text!apps/login/index/templates/index.html"],
          function(CDSCeunes, indexTpl) {
  CDSCeunes.module("LoginApp.Index.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
    View.Layout = Marionette.LayoutView.extend({
      template: _.template(indexTpl)
      /*events: {

      }*/

    });
  });


  return CDSCeunes.LoginApp.Index.View;
}); 