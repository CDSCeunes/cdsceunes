define(["app", "q"], function(CDSCeunes, Q) {
  CDSCeunes.module("Entities", function(Entities, CDSCeunes, Backbone, Marionette, $, _) {
    Entities.Position = Backbone.Model.extend({
      urlRoot: "/api/v1/positions",

      defaults: {
        name: "",
        minWorkload: 0,
        maxWorkload: 0,
        currentWorkload: 0,
        commission: ""
      },

      initialize: function() {
        this.bind("change", function() {
          this.save();
        });
      }
    });

    Entities.PositionsCollection = Backbone.Collection.extend({
      url: "/api/v1/positions",
      model: Entities.Position,
      comparator: "name"
    });


    var API = {
      getPositionEntity: function(positionId) {
        var position = new Entities.Position({ id: positionId });
        return Q.promise(function(resolve) {
          position.fetch({
            success: function(data) {
              resolve(data);
            },
            error: function(data) {
              resolve(undefined);
            }
          });
        });
      },
      getPositionsEntities: function() {
        var positions = new Entities.PositionsCollection();
        return Q.promise(function(resolve) {
          positions.fetch({
            success: function(data) {
              resolve(data);
            }
          });
        });
      }
    };

    CDSCeunes.reqres.setHandler("position:entity", function(id) {
      return API.getPositionEntity(id);
    });

    CDSCeunes.reqres.setHandler("position:entities", function() {
      return API.getPositionsEntities();
    });

    CDSCeunes.reqres.setHandler("position:entity:new", function(id) {
      return new Entities.Position();
    });

  });
  return ;
});
