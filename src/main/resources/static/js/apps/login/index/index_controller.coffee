define [
  'cs!app'
  'cs!apps/login/index/index_view'
  'q'
], (CDSCeunes, View, Q) ->
  CDSCeunes.module 'LoginApp.Index', (Index, CDSCeunes, Backbone, Marionette, $, _) ->
    Index.Controller = loginHome: ->
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
            CDSCeunes.trigger 'teachers:list'
            return
          error: (xhr) ->
            CDSCeunes.trigger 'login:home'
            return
        )

        return
      CDSCeunes.regions.main.show loginView
      return
    return
  CDSCeunes.LoginApp.Index.Controller
