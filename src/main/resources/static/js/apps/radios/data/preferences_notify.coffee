define [
  'cs!app'
  'marionette'
  'cs!entities/preferences'
  'jquery'
], (CDSCeunes, Marionette, Entities, $) ->
  Request =
    Preferences: Marionette.Object.extend(
      channelName: 'data-request'
      radioRequests:
        'preferences:entities': 'getPreferencesEntities'
        'preferences:entities:class': 'getPreferencesEntitiesByClass'
        'preferences:entity': 'getPreferencesEntity'
        'preferences:entity:new': 'newPreferences'
      getPreferencesEntities: ->
        prefs = new (Entities.Preferences)
        defer = $.Deferred()
        prefs.fetch
          success: (data) ->
            defer.resolve data
            return
          error: (data) ->
            defer.resolve undefined
            return
        defer.promise()
      getPreferencesEntitiesByClass: (args) ->
        prefs = new (Entities.PreferencesCollection)
        defer = $.Deferred()
        prefs.fetchByClass args,
          success: (data) ->
            defer.resolve data
            return
          error: (data) ->
            defer.resolve undefined
            return
        defer.promise()
      getPreferencesEntity: (id) ->
        prefs = new (Entities.Preferences)(id: id)
        defer = $.Deferred()
        prefs.fetch
          success: (data) ->
            defer.resolve data
            return
          error: (data) ->
            defer.resolve undefined
            return
        defer.promise()
      newPreferences: ->
        new (Entities.Preferences)
    )
  new (Request.Preferences)
