define [
  'cs!app'
  'q'
], (CDSCeunes, Q) ->
  CDSCeunes.module 'Entities', (Entities, CDSCeunes, Backbone, Marionette, $, _) ->
    Entities.OfferedClass = Backbone.Model.extend(
      urlRoot: '/api/v1/classes'
      defaults:
        name: ''
        course: ''
        semesters: ''
        teoricLoad: 0
        exerciseLoad: 0
        labLoad: 0
      initialize: ->
        @bind 'change', ->
          @save()
          return
        return
    )

    Entities.OfferedClassesCollection = Backbone.Collection.extend(
      url: '/api/v1/classes'
      model: Entities.OfferedClass
      comparator: 'name')

    API =
      getClassEntity: (classId) ->
        class = new (Entities.OfferedClass)(id: classId)
        Q.promise (resolve) ->
          class.fetch
            success: (data) ->
              resolve data
              return
            error: (data) ->
              resolve undefined
              return
          return

      getClassEntities: ->
        classes = new (Entities.OfferedClassesCollection)
        Q.promise (resolve) ->
          classes.fetch success: (data) ->
            resolve data
            return
          return

    CDSCeunes.reqres.setHandler 'offeredClass:entity', (id) ->
      API.getClassEntity id
    CDSCeunes.reqres.setHandler 'offeredClass:entities', ->
      API.getClassesEntities()
    CDSCeunes.reqres.setHandler 'offeredClass:entity:new', ->
      new (Entities.OfferedClass)
  return
return
