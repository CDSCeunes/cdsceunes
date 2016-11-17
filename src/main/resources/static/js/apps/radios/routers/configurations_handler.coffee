define [
  'cs!app'
  'marionette'
], (CDSCeunes, Marionette) ->
  Radios =
    Routers:
      Configurations: Marionette.Object.extend(
        initialize: (opts) ->
          @router = opts.router
          return
        channelName: 'router-handler'
        radioEvents:
          'configurations:config': 'routeMainForm'
        routeMainForm: ->
          CDSCeunes.navigate 'configurations'
          @router.controller.config()
          return
      )
  Radios.Routers
