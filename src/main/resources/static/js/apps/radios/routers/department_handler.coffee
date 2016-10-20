define [
  'cs!app'
  'marionette'
], (CDSCeunes, Marionette) ->
  Radios =
    Routers:
      Department: Marionette.Object.extend(
        initialize: (opts) ->
          @router = opts.router
          return
        channelName: 'router-handler'
        radioEvents:
          'departments:list': 'routeDepartmentsList'
        routeDepartmentsList: ->
          CDSCeunes.navigate 'departments'
          @router.controller.list()
          return
      )
  Radios.Routers
