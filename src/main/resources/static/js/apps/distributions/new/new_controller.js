define(["app", "apps/distributions/new/new_view", "q"], function(CDSCeunes, View, Q) {
  CDSCeunes.module("DistributionsApp.List", function(NewDistribution, CDSCeunes, Backbone, Marionette, $, _) {
    NewDistribution.new = {
      newDistribution: function() {
        require(["entities/common", "entities/teacher", "entities/discipline", "entities/distribution"], function() {
          var listLayout = new View.Layout();
          var listPanel = new View.Panel();
          var teachersListView;
          var disciplinesListView;

          Q.all(CDSCeunes.request("discipline:entities", "teacher:entities", "distribution:entities").then(
            function(distributions) {

              teachersListView = new View.Teachers({
                collection: teacher,
              });
              disciplinesListView = new View.Disciplines({
                collection: discipline,
              });

              listPanel.on("distribution", function() {
                require(["apps/distributions/new/new_view", "entities/distribution", "entities/teacher", "entities/discipline"],
                  function(NewView) {
                  var distribution = CDSCeunes.request("distribution:entity:new");
                  Q.all(CDSCeunes.request("teacher:entities", "discipline:entities")).then(function(teachers, disciplines) {
                    var newView = new NewView.Distribution({
                      model: distribution,
                      teachers: teachers,
                      disciplines: disciplines
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

          });))
        });
      }
    };
  });
  return CDSCeunes.DistributionsApp.NewDistribution.Controller;
});
