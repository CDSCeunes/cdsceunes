define [
  'app'
  'handlebars'
  'text!apps/positions/common/templates/form.hbs'
  'backbone.syphon'
  'jquery-ui'
], (CDSCeunes, Handlebars, formTpl) ->
  CDSCeunes.module 'PositionsApp.Common.Views', (Views, CDSCeunes, Backbone, Marionette, $, _) ->
    Views.Form = Marionette.ItemView.extend(
      template: Handlebars.compile(formTpl)
      ui: 'submitData': '.js-submit-position'
      events: 'click @ui.submitData': 'submitData'
      submitData: (e) ->
        e.preventDefault()
        data = Backbone.Syphon.serialize(this)
        @trigger 'position:form:submit', data
        return
    )
    return
  CDSCeunes.PositionsApp.Common.Views
