define [
  'cs!app'
  'marionette'
  'cs!entities/position'
  'jquery'
], (CDSCeunes, Marionette, Entities, $) ->
  Request =
    Position: Marionette.Object.extend(
      channelName: 'data-request'
      radioRequests:
        'position:entity': 'getPositionEntity'
        'position:entities': 'getPositionEntities'
      getPositionEntities: ->
        pos = new (Entities.PositionsCollection)
        defer = $.Deferred()
        pos.fetch
          success: (data) ->
            defer.resolve data
            return
          error: (data) ->
            defer.resolve undefined
            return
        defer.promise()
      getPositionEntity: (id) ->
        pos = new (Entities.Position)(id: id)
        defer = $.Deferred()
        por.fetch
          success: (data) ->
            defer.resolve data
            return
          error: (data) ->
            defer.resolve undefined
            return
        defer.promise()
    )

  new (Request.Position)
