define [
  'cs!app'
  'marionette'
  'cs!apps/views/login_view'
  'jquery'
], (CDSCeunes, Marionette, View, $, Radio) ->
  Controller =
    Login: Marionette.Object.extend(
      login: ->
        loginView = new (View.Layout)
        loginView.on 'childview:login:home', ->
        loginView.on 'login:auth', (user, pass) ->
          $.ajax(
            type: 'POST'
            url: '/login'
            data:
              username: user
              password: pass
            success: (data, status, xhr) ->
              CDSCeunes.configureRequest(xhr.getResponseHeader('X-AUTH-TOKEN'))
              CDSCeunes.route 'teachers:list'
              return
            error: (xhr) ->
              CDSCeunes.route 'login:home'
              return
          )

        CDSCeunes.regions.showChildView 'main', loginView
        return
    )

  Controller.Login
