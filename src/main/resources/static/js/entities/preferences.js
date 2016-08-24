define(["app", "q"], function(CDSCeunes, Q) {
  CDSCeunes.module("Entities", function(Entities, CDSCeunes, Backbone, Marionette, $, _) {
    Entities.Preference = Backbone.Model.extend({
      urlRoot: "/api/v1/preferences"
    });

    Entities.PreferencesCollection = Backbone.Collection.extend({
      url: "/api/v1/preferences",
      model: Entities.Preference,
      comparator: function(pref1, pref2) {
        var disc1 = pref1.get("discipline").name;
        var disc2 = pref2.get("discipline").name;
        if (disc1 == disc2) {
          var val1 = pref1.get("preference");
          var val2 = pref2.get("preference");
          if (val1 < val2) return -1;
          else if (val1 > val2) return 1;
          else {
            var name1 = pref1.get("teacher").name;
            var name2 = pref2.get("teacher").name;
            if (name1 < name2) return -1;
            else if (name1 > name2) return 1;
            else return 0;
          }
        } else {
          if (disc1 < disc2) return -1;
          else return 1;
        }
      }
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