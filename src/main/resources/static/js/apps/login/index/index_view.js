define(["app",
        "handlebars",
        "text!apps/login/index/templates/index.hbs"],
          function(CDSCeunes, Handlebars, indexTpl) {
  CDSCeunes.module("LoginApp.Index.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
    View.Layout = Marionette.LayoutView.extend({
      template: Handlebars.compile(indexTpl),
      events: {
        "submit form": "submit"
      },
      submit: function(e) {
        e.preventDefault();
        console.log("t from submit fn");
        var props = document.getElementsByTagName("input");
        var user = props.login.value;
        var password = props.password.value;
        this.triggerMethod("login:auth", user, password);
      }
      /*events: {

      }*/

    });
  });


  return CDSCeunes.LoginApp.Index.View;
});
