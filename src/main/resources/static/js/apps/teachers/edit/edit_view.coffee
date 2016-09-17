define [
  'cs!app'
  'cs!apps/teachers/common/views'
], (CDSCeunes, CommonViews) ->
  CDSCeunes.module 'TeachersApp.Edit.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    View.Teacher = CommonViews.Form.extend(
      title: 'Editar professor'
      onRender: ->
        @$('.js-submit-teacher').text 'Atualizar'
        return
      templateHelpers: isDepartment: (id) ->
        @model.department.id == id
    )
    return
  CDSCeunes.TeachersApp.Edit.View
