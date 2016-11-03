define [
  'cs!app'
  'marionette'
  'cs!apps/controllers/discipline_controller'
  'cs!apps/radios/routers/discipline_handler'
], (CDSCeunes, Marionette, DisciplineController, Handler) ->
  instance = undefined
  Router =
    Discipline: Marionette.AppRouter.extend(
      controller: new (DisciplineController)
      appRoutes:
        'disciplines(/filter/criterion::criterion)' : 'list'
      onRoute: (name, path, args) ->
        console.log "User navigated to #{path}"
        CDSCeunes.configureRequest(undefined)
        return
      initialize: () ->
        @router = new (Handler.Discipline)(router: this)
        return
    )

  CDSCeunes.on 'before:start', ->
    console.log 'Starting DisciplineRouter'
    new (Router.Discipline)
    return

  Router
