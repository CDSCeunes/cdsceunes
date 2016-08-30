define(["app", "apps/distributions/list/list_view", "q"], function(CDSCeunes, View, Q) {
  CDSCeunes.module("DistributionsApp.List", function(List, CDSCeunes, Backbone, Marionette, $, _) {
    List.Controller = {
      listDistributions: function(criterion) {
        require(["entities/common", "entities/distribution"], function(FilteredCollection) {
          var listLayout = new View.Layout();
          var listPanel = new View.Panel();
          var distributionsListView;

          Q.all(CDSCeunes.request("distribution:entities")).then(function(distributions) {
            distributionsListView = new View.Distributions({
              collection: distribution
            });

            if (criterion) {

            }

            listLayout.on("show", function() {
              listLayout.panelRegion.show(listPanel);
              listLayout.distributionsRegion.show(distributionsListView);
            });

            listPanel.on("distribution:new", function() {
              require(["apps/distributions/new/new_view", "entities/distribution"], function(NewView) {
                var distribution = CDSCeunes.request("distribution:entity:new");
                Q.all(CDSCeunes.request("distribution:entities")).then(function(distributions) {
                  var newView = new NewView.Distribution({
                    model: distribution
                  });

                  CDSCeunes.regions.dialog.show(newView);

                  newView.on("distribution:form:submit", function(data) {
                    if (distribution.save(data)) {
                      distributions.add(distribution);
                      newView.trigger("dialog:close");
                    }
                  });

                });
              });
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

                  CDSCeunes.regions.dialog.show(editView);
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
