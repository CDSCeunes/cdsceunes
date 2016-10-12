define [], ->
  Secure = (module) ->
    secure = ->
      _token = localStorage.getItem 'token'
      setToken = (token) ->
        _token = token
        localStorage.setItem 'token', _token
        return
      refreshToken = ->
        _token = localStorage.getItem 'token'
        return
      removeToken = ->
        localStorage.removeItem 'token'
        @refreshToken()
        if ($.ajaxSettings.headers)
          delete $.ajaxSettings.headers['X-AUTH-TOKEN']
        return
      configureRequest = (token) ->
        @refreshToken()
        console.log _token
        if _token == null
          if token != undefined
            @setToken token
          else
            console.log _token
            @removeToken()
            console.log "route is #{module.getCurrentRoute()}"
            unless module.getCurrentRoute() in [undefined, '', 'login']
              module.route 'login:home'
            return
        else
          if token != undefined
            @setToken token
        $.ajaxSetup headers: 'X-AUTH-TOKEN': _token

        return

      { setToken: setToken, removeToken: removeToken, refreshToken: refreshToken,
      configureRequest: configureRequest }



    secure()


  Secure
