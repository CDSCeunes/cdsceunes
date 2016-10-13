define [
  'cs!app'
], (CDSCeunes) ->
  Entities = ->
    model = Backbone.Model.extend(
      urlRoot: '/api/v1/preferences'
    )

    collection = Backbone.Collection.extend(
      url: '/api/v1/preferences'
      model: model
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
      fetchByClass: (args, opts) ->
        opts ?= {}
        if opts.url == undefined
          opts.url = "#{@url}/class/#{args}"
        Backbone.Collection::fetch.call(this, opts)

    )

    Preferences: model, PreferencesCollection: collection
  Entities()
