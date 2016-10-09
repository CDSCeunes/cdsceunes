define [
  "cs!app"
  "marionette"
], (CDSCeunes, Marionette) ->
  View = ->
    Layout = Marionette.View.extend(
      template: 'teacher/layout'
      regions:
        panelRegion: '#panel-region'
        teachersRegion: '#teachers-region'
    )

    Panel = Marionette.View.extend(
      template: 'teacher/panel'
      className: 'row'
    )

    TeacherItem = Marionette.View.extend(
      template: 'teacher/teacher_item'
      tagName: 'div'
      className: 'row'
    )

    TeacherBody = Marionette.CollectionView.extend(
      id: '#list-teacher-items'
      childView: TeacherItem
    )

    TeacherList = Marionette.View.extend(
      template: 'teacher/teacher_list'
      className: 'list-teacher'
      regions:
        body: '#list-teacher-items'
      onRender: ->
        @showChildView 'body', new (TeacherBody)(collection: @collection)
        return
    )

    Layout: Layout, Panel: Panel, TeachersList: TeacherList

  View()
