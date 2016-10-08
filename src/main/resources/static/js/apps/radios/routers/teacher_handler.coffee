define [
  'cs!app'
  'marionette'
], (CDSCeunes, Marionette) ->
  Radios =
    Routers:
      Teacher: Marionette.Object.extend(
        initialize: (opts) ->
          @router = opts.router
          return
        channelName: 'router-handler'
        radioEvents:
          'teachers:list': 'routeTeachersList'
        routeTeachersList: ->
          CDSCeunes.navigate 'teachers'
          @router.controller.list()
          return
      )
  Radios.Routers
