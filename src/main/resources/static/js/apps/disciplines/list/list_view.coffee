define [
  'app'
  'handlebars'
  'text!apps/disciplines/list/templates/layout.hbs'
  'text!apps/disciplines/list/templates/panel.hbs'
  'text!apps/disciplines/list/templates/list.hbs'
  'text!apps/disciplines/list/templates/list_item.hbs'
], (CDSCeunes, Handlebars, layoutTpl, panelTpl, listTpl, listItemTpl) ->
  CDSCeunes.module 'DisciplinesApp.List.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    LockEdit = false
    View.Layout = Marionette.LayoutView.extend(
      template: Handlebars.compile(layoutTpl)
      regions:
        panelRegion: '#panel-region'
        disciplinesRegion: '#disciplines-region')
    View.Panel = Marionette.ItemView.extend(
      template: Handlebars.compile(panelTpl)
      triggers: 'click button.js-new-discipline': 'discipline:new')
    View.Discipline = Marionette.ItemView.extend(
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
        'click a.js-edit-discipline': 'discipline:edit'
        'click a.js-show-discipline': 'discipline:show'
        'click button.js-delete-discipline': 'discipline:delete'
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
    View.Disciplines = Marionette.CompositeView.extend(
      template: Handlebars.compile(listTpl)
      childView: View.Discipline
      childViewContainer: '#list-item-discipline')
    return
  CDSCeunes.DisciplinesApp.List.View
