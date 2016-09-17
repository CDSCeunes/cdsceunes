define [
  'app'
  'apps/disciplines/common/views'
], (CDSCeunes, CommonViews) ->
  CDSCeunes.module 'DisciplinesApp.Edit.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    View.Discipline = CommonViews.Form.extend(
      title: 'Editar disciplina'
      onRender: ->
        @$('.js-submit-discipline').text 'Atualizar'
        return
    )
    return
  CDSCeunes.DisciplinesApp.Edit.View
