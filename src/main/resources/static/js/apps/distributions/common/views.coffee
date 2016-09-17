define [
  'cs!app'
  'handlebars'
  'text!apps/distributions/common/templates/form.hbs'
  'text!apps/distributions/common/templates/layout.hbs'
  'text!apps/distributions/common/templates/panel.hbs'
  'backbone.syphon'
  'jquery-ui'
], (CDSCeunes, Handlebars, formTpl, layoutTpl, panelTpl) ->
  CDSCeunes.module 'DistributionsApp.Common.Views', (Views, CDSCeunes, Backbone, Marionette, $, _) ->
    Views.Form = Marionette.ItemView.extend(
      template: Handlebars.compile(formTpl)
      ui: 'submitData': '.js-submit-distribution'
      events: 'click @ui.submitData': 'submitData'
      serializeData: ->
        {
          model: @model.toJSON()
          teachers: @options.teachers.toJSON()
          disciplines: @option.disciplines.toJSON()
        }
      submitData: (e) ->
        e.preventDefault()
        data = Backbone.Syphon.serialize(this)
        console.log data
        @trigger 'distribution:form:submit', data
        return
    )
    Views.Layout = Marionette.LayoutView.extend(
      template: Handlebars.compile(layoutTpl)
      regions:
        panelRegion: '#panel-region'
        distributionsRegion: '#distributions-region')
    Views.Panel = Marionette.ItemView.extend(template: Handlebars.compile(panelTpl))
    return
  CDSCeunes.DistributionsApp.Common.Views
