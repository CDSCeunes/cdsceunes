define [
  'cs!app'
  'marionette'
], (CDSCeunes, Marionette) ->
  Radios =
    Routers:
      Main: Marionette.Object.extend(
        initialize: (opts) ->
          @router = opts.router
          return
        channelName: 'router-handler'
        radioEvents:
          'main:loadPage': 'routeMainList'
        routeMainList: ->
          CDSCeunes.navigate 'main'
          @router.controller.loadPage()
          return
      )
  Radios.Routers
