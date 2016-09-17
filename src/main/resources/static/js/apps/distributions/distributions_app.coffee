define [ 'app' ], (CDSCeunes) ->
  CDSCeunes.module 'Routers.DistributionsApp', (DistributionsAppRouter, CDSCeunes, Backbone, Marionette, $, _) ->
    DistributionsAppRouter.Router = Marionette.AppRouter.extend(appRoutes:
      'distributions': 'listDistributions'
      'distributions/:id': 'showScenario'
      'distributions(/filter/criterion::criterion)': 'listDistributions'
      'distributions/new': 'newDistribution')

    executeAction = (action, arg) ->
      CDSCeunes.startSubApp 'DistributionApp'
      action arg
      return

    API = 
      listDistributions: (criterion) ->
        require [ 'apps/distributions/list/list_controller' ], (ListController) ->
          executeAction ListController.listDistributions, criterion
          return
        return
      newDistribution: ->
        require [ 'apps/distributions/new/new_controller' ], (NewController) ->
          executeAction NewController.newDistribution
          return
        return
      showScenario: (id) ->
        require [ 'apps/distributions/show/show_controller' ], (ShowController) ->
          executeAction ShowController.showDistribution
          return
        return
    CDSCeunes.on 'distributions:list', ->
      CDSCeunes.navigate 'distributions'
      API.listDistributions()
      return
    CDSCeunes.on 'distributions:filter', (criterion) ->
      if criterion
        CDSCeunes.navigate 'distributions/filter/criterion:' + criterion
      else
        CDSCeunes.navigate 'distributions'
      return
    CDSCeunes.on 'distributions:new', ->
      CDSCeunes.navigate 'distributions/new'
      API.newDistribution()
      return
    CDSCeunes.Routers.on 'start', ->
      console.log 'Distributions router'
      new (DistributionsAppRouter.Router)(controller: API)
      return
    return
  CDSCeunes.DistributionsAppRouter
