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
      modelEvents:
        'change:teacher': 'doTeacher'
      doTeacher: ->
        @render()
        return
      selectTeacher: (e) ->
        e.preventDefault()
        teacher = @model.get('teacher')
        args =
          id: @model.id
          name: @model.get('discipline').name
          teacher: if teacher then teacher.id else -1
        @triggerMethod 'select:teacher', args
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
          selected: @options.selected
        }
      ui:
        'radio-checked': ".form-teacher-selector input[name='teacher']:checked"
      events:
        "click input[type='submit']": 'saveSelect'
        'change .js-select-teacher': 'changeSelect'
        'change input[type=radio][name=teacher]': 'changeRadio'
      changeRadio: (e) ->
        e.preventDefault()
        radio = $("input[name=teacher]:checked", ".form-teacher-selector").val()
        if radio != "-1"
          $('.js-select-teacher option[value=-1]').attr('selected', true)
        return
      changeSelect: (e) ->
        e.preventDefault()
        radio = $("input[name=teacher]:checked", ".form-teacher-selector").val()
        if radio != "-1"
          $("\#radio#{radio}").attr('checked', false)
          $("\#radio-1").attr('checked', true)
        return
      saveSelect: (e) ->
        e.preventDefault()
        selected = $("input[name=teacher]:checked", ".form-teacher-selector").val()
        if selected == "-1"
          selected =  $('.js-select-teacher').val()
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
      serializeData: ->
        _.extend @model.toJSON(), workload: @model.workload()
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
