define(["app", "q", "apps/config/storage/localstorage"], function(CDSCeunes, Q) {
  CDSCeunes.module("Entities", function(Entities, CDSCeunes, Backbone, Marionette, $, _) {
    Entities.Teacher = Backbone.Model.extend({
      urlRoot: "/api/v1/teachers",

      defaults: {
        name: "",
        login: "",
        available: true
      },

      shouldBeShown: function(search) {
        var name = this.get("name").toLowerCase();
        var login = this.get("login").toLowerCase();
        return (name.indexOf(search) > -1) || (login.indexOf(search) > -1);
      }
    });

    Entities.configureStorage("CDSCeunes.Entities.Teacher", Entities.Teacher);

    Entities.TeachersCollection = Backbone.Collection.extend({
      url: "/api/v1/teachers",
      model: Entities.Teacher,
      comparator: "name"
    });

    Entities.configureStorage("CDSCeunes.Entities.TeachersCollection", Entities.TeachersCollection);

    var API = {
      getTeacherEntity: function(teacherId) {
        var teacher = new Entities.Teacher({ id: teacherId });
        return Q.promise(function(resolve) {
          teachers.fetch({
            success: function(data) {
              resolve(data);
            },
            error: function(data) {
              resolve(undefined);
            }
          });
        });
      },
      getTeachersEntities: function() {
        var teachers = new Entities.TeachersCollection();
        return Q.promise(function(resolve) {
          teachers.fetch({
            success: function(data) {
              resolve(data);
            }
          });
        });
      }
    };

    CDSCeunes.reqres.setHandler("teacher:entity", function(id) {
      return API.getTeacherEntity(id);
    });

    CDSCeunes.reqres.setHandler("teacher:entities", function() {
      return API.getTeachersEntities();
    });

    CDSCeunes.reqres.setHandler("teacher:entity:new", function(id) {
      return new Entities.Teacher();
    });
  });
  return ;
});
