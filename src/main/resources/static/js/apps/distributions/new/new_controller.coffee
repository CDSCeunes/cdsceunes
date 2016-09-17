define [
  'app'
  'apps/distributions/new/new_view'
  'q'
], (CDSCeunes, View, Q) ->
  CDSCeunes.module 'DistributionsApp.NewDistribution', (NewDistribution, CDSCeunes, Backbone, Marionette, $, _) ->
    NewDistribution.Controller = newDistribution: ->
      require [
        'entities/common'
        'entities/teacher'
        'entities/discipline'
        'entities/distribution'
      ], ->
        listLayout = new (View.Layout)
        listPanel = new (View.Panel)
        teachersListView = undefined
        disciplinesListView = undefined
        Q.all CDSCeunes.request('discipline:entities', 'teacher:entities', 'distribution:entities').then((distributions) ->
          teachersListView = new (View.Teachers)(collection: teacher)
          disciplinesListView = new (View.Disciplines)(collection: discipline)
          listPanel.on 'distribution', ->
            require [
              'apps/distributions/new/new_view'
              'entities/distribution'
              'entities/teacher'
              'entities/discipline'
            ], (NewView) ->
              distribution = CDSCeunes.request('distribution:entity:new')
              Q.all(CDSCeunes.request('teacher:entities', 'discipline:entities')).then (teachers, disciplines) ->
                newView = new (NewView.Distribution)(
                  model: distribution
                  teachers: teachers
                  disciplines: disciplines)
                CDSCeunes.regions.dialog.show newView
                newView.on 'distribution:form:submit', (data) ->
                  if distribution.save(data)
                    distributions.add distribution
                    newView.trigger 'dialog:close'
                  return
                return
              return
            return
          return
        )
        return
      return
    return
  CDSCeunes.DistributionsApp.NewDistribution.Controller
