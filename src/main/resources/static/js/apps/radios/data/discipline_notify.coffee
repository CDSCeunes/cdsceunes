define [
  'cs!app'
  'marionette'
  'cs!entities/discipline'
  'jquery'
], (CDSCeunes, Marionette, Entities, $) ->
  Request =
    Discipline: Marionette.Object.extend(
      channelName: 'data-request'
      radioRequests:
        'discipline:entity': 'getDisciplineEntity'
        'discipline:entities': 'getDisciplineEntities'
      getDisciplineEntities: ->
        depts = new (Entities.DisciplinesCollection)
        defer = $.Deferred()
        depts.fetch
          success: (data) ->
            defer.resolve data
            return
          error: (data) ->
            defer.resolve undefined
            return
        defer.promise()
      getDisciplineEntity: (id) ->
        dept = new (Entities.Discipline)(id: id)
        defer = $.Deferred()
        dept.fetch
          success: (data) ->
            defer.resolve data
            return
          error: (data) ->
            defer.resolve undefined
            return
        defer.promise()
    )

  new (Request.Discipline)
