define [
  'marionette'
  'nunjucks'
  'jquery'
  'cs!utils/secure'
  'cs!utils/error'
  'backbone.radio'
  'jquery-ui'
  'backbone'
  'backbone.syphon'
#  'cs!apps/config/data_handlers'
], (Marionette, nunjucks, $, Secure, Error, Radio) ->
  CDSCeunes = new (Marionette.Application)

  #Marionette.TemplateCache::compileTemplate = (templateText) ->
  #  nunjucks.configure('apps/templates', autoescape: true)
  #  nunjucks.render(templateText)

  nunjucks.configure 'js/apps/templates', autoescape: true, web: { useCache: true }

  Marionette.Renderer.render = (template, data) ->
    nunjucks.render("#{template}.html.njk", data)

  Backbone.Syphon.InputReaders.register 'checkbox', ($el) ->
    if $el.prop('checked') then true else false

  Marionette.View::mixinTemplateHelpers = (target) ->
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

  CDSCeunes.dataRequest = (trigger, args) ->
    console.log 'Data Request'
    Radio.channel('data-request').request trigger, args

  CDSCeunes.oldRoute = ( ->
    route = ''
    get = ->
      route
    set = (r) ->
      route = r
      return
    get: get, set: set
  )()

  CDSCeunes.route = (trigger) ->
    console.log 'Triggering route'
    if trigger != CDSCeunes.oldRoute.get()
      console.log 'Configuring'
      CDSCeunes.configureRequest(undefined)
    CDSCeunes.oldRoute.set trigger
    Radio.channel('router-handler').trigger trigger
    return

  CDSCeunes.navigate = (route, options) ->
    options or (options = {})
    Backbone.history.navigate route, options
    return

  CDSCeunes.getCurrentRoute = ->
    Backbone.history.fragment

  CDSCeunes.startSubApp = (app, args) ->
    currentApp = if app.name then app else null
    if CDSCeunes.currentApp.name == currentApp.name
      return
    if CDSCeunes.currentApp.obj
      CDSCeunes.currentApp.obj.stop()
    CDSCeunes.currentApp = currentApp
    if currentApp
      currentApp.obj.start args
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

    DialogRegion = Marionette.Region.extend(
      closeDialog: ->
        @$el.dialog 'destroy'
        return
      onEmpty: ->
        @closeDialog()
        return
      onShow: (view) ->
        self = this
        @$el.dialog
          modal: true
          title: view.title
          width: 'auto'
          maxWidth: 600
          fluid: true
          responsive: true
          resizable: false
          close: (e, ui) ->
            self.empty()
            return
        return
    )

    RootContainer = Marionette.View.extend(
      el: '#app-container'
      regions:
        header: '#header-container'
        main: '#main-container'
        dialog:
          el: '#dialog-container'
          regionClass: DialogRegion
        footer: '#footer-container'
    )

    CDSCeunes.regions = new RootContainer
    return

  CDSCeunes.on 'start', ->
    #CDSCeunes.Secure.removeToken
    console.log "Application starting"
    CDSCeunes.configureRequest(undefined)
    if Backbone.history
      Backbone.history.start()
      console.log 'started'
      if CDSCeunes.getCurrentRoute() == ''
        CDSCeunes.route 'login:home'
    return
  CDSCeunes
