define [
  'cs!app'
  'cs!apps/distributions/common/views'
], (CDSCeunes, CommonViews) ->
  CDSCeunes.module 'DistributionsApp.New.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    View.Distribution = CommonViews.Form.extend(
      title: 'Nova Distribuição'
      onRender: ->
        @$('.js-submit-distribution').text 'Enviar'
        return
    )
    return
  CDSCeunes.DistributionsApp.New.View
