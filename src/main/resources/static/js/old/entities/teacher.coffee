define [
  'cs!app'
  'q'
  'cs!apps/config/storage/localstorage'
], (CDSCeunes, Q) ->
  CDSCeunes.module 'Entities', (Entities, CDSCeunes, Backbone, Marionette, $, _) ->
    Entities.Teacher = Backbone.Model.extend(
      urlRoot: '/api/v1/teachers'
      defaults:
        name: ''
        login: ''
        available: true
      shouldBeShown: (search) ->
        name = @get('name').toLowerCase()
        login = @get('login').toLowerCase()
        name.indexOf(search) > -1 or login.indexOf(search) > -1
    )
    Entities.configureStorage 'CDSCeunes.Entities.Teacher', Entities.Teacher
    Entities.TeachersCollection = Backbone.Collection.extend(
      url: '/api/v1/teachers'
      model: Entities.Teacher
      comparator: 'name')
    Entities.configureStorage 'CDSCeunes.Entities.TeachersCollection', Entities.TeachersCollection
    API =
      getTeacherEntity: (teacherId) ->
        teacher = new (Entities.Teacher)(id: teacherId)
        Q.promise (resolve) ->
          teachers.fetch
            success: (data) ->
              resolve data
              return
            error: (data) ->
              resolve undefined
              return
          return
      getTeachersEntities: ->
        teachers = new (Entities.TeachersCollection)
        Q.promise (resolve) ->
          teachers.fetch success: (data) ->
            resolve data
            return
          return
    CDSCeunes.reqres.setHandler 'teacher:entity', (id) ->
      API.getTeacherEntity id
    CDSCeunes.reqres.setHandler 'teacher:entities', ->
      API.getTeachersEntities()
    CDSCeunes.reqres.setHandler 'teacher:entity:new', (id) ->
      new (Entities.Teacher)
    return
  return
