define [
  'cs!app'
  'marionette'
  'cs!apps/controllers/login_controller'
  'cs!apps/radios/routers/login_handler'
], (CDSCeunes, Marionette, LoginController, Handler) ->
  Router =
    Login: Marionette.AppRouter.extend(
      controller: new (LoginController)
      appRoutes:
        'login': 'login'
      onRoute: (name, path, args) ->
        console.log "name: #{name}, path: #{path}, args: #{args}"
        #
        return
      initialize: ->
        @handler = new (Handler.Login)(router: this)
        return
    )

  CDSCeunes.on 'before:start', ->
    console.log 'Starting LoginRouter'
    new (Router.Login)
    return



  Router
