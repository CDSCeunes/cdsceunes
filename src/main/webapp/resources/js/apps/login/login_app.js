define(["app", "apps/login/index/index_view"], function(CDSCeunes, LoginView) {
	CDSCeunes.module("LoginApp", function(LoginApp, CDSCeunes, Backbone, Marionette, $, _) {
    LoginApp.Router = Marionette.AppRouter.extend({
      appRoutes: {
        "login" : "loginHome"
      }
    });

    var API = {
      loginHome: function() {
          LoginApp.regions.main.show(LoginView.Layout);
      }
    };

    LoginApp.onStart = function() {
      console.log("Iniciando login app");
    };

    /*CDSCeunes.Router.on("start", function() {
      console.log("Iniciado routers");
      new LoginApp.Router({
        controller: API
      });
    });*/

  });

  return CDSCeunes.LoginApp;
});