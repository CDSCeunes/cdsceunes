define [
  'cs!app'
  'backbone'
], (CDSCeunes, Backbone) ->
  Entities = ->
    model = Backbone.Model.extend(
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

    collection = Backbone.Collection.extend(
      url: '/api/v1/disciplines'
      model: model
      comparator: 'name'
    )

    Discipline: model, DisciplinesCollection: collection

  Entities()
