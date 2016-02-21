define(["marionette", "handlebars"], function(Marionette, Handlebars) {
	var CDSCeunes = new Marionette.Application();

  Marionette.TemplateCache.prototype.compileTemplate = function(templateText) {
    return Handlebars.compile(templateText);
  }

  CDSCeunes.baseUrl = "http://localhost:8080/CDSCeunes/"

	CDSCeunes.navigate = function(route, options) {
		options || (options = {});
		Backbone.history.navigate(route, options);
	};

  CDSCeunes.getCurrentRoute = function() {
    return Backbone.history.fragment;
  };

  CDSCeunes.startSubApp = function(appName, args) {
    var currentApp = appName ? CDSCeunes.module(appName) : null;
    if (CDSCeunes.currentApp === currentApp) {
      return;
    }

    if (CDSCeunes.currentApp) {
      CDSCeunes.currentApp.stop();
    }

    CDSCeunes.currentApp = currentApp;
    if (currentApp) {
      currentApp.start(args);
    }
  };


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
    if (Backbone.history) {
      Backbone.history.start();

      if (CDSCeunes.getCurrentRoute() === "") {
        /*require(["apps/login/index/index_view"], function(View) {
          CDSCeunes.regions.main.show(new View.Layout());
        });*/
        CDSCeunes.trigger("login:home");
      }
    }
  });

	return CDSCeunes;

});