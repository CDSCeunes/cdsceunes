define [
  'app'
  'handlebars'
  'text!apps/teachers/list/templates/layout.hbs'
  'text!apps/teachers/list/templates/panel.hbs'
  'text!apps/teachers/list/templates/list.hbs'
  'text!apps/teachers/list/templates/list_item.hbs'
], (CDSCeunes, Handlebars, layoutTpl, panelTpl, listTpl, listItemTpl) ->
  CDSCeunes.module 'TeachersApp.List.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    LockEdit = false
    View.Layout = Marionette.LayoutView.extend(
      template: Handlebars.compile(layoutTpl)
      regions:
        panelRegion: '#panel-region'
        teachersRegion: '#teachers-region')
    View.Panel = Marionette.ItemView.extend(
      template: Handlebars.compile(panelTpl)
      triggers: {}
      events:
        'submit #filter-teacher': 'filterTeachers'
        'keypress .js-filter-teacher': 'filterTeachers'
        'click button.js-new-teacher': 'newTeacher'
      ui: 'search': 'input.js-filter-teacher'
      newTeacher: (e) ->
        @triggerMethod 'teacher:new'
        return
      filterTeachers: (e) ->
        search = @ui.search.val()
        if (e.key >= 'A' and e.key <= 'Z' or e.key >= 'a' and e.key <= 'z') and e.key.length == 1
          search = search + e.key
        @trigger 'teacher:filter', search
        return
      onSetFilterCriterion: (criterion) ->
        @ui.search.val criterion
        return
    )
    View.Teacher = Marionette.ItemView.extend(
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
        'click a.js-edit-teacher': 'teacher:edit'
        'click a.js-show-teacher': 'teacher:show'
        'click button.js-delete-teacher': 'teacher:delete'
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
      filter: (search) ->
        if @model.shouldBeShown(search)
          @$el.show()
        else
          @$el.hide()
        return
    )
    View.Teachers = Marionette.CompositeView.extend(
      template: Handlebars.compile(listTpl)
      childView: View.Teacher
      childViewContainer: '#list-item-teacher')
    return
  CDSCeunes.TeachersApp.List.View
