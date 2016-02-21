define(["app"], function(CDSCeunes) {
	CDSCeunes.module("Routers.LoginApp", function(LoginApp, CDSCeunes, Backbone, Marionette, $, _) {
    LoginApp.Router = Marionette.AppRouter.extend({
      appRoutes: {
        "login" : "loginHome"
      }
    });

    var executeAction = function(action, arg) {
      CDSCeunes.startSubApp("LoginApp");
      action(arg);
      //CDSCeunes.execute()
    }

    var API = {
      loginHome: function() {
        require(["apps/login/index/index_controller"], function(Controller) {
          CDSCeunes.startSubApp("LoginApp");
          Controller.loginHome();
        });
      }
    };

    CDSCeunes.on("login:home", function() {
      CDSCeunes.navigate("login");
      API.loginHome();
    });

    CDSCeunes.Routers.on("start", function() {
      console.log("Iniciado routers");
      new LoginApp.Router({
        controller: API
      });
    });


  });

  return CDSCeunes.LoginApp;
});