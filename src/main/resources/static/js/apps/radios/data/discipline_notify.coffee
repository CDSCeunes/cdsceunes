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
        disc = new (Entities.DisciplinesCollection)
        defer = $.Deferred()
        disc.fetch
          success: (data) ->
            defer.resolve data
            return
          error: (data) ->
            defer.resolve undefined
            return
        defer.promise()
      getDisciplineEntity: (id) ->
        disc = new (Entities.Discipline)(id: id)
        defer = $.Deferred()
        disc.fetch
          success: (data) ->
            defer.resolve data
            return
          error: (data) ->
            defer.resolve undefined
            return
        defer.promise()
    )

  new (Request.Discipline)
