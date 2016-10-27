define [
  'cs!app'
  'marionette'
  'cs!apps/controllers/position_controller'
  'cs!apps/radios/routers/position_handler'
], (CDSCeunes, Marionette, PositionController, Handler) ->
  instance = undefined
  Router =
    Position: Marionette.AppRouter.extend(
      controller: new (PositionController)
      appRoutes:
        'positions(/filter/criterion::criterion)' : 'list'
      onRoute: (name, path, args) ->
        console.log "User navigated to #{path}"
        CDSCeunes.configureRequest(undefined)
        return
      initialize: () ->
        @router = new (Handler.Position)(router: this)
        return
    )

  CDSCeunes.on 'before:start', ->
    console.log 'Starting PositionRouter'
    new (Router.Position)
    return

  Router
