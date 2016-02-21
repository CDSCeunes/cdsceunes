define(["app"], function(CDSCeunes) {
  CDSCeunes.module("Routers.TeachersApp", function(TeachersAppRouter, CDSCeunes, Backbone, Marionette, $, _) {
    TeachersAppRouter.Router = Marionette.AppRouter.extend({
      appRoutes: {
        "teachers": "listTeachers",
        "teachers(/filter/criterion::criterion)": "listTeachers"
      }
    });

    var executeAction = function(action, arg) {
      CDSCeunes.startSubApp("TeacherApp");
      action(arg);
    }

    var API = {
      listTeachers: function(criterion) {
        require(["apps/teachers/list/list_controller"], function(ListController) {
          executeAction(ListController.listTeachers, criterion)
        });
      }
    };

    CDSCeunes.on("teachers:list", function() {
      CDSCeunes.navigate("teachers");
      API.listTeachers();
    });

    CDSCeunes.on("contacts:filter", function(criterion) {
      if (criterion) {
        CDSCeunes.navigate("teachers/filter/criterion:" + criterion);
      } else {
        CDSCeunes.navigate("teachers");
      }
    })

    CDSCeunes.Routers.on("start", function() {
      console.log("Teacher router");
      new TeachersAppRouter.Router({
        controller: API
      });
    });
  });
  return CDSCeunes.TeachersAppRouter;
});