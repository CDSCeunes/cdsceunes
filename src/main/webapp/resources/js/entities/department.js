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

    Entities.DepartmentsCollection = Backbone.Collection.extend({
      url: "departments",
      model: Entities.Department,
      comparator: "name"
    });


    var API = {
      getDepartmentEntity: function(departmentId) {
        var department = new Entities.Department({ id: departmentId });
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
      },
      getDepartmentsEntities: function() {
        var departments = new Entities.DepartmentsCollection();
        return Q.promise(function(resolve) {
          departments.fetch({
            success: function(data) {
              resolve(data);
            }
          });
        });
      }
    };

    CDSCeunes.reqres.setHandler("department:entity", function(id) {
      return API.getDepartmentEntity(id);
    });

    CDSCeunes.reqres.setHandler("department:entities", function() {
      return API.getDepartmentsEntities();
    });

    CDSCeunes.reqres.setHandler("department:entity:new", function(id) {
      return new Entities.Department();
    });

  });
  return ;
});
