define [
  'cs!app'
  'cs!apps/distributions/common/views'
], (CDSCeunes, CommonViews) ->
  CDSCeunes.module 'DistributionsApp.Edit.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    View.Distribution = CommonViews.Form.extend(
      title: 'Editar distribuição'
      onRender: ->
        @$('.js-submit-distribution').text 'Atualizar'
        return
      templateHelpers:
        isTeacher: (id) ->
          @model.teacher.id == id
        isDiscipline: (id) ->
          @model.discipline.id == id
    )
    return
  CDSCeunes.DistributionsApp.Edit.View
