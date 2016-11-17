define [
  'cs!app'
  'marionette'
  'cs!apps/views/configurations_view'
  'jquery'
], (CDSCeunes, Marionette, View, $) ->
  Controller =
    Configurations: Marionette.Object.extend(
      config: ->
        require [
          'cs!entities/common'
          'cs!apps/radios/data/teacher_notify'
          'cs!apps/radios/data/department_notify'
        ],
        listLayout = new (View.Layout)
        listPanel = new (View.Panel)

        listLayout.on 'render', ->
          listLayout.showChildView 'panelRegion', listPanel
          return # end show

        $.when(CDSCeunes.dataRequest 'department:entities').done (departments) ->
          listLayout.on 'childview:teacher:new', ->
            console.log 'trigger2'

            CDSCeunes.regions.showChildView 'dialog', new (View.Form)(
              model: CDSCeunes.dataRequest 'teacher:entity:new'
              departments: departments
            )
            return

          return
        
        #We need to add commission methods, but we didn't created the app yet

        CDSCeunes.regions.showChildView 'main', listLayout
        return
    )
  Controller.Configurations
