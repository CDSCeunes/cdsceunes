define [
  'cs!app'
  'cs!apps/positions/common/views'
], (CDSCeunes, CommonViews) ->
  CDSCeunes.module 'PositionsApp.Edit.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    View.Positions = CommonViews.Form.extend(
      title: 'Editar cargo'
      onRender: ->
        @$('.js-submit-position').text 'Atualizar'
        return
    )
    return
  CDSCeunes.PositionsApp.Edit.View
