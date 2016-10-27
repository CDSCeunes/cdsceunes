define [
  'cs!app'
  'backbone'
], (CDSCeunes, Backbone) ->
  Entities = ->
    Position = Backbone.Model.extend(
      urlRoot: '/api/v1/positions'
      defaults:
        name: ''
        minWorkload: 0
        maxWorkload: 0
        currentWorkload: 0
        commission: ''
      shouldBeShown: (search) ->
        name = @get('name').toLowerCase()
        login = @get('login').toLowerCase()
        name.indexOf(search) > -1 or login.indexOf(search) > -1
    )

    PositionsCollection = Backbone.Collection.extend(
      url: '/api/v1/positions'
      model: Position
      comparator: 'name'
    )

    return {
      Position: Position
      PositionsCollection: PositionsCollection
    }

  Entities()
