define [
  "cs!app"
  "marionette"
], (CDSCeunes, Marionette) ->
  View = ->
    Layout = Marionette.View.extend(
      template: 'position/layout'
      regions:
        panelRegion: '#panel-region'
        positionsRegion: '#positions-region'
    )

    Panel = Marionette.View.extend(
      template: 'position/panel'
      className: 'row'
      events:
        'click button.js-new-position': 'newPosition'
      newPosition: (e) ->
        e.preventDefault()
        console.log 'new position'
        @triggerMethod 'position:new'
        return
    )

    Form = Marionette.View.extend(
      template: 'position/form'
      ui:'submitData': '.js-submit-position'
      title: 'Novo Cargo'
      submitData: (e) ->
        e.preventDefault()
        data = Backbone.Syphon.serialize(this)
        console.log data
        @trigger 'position:form:submit', data
        return
    )

    PositionItem = Marionette.View.extend(
      template: 'position/position_item'
      tagName: 'div'
      className: 'row'
    )

    PositionBody = Marionette.CollectionView.extend(
      id: '#list-position-items'
      childView: PositionItem
    )

    PositionList = Marionette.View.extend(
      template: 'position/position_list'
      className: 'list-position'
      regions:
        body: '#list-position-items'
      onRender: ->
        @showChildView 'body', new (PositionBody)(collection: @collection)
        return
    )

    Layout: Layout, Panel: Panel, PositionsList: PositionList, Form: Form

  View()
