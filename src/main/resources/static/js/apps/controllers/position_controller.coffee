define [
  'cs!app'
  'marionette'
  'cs!apps/views/position_view'
  'jquery'
], (CDSCeunes, Marionette, View, $) ->
  Controller =
    Position: Marionette.Object.extend(
      list: (criterion) ->
        require [
          'cs!entities/common'
          'cs!apps/radios/data/position_notify'
        ], (FilteredCollection) ->
          list_layout = new (View.Layout)
          list_panel = new (View.Panel)
          list_positions = undefined
          filtered_positions = undefined

          $.when(CDSCeunes.dataRequest 'position:entities').done (positions) ->
            filtered_positions = FilteredCollection(
              collection: positions
              filter: (criterion) ->
                (position) ->
                  position.get('name').indexOf(criterion) > -1
            )

            list_positions = new (View.PositionsList)(collection: filtered_positions)

            list_layout.on 'render', ->
              list_layout.showChildView 'panelRegion', list_panel
              list_layout.showChildView 'positionsRegion', list_positions
              return # end show

            CDSCeunes.regions.showChildView 'main', list_layout
            return # end promise


          return # end require
        return # end list
    )

  Controller.Position
