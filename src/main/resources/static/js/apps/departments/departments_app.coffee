define [ 'cs!app' ], (CDSCeunes) ->
  CDSCeunes.module 'Routers.DepartmentsApp', (DepartmentsAppRouter, CDSCeunes, Backbone, Marionette, $, _) ->
    DepartmentsAppRouter.Router = Marionette.AppRouter.extend(appRoutes:
      'departments': 'listDepartments'
      'departments(/filter/criterion::criterion)': 'listDepartments')

    executeAction = (action, arg) ->
      CDSCeunes.startSubApp 'DepartmentApp'
      action arg
      return

    API = listDepartments: (criterion) ->
      require [ 'cs!apps/departments/list/list_controller' ], (ListController) ->
        executeAction ListController.listDepartments, criterion
        return
      return
    CDSCeunes.on 'departments:list', ->
      CDSCeunes.navigate 'departments'
      API.listDepartments()
      return
    CDSCeunes.on 'contacts:filter', (criterion) ->
      if criterion
        CDSCeunes.navigate 'departments/filter/criterion:' + criterion
      else
        CDSCeunes.navigate 'departments'
      return
    CDSCeunes.Routers.on 'start', ->
      console.log 'Departments router'
      new (DepartmentsAppRouter.Router)(controller: API)
      return
    return
  CDSCeunes.DepartmentsAppRouter
