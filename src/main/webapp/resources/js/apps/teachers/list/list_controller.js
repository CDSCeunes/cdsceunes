define(["app", "apps/teachers/list/list_view", "q"], function(CDSCeunes, View, Q) {
  CDSCeunes.module("TeachersApp.List", function(List, CDSCeunes, Backbone, Marionette, $, _) {
    List.Controller = {
      listTeachers: function(criterion) {
        require(["entities/teacher"], function() {
          var listLayout = new View.Layout();
          var listPanel = new View.Panel();

          var teachersListView;
          Q.all(CDSCeunes.request("teacher:entities")).then(function(teachers) {
            teachersListView = new View.Teachers({
              collection: teachers,
            });
            

            if (criterion) {

            }

            listLayout.on("show", function() {
              listLayout.panelRegion.show(listPanel);
              listLayout.teachersRegion.show(teachersListView);

            });

            CDSCeunes.regions.main.show(listLayout);
          });
        });
      }
    };
  });
  return CDSCeunes.TeachersApp.List.Controller;
});