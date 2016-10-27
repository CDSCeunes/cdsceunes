define [
  'cs!app'
  'marionette'
], (CDSCeunes, Marionette) ->
  Radios =
    Routers:
      Position: Marionette.Object.extend(
        initialize: (opts) ->
          @router = opts.router
          return
        channelName: 'router-handler'
        radioEvents:
          'Positions:list': 'routePositionsList'
        routePositionsList: ->
          CDSCeunes.navigate 'positions'
          @router.controller.list()
          return
      )
  Radios.Routers
