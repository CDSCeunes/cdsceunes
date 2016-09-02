define(["app","q"], function(CDSCeunes, Q) {
  CDSCeunes.module("Entities", function(Entities, CDSCeunes, Backbone, Marionette, $, _) {
    Entities.Distribution = Backbone.Model.extend({
      urlRoot: "/api/v1/scenarios",

      defaults: {

      },

      initialize: function() {
        this.bind("change", function() {
          this.save();
        });
      }
    });

    Entities.DistributionCollection = Backbone.Collection.extend({
      url: "/api/v1/scenarios",
      model: Entities.Distribution
    });

    var API = {
      getDistributionEntity: function(distributionId) {
        var distribution = new Entities.Distribution({ id: distributionId });
        return Q.promise(function(resolve) {
          distribution.fetch({
            success: function(data) {
              resolve(data);
            },
            error: function(data) {
              resolve(undefined);
            }
          });
        });
      },
      getDistributionsEntities: function() {
        var distributions = new Entities.DistributionsCollection();
        return Q.promise(function(resolve) {
          distributions.fetch({
            success: function(data) {
              resolve(data);
            }
          });
        });
      }
    };

    CDSCeunes.reqres.setHandler("distribution:entity", function(id) {
      return API.getDistributionEntity(id);
    });

    CDSCeunes.reqres.setHandler("distribution:entities", function() {
      return API.getDistributionsEntities();
    });

    CDSCeunes.reqres.setHandler("distribution:entity:new", function(id) {
      return new Entities.Distribution();
    });

  });
  return ;
});
