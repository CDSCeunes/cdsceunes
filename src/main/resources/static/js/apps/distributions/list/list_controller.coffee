define [
  'cs!app'
  'cs!apps/distributions/common/views'
  'cs!apps/distributions/list/list_view'
  'q'
], (CDSCeunes, CommonView, View, Q) ->
  CDSCeunes.module 'DistributionsApp.List', (List, CDSCeunes, Backbone, Marionette, $, _) ->
    List.Controller = listDistributions: (criterion) ->
      require [ 'cs!entities/semester' ], ->
        listLayout = new (CommonView.Layout)
        listPanel = new (CommonView.Panel)
        distributionsListView = undefined

        Q.all(CDSCeunes.request('semesters:entities')).then (semesters) ->
          distributionsListView = new (View.Distributions)(collection: semesters)

          if criterion
          else

          listLayout.on 'show', ->
            listLayout.panelRegion.show listPanel
            listLayout.mainRegion.show distributionsListView
            return

          distributionsListView.on 'childview:distribution:show', (that, args) ->
            CDSCeunes.trigger 'distributions:show', args
            return


          CDSCeunes.regions.main.show listLayout
          return
        return
      return
    return
  CDSCeunes.DistributionsApp.List.Controller
