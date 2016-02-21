define(["app", "apps/login/index/index_view"], function(CDSCeunes, View) {
  CDSCeunes.module("LoginApp.Index", function(Index, CDSCeunes, Backbone, Marionette, $, _) {
    Index.Controller = {
      loginHome: function() {
        var loginView = new View.Layout();

        loginView.on("childview:login:home", function() {});
      
        CDSCeunes.regions.main.show(loginView);
      }
    }
  });
  return CDSCeunes.LoginApp.Index.Controller;
});