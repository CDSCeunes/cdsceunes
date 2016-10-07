define [
  'cs!app'
  'handlebars'
  'text!apps/distributions/list/templates/list.hbs'
  'text!apps/distributions/list/templates/list_item.hbs'
], (CDSCeunes, Handlebars, listTpl, listItemTpl) ->
  CDSCeunes.module 'DistributionsApp.List.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->

    View.Distribution = Marionette.ItemView.extend(
      className: 'row'
      template: Handlebars.compile(listItemTpl)
      events:
        'click a.js-show-distribution': 'distributionShow'
      distributionShow: (e) ->
        e.preventDefault()
        args =
          year: @model.get('year')
          semester: @model.get('semester')
        @trigger 'distribution:show', args
        return

    )

    View.Distributions = Marionette.CollectionView.extend(
      template: Handlebars.compile(listTpl)
      childView: View.Distribution
      childViewContainer: '#list-item-distribution'
    )
    return
  CDSCeunes.DistributionsApp.List.View
