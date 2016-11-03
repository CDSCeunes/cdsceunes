define [
  'cs!app'
  'handlebars'
  'text!apps/main/templates/layout.hbs'
  'text!apps/main/templates/panel.hbs'
  'text!apps/main/templates/main.hbs'
], (CDSCeunes, Handlebars, layoutTpl, panelTpl, mainTpl) ->
  CDSCeunes.module 'MainApp.List.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    LockEdit = false

    View.Layout = Marionette.LayoutView.extend(
      template: Handlebars.compile(layoutTpl)
      regions:
        panelRegion: '#panel-region'
        disciplinesRegion: '#disciplines-region')

    View.Panel = Marionette.ItemView.extend(
      template: Handlebars.compile(panelTpl))

    View.Distribution = Marionette.ItemView.extend(
      className: 'row'
      template: Handlebars.compile(mainTpl)
      triggers:
        'click 1': 'main:disciplines'
        'click 2': 'main:teachers'
        'click 3': 'main:departments'
        'click 4': 'main:distributions'
        'click 5': 'main:distributions:new'
        'click 6': 'main:positions'
        'click 7': 'main:preferences')

    return
  CDSCeunes.Main.App.List.View
