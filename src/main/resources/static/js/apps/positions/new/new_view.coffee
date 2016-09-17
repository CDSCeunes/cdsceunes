define [
  'cs!app'
  'cs!apps/positions/common/views'
], (CDSCeunes, CommonViews) ->
  CDSCeunes.module 'PositionsApp.New.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    View.Positions = CommonViews.Form.extend(
      title: 'Novo cargo'
      onRender: ->
        @$('.js-submit-position').text 'Enviar'
        return
    )
    return
  CDSCeunes.PositionsApp.New.View
