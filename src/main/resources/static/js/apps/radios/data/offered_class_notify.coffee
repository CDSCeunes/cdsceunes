define [
  'cs!app'
  'marionette'
  'cs!entities/offered_class'
  'jquery'
], (CDSCeunes, Marionette, Entities, $) ->
  Request =
    OfferedClass: Marionette.Object.extend(
      channelName: 'data-request'
      radioRequests:
        'offered_class:entity': 'getOfferedClassEntity'
        'offered_class:entities': 'getOfferedClassesEntities'
      getOfferedClassEntity: (id) ->
        distribution = new (Entities.OfferedClass)(id: did)
        defer = $.Deferred()
        distribution.fetch
          success: (data) ->
            defer.resolve data
            return
          error: (data) ->
            defer.resolve undefined
            return
        defer.promise()
      getOfferedClassesEntities: (args) ->
        classes = new (Entities.OfferedClassesCollection)
        defer = $.Deferred()
        classes.fetchBySemester args,
          success: (data) ->
            defer.resolve data
            return
          error: (data) ->
            defer.resolve(undefined)
            return
        defer.promise()
    )

  new (Request.OfferedClass)
