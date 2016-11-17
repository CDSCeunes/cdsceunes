define [
  'cs!app'
  'handlebars'
  'text!apps/configurations/templates/layout.hbs'
  'text!apps/configurations/templates/panel.hbs'
  'text!apps/configurations/templates/configurations.hbs'
], (CDSCeunes, Handlebars, layoutTpl, panelTpl, configurationsTpl) ->
  CDSCeunes.module 'ConfigurationsApp.List.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    LockEdit = false

    View.Layout = Marionette.LayoutView.extend(
      template: Handlebars.compile(layoutTpl)
      regions:
        panelRegion: '#panel-region'
        configurationsRegion: '#configurations-region')

    View.Panel = Marionette.ItemView.extend(
      template: Handlebars.compile(panelTpl))

    View.Configurations = Marionette.ItemView.extend(
      className: 'row'
      template: Handlebars.compile(configurationsTpl)
      ui:
        'edit': 'span.js-edit-name'
        'endEdit': 'input.js-edit-name'
      events:
        'dblclick @ui.edit': 'editName'
        'blur @ui.endEdit': 'endEdit'
        'keydown @ui.endEdit': 'endEditByKey'
      triggers:
        'click a.js-edit-configurations': 'configurations:edit'
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

    return
  CDSCeunes.Configurations.App.List.View
