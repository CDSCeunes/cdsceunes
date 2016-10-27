define [
  "cs!app"
  "marionette"
], (CDSCeunes, Marionette) ->
  View = ->
    Layout = Marionette.View.extend(
      template: 'discipline/layout'
      regions:
        panelRegion: '#panel-region'
        disciplinesRegion: '#disciplines-region'
    )

    Panel = Marionette.View.extend(
      template: 'discipline/panel'
      className: 'row'
      events:
        'click button.js-new-discipline': 'newDiscipline'
      newDiscipline: (e) ->
        e.preventDefault()
        console.log 'new discipline'
        @triggerMethod 'discipline:new'
        return
    )

    Form = Marionette.View.extend(
      template: 'discipline/form'
      ui:
        'admissionDate': '.datepicker'
        'submitData': '.js-submit-discipline'
      title: 'Nova Disciplina'
      submitData: (e) ->
        e.preventDefault()
        data = Backbone.Syphon.serialize(this)
        console.log data
        @trigger 'discipline:form:submit', data
        return
    )

    DisciplineItem = Marionette.View.extend(
      template: 'discipline/discipline_item'
      tagName: 'div'
      className: 'row'
    )

    DisciplineBody = Marionette.CollectionView.extend(
      id: '#list-discipline-items'
      childView: DisciplineItem
    )

    DisciplineList = Marionette.View.extend(
      template: 'discipline/discipline_list'
      className: 'list-discipline'
      regions:
        body: '#list-discipline-items'
      onRender: ->
        @showChildView 'body', new (DisciplineBody)(collection: @collection)
        return
    )

    Layout: Layout, Panel: Panel, DisciplinesList: DisciplineList, Form: Form

  View()
