define [
  'cs!app'
  'marionette'
], (CDSCeunes, Marionette) ->
  View = ->
    Layout = Marionette.View.extend(
      template: 'configurations/layout'
      regions:
        panelRegion: '#panel-region'
        formRegion: '#form-region'
    )

    Panel = Marionette.View.extend(
      template: 'configurations/panel'
      events:
        'click button.js-edit-nameteacher': 'configName'
        'click button.js-edit-passteacher': 'configPass'
      configName: (e) ->
        e.preventDefault()
        console.log 'edit nome'
        @triggerMethod 'teacher:new'
        return
      configPass: (e) ->
        console.log 'showTeacher'
        @triggerMethod 'teacher:show', e.get('id')
        return
    )

    Form = Marionette.View.extend(
      template: 'configurations/form'
      console.log 'formulario'
    )

    Layout: Layout, Panel: Panel, Form: Form

  View()
