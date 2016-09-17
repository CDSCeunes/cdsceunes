define [
  'app'
  'apps/positions/list/list_view'
  'q'
], (CDSCeunes, View, Q) ->
  CDSCeunes.module 'PositionsApp.List', (List, CDSCeunes, Backbone, Marionette, $, _) ->
    List.Controller = listPositions: (criterion) ->
      require [ 'entities/position' ], ->
        listLayout = new (View.Layout)
        listPanel = new (View.Panel)
        positionsListView = undefined
        Q.all(CDSCeunes.request('position:entities')).then (positions) ->
          positionsListView = new (View.Positions)(collection: positions)
          if criterion
          else
          listLayout.on 'show', ->
            listLayout.panelRegion.show listPanel
            listLayout.positionsRegion.show positionsListView
            return
          listPanel.on 'position:new', ->
            require [
              'apps/positions/new/new_view'
              'entities/position'
            ], (NewView) ->
              position = CDSCeunes.request('position:entity:new')
              Q.all(CDSCeunes.request('position:entities')).then (positions) ->
                newView = new (NewView.Position)(model: position)
                CDSCeunes.regions.dialog.show newView
                newView.on 'position:form:submit', (data) ->
                  if position.save(data)
                    positions.add position
                    newView.trigger 'dialog:close'
                  return
                return
              return
            return
          positionsListView.on 'childview:position:edit', (childview, args) ->
            require [ 'apps/positions/edit/edit_view' ], (EditView) ->
              model = args.model
              Q.all(CDSCeunes.request('position:entities')).then (positions) ->
                editView = new (EditView.Position)(model: model)
                editView.on 'position:form:submit', (data) ->
                  if model.save(data)
                    childview.render()
                    editView.trigger 'dialog:close'
                  return
                CDSCeunes.regions.dialog.show editView
                return
              return
            return
          positionsListView.on 'childview:position:delete', (childview, args) ->
            args.model.destroy()
            return
          CDSCeunes.regions.main.show listLayout
          return
        return
      return
    return
  CDSCeunes.PositionsApp.List.Controller
