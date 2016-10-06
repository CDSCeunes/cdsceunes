define [
  'marionette'
  'handlebars'
  'jquery'
  'cs!utils/secure'
  'cs!utils/error'
  'jquery-ui'
  'backbone'
  'backbone.syphon'
], (Marionette, Handlebars, $, Secure, Error) ->
  CDSCeunes = new (Marionette.Application)

  Marionette.TemplateCache::compileTemplate = (templateText) ->
    Handlebars.compile templateText

  Backbone.Syphon.InputReaders.register 'checkbox', ($el) ->
    if $el.prop('checked') then true else false

  Marionette.ItemView::mixinTemplateHelpers = (target) ->
    self = this
    templateHelpers = Marionette.getOption(self, 'templateHelpers')
    result = {}
    target = target or {}
    if _.isFunction(templateHelpers)
      templateHelpers = templateHelpers.call(self)
      # This _.each block is what we're adding
    _.each templateHelpers, (helper, index) ->
      if _.isFunction(helper)
        result[index] = helper.call(self)
      else
        result[index] = helper
      return
    _.extend target, result

  CDSCeunes.Secure = Secure(CDSCeunes)

  Backbone.sync = Error(CDSCeunes)


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
      CDSCeunes.Secure.configureRequest(undefined)
    return

  CDSCeunes.deleteKey = ->
    console.log "Removing token"
    CDSCeunes.Secure.removeToken()
    return


  CDSCeunes.configureRequest = (token) ->
    #API_Token.configureRequest(token)
    CDSCeunes.Secure.configureRequest(token)
    return

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
    #CDSCeunes.Secure.removeToken
    console.log "Application starting"
    if Backbone.history
      Backbone.history.start()
      if CDSCeunes.getCurrentRoute() == ''
        CDSCeunes.trigger 'login:home'
    return
  CDSCeunes
