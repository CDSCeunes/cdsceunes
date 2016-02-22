define(["app", "q"], function(CDSCeunes, Q) {
  CDSCeunes.module("Entities", function(Entities, CDSCeunes, Backbone, Marionette, $, _) {
    Entities.Department = Backbone.Model.extend({
      urlRoot: "departments",

      defaults: {
        name: "",
        center: ""
      },

      initialize: function() {
        this.bind("change", function() {
          this.save();
        });
      }
    });

    Entities.DepartmentCollection = Backbone.Collection.extend({
      model: Entities.Department,
      url: "departments",
      comparator: "name"
    });

    var API = {
      getDepartments: function() {
        var departments = new Entities.DepartmentCollection();
        return Q.promise(function(resolve) {
          departments.fetch({
            success: function(data) {
              resolve(data);
            }
          });
        });
      },
      getDepartmentEntity: function(id) {
        var department = new Entities.Department({id: id});
        return Q.promise(function(resolve) {
          department.fetch({
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

    CDSCeunes.reqres.setHandler("department:entity", function(id) {
      return API.getDepartmentEntity(id);
    });

    CDSCeunes.reqres.setHandler("department:entities", function() {
      return API.getDepartments();
    });

    CDSCeunes.reqres.setHandler("department:entity:new", function(id) {
      return new Entities.Department();
    });

  });
  return ;
});