define [
  "cs!app"
  "marionette"
], (CDSCeunes, Marionette) ->
  View = ->
    Layout = Marionette.View.extend(
      template: 'department/layout'
      regions:
        panelRegion: '#panel-region'
        departmentsRegion: '#departments-region'
    )

    Panel = Marionette.View.extend(
      template: 'department/panel'
      className: 'row'
      events:
        'click button.js-new-department': 'newDepartment'
      newDepartment: (e) ->
        e.preventDefault()
        console.log 'new department'
        @triggerMethod 'department:new'
        return
    )

    Form = Marionette.View.extend(
      template: 'department/form'
      ui: 'submitData': '.js-submit-department'
      title: 'Novo Departamento'
      submitData: (e) ->
        e.preventDefault()
        data = Backbone.Syphon.serialize(this)
        console.log data
        @trigger 'department:form:submit', data
        return
    )

    DepartmentItem = Marionette.View.extend(
      template: 'department/department_item'
      tagName: 'div'
      className: 'row'
    )

    DepartmentBody = Marionette.CollectionView.extend(
      id: '#list-department-items'
      childView: DepartmentItem
    )

    DepartmentList = Marionette.View.extend(
      template: 'department/department_list'
      className: 'list-department'
      regions:
        body: '#list-department-items'
      onRender: ->
        @showChildView 'body', new (DepartmentBody)(collection: @collection)
        return
    )

    Layout: Layout, Panel: Panel, DepartmentsList: DepartmentList, Form: Form

  View()
