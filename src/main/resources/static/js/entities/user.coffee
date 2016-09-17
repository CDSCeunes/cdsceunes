define [
  'app'
  'q'
], (CDSCeunes, Q) ->
  CDSCeunes.module 'Entities', (Entities, CDSCeunes, Backbone, Marionette, $, _) ->
    Entities.User = Backbone.Model.extend(
      urlRoot: 'api/v1/auth'
      defaults:
        login: ''
        password: ''
      login: ->
    )
    API = authenticate: (user, password) ->
      `var user`
      console.log 'saving'
      user = new (Entities.User)(
        login: user
        password: password)
      console.log 'saving2'
      Q.promise (resolve) ->
        user.save
          success: (data) ->
            resolve data
            return
          error: (data) ->
            resolve undefined
            return
        return
    CDSCeunes.reqres.setHandler 'user:auth:new', (user, pass) ->
      API.authenticate user, pass
    return
  return
