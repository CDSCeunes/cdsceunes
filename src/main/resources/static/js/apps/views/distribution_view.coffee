define [
  'cs!app'
  'marionette'
], (CDSCeunes, Marionette) ->
  View = ->
    Header = Marionette.View.extend(
      template: 'distribution/header.html'
    )

    DistributionItem = Marionette.View.extend(
      template: 'distribution/distrib-item.html'
      className: 'row'
      ui:
        button: 'a.distrib-select-teacher'
    )

    DistributionList = Marionette.CollectionView.extend(
      childView: DistributionItem
      id: 'distribution-list-item'
    )

    DistributionListLayout = Marionette.View.extend(
      template: 'distribution/distrib-list.html'
      regions:
        body: '#distribution-list-items'
      onRender: ->
        @showChildView 'body', new (DistributionList)(collection: @collection)
    )

    Layout = Marionette.View.extend(
      template: 'distribution/layout.html'
      regions:
        header: '#distribution-header'
        body: '#distribution-body'
      onRender: ->
        @showChildView 'header', new (Header)
        return
    )

    Layout: Layout, DistributionList: DistributionListLayout

  View()
