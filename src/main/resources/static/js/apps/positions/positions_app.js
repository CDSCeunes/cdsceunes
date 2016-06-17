define(["app"], function(CDSCeunes) {
  CDSCeunes.module("Routers.PositionsApp", function(PositionsAppRouter,
    CDSCeunes, Backbone, Marionette, $, _) {
    PositionsAppRouter.Router = Marionette.AppRouter.extend({
      appRoutes: {
        "positions": "listPositions",
        "positions(/filter/criterion::criterion)": "listPositions"
      }
    });

    var executeAction = function(action, arg) {
      CDSCeunes.startSubApp("PositionsApp");
      action(arg);
    }

    var API = {
      listPositions: function(criterion) {
        require(["apps/positions/list/list_controller"], function(ListController) {
          executeAction(ListController.listPositions, criterion)
        });
      }
    };

    CDSCeunes.on("positions:list", function() {
      CDSCeunes.navigate("positions");
      API.listPositions();
    });

    CDSCeunes.on("contacts:filter", function(criterion) {
      if (criterion) {
        CDSCeunes.navigate("positions/filter/criterion:" + criterion);
      } else {
        CDSCeunes.navigate("positions");
      }
    })

    CDSCeunes.Routers.on("start", function() {
      console.log("Positions Router");
      new PositionsAppRouter.Router({
        controller: API
      });
    });

  });
  return CDSCeunes.PositionsAppRouter;
});
