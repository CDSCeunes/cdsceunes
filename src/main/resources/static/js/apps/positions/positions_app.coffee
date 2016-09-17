define [ 'cs!app' ], (CDSCeunes) ->
  CDSCeunes.module 'Routers.PositionsApp', (PositionsAppRouter, CDSCeunes, Backbone, Marionette, $, _) ->
    PositionsAppRouter.Router = Marionette.AppRouter.extend(appRoutes:
      'positions': 'listPositions'
      'positions(/filter/criterion::criterion)': 'listPositions')

    executeAction = (action, arg) ->
      CDSCeunes.startSubApp 'PositionsApp'
      action arg
      return

    API = listPositions: (criterion) ->
      require [ 'cs!apps/positions/list/list_controller' ], (ListController) ->
        executeAction ListController.listPositions, criterion
        return
      return
    CDSCeunes.on 'positions:list', ->
      CDSCeunes.navigate 'positions'
      API.listPositions()
      return
    CDSCeunes.on 'contacts:filter', (criterion) ->
      if criterion
        CDSCeunes.navigate 'positions/filter/criterion:' + criterion
      else
        CDSCeunes.navigate 'positions'
      return
    CDSCeunes.Routers.on 'start', ->
      console.log 'Positions Router'
      new (PositionsAppRouter.Router)(controller: API)
      return
    return
  CDSCeunes.PositionsAppRouter
