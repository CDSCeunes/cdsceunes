define [
  'cs!app'
  'cs!apps/distributions/common/views'
  'cs!apps/distributions/show/show_views'
  'q'
], (CDSCeunes, CommonView, View, Q) ->
  CDSCeunes.module 'DistributionsApp.Show', (Show, CDSCeunes, Backbone, Marionette, $, _) ->
    Show.Controller = showDistribution: (args)->
      require [
        "cs!entities/offered_class"
      ], ->
        layoutView = new (CommonView.Layout)
        classes_views = undefined

        Q.all( CDSCeunes.request("offered_class:entities", args) ).then (classes) ->

          classes_views = new (View.Classes)(
            collection: classes
          )


          layoutView.on 'show', ->
            console.log "try"
            console.log classes_views
            layoutView.mainRegion.show classes_views
            return

          return # Q Func

          CDSCeunes.regions.main.show layoutView
        return # require
      return # Controller
    return # module
  CDSCeunes.DistributionsApp.Show.Controller # define
