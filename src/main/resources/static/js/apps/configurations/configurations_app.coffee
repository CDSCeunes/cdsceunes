define [ 'cs!app' ], (CDSCeunes) ->
  CDSCeunes.module 'Routers.ConfigurationsApp', (ConfigurationsAppRouter, CDSCeunes, Backbone, Marionette, $, _) ->
    ConfigurationsAppRouter.Router = Marionette.AppRouter.extend(appRoutes:
      'configurations': 'showAllConfigures'

    executeAction = (action, arg) ->
      CDSCeunes.startSubApp 'ConfigurationsApp'
      action arg
      return

    API = showAllConfigures: ->
      require [ 'cs!apps/configurations/configurations_controller' ], (ConfigurationsController) ->
        executeAction ConfigurationsController.loadPage
        return
      return

    CDSCeunes.on 'configurations:show', ->
      CDSCeunes.navigate 'configurations'
      API.showAllConfigures()
      return

    CDSCeunes.Routers.on 'start', ->
      console.log 'Configurations page router'
      new (ConfigurationsAppRouter.Router)(controller: API)
      return
    return

    CDSCeunes.ConfigurationsAppRouter
