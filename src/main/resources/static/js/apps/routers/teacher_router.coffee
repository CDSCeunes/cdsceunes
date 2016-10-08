define [
  'cs!app'
  'marionette'
  'cs!apps/controllers/teacher_controller'
  'cs!apps/radios/routers/teacher_handler'
], (CDSCeunes, Marionette, TeacherController, Handler) ->
  instance = undefined
  Router =
    Teacher: Marionette.AppRouter.extend(
      controller: new (TeacherController)
      appRoutes:
        'teachers(/filter/criterion::criterion)' : 'list'
      onRoute: (name, path, args) ->
        console.log "User navigated to #{path}"
        CDSCeunes.configureRequest(undefined)
        return
      initialize: () ->
        @router = new (Handler.Teacher)(router: this)
        return
    )

  CDSCeunes.on 'before:start', ->
    console.log 'Starting TeacherRouter'
    new (Router.Teacher)
    return

  Router
