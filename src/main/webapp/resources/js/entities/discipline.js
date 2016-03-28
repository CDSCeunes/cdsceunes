define(["app","q"], function(CDSCeunes, Q) {
  CDSCeunes.module("Entities", function(Entities, CDSCeunes, Backbone, Marionette, $, _) {
    Entities.Discipline = Backbone.Model.extend({
      urlRoot: "disciplines",
      defaults: {
        name: "",
        course: "",
        semesters: "",
        teoricLoad: 0,
        exerciseLoad: 0,
        labLoad: 0
      },

      initialize: function() {
        this.bind("change", function() {
          this.save();
        });
      }
    });

    Entities.DisciplinesCollection = Backbone.Collection.extend({
      url: "disciplines",
      model: Entities.Discipline,
      comparator: "name"
    });

    var API = {
      getDisciplineEntity: function(disciplineId) {
        var discipline = new Entities.Discipline({ id: disciplineId});
        return Q.promise(function(resolve) {
          discipline.fetch({
            success: function(data) {
              resolve(data);
            },
            error: function(data) {
              resolve(undefined);
            }
          });
        });
      },
      getDisciplinesEntities: function() {
        var disciplines = new Entities.DisciplinesCollection();
        return Q.promise(function(resolve) {
          disciplines.fetch({
            success: function(data) {
              resolve(data);
            }
          });
        });
      }
    };

    CDSCeunes.reqres.setHandler("discipline:entity", function(id) {
      return API.getDisciplineEntity(id);
    });
    CDSCeunes.reqres.setHandler("discipline:entities", function() {
      return API.getDisciplinesEntities();
    });
    CDSCeunes.reqres.setHandler("discipline:entity:new", function() {
      return new Entities.Discipline();
    });

  });
  return ;
});
