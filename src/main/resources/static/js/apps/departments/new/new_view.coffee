define [
  'app'
  'apps/departments/common/views'
], (CDSCeunes, CommonViews) ->
  CDSCeunes.module 'DepartmentsApp.New.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    View.Department = CommonViews.Form.extend(
      title: 'Novo Departamento'
      onRender: ->
        @$('.js-submit-department').text 'Enviar'
        return
    )
    return
  CDSCeunes.DepartmentsApp.New.View
