define [ 'cs!app' ], (CDSCeunes) ->
  CDSCeunes.module 'Routers.MainApp', (MainAppRouter, CDSCeunes, Backbone, Marionette, $, _) ->
    MainAppRouter.Router = Marionette.AppRouter.extend(appRoutes:
      'main': 'showAll'

    executeAction = (action, arg) ->
      CDSCeunes.startSubApp 'MainApp'
      action arg
      return

    API = showAll: ->
      require [ 'cs!apps/main/main_controller' ], (MainController) ->
        executeAction MainController.loadPage
        return
      return

    CDSCeunes.on 'main:show', ->
      CDSCeunes.navigate 'main'
      API.showAll()
      return

    CDSCeunes.Routers.on 'start', ->
      console.log 'Main page router'
      new (MainAppRouter.Router)(controller: API)
      return
    return

    CDSCeunes.MainAppRouter
