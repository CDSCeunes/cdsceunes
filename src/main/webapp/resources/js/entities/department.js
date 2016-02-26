define(["app", "q", "apps/config/storage/localstorage"], function(CDSCeunes, Q) {
  CDSCeunes.module("Entities", function(Entities, CDSCeunes, Backbone, Marionette, $, _) {
    Entities.Department = Backbone.Model.extend({
      urlRoot: "departments",

      defaults: {
        name: "",
        center: "",
        available: true
      },

      initialize: function() {
        this.bind("change", function() {
          this.save();
        });
      }
    });

    Entities.configureStorage("CDSCeunes.Entities.Department", Entities.Department);
    
    Entities.DepartmentCollection = Backbone.Collection.extend({
      model: Entities.Department,
      url: "departments",
      comparator: "name"
    });

    Entities.configureStorage("CDSCeunes.Entities.DepartmentsCollection", Entities.DepartmentsCollection);
    
    var API = {
      getDepartmentsEntity: function(departmentId) {
        var departments = new Entities.Department({ id: departmentId });
        return Q.promise(function(resolve) {
          departments.fetch({
            success: function(data) {
              resolve(data);
            }
          });
        });
      },
      getDepartmentsEntities: function() {
        var departments = new Entities.DepartmentsCollection();
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
      return API.getDepartmentsEntities();
    });

    CDSCeunes.reqres.setHandler("department:entity:new", function(id) {
      return new Entities.Department();
    });

  });
  return ;
});