define [
  'app'
  'q'
], (CDSCeunes, Q) ->
  CDSCeunes.module 'Entities', (Entities, CDSCeunes, Backbone, Marionette, $, _) ->
    Entities.Position = Backbone.Model.extend(
      urlRoot: '/api/v1/positions'
      defaults:
        name: ''
        minWorkload: 0
        maxWorkload: 0
        currentWorkload: 0
        commission: ''
      initialize: ->
        @bind 'change', ->
          @save()
          return
        return
    )
    Entities.PositionsCollection = Backbone.Collection.extend(
      url: '/api/v1/positions'
      model: Entities.Position
      comparator: 'name')
    API = 
      getPositionEntity: (positionId) ->
        position = new (Entities.Position)(id: positionId)
        Q.promise (resolve) ->
          position.fetch
            success: (data) ->
              resolve data
              return
            error: (data) ->
              resolve undefined
              return
          return
      getPositionsEntities: ->
        positions = new (Entities.PositionsCollection)
        Q.promise (resolve) ->
          positions.fetch success: (data) ->
            resolve data
            return
          return
    CDSCeunes.reqres.setHandler 'position:entity', (id) ->
      API.getPositionEntity id
    CDSCeunes.reqres.setHandler 'position:entities', ->
      API.getPositionsEntities()
    CDSCeunes.reqres.setHandler 'position:entity:new', (id) ->
      new (Entities.Position)
    return
  return
