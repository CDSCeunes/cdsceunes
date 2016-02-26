define([ "app", "apps/departments/common/views" ], function(CDSCeunes,
		CommonViews) {
	CDSCeunes.module("DepartmentsApp.Edit.View", function(View, CDSCeunes,
			Backbone, Marionette, $, _) {
		View.Teacher = CommonViews.Form.extend({
			title : "Editar departamento",

			onRender : function() {
				this.$(".js-submit-department").text("Atualizar");
			},

		});
	});
	return CDSCeunes.DepartmentsApp.Edit.View;
});