define [
  'cs!app'
  'cs!apps/preferences/list/list_view'
  'q'
], (CDSCeunes, View, Q) ->
  CDSCeunes.module 'PreferencesApp.List', (List, CDSCeunes, Backbone, Marionette, $, _) ->
    List.Controller = listPreferences: (criterion) ->
      layoutView = new (View.Layout)
      panelView = new (View.Panel)
      rowsView = undefined
      require [ 'entities/preferences' ], ->
        Q.all(CDSCeunes.request('preference:entities')).then (preferences) ->
          rowsView = new (View.Rows)(collection: preferences)
          layoutView.on 'show', ->
            layoutView.main.show rowsView
            layoutView.panel.show panelView
            return
          panelView.on 'preference:new', ->
          CDSCeunes.regions.main.show layoutView
          return
        return
      return
    return
  CDSCeunes.PreferencesApp.List.Controller
