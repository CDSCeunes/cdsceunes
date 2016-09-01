define(["app", "apps/distributions/new/new_view", "q"], function(CDSCeunes, View, Q) {
  CDSCeunes.module("DistributionsApp.List", function(NewDistribution, CDSCeunes, Backbone, Marionette, $, _) {
    NewDistribution.new = {
      newDistribution: function() {
        require(["entities/common", "entities/teacher", "entities/discipline", "entities/distribution"], function() {
          var listLayout = new View.Layout();
          var listPanel = new View.Panel();
          var teachersListView;
          var disciplinesListView;

          
        });
      }
    };
  });
  return CDSCeunes.DistributionsApp.NewDistribution.Controller;
});
