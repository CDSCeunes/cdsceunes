define [ 'app' ], (CDSCeunes) ->
  CDSCeunes.module 'Routers.TeachersApp', (TeachersAppRouter, CDSCeunes, Backbone, Marionette, $, _) ->
    TeachersAppRouter.Router = Marionette.AppRouter.extend(appRoutes: 'teachers(/filter/criterion::criterion)': 'listTeachers')

    executeAction = (action, arg) ->
      CDSCeunes.startSubApp 'TeacherApp'
      action arg
      return

    API = listTeachers: (criterion) ->
      require [ 'apps/teachers/list/list_controller' ], (ListController) ->
        executeAction ListController.listTeachers, criterion
        return
      return
    CDSCeunes.on 'teachers:list', ->
      CDSCeunes.navigate 'teachers'
      API.listTeachers()
      return
    CDSCeunes.on 'teachers:filter', (criterion) ->
      if criterion
        CDSCeunes.navigate 'teachers/filter/criterion:' + criterion
      else
        CDSCeunes.navigate 'teachers'
      return
    CDSCeunes.Routers.on 'start', ->
      console.log 'Teachers router'
      new (TeachersAppRouter.Router)(controller: API)
      return
    return
  CDSCeunes.TeachersAppRouter
