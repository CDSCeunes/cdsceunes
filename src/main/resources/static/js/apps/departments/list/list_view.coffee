define [
  'cs!app'
  'handlebars'
  'text!apps/departments/list/templates/layout.hbs'
  'text!apps/departments/list/templates/panel.hbs'
  'text!apps/departments/list/templates/list.hbs'
  'text!apps/departments/list/templates/list_item.hbs'
], (CDSCeunes, Handlebars, layoutTpl, panelTpl, listTpl, listItemTpl) ->
  CDSCeunes.module 'DepartmentsApp.List.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    LockEdit = false
    View.Layout = Marionette.LayoutView.extend(
      template: Handlebars.compile(layoutTpl)
      regions:
        panelRegion: '#panel-region'
        departmentsRegion: '#departments-region')
    View.Panel = Marionette.ItemView.extend(
      template: Handlebars.compile(panelTpl)
      triggers: 'click button.js-new-department': 'department:new')
    View.Department = Marionette.ItemView.extend(
      className: 'row'
      template: Handlebars.compile(listItemTpl)
      ui:
        'edit': 'span.js-edit-name'
        'endEdit': 'input.js-edit-name'
      events:
        'dblclick @ui.edit': 'editName'
        'blur @ui.endEdit': 'endEdit'
        'keydown @ui.endEdit': 'endEditByKey'
      triggers:
        'click a.js-edit-department': 'department:edit'
        'click a.js-show-department': 'department:show'
        'click button.js-delete-department': 'department:delete'
      modelEvents: 'change': 'fieldChanged'
      editName: (e) ->
        if LockEdit == false
          myEl = @$el.find('.js-edit-name')
          cont = '<input type=\'text\' class=\'js-edit-name\' value=\'' + myEl.text() + '\'>'
          myEl.replaceWith cont
          LockEdit = true
        return
      endEdit: (e) ->
        LockEdit = false
        myEl = @$el.find('.js-edit-name')
        input = $('input.js-edit-name').val()
        @model.set 'name', input
        @model.save()
        return
      endEditByKey: (e) ->
        ENTER_KEY = 13
        if e.which == ENTER_KEY
          @endEdit()
        return
      onDomRefresh: ->
        @ui.endEdit.focus()
        return
      fieldChanged: ->
        @render()
        return
    )
    View.Departments = Marionette.CompositeView.extend(
      template: Handlebars.compile(listTpl)
      childView: View.Department
      childViewContainer: '#list-item-department')
    return
  CDSCeunes.DepartmentsApp.List.View
