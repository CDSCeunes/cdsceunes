define [
  'cs!app'
  'cs!apps/distributions/common/views'
  'cs!apps/distributions/new/new_view'
  'q'
], (CDSCeunes, CommonView, View, Q) ->
  CDSCeunes.module 'DistributionsApp.New', (New, CDSCeunes, Backbone, Marionette, $, _) ->
    New.Controller = newDistribution: ->
      layoutView = new (CommonView.Layout)
      panelView = new (LayoutView)
      distributionsView = undefined

      require [
        "entities/teacher"
        "entities/offered_class"
      ], ->

        layoutView.on 'show', ->
          layoutView.regions.panel.show(panelView)
          layoutView.regions.distributions.show(distributionsView)
          return

        return

      CDSCeunes.regions.main.show(layoutView)
      return
    return
  CDSCeunes.DistributionsApp.New.Controller
