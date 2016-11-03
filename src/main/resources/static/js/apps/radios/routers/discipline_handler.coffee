define [
  'cs!app'
  'marionette'
], (CDSCeunes, Marionette) ->
  Radios =
    Routers:
      Discipline: Marionette.Object.extend(
        initialize: (opts) ->
          @router = opts.router
          return
        channelName: 'router-handler'
        radioEvents:
          'disciplines:list': 'routeDisciplinesList'
        routeDisciplinesList: ->
          CDSCeunes.navigate 'disciplines'
          @router.controller.list()
          return
      )
  Radios.Routers
