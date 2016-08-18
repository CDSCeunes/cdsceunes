define(["app", "apps/login/index/index_view", "q"], function(CDSCeunes, View, Q) {
  CDSCeunes.module("LoginApp.Index", function(Index, CDSCeunes, Backbone, Marionette, $, _) {
    Index.Controller = {
      loginHome: function() {
        var loginView = new View.Layout();

        loginView.on("childview:login:home", function() {});

        loginView.on("login:auth", function(user, pass) {
          require(["entities/user"], function() {
            var user = CDSCeunes.request()
            Q.all(CDSCeunes.request("user:auth:new", user, pass)).then(function() {

            });
          });
          console.log("trigger");
          console.log(user);
          console.log(pass);

        });

        CDSCeunes.regions.main.show(loginView);
      }
    }
  });
  return CDSCeunes.LoginApp.Index.Controller;
});
