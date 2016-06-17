define([ "app", "handlebars",
		"text!apps/positions/common/templates/form.hbs", "backbone.syphon",
		"jquery-ui" ], function(CDSCeunes, Handlebars, formTpl) {
	CDSCeunes.module("PositionsApp.Common.Views", function(Views, CDSCeunes,
			Backbone, Marionette, $, _) {
		Views.Form = Marionette.ItemView.extend({
			template : Handlebars.compile(formTpl),

			ui : {
				"submitData" : ".js-submit-position"
			},

			events : {
				"click @ui.submitData" : "submitData"
			},

			submitData : function(e) {
				e.preventDefault();
				var data = Backbone.Syphon.serialize(this);
				this.trigger("position:form:submit", data);
			}
		});

	});

	return CDSCeunes.PositionsApp.Common.Views;
});
