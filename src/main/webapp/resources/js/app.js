define(["marionette"], function(Marionette) {
	var CDSCeunes = new Marionette.Application();

/*
	CDSCeunes.navigate = function(route, options) {
		options || (options = {});
		Backbone.history.navigate(route, options);
	};
*/

	CDSCeunes.on("before:start", function() {
		var RootContainer = Marionette.LayoutView.extend({
			el: "#app-container",

			regions: {
				header: "#header-container",
				main: "#main-container",
				footer: "#footer-container"
			}
		});

		CDSCeunes.regions = new RootContainer();

	});

  CDSCeunes.on("start", function() {
    require(["apps/login/index/index_view"], function(View) {
      CDSCeunes.regions.main.show(new View.Layout());
    });
  });

	return CDSCeunes;

});