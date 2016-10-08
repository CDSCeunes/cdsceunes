define [
  'cs!app'
  'marionette'
  'cs!apps/controllers/distribution_controller'
  'cs!apps/radios/routers/distribution_handler'
], (CDSCeunes, Marionette, DistributionController, Handler) ->
  Router =
    Distribution: Marionette.AppRouter.extend(
      appRoutes:
        'distributions': 'home'
        'distributions/show/:year/:semester': 'list'
      controller: new (DistributionController)
      onRoute: (path, name, args) ->
        CDSCeunes.configureRequest(undefined)
        return
      initialize: ->
        @handler = new (Handler.Distribution)(router: this)
        return
    )

  CDSCeunes.on 'before:start', ->
    console.log 'Starting DistributionRouter'
    new (Router.Distribution)
    return

  Router
