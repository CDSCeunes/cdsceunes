define(["app", "apps/positions/list/list_view", "q"], function(CDSCeunes, View, Q) {
  CDSCeunes.module("PositionsApp.List", function(List, CDSCeunes, Backbone, Marionette, $, _) {
    List.Controller = {
      listPositions: function(criterion) {
        require(["entities/position"], function() {
          var listLayout = new View.Layout();
          var listPanel = new View.Panel();
          var positionsListView;

          Q.all(CDSCeunes.request("position:entities")).then(function(positions) {
            positionsListView = new View.Positions({
              collection: positions,
            });

            if (criterion) {

            }

            listLayout.on("show", function() {
              listLayout.panelRegion.show(listPanel);
              listLayout.positionsRegion.show(positionsListView);
            });

            listPanel.on("position:new", function() {
              require(["apps/positions/new/new_view", "entities/position"], function(NewView) {
                var position = CDSCeunes.request("position:entity:new");
                Q.all(CDSCeunes.request("position:entities")).then(function(positions) {
                  var newView = new NewView.Position({
                    model: position
                  });

                  CDSCeunes.regions.dialog.show(newView);

                  newView.on("position:form:submit", function(data) {
                    if (position.save(data)) {
                      positions.add(position);
                      newView.trigger("dialog:close");
                    }
                  });

                });
              });
            });

            positionsListView.on("childview:position:edit", function(childview, args) {
              require(["apps/positions/edit/edit_view"], function(EditView) {
                var model = args.model;
                Q.all(CDSCeunes.request("position:entities")).then(function(positions) {
                  var editView = new EditView.Position({
                    model: model
                  });

                  editView.on("position:form:submit", function(data) {
                    if (model.save(data)) {
                      childview.render();
                      editView.trigger("dialog:close");
                    }
                  });

                  CDSCeunes.regions.dialog.show(editView);
                });
              });
            });

            positionsListView.on("childview:position:delete", function(childview, args) {
              args.model.destroy();
            });

            CDSCeunes.regions.main.show(listLayout);
          });
        });
      }
    };
  });
  return CDSCeunes.PositionsApp.List.Controller;
});
