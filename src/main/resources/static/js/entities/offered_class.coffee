define [
  'cs!app'
], (CDSCeunes) ->
  Entities = ->
    model = Backbone.Model.extend(
      urlRoot: '/api/v1/classes'
      initialize: ->
        @bind 'change', ->
          @save()
          return
        return
    )

    collection = Backbone.Collection.extend(
      url: '/api/v1/classes'
      model: model
      comparator: 'name'
      fetchBySemester: (args, opts) ->
        opts = opts || {}
        if opts.url == undefined
          opts.url = "#{@url}/#{args.year}/#{args.semester}"
        Backbone.Collection::fetch.call(this, opts)
    )

    OfferedClass: model, OfferedClassesCollection: collection

  Entities()
