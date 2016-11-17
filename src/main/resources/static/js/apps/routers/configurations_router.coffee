define [
  'cs!app'
  'marionette'
  'cs!apps/controllers/configurations_controller'
  'cs!apps/radios/routers/configurations_handler'
], (CDSCeunes, Marionette, ConfigurationsController, Handler) ->
  Router =
    Configurations: Marionette.AppRouter.extend(
      controller: new (ConfigurationsController)
      appRoutes:
        'configurations': 'config'
      onRoute: (name, path, args) ->
        CDSCeunes.configureRequest(undefined)
        console.log "name: #{name}, path: #{path}, args: #{args}"
        return
      initialize: () ->
        @router = new (Handler.Configurations)(router: this)
        return
    )

  CDSCeunes.on 'before:start', ->
    console.log 'Starting ConfigurationsRouter'
    new (Router.Configurations)
    return

  Router
