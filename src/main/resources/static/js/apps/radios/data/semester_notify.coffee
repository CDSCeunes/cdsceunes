define [
  'cs!app'
  'marionette'
  'cs!entities/semester'
  'jquery'
], (CDSCeunes, Marionette, Entities, $) ->
  Request =
    Semester: Marionette.Object.extend(
      channelName: 'data-request'
      radioRequests:
        'semester:entity': 'getSemesterEntity'
        'semester:entities': 'getSemestersEntities'
        'semester:entity:new': 'newSemester'
      getSemesterEntity: (year, semes) ->
        semester = new (Entities.Semester)(
          year: year
          semester: semes
        )

        defer = $.Deferred()

        semester.fetch
          success: (data) ->
            defer.resolve(data)
            return
          error: (data) ->
            defer.resolve(undefined)
            return
        defer.promise()

      getSemestersEntities: ->
        semesters = new (Entities.SemesterCollection)
        defer = $.Deferred()
        semesters.fetch
          success: (data) ->
            defer.resolve(data)
            return
          error: (data) ->
            defer.resolve(undefined)
            return
        defer.promise()

      newSemester: (id) ->
        new (Entities.Semester)(id: id)
    )

  new (Request.Semester)
