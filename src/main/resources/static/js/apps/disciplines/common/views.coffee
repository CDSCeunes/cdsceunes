define [
  'app'
  'handlebars'
  'text!apps/disciplines/common/templates/form.hbs'
  'backbone.syphon'
  'jquery-ui'
], (CDSCeunes, Handlebars, formTpl) ->
  CDSCeunes.module 'DisciplinesApp.Common.Views', (Views, CDSCeunes, Backbone, Marionette, $, _) ->
    Views.Form = Marionette.ItemView.extend(
      template: Handlebars.compile(formTpl)
      ui: 'submitData': '.js-submit-discipline'
      events: 'click @ui.submitData': 'submitData'
      submitData: (e) ->
        e.preventDefault()
        data = Backbone.Syphon.serialize(this)
        @trigger 'discipline:form:submit', data
        return
    )
    return
  CDSCeunes.DisciplinesApp.Common.Views
