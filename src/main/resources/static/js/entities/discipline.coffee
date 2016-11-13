define [
  'cs!app'
], (CDSCeunes) ->
  CDSCeunes.module 'Entities', (Entities, CDSCeunes, Backbone, Marionette, $, _) ->
    Entities.Discipline = Backbone.Model.extend(
      urlRoot: '/api/v1/disciplines'
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
    Entities.DisciplinesCollection = Backbone.Collection.extend(
      url: '/api/v1/disciplines'
      model: Entities.Discipline
      comparator: 'name')
    API =
      getDisciplineEntity: (disciplineId) ->
        discipline = new (Entities.Discipline)(id: disciplineId)
        Q.promise (resolve) ->
          discipline.fetch
            success: (data) ->
              resolve data
              return
            error: (data) ->
              resolve undefined
              return
          return
      getDisciplinesEntities: ->
        disciplines = new (Entities.DisciplinesCollection)
        Q.promise (resolve) ->
          disciplines.fetch success: (data) ->
            resolve data
            return
          return
    CDSCeunes.reqres.setHandler 'discipline:entity', (id) ->
      API.getDisciplineEntity id
    CDSCeunes.reqres.setHandler 'discipline:entities', ->
      API.getDisciplinesEntities()
    CDSCeunes.reqres.setHandler 'discipline:entity:new', ->
      new (Entities.Discipline)
    return
  return
