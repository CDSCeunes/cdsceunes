define [
  'cs!app'
  'marionette'
  'cs!apps/views/distribution_view'
  'jquery'
], (CDSCeunes, Marionette, View, $) ->
  Controller =
    Distribution: Marionette.Object.extend(
      home: ->
        layout = new (View.Layout)

        return
      list: (year, semester) ->
        layout = new (View.Layout)
        require ['cs!apps/radios/data/offered_class_notify'], ->
          $.when(CDSCeunes.dataRequest 'offered_class:entities', { year: year, semester: semester }).done (classes) ->
            classesList = new (View.DistributionList)(collection: classes)

            layout.on 'render', ->
              layout.showChildView 'body', classesList
              return # end 'on:render'

            CDSCeunes.regions.showChildView 'main', layout
            return # end defer
          return #end require
        return # end fn
    )

  Controller.Distribution
