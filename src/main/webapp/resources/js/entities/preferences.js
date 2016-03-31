define(["app", "q"], function(CDSCeunes, Q) {
  CDSCeunes.module("Entities", function(Entities, CDSCeunes, Backbone, Marionette, $, _) {
    Entities.Preference = Backbone.Model.extend({
      urlRoot: "preferences"
    });

    Entities.PreferencesCollection = Backbone.Collection.extend({
      url: "preferences",
      model: Entities.Preference,
      comparator: "teacher.name"
    });


    var API = {
      getPreferences: function() {
        var preferences = new Entities.PreferencesCollection();
        return Q.promise(function(resolve) {
          preferences.fetch({
            success: function(data) {
              resolve(data);
            }
          });
        });
      },

      getPreference: function(id) {
        var preference = new Entities.Preference({id: id});
        return Q.promise(function(resolve) {
          preference.fetch({
            success: function(data) {
              resolve(data);
            },
            error: function(data) {
              resolve(undefined);
            }
          });
        });
      }
    };

    CDSCeunes.reqres.setHandler("preference:entity", function(id) {
      return API.getPreference(id);
    });

    CDSCeunes.reqres.setHandler("preference:entities", function() {
      return API.getPreferences();
    });

    CDSCeunes.reqres.setHandler("preference:entity:new", function(id) {
      return new Entities.Preference();
    });

  });


  return ;
});