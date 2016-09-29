define [
  'cs!app'
  'cs!apps/distributions/common/views'
  'cs!apps/distributions/list/list_view'
  'q'
], (CDSCeunes, CommonView, View, Q) ->
  CDSCeunes.module 'DistributionsApp.List', (List, CDSCeunes, Backbone, Marionette, $, _) ->
    List.Controller = listDistributions: (criterion) ->
      require [ 'cs!entities/distribution' ], ->
        listLayout = new (CommonView.Layout)
        listPanel = new (CommonView.Panel)
        distributionsListView = undefined

        Q.all(CDSCeunes.request('distribution:entities')).then (distributions) ->
          distributionsListView = new (View.Distributions)(collection: distributions)

          if criterion
          else
          listLayout.on 'show', ->
            listLayout.panelRegion.show listPanel
            listLayout.distributionsRegion.show distributionsListView
            return

          listPanel.on 'distribution:new', ->
            CDSCeunes.trigger 'distributions:new'
            return

          distributionsListView.on 'childview:distribution:edit', (childview, args) ->
            require [ 'cs!apps/distributions/edit/edit_view' ], (EditView) ->
              model = args.model
              Q.all(CDSCeunes.request('distribution:entities')).then (distributions) ->
                editView = new (EditView.Distribution)(model: model)
                editView.on 'distribution:form:submit', (data) ->
                  if model.save(data)
                    childview.render()
                    editView.trigger 'dialog:close'
                  return
                CDSCeunes.regions.dialog.show editView
                return
              return
            return

          distributionsListView.on 'childview:distribution:delete', (childview, args) ->
            args.model.destroy()
            return

          CDSCeunes.regions.main.show listLayout
          return
        return
      return
    return
  CDSCeunes.DistributionsApp.List.Controller
