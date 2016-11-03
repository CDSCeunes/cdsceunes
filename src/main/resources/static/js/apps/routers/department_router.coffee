define [
  'cs!app'
  'marionette'
  'cs!apps/controllers/department_controller'
  'cs!apps/radios/routers/department_handler'
], (CDSCeunes, Marionette, DepartmentController, Handler) ->
  instance = undefined
  Router =
    Department: Marionette.AppRouter.extend(
      controller: new (DepartmentController)
      appRoutes:
        'departments(/filter/criterion::criterion)' : 'list'
      onRoute: (name, path, args) ->
        console.log "User navigated to #{path}"
        CDSCeunes.configureRequest(undefined)
        return
      initialize: () ->
        @router = new (Handler.Department)(router: this)
        return
    )

  CDSCeunes.on 'before:start', ->
    console.log 'Starting DepartmentRouter'
    new (Router.Department)
    return

  Router
