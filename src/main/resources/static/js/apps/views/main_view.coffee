define [
  'cs!app'
  'marionette'
], (CDSCeunes, Marionette) ->
  View = ->
    Layout = Marionette.View.extend(
      template: 'main/layout'
      regions:
        panelRegion: '#panel-region'
        formRegion: '#form-region'
    )
    Panel = Marionette.View.extend(
      template: 'main/panel'
    )
    Form = Marionette.View.extend(
      template: 'main/form'
      className = 'row'
    )

    Layout: Layout, Panel: Panel, Form: Form

  View()
