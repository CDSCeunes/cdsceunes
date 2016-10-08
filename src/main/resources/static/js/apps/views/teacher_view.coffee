define [
  "cs!app"
  "marionette"
], (CDSCeunes, Marionette) ->
  View = ->
    Layout = Marionette.View.extend(
      template: 'teacher/layout.html'
      regions:
        panelRegion: '#panel-region'
        teachersRegion: '#teachers-region'
    )

    Panel = Marionette.View.extend(
      template: 'teacher/panel.html'
      className: 'row'
    )

    TeacherItem = Marionette.View.extend(
      template: 'teacher/teacher_item.html'
      tagName: 'div'
      className: 'row'
    )

    TeacherBody = Marionette.CollectionView.extend(
      id: '#list-teacher-items'
      childView: TeacherItem
    )

    TeacherList = Marionette.View.extend(
      template: 'teacher/teacher_list.html'
      className: 'list-teacher'
      regions:
        body: '#list-teacher-items'
      onRender: ->
        @showChildView 'body', new (TeacherBody)(collection: @collection)
        return
    )

    Layout: Layout, Panel: Panel, TeachersList: TeacherList

  View()
