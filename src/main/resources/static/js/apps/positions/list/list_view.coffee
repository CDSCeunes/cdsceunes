define [
  'app'
  'handlebars'
  'text!apps/positions/list/templates/layout.hbs'
  'text!apps/positions/list/templates/panel.hbs'
  'text!apps/positions/list/templates/list.hbs'
  'text!apps/positions/list/templates/list_item.hbs'
], (CDSCeunes, Handlebars, layoutTpl, panelTpl, listTpl, listItemTpl) ->
  CDSCeunes.module 'PositionsApp.List.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    LockEdit = false
    View.Layout = Marionette.LayoutView.extend(
      template: Handlebars.compile(layoutTpl)
      regions:
        panelRegion: '#panel-region'
        positionsRegion: '#positions-region')
    View.Panel = Marionette.ItemView.extend(
      template: Handlebars.compile(panelTpl)
      triggers: 'click button.js-new-position': 'position:new')
    View.Position = Marionette.ItemView.extend(
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
        'click a.js-edit-position': 'position:edit'
        'click a.js-show-position': 'position:show'
        'click button.js-delete-position': 'position:delete'
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
    View.Positions = Marionette.CompositeView.extend(
      template: Handlebars.compile(listTpl)
      childView: View.Position
      childViewContainer: '#list-item-position')
    return
  CDSCeunes.PositionsApp.List.View
