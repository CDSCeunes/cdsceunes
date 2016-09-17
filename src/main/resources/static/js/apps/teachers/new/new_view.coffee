define [
  'app'
  'apps/teachers/common/views'
], (CDSCeunes, CommonViews) ->
  CDSCeunes.module 'TeachersApp.New.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    View.Teacher = CommonViews.Form.extend(
      title: 'Novo Professor'
      onRender: ->
        @$('.js-submit-teacher').text 'Enviar'
        return
    )
    return
  CDSCeunes.TeachersApp.New.View
