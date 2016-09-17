define [ 'app' ], (CDSCeunes) ->
  CDSCeunes.module 'Routers.DisciplinesApp', (DisciplinesAppRouter, CDSCeunes, Backbone, Marionette, $, _) ->
    DisciplinesAppRouter.Router = Marionette.AppRouter.extend(appRoutes:
      'disciplines': 'listDisciplines'
      'disciplines(/filter/criterion::criterion)': 'listDisciplines')

    executeAction = (action, arg) ->
      CDSCeunes.startSubApp 'DisciplineApp'
      action arg
      return

    API = listDisciplines: (criterion) ->
      require [ 'apps/disciplines/list/list_controller' ], (ListController) ->
        executeAction ListController.listDisciplines, criterion
        return
      return
    CDSCeunes.on 'disciplines:list', ->
      CDSCeunes.navigate 'disciplines'
      API.listDisciplines()
      return
    CDSCeunes.on 'contacts:filter', (criterion) ->
      if criterion
        CDSCeunes.navigate 'disciplines/filter/criterion:' + criterion
      else
        CDSCeunes.navigate 'disciplines'
      return
    CDSCeunes.Routers.on 'start', ->
      console.log 'Disciplines router'
      new (DisciplinesAppRouter.Router)(controller: API)
      return
    return
  CDSCeunes.DisciplinesAppRouter
