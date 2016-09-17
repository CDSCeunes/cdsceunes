define(["app", "apps/login/index/index_view", "q"], function(CDSCeunes, View, Q) {
  CDSCeunes.module("LoginApp.Index", function(Index, CDSCeunes, Backbone, Marionette, $, _) {
    Index.Controller = {
      loginHome: function() {
        var loginView = new View.Layout();

        loginView.on("childview:login:home", function() {});

        loginView.on("login:auth", function(user, pass) {
          console.log("testessss");
          $.ajax({
            type: "POST",
            url: "/login",
            contentType: "application/x-www-form-urlencoded",
            data: {
              username: user,
              password: pass
            }
          }).done(function(res, status, xhr) {
            console.log(res);
            console.log(status);
            console.log(xhr.getResponseHeader("X-AUTH-TOKEN"));
            window.token = xhr.getResponseHeader("X-AUTH-TOKEN");
            $.ajaxSetup({
              headers: {
                "X-AUTH-TOKEN": window.token
              }
            });
            CDSCeunes.trigger("teachers:list");
          });

          console.log("triggers");
          console.log(user);
          console.log(pass);
        });
        CDSCeunes.regions.main.show(loginView);
      }
    }
  });
  return CDSCeunes.LoginApp.Index.Controller;
});
