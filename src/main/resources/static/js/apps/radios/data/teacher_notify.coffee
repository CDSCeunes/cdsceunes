define [
  'cs!app'
  'marionette'
  'cs!entities/teacher'
  'jquery'
], (CDSCeunes, Marionette, Entities, $) ->
  Request =
    Teacher: Marionette.Object.extend(
      channelName: 'data-request'
      radioRequests:
        'teacher:entity': 'getTeacherEntity'
        'teacher:entities': 'getTeacherEntities'
        'teacher:entity:new': 'newTeacher'
      getTeacherEntity: (id) ->
        teacher = new (Entities.Teacher)(id: teacherId)
        defer = $.Deferred()
        teachers.fetch
          success: (data) ->
            defer.resolve data
            return
          error: (data) ->
            defer.resolve undefined
            return
        defer.promise()
      getTeacherEntities: ->
        defer = $.Deferred()
        teachers = new (Entities.TeachersCollection)
        teachers.fetch
          success: (data) ->
            defer.resolve data
            return
        defer.promise()
      newTeacher: ->
        new (Entities.Teacher)
    )
  new (Request.Teacher)
