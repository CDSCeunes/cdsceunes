define(["marionette", "handlebars", "jquery-ui", "backbone.syphon"], function(Marionette, Handlebars) {
	var CDSCeunes = new Marionette.Application();

  Marionette.TemplateCache.prototype.compileTemplate = function(templateText) {
    return Handlebars.compile(templateText);
  };

  Backbone.Syphon.InputReaders.register('checkbox', function($el){
    return $el.prop('checked') ? true : false;
  });

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
        dialog: "#dialog-container",
				footer: "#footer-container"
			}
		});

		CDSCeunes.regions = new RootContainer();
    CDSCeunes.regions.dialog.onShow = function(view) {
      var self = this;
      var closeDialog = function() {
        self.stopListening();
        self.empty();
        self.$el.dialog("destroy");
      };

      this.listenTo(view, "dialog:close", closeDialog);

      this.$el.dialog({
        modal: true,
        title: view.title,
        width: "auto",
        maxWidth: 600,
        fluid: true,
        responsive: true,
        resizable: false,
        close: function(e, ui) {
          closeDialog();
        }
      });
    };

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
