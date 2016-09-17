define(["app", "apps/distributions/common/views", "apps/distributions/list/list_view","q"], function(CDSCeunes, CommonView, View, Q) {
  CDSCeunes.module("DistributionsApp.List", function(List, CDSCeunes, Backbone, Marionette, $, _) {
    List.Controller = {
      listDistributions: function(criterion) {
                  console.log("0");
        require(["entities/distribution"], function() {
          var listLayout = new CommonView.Layout();
          var listPanel = new CommonView.Panel();
          var distributionsListView;

          console.log("1");
          Q.all(CDSCeunes.request("distribution:entities")).then(function(distributions) {
                      console.log("2");
            distributionsListView = new View.Distributions({
              collection: distributions
            });

            if (criterion) {

            }

            listLayout.on("show", function() {
                        console.log("3");
              listLayout.panelRegion.show(listPanel);
              listLayout.distributionsRegion.show(distributionsListView);
                        console.log("4");
            });

            distributionsListView.on("childview:distribution:edit", function(childview, args) {
              require(["apps/distributions/edit/edit_view"], function(EditView) {
                var model = args.model;
                Q.all(CDSCeunes.request("distribution:entities")).then(function(distributions) {
                  var editView = new EditView.Distribution({
                    model: model
                  });

                  editView.on("distribution:form:submit", function(data) {
                    if (model.save(data)) {
                      childview.render();
                      editView.trigger("dialog:close");
                    }
                  });

                            console.log("5");
                  CDSCeunes.regions.dialog.show(editView);
                            console.log("6");
                });
              });
            });

            distributionsListView.on("childview:distribution:delete", function(childview, args) {
              args.model.destroy();
            });
            CDSCeunes.regions.main.show(listLayout);
          });

        });
      }
    };
  });
  return CDSCeunes.DistributionsApp.List.Controller;
});
