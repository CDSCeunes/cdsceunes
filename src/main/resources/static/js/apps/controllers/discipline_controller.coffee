define [
  'cs!app'
  'marionette'
  'cs!apps/views/discipline_view'
  'jquery'
], (CDSCeunes, Marionette, View, $) ->
  Controller =
    Discipline: Marionette.Object.extend(
      list: (criterion) ->
        require [
          'cs!entities/common'
          'cs!apps/radios/data/discipline_notify'
        ], (FilteredCollection) ->
          list_layout = new (View.Layout)
          list_panel = new (View.Panel)
          list_disciplines = undefined
          filtered_disciplines = undefined

          $.when(CDSCeunes.dataRequest 'discipline:entities').done (disciplines) ->
            filtered_disciplines = FilteredCollection(
              collection: disciplines
              filter: (criterion) ->
                (discipline) ->
                  discipline.get('name').indexOf(criterion) > -1
            )
            list_layout.on 'childview:discipline:new', ->
              console.log 'Showing new discipline dialog'
              CDSCeunes.regions.showChildView 'dialog', new (View.Form)(
                model: CDSCeunes.dataRequest 'discipline:entity:new'
              )
              return

            list_disciplines = new (View.DisciplinesList)(collection: filtered_disciplines)

            list_layout.on 'render', ->
              list_layout.showChildView 'panelRegion', list_panel
              list_layout.showChildView 'disciplinesRegion', list_disciplines
              return # end show

            CDSCeunes.regions.showChildView 'main', list_layout
            return # end promise


          return # end require
        return # end list
    )

  Controller.Discipline
