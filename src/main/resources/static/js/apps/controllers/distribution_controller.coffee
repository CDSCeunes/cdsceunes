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
        require [
          'cs!apps/radios/data/offered_class_notify'
          'cs!apps/radios/data/preferences_notify'
          'cs!apps/radios/data/teacher_notify'
        ], ->
          $.when(CDSCeunes.dataRequest('offered_class:entities', { year: year, semester: semester }),
                 CDSCeunes.dataRequest('teacher:entities')).done (classes, teachers) ->
            classesList = new (View.DistributionList)(collection: classes, teachers: teachers)
            layout.on 'render', ->
              layout.showChildView 'body', classesList
              return # end 'on:render'

            classesList.on 'childview:select:teacher', (args) ->
              $.when(CDSCeunes.dataRequest 'preferences:entities:class', args.id).done (prefs) ->
                selectTeacher = new (View.DistributionSelect)(
                  model: prefs
                  name: new (Backbone.Model)(name: args.name)
                  class_id: args.id
                )

                selectTeacher.on 'save:select', (args) ->
                  console.log args
                  class_ = classes.findWhere(id: args.class_id)

                  #class_.save()
                  console.log class_
                  class_.set({ teacher: { id: parseInt(args.selected) } }, silent: true)
                  class_.save(null)
                  return # ender 'on:save:select'

                CDSCeunes.regions.showChildView 'dialog', selectTeacher
                return # end defer
              return # ender 'on:select:teacher'
            CDSCeunes.regions.showChildView 'main', layout
            return # end defer
          return #end require
        return # end fn
    )

  Controller.Distribution
