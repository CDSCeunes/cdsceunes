define [
  'cs!app'
  'q'
], (CDSCeunes, Q) ->
  CDSCeunes.module 'Entities', (Entities, CDSCeunes, Backbone, Marionette, $, _) ->
    Entities.OfferedClass = Backbone.Model.extend(
      urlRoot: '/api/v1/classes'
      initialize: ->
        @bind 'change', ->
          @save()
          return
        return
    )

    Entities.OfferedClassesCollection = Backbone.Collection.extend(
      url: '/api/v1/classes'
      model: Entities.OfferedClass
      comparator: 'name'
      fetchBySemester: (args, opts) ->
        opts = opts || {}
        if opts.url == undefined
          opts.url = "#{@url}/#{args.year}/#{args.semester}"
        Backbone.Collection::fetch.call(this, opts)
      )

    API =
      getClassEntity: (classId) ->
        class_ = new (Entities.OfferedClass)(id: classId)
        Q.promise (resolve) ->
          class_.fetch
            success: (data) ->
              resolve data
              return
            error: (data) ->
              resolve undefined
              return
          return

      getClassesEntities: (args) ->
        classes = new (Entities.OfferedClassesCollection)
        Q.promise (resolve) ->
          classes.fetchBySemester args,
            success: (data) ->
              resolve data
              return
            error: (data) ->
              resolve(undefined)
              return
          return

    CDSCeunes.reqres.setHandler 'offered_class:entity', (id) ->
      API.getClassEntity id
    CDSCeunes.reqres.setHandler 'offered_class:entities', (args) ->
      API.getClassesEntities(args)
    CDSCeunes.reqres.setHandler 'offered_class:entity:new', ->
      new (Entities.OfferedClass)
  return
return
