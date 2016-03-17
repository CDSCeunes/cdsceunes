define([ "app" ], function(CDSCeunes) {
	CDSCeunes.module("Routers.DepartmentsApp", function(DepartmentsAppRouter,
			CDSCeunes, Backbone, Marionette, $, _) {
		DepartmentsAppRouter.Router = Marionette.AppRouter.extend({
			appRoutes : {
				"departments" : "listDepartments",
				"departments(/filter/criterion::criterion)" : "listDepartments"
			}
		});

		var executeAction = function(action, arg) {
			CDSCeunes.startSubApp("DepartmentApp");
			action(arg);
		}

		var API = {
			listDepartments : function(criterion) {
				require([ "apps/departments/list/list_controller" ], function(ListController) {
					executeAction(ListController.listDepartments, criterion)
				});
			}
		};

		CDSCeunes.on("departments:list", function() {
			CDSCeunes.navigate("departments");
			API.listDepartments();
		});

		CDSCeunes.on("contacts:filter", function(criterion) {
					if (criterion) {
						CDSCeunes.navigate("departments/filter/criterion:"
								+ criterion);
					} else {
						CDSCeunes.navigate("departments");
					}
				})

		CDSCeunes.Routers.on("start", function() {
			console.log("Departments router");
			new DepartmentsAppRouter.Router({
				controller : API
			});
		});

	});

	return CDSCeunes.DepartmentsAppRouter;
});
