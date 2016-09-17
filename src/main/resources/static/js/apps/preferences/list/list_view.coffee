define [
  'cs!app'
  'handlebars'
  'text!apps/preferences/list/templates/layout.hbs'
  'text!apps/preferences/list/templates/panel.hbs'
  'text!apps/preferences/list/templates/preferences_rows.hbs'
  'text!apps/preferences/list/templates/preferences_item.hbs'
], (CDSCeunes, Handlebars, layoutTpl, panelTpl, rowsTpl, itemTpl) ->
  CDSCeunes.module 'PreferencesApp.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    View.Layout = Marionette.LayoutView.extend(
      template: Handlebars.compile(layoutTpl)
      regions:
        panel: '#panel-region'
        main: '#preferences-region')
    View.Panel = Marionette.ItemView.extend(
      template: Handlebars.compile(panelTpl)
      triggers: 'click .js-new-preference': 'preference:new')
    View.Row = Marionette.ItemView.extend(
      template: Handlebars.compile(itemTpl)
      className: 'row')
    View.Rows = Marionette.CompositeView.extend(
      template: Handlebars.compile(rowsTpl)
      childView: View.Row
      childViewContainer: '#preferences-rows')
    return
  CDSCeunes.PreferencesApp.View
