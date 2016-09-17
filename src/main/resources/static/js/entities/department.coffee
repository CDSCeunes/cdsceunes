define [
  'app'
  'q'
], (CDSCeunes, Q) ->
  CDSCeunes.module 'Entities', (Entities, CDSCeunes, Backbone, Marionette, $, _) ->
    Entities.Department = Backbone.Model.extend(
      urlRoot: '/api/v1/departments'
      defaults:
        name: ''
        center: ''
      initialize: ->
        @bind 'change', ->
          @save()
          return
        return
    )
    Entities.DepartmentsCollection = Backbone.Collection.extend(
      url: '/api/v1/departments'
      model: Entities.Department
      comparator: 'name')
    API = 
      getDepartmentEntity: (departmentId) ->
        department = new (Entities.Department)(id: departmentId)
        Q.promise (resolve) ->
          department.fetch
            success: (data) ->
              resolve data
              return
            error: (data) ->
              resolve undefined
              return
          return
      getDepartmentsEntities: ->
        departments = new (Entities.DepartmentsCollection)
        Q.promise (resolve) ->
          departments.fetch success: (data) ->
            resolve data
            return
          return
    CDSCeunes.reqres.setHandler 'department:entity', (id) ->
      API.getDepartmentEntity id
    CDSCeunes.reqres.setHandler 'department:entities', ->
      API.getDepartmentsEntities()
    CDSCeunes.reqres.setHandler 'department:entity:new', (id) ->
      new (Entities.Department)
    return
  return
