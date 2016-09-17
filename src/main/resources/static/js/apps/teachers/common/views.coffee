define [
  'cs!app'
  'handlebars'
  'text!apps/teachers/common/templates/form.hbs'
  'backbone.syphon'
  'jquery-ui'
], (CDSCeunes, Handlebars, formTpl) ->
  CDSCeunes.module 'TeachersApp.Common.Views', (Views, CDSCeunes, Backbone, Marionette, $, _) ->
    Views.Form = Marionette.LayoutView.extend(
      template: Handlebars.compile(formTpl)
      ui:
        'admissionDate': '.datepicker'
        'submitData': '.js-submit-teacher'
      events: 'click @ui.submitData': 'submitData'
      serializeData: ->
        {
          model: @model.toJSON()
          departments: @options.departments.toJSON()
        }
      onShow: ->
        @ui.admissionDate.datepicker
          dateFormat: 'yy-mm-dd'
          defaultDate: 0
          showAnim: 'drop'
        return
      submitData: (e) ->
        e.preventDefault()
        data = Backbone.Syphon.serialize(this)
        console.log data
        @trigger 'teacher:form:submit', data
        return
    )
    return
  CDSCeunes.TeachersApp.Common.Views
