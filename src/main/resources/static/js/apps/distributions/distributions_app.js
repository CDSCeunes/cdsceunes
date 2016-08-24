define([ "app" ], function(CDSCeunes) {
  CDSCeunes.module("Routers.DistributionsApp", function(DistributionsAppRouter,
    CDSCeunes, Backbone, Marionette, $, _) {
      DistributionsAppRouter.Router = Marionette.AppRouter.extend({
        appRoutes: {
          "distributions": "listDistributions",
          "distributions(/filter/criterion::criterion)" : "listDistributions"
        }
      });

      var executeAction = function(action, arg) {
        CDSCeunes.startSubApp("DistributionApp");
        action(arg);
      }

      var API = {
        listDistributions: function(criterion) {
          require(["apps/distrinutions/list/list_controller"], function(ListController) {
            executeAction(ListController.listDistributions, criterion)
          });
        }
      };

      CDSCeunes.on("distributions:list", function() {
        CDSCeunes.navigate("distributions");
        API.listDistributions();
      }

      CDSCeunes.on("distributions:filter", function(criterion) {
        if (criterion) {
          CDSCeunes.navigate("distributions/filter/criterion:" + criterion);
        } else {
          CDSCeunes.navigate("distributions");
        }
      })

      CDSCeunes.Routers.on("start", function() {
        console.log("Distributions router");
        new DistributionsAppRouter.Router({
          controller: API
        });
      });
    });
    return CDSCeunes.DistributionsAppRouter;
  });
