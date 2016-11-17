define [
  'cs!app'
  'marionette'
  'cs!apps/controllers/main_controller'
  'cs!apps/radios/routers/main_handler'
], (CDSCeunes, Marionette, MainController, Handler) ->
  Router =
    Main: Marionette.AppRouter.extend(
      controller: new (MainController)
      appRoutes:
        'main': 'loadPage'
      onRoute: (name, path, args) ->
        CDSCeunes.configureRequest(undefined)
        console.log "name: #{name}, path: #{path}, args: #{args}"
        return
      initialize: () ->
        @router = new (Handler.Main)(router: this)
        return
    )

  CDSCeunes.on 'before:start', ->
    console.log 'Starting MainRouter'
    new (Router.Main)
    return

  Router
