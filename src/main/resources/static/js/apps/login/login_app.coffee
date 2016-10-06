define [ 'cs!app' ], (CDSCeunes) ->
  CDSCeunes.module 'Routers.LoginApp', (LoginApp, CDSCeunes, Backbone, Marionette, $, _) ->
    LoginApp.Router = Marionette.AppRouter.extend(appRoutes: 'login': 'loginHome')

    executeAction = (action, arg) ->
      CDSCeunes.startSubApp 'LoginApp'
      action arg
      #CDSCeunes.execute()
      return

    API = loginHome: ->
      require [ 'cs!apps/login/index/index_controller' ], (Controller) ->
        CDSCeunes.startSubApp 'LoginApp'
        Controller.loginHome()
        return
      return
    CDSCeunes.on 'login:home', ->
      CDSCeunes.deleteKey()
      CDSCeunes.navigate 'login'
      API.loginHome()
      return
    CDSCeunes.Routers.on 'start', ->
      console.log 'Iniciado routers'
      new (LoginApp.Router)(controller: API)
      return
    return
  CDSCeunes.LoginApp
