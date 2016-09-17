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
        console.log 'testessss'
        $.ajax(
          type: 'POST'
          url: '/login'
          contentType: 'application/x-www-form-urlencoded'
          data:
            username: user
            password: pass).done (res, status, xhr) ->
          console.log res
          console.log status
          console.log xhr.getResponseHeader('X-AUTH-TOKEN')
          window.token = xhr.getResponseHeader('X-AUTH-TOKEN')
          $.ajaxSetup headers: 'X-AUTH-TOKEN': window.token
          CDSCeunes.trigger 'teachers:list'
          return
        console.log 'triggers'
        console.log user
        console.log pass
        return
      CDSCeunes.regions.main.show loginView
      return
    return
  CDSCeunes.LoginApp.Index.Controller
