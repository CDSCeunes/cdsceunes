define([ "app", "apps/positions/common/views" ], function(CDSCeunes,
		CommonViews) {
	CDSCeunes.module("PositionsApp.Edit.View", function(View, CDSCeunes,
			Backbone, Marionette, $, _) {
		View.Positions = CommonViews.Form.extend({
			title : "Editar cargo",

			onRender : function() {
				this.$(".js-submit-position").text("Atualizar");
			},

		});
	});
	return CDSCeunes.PositionsApp.Edit.View;
});
