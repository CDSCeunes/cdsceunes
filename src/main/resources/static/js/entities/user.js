define(["app", "q"], function(CDSCeunes, Q) {
  CDSCeunes.module("Entities", function(Entities, CDSCeunes, Backbone, Marionette, $, _) {
    Entities.User = Backbone.Model.extend({
      urlRoot: "api/v1/auth",

      defaults: {
        login: "",
        password: ""
      }
    });

    var API = {
      authenticate: function(user, password) {
                console.log("saving");
        var user = new Entities.User({ login: user, password: password });
                console.log("saving2");
        return Q.promise(function(resolve) {
          user.save({
            success: function(data) {
              resolve(data);
            },
            error: function(data) {
              resolve(undefined);
            }
          });
        });

      }
    }

    CDSCeunes.reqres.setHandler("user:auth:new", function(user, pass) {
      return API.authenticate(user, pass);
    });
  });
  return ;

});
