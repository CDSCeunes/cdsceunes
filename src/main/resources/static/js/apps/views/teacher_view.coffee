define [
  "cs!app"
  "marionette"
  "text!apps/templates/teacher/layout.hbs"
  "text!apps/templates/teacher/panel.hbs"
  "text!apps/templates/teacher/teacher_item.hbs"
  "text!apps/templates/teacher/teacher_list.hbs"
], (CDSCeunes, Marionette, layoutTpl, panelTpl, teacherItemTpl, teacherListTpl) ->
  View = ->
    Layout = Marionette.View.extend(
      template: layoutTpl
      regions:
        panelRegion: '#panel-region'
        teachersRegion: '#teachers-region'
    )

    Panel = Marionette.View.extend(
      template: panelTpl
      className: 'row'
    )

    TeacherItem = Marionette.View.extend(
      template: teacherItemTpl
      tagName: 'div'
      className: 'row'
    )

    TeacherBody = Marionette.CollectionView.extend(
      id: '#list-teacher-items'
      childView: TeacherItem
    )

    TeacherList = Marionette.View.extend(
      template: teacherListTpl
      className: 'list-teacher'
      regions:
        body: '#list-teacher-items'
      onRender: ->
        @showChildView 'body', new (TeacherBody)(collection: @collection)
        return
    )

    Layout: Layout, Panel: Panel, TeachersList: TeacherList

  View()
