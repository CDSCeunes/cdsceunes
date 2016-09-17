define(["app", "apps/departments/list/list_view", "q"], function(CDSCeunes, View, Q) {
  CDSCeunes.module("DepartmentsApp.List", function(List, CDSCeunes, Backbone, Marionette, $, _) {
    List.Controller = {
      listDepartments: function(criterion) {
        require(["entities/department"], function() {
          var listLayout = new View.Layout();
          var listPanel = new View.Panel();
          var departmentsListView;
          
          Q.all(CDSCeunes.request("department:entities")).then(function(departments) {
            departmentsListView = new View.Departments({
              collection: departments,
            });


            if (criterion) {

            }

            listLayout.on("show", function() {
              listLayout.panelRegion.show(listPanel);
              listLayout.departmentsRegion.show(departmentsListView);

            });

            listPanel.on("department:new", function() {
              require(["apps/departments/new/new_view", "entities/department"], function(NewView) {
                var department = CDSCeunes.request("department:entity:new");
                Q.all(CDSCeunes.request("department:entities")).then(function(departments) {

                  var newView = new NewView.Department({
                    model: department
                  });

                  CDSCeunes.regions.dialog.show(newView);
                  newView.on("department:form:submit", function(data) {
                    if (department.save(data)) {
                      departments.add(department);
                      newView.trigger("dialog:close");
                    }
                  });

                });
              });
            });

            departmentsListView.on("childview:department:edit", function(childview, args) {
              require(["apps/departments/edit/edit_view"], function(EditView) {
                var model = args.model;
                Q.all(CDSCeunes.request("department:entities")).then(function(departments) {
                  var editView = new EditView.Department({
                    model: model
                  });

                  editView.on("department:form:submit", function(data) {
                    if (model.save(data)) {
                      childview.render();
                      editView.trigger("dialog:close");
                    }
                  });

                  CDSCeunes.regions.dialog.show(editView);
                });

              });
            });

            departmentsListView.on("childview:department:delete", function(childview, args) {
              args.model.destroy();
            });

            CDSCeunes.regions.main.show(listLayout);
          });
        });
      }
    };
  });
  return CDSCeunes.DepartmentsApp.List.Controller;
});
