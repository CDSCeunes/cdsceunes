define [
  'app'
  'q'
], (CDSCeunes, Q) ->
  CDSCeunes.module 'Entities', (Entities, CDSCeunes, Backbone, Marionette, $, _) ->
    Entities.Distribution = Backbone.Model.extend(
      urlRoot: '/api/v1/scenarios'
      defaults: {}
      initialize: ->
        @bind 'change', ->
          @save()
          return
        return
    )
    Entities.DistributionCollection = Backbone.Collection.extend(
      url: '/api/v1/scenarios'
      model: Entities.Distribution)
    API = 
      getDistributionEntity: (distributionId) ->
        distribution = new (Entities.Distribution)(id: distributionId)
        Q.promise (resolve) ->
          distribution.fetch
            success: (data) ->
              resolve data
              return
            error: (data) ->
              resolve undefined
              return
          return
      getDistributionsEntities: ->
        distributions = new (Entities.DistributionCollection)
        Q.promise (resolve) ->
          distributions.fetch success: (data) ->
            resolve data
            return
          return
    CDSCeunes.reqres.setHandler 'distribution:entity', (id) ->
      API.getDistributionEntity id
    CDSCeunes.reqres.setHandler 'distribution:entities', ->
      API.getDistributionsEntities()
    CDSCeunes.reqres.setHandler 'distribution:entity:new', (id) ->
      new (Entities.Distribution)
    return
  return
