define(["app"], function(CDSCeunes) {
  CDSCeunes.module("Routers.DisciplinesApp", function(DisciplinesAppRouter, CDSCeunes, Backbone, Marionette, $, _) {
    DisciplinesAppRouter.Router = Marionette.AppRouter.extend({
      appRoutes: {
        "disciplines": "listDisciplines",
        "disciplines(/filter/criterion::criterion)": "listDisciplines"
      }
    });

    var executeAction = function(action, arg) {
      CDSCeunes.startSubApp("DisciplineApp");
      action(arg);
    };

    var API = {
      listDisciplines: function(criterion) {
        require(["apps/disciplines/list/list_controller"], function(ListController) {
          executeAction(ListController.listDisciplines, criterion);
        });
      }
    };

    CDSCeunes.on("disciplines:list", function() {
      CDSCeunes.navigate("disciplines");
      API.listDisciplines();
    });

    CDSCeunes.on("contacts:filter", function(criterion) {
      if (criterion) {
        CDSCeunes.navigate("disciplines/filter/criterion:" + criterion);
      } else {
        CDSCeunes.navigate("disciplines");
      }
    });

    CDSCeunes.Routers.on("start", function() {
      console.log("Disciplines router");
      new DisciplinesAppRouter.Router({
        controller: API
      });
    });

  });
  return CDSCeunes.DisciplinesAppRouter;
});
