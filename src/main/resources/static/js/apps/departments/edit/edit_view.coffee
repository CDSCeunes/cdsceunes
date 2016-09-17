define [
  'cs!app'
  'cs!apps/departments/common/views'
], (CDSCeunes, CommonViews) ->
  CDSCeunes.module 'DepartmentsApp.Edit.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    View.Department = CommonViews.Form.extend(
      title: 'Editar departamento'
      onRender: ->
        @$('.js-submit-department').text 'Atualizar'
        return
    )
    return
  CDSCeunes.DepartmentsApp.Edit.View
