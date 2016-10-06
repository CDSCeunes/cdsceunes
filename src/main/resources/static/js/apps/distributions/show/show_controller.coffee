define [
  'cs!app'
  'cs!apps/distributions/common/views'
  'cs!apps/distributions/show/show_views'
  'q'
], (CDSCeunes, CommonView, View, Q) ->
  CDSCeunes.module 'DistributionsApp.Show', (Show, CDSCeunes, Backbone, Marionette, $, _) ->
    Show.Controller = showDistribution: (args)->
      year = args.year
      semester = args.semester

      layoutView = new (CommonView.Layout)
      classes_views = undefined

      require [
        "cs!entities/offered_class"
      ], ->
        Q.all( CDSCeunes.request("offered_class:entities") ).then (classes) ->

          classes_views = new (View.Classes)(
            collection: classes
          )


          layoutView.on 'show', ->
            layoutView.regions.main.show(classes_views)
            return

          return
        return

      return
      CDSCeunes.regions.main.show(layoutView)
    return


  CDSCeunes.DistributionsApp.Show.Controller
