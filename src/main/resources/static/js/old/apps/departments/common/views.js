define([ "app", "handlebars",
		"text!apps/departments/common/templates/form.hbs", "backbone.syphon",
		"jquery-ui" ], function(CDSCeunes, Handlebars, formTpl) {
	CDSCeunes.module("DepartmentsApp.Common.Views", function(Views, CDSCeunes,
			Backbone, Marionette, $, _) {
		Views.Form = Marionette.ItemView.extend({
			template : Handlebars.compile(formTpl),

			ui : {
				"submitData" : ".js-submit-department"
			},

			events : {
				"click @ui.submitData" : "submitData"
			},

			submitData : function(e) {
				e.preventDefault();
				var data = Backbone.Syphon.serialize(this);
				this.trigger("department:form:submit", data);
			}
		});

	});

	return CDSCeunes.DepartmentsApp.Common.Views;
});