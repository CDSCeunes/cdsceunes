define [
  'app'
  'q'
], (CDSCeunes, Q) ->
  CDSCeunes.module 'Entities', (Entities, CDSCeunes, Backbone, Marionette, $, _) ->
    Entities.Preference = Backbone.Model.extend(urlRoot: '/api/v1/preferences')
    Entities.PreferencesCollection = Backbone.Collection.extend(
      url: '/api/v1/preferences'
      model: Entities.Preference
      comparator: (pref1, pref2) ->
        disc1 = pref1.get('discipline').name
        disc2 = pref2.get('discipline').name
        if disc1 == disc2
          val1 = pref1.get('preference')
          val2 = pref2.get('preference')
          if val1 < val2
            -1
          else if val1 > val2
            1
          else
            name1 = pref1.get('teacher').name
            name2 = pref2.get('teacher').name
            if name1 < name2
              -1
            else if name1 > name2
              1
            else
              0
        else
          if disc1 < disc2
            -1
          else
            1
    )
    API = 
      getPreferences: ->
        preferences = new (Entities.PreferencesCollection)
        Q.promise (resolve) ->
          preferences.fetch success: (data) ->
            resolve data
            return
          return
      getPreference: (id) ->
        preference = new (Entities.Preference)(id: id)
        Q.promise (resolve) ->
          preference.fetch
            success: (data) ->
              resolve data
              return
            error: (data) ->
              resolve undefined
              return
          return
    CDSCeunes.reqres.setHandler 'preference:entity', (id) ->
      API.getPreference id
    CDSCeunes.reqres.setHandler 'preference:entities', ->
      API.getPreferences()
    CDSCeunes.reqres.setHandler 'preference:entity:new', (id) ->
      new (Entities.Preference)
    return
  return
