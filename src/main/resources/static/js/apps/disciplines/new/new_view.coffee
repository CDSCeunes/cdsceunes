define [
  'cs!app'
  'cs!apps/disciplines/common/views'
], (CDSCeunes, CommonViews) ->
  CDSCeunes.module 'DisciplinesApp.New.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    View.Discipline = CommonViews.Form.extend(
      title: 'Nova disciplina'
      onRender: ->
        @$('.js-submit-discipline').text 'Enviar'
        return
    )
    return
  CDSCeunes.DisciplinesApp.New.View
