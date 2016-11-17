define [
  'cs!app'
  'backbone'
], (CDSCeunes, Backbone) ->
  Entities = ->
    Teacher = Backbone.Model.extend(
      urlRoot: '/api/v1/teachers'
      defaults:
        name: ''
        details:
          login: ''
        available: true
      shouldBeShown: (search) ->
        name = @get('name').toLowerCase()
        login = @get('login').toLowerCase()
        name.indexOf(search) > -1 or login.indexOf(search) > -1
    )

    TeachersCollection = Backbone.Collection.extend(
      url: '/api/v1/teachers'
      model: Teacher
      comparator: 'name'
    )

    return {
      Teacher: Teacher
      TeachersCollection: TeachersCollection
    }


  Entities()
