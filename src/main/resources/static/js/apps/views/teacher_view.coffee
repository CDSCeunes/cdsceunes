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
      events:
        'click button.js-new-teacher': 'newTeacher'
      newTeacher: (e) ->
        e.preventDefault()
        console.log 'new teacher'
        @triggerMethod 'teacher:new'
        return
    )

    Form = Marionette.View.extend(
      template: 'teacher/form'
      ui:
        'admissionDate': '.datepicker'
        'submitData': '.js-submit-teacher'
        'form': '#teacher-form'
      events:
        'submit @ui.form': 'submitData'
      title: 'Novo Professor'
      serializeData: ->
        {
          model: @model.toJSON()
          departments: @options.departments.toJSON()
        }
      onRender: ->
        @getUI('admissionDate').datepicker
          dateFormat: 'yy-mm-dd'
          defaultDate: 0
          showAnim: 'drop'
        @ui.submitData.text 'Enviar'
        return
      submitData: (e) ->
        e.preventDefault()
        data = Backbone.Syphon.serialize(this)
        console.log data
        @trigger 'teacher:form:submit', data
        return
    )

    TeacherItem = Marionette.View.extend(
      template: 'teacher/teacher_item'
      tagName: 'div'
      className: 'row'
      events:
        'click button.js-delete-teacher' : 'deleteTeacherEntity'
      deleteTeacherEntity: (e) ->
        e.preventDefault()
        console.log 'delete teacher'
        @triggerMethod 'teacher:delete', @model
        return
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

    Layout: Layout, Panel: Panel, TeachersList: TeacherList, Form: Form

  View()
