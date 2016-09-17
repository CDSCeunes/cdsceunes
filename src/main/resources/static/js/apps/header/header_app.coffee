define [
  'cs!app'
  'cs!apps/header/list/list_controller'
], (CDSCeunes, HeaderController) ->
  CDSCeunes.module 'HeaderApp', (Header, CDSCeunes, Backbone, Marionette, $, _) ->
    API = listHeader: ->
      HeaderController.listHeader()
      return
    CDSCeunes.commands.setHandler 'set:active:header', (name) ->
      HeaderController.setActiveHeader name
      return
    Header.on 'start', ->
      console.log 'header inicializado'
      API.listHeader()
      return
    return
  CDSCeunes.HeaderApp
