define [], ->
  Secure = (module) ->
    secure =
      token: localStorage.getItem 'token'
      setToken: (token) ->
        @token = token
        localStorage.setItem 'token', @token
        return
      refreshToken: ->
        @token = localStorage.getItem 'token'
        return
      removeToken: ->
        localStorage.removeItem 'token'
        @refreshToken()
        if ($.ajaxSettings.headers)
          delete $.ajaxSettings.headers['X-AUTH-TOKEN']
        return
      configureRequest: (token) ->
        if @token == null
          if token != undefined
            @setToken token
          else
            module.route 'login:home'
            return
        else
          if token != undefined
            @setToken token
        $.ajaxSetup headers: 'X-AUTH-TOKEN': @token

        return



    secure


  Secure
