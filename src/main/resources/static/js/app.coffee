define [
  'marionette'
  'handlebars'
  'jquery'
  'jquery-ui'
  'backbone.syphon'
], (Marionette, Handlebars, $) ->
  CDSCeunes = new (Marionette.Application)

  API_Token =
    setToken: (token) ->
      localStorage.setItem 'token', token
      return
    verifyTokenExistance: ->
      token = localStorage.getItem 'token'
      return token != null
    configureRequest: (token) ->
      if token == undefined
        token = localStorage.getItem 'token'
        console.log token
        if token == null
          CDSCeunes.trigger 'login:home'
          return
      else
        @setToken token
      $.ajaxSetup headers: 'X-AUTH-TOKEN': token
      return

  Marionette.TemplateCache::compileTemplate = (templateText) ->
    Handlebars.compile templateText

  Backbone.Syphon.InputReaders.register 'checkbox', ($el) ->
    if $el.prop('checked') then true else false

  CDSCeunes.navigate = (route, options) ->
    options or (options = {})
    Backbone.history.navigate route, options
    return

  CDSCeunes.getCurrentRoute = ->
    Backbone.history.fragment

  CDSCeunes.startSubApp = (appName, args) ->
    currentApp = if appName then CDSCeunes.module(appName) else null
    if CDSCeunes.currentApp == currentApp
      return
    if CDSCeunes.currentApp
      CDSCeunes.currentApp.stop()
    CDSCeunes.currentApp = currentApp
    if currentApp
      currentApp.start args
    return

  CDSCeunes.configureRequest = (token) ->
    API_Token.configureRequest(token)

  CDSCeunes.on 'before:start', ->
    RootContainer = Marionette.LayoutView.extend(
      el: '#app-container'
      regions:
        header: '#header-container'
        main: '#main-container'
        dialog: '#dialog-container'
        footer: '#footer-container')
    CDSCeunes.regions = new RootContainer

    CDSCeunes.regions.dialog.onShow = (view) ->
      self = this

      closeDialog = ->
        self.stopListening()
        self.empty()
        self.$el.dialog 'destroy'
        return

      @listenTo view, 'dialog:close', closeDialog
      @$el.dialog
        modal: true
        title: view.title
        width: 'auto'
        maxWidth: 600
        fluid: true
        responsive: true
        resizable: false
        close: (e, ui) ->
          closeDialog()
          return
      return

    return


  CDSCeunes.on 'start', ->
    if Backbone.history
      Backbone.history.start()
      if CDSCeunes.getCurrentRoute() == ''
        CDSCeunes.trigger 'login:home'
    return
  CDSCeunes
