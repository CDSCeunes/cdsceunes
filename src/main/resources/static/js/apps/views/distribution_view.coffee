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
          teachers: @options.teachers.toJSON()
        }
      events:
        "click input[type='submit']": 'saveSelect'
      saveSelect: (e) ->
        e.preventDefault()
        selected = $('input[name=teacher]:checked', '.form-teacher-selector').val()
        @trigger 'save:select', { selected: selected, class_id: @options.class_id }
        return
    )

    DistributionList = Marionette.CollectionView.extend(
      childView: DistributionItem
      id: 'distribution-list-item'
      onSelectTeacher: (args) ->
        @triggerMethod 'select:teacher', args
        return

    )

    DistributionTeacherItem = Marionette.View.extend(
      template: 'distribution/distrib-teacher-item'
      className: 'distrib-teacher-item'
    )

    DistributionTeacherList = Marionette.CollectionView.extend(
      id: 'distribution-teacher-items'
      childView: DistributionTeacherItem
    )

    DistributionListLayout = Marionette.View.extend(
      template: 'distribution/distrib-list'
      regions:
        body: '#distribution-list-items'
        teachers: '#distribution-teacher-items'
      onRender: ->
        @showChildView 'body', new (DistributionList)(collection: @collection)
        @showChildView 'teachers', new (DistributionTeacherList)(collection: @options.teachers)
        return
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
