define [
  'cs!app'
  'marionette'
], (CDSCeunes, Marionette) ->
  Radios =
    Routers:
      Distribution: Marionette.Object.extend(
        initialize: (opts) ->
          @router = opts.router
          return
        channelName: 'router-handler'
        radioEvents:
          'distribution:home': 'routeDistributionHome'
          'distribution:list': 'routeDistributionList'
        routeDistributionHome: ->
          CDSCeunes.navigate 'distributions'
          @router.controller.home()
          return
        routeDistributionList: (args) ->
          CDSCeunes.navigate "distributions/show/#{args.year}/#{args.semester}"
          @router.controller.list args
          return
      )

  Radios.Routers
