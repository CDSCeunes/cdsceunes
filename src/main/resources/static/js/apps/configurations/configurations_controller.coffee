define [
  'cs!app'
  'cs!apps/configurations/configurations_view'
  'q'
], (CDSCeunes, View, Q) ->
  CDSCeunes.module 'ConfigurationsApp.List', (Configurations, CDSCeunes, Backbone, Marionette, $, _) ->
    Configurations.Controller = loadPage: ->
      listLayout = new (View.Layout)
      listPanel = new (View.Panel)

      listLayout.on 'show', ->
        listLayout.panelRegion.show listPanel
        return

      listPanel.on 'configurations:edit', ( args ) ->
        require [
          'cs!apps/configurations/templates'
          'cs!entities/department'
        ], (EditView) ->
          model = args.model
          Q.all(CDSCeunes.request('department:entities')).then (departments) ->
            editView = new (EditView.Configurations)(
              model: model
              departments: departments)
            editView.on 'configurations:form:submit', (data) ->
              if model.save(data)
                editView.trigger 'dialog:close'
              return
            CDSCeunes.regions.dialog.show editView
            return
          return
        return



      CDSCeunes.regions.configurations.show listLayout
      return
    return
  CDSCeunes.ConfigurationsApp.Configurations.Controller
