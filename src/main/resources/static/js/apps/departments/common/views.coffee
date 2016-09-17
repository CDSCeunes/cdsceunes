define [
  'cs!app'
  'handlebars'
  'text!apps/departments/common/templates/form.hbs'
  'backbone.syphon'
  'jquery-ui'
], (CDSCeunes, Handlebars, formTpl) ->
  CDSCeunes.module 'DepartmentsApp.Common.Views', (Views, CDSCeunes, Backbone, Marionette, $, _) ->
    Views.Form = Marionette.ItemView.extend(
      template: Handlebars.compile(formTpl)
      ui: 'submitData': '.js-submit-department'
      events: 'click @ui.submitData': 'submitData'
      submitData: (e) ->
        e.preventDefault()
        data = Backbone.Syphon.serialize(this)
        @trigger 'department:form:submit', data
        return
    )
    return
  CDSCeunes.DepartmentsApp.Common.Views
