define [
  'cs!app'
  'cs!apps/main/main_view'
  'q'
], (CDSCeunes, View, Q) ->
  CDSCeunes.module 'MainApp.List', (Main, CDSCeunes, Backbone, Marionette, $, _) ->
    Main.Controller = loadPage: ->
      listLayout = new (View.Layout)
      listPanel = new (View.Panel)

      listLayout.on 'show', ->
        listLayout.panelRegion.show listPanel
        return

      listPanel.on 'main:disciplines', ->
        require ['cs!apps/disciplines/disciplines_app'], ->
          CDSCeunes.trigger 'disciplines:list'
          return
        return

      listPanel.on 'main:teachers', ->
        require ['cs!apps/teachers/teachers_app'], ->
          CDSCeunes.trigger 'teachers:list'
          return
        return

      listPanel.on 'main:departments', ->
        require ['cs!apps/departments/departments_app'], ->
          CDSCeunes.trigger 'departments:list'
          return
        return

      listPanel.on 'main:distributions', ->
        require ['cs!apps/distributions/distributions_app'], ->
          CDSCeunes.trigger 'distributions:list'
          return
        return

      listPanel.on 'main:distributions:new', ->
        require ['cs!apps/distributions/distributions_app'], ->
          CDSCeunes.trigger 'distributions:new'
          return
        return

      listPanel.on 'main:positions', ->
        require ['cs!apps/positions/positions_app'], ->
          CDSCeunes.trigger 'positions:list'
          return
        return

      listPanel.on 'main:preferences', ->
        require ['cs!apps/preferences/preferences_app'], ->
          CDSCeunes.trigger 'preferences:list'
          return
        return

      #We need to add commission methods, but we didn't created the app yet

      CDSCeunes.regions.main.show listLayout
      return
    return
  CDSCeunes.MainApp.Main.Controller
