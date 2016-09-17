define [
  'cs!app'
  'cs!apps/distributions/common/views'
  'cs!apps/distributions/list/list_view'
  'q'
], (CDSCeunes, CommonView, View, Q) ->
  CDSCeunes.module 'DistributionsApp.List', (List, CDSCeunes, Backbone, Marionette, $, _) ->
    List.Controller = listDistributions: (criterion) ->
      console.log '0'
      require [ 'entities/distribution' ], ->
        listLayout = new (CommonView.Layout)
        listPanel = new (CommonView.Panel)
        distributionsListView = undefined
        console.log '1'
        Q.all(CDSCeunes.request('distribution:entities')).then (distributions) ->
          console.log '2'
          distributionsListView = new (View.Distributions)(collection: distributions)
          if criterion
          else
          listLayout.on 'show', ->
            console.log '3'
            listLayout.panelRegion.show listPanel
            listLayout.distributionsRegion.show distributionsListView
            console.log '4'
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
                console.log '5'
                CDSCeunes.regions.dialog.show editView
                console.log '6'
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
