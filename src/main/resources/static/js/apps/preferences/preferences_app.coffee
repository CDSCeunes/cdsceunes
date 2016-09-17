define [ 'cs!app' ], (CDSCeunes) ->
  CDSCeunes.module 'Routers.PreferencesApp', (PreferencesAppRouter, CDSCeunes, Backbone, Marionette, $, _) ->
    PreferencesAppRouter.Router = Marionette.AppRouter.extend(appRoutes: 'preferences': 'listPreferences')

    executeAction = (action, args) ->
      CDSCeunes.startSubApp 'PreferencesApp'
      action args
      return

    API = listPreferences: ->
      require [ 'cs!apps/preferences/list/list_controller' ], (ListController) ->
        executeAction ListController.listPreferences, undefined
        return
      return
    CDSCeunes.Routers.on 'start', ->
      console.log 'Preferences Router'
      new (PreferencesAppRouter.Router)(controller: API)
      return
    return
  return
