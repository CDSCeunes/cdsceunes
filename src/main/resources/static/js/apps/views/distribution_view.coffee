define [
  'cs!app'
  'marionette'
], (CDSCeunes, Marionette) ->
  View = ->
    Header = Marionette.View.extend(
      template: 'distribution/header'
    )

    DistributionItem = Marionette.View.extend(
      template: 'distribution/distrib-item'
      className: 'row'
      ui:
        button: 'a.distrib-select-teacher'
      events:
        'click a.distrib-select-teacher': 'selectTeacher'
      selectTeacher: (e) ->
        e.preventDefault()
        @triggerMethod 'select:teacher', { id: @model.id, name: @model.get('discipline').name }
        return
    )

    DistributionSelect = Marionette.View.extend(
      template: 'distribution/distrib-select'
      title: 'Selecionar professor'
      serializeData: ->
        {
          model: @model.sort().toJSON()
          name: @options.name.toJSON()
        }
      events:
        "click input[type='submit']": 'saveSelect'
      saveSelect: (e) ->
        e.preventDefault()
        selected = $('input[name=teacher]:checked', '.form-teacher-selector').val()
        @trigger 'save:select', { selected: selected, class_id: @options.class_id }
    )

    DistributionList = Marionette.CollectionView.extend(
      childView: DistributionItem
      id: 'distribution-list-item'
      onSelectTeacher: (args) ->
        @triggerMethod 'select:teacher', args
        return

    )

    DistributionListLayout = Marionette.View.extend(
      template: 'distribution/distrib-list'
      regions:
        body: '#distribution-list-items'
      onRender: ->
        @showChildView 'body', new (DistributionList)(collection: @collection)
    )

    Layout = Marionette.View.extend(
      template: 'distribution/layout'
      regions:
        header: '#distribution-header'
        body: '#distribution-body'
      onRender: ->
        @showChildView 'header', new (Header)
        return
    )

    Layout: Layout, DistributionList: DistributionListLayout, DistributionSelect: DistributionSelect

  View()
