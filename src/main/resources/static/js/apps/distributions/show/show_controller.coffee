define [
  'cs!app'
  'cs!apps/distributions/common/views'
  'cs!apps/distributions/common/show/list_view'
  'q'
], (CDSCeunes, CommonView, View, Q) ->
  CDSCeunes.module 'DistributionsApp.ShowController', (Show, CDSCeunes, Backbone, Marionette, $, _) ->
    Show.Controller = showDistribution: (id) ->

      require['cs!entities/distribution'], ->
        listLayout = new (View.Layout)
        listPanel = new (View.Panel)
        distributionListView = undefined

        Q.all(CDSCeunes.request('distribution:entity', id)).then(scenario) ->
          distributionsListView = new (View.Distributions)

          listLayout.on 'show', ->
            listLayout.panelRegion.show listPanel
            listLayout.distributionsRegion.show distributionListView
          return

        CDSCeunes.regions.main.show listLayout
        return

      return

    return

  return
CDSCeunes.DistributionsApp.Show.Controller
