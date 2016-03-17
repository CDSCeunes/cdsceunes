define(["app", "apps/disciplines/list/list_view", "q"], function(CDSCeunes, View, Q) {
  CDSCeunes.module("DisciplinesApp.List", function(List, CDSCeunes, Backbone, Marionette, $, _) {
    List.Controller = {
      listDisciplines: function(criterion) {
        require(["entities/discipline"], function() {
          var listLayout = new View.Layout();
          var listPanel = new View.Panel();
          var disciplinesListView;

          Q.all(CDSCeunes.request("discipline:entities")).then(function(disciplines) {
            disciplinesListView = new View.Disciplines({
              collection: disciplines,
            });

            if (criterion) {

            }

            listLayout.on("show", function() {
              listLayout.panelRegion.show(listPanel);
              listLayout.disciplinesRegion.show(disciplinesListView);
            });

            listPanel.on("discipline:new", function() {
              require(["apps/disciplines/new/new_view", "entities/discipline"], function(NewView) {
                var discipline = CDSCeunes.request("discipline:entity:new");
                Q.all(CDSCeunes.request("discipline:entities")).then(function(disciplines) {
                  var new_view =  new NewView.Discipline({
                    model: discipline
                  });

                  CDSCeunes.regions.dialog.show(newView);

                  newView.on("discipline:form:submit", function(data) {
                    if (discipline.save(data)) {
                      disciplines.add(discipline);
                      newView.trigger("dialog:close");
                    }
                  });

                });
              });
            });

            disciplinesListView.on("childview:discipline:edit", function(childview, args) {
              require(["apps/disciplines/edit/edit_view"], function(EditView) {
                var model = args.model;
                Q.all(CDSCeunes.request("discipline:entities")).then(function(disciplines) {
                  var editView = new EditView.Discipline({
                    model: model
                  });

                  editView.on("discipline:form:submit", function(data) {
                    if (model.save(data)) {
                      childview.render();
                      editView.trigger("dialog:close");
                    }
                  });

                  CDSCeunes.regions.dialog.show(editView);
                });
              });
            });

            disciplinesListView.on("childview:discipline:delete", function(childview, args) {
              args.model.destroy();
            });

            CDSCeunes.regions.main.show(listLayout);
          });
        });
      }
    };
  });
  return CDSCeunes.DisciplinesApp.List.Controller;
});
