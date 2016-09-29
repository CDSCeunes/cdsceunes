define [
  'cs!app'
  'cs!apps/distributions/common/views'
  'cs!apps/distributions/new/new_view'
  'q'
], (CDSCeunes, CommonView, View, Q) ->
  CDSCeunes.module 'DistributionsApp.NewDistribution', (NewDistribution, CDSCeunes, Backbone, Marionette, $, _) ->
    NewDistribution.Controller = newDistribution: ->
      require ['cs!entities/preferences'], ->
        listLayout = new (CommonView.Layout)
        listPanel = new (CommonView.Panel)
        preferencesListView = undefined

        Q.all (CDSCeunes.request('preferences:entities'), CDSCeunes.request('offeredClass:entities')).then(preferences, offeredClass) ->
          preferencesListView = new (View.Preferences)(collection: preferences)

          listLayout.on 'show', ->
            listLayout.panelRegion.show listPanel
            listLayout.distributionsRegion.show preferencesListView

            distribution = CDSCeunes.request('distribution:entity:new')

            newView = new (NewView.Distribution)(
              model: distributionpp
              teachers: preferences.teacher
              disciplines: preferences.discipline)

            CDSCeunes.regions.dialog.show newView
            newView.on 'distribution:form:submit', (data) ->
              if distribution.save(data)
                distributions.add distribution
              return
            return

            CDSCeunes.regions.main.show listLayout
          return

        return

      return

    return
  CDSCeunes.DistributionsApp.NewDistribution.Controller
