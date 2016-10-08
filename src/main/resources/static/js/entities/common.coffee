define [ 'cs!app' ], (CDSCeunes) ->
  Entities = FilteredCollection: (options) ->
    original = options.collection
    filtered = new (original.constructor)
    filtered.add original.models
    filtered.filterFunction = options.filter

    applyFilter = (filterCriterion, filterStrategy, collection) ->
      `var collection`
      collection = collection or original
      criterion = undefined
      if filterStrategy == 'filter'
        criterion = filterCriterion.trim()
      else
        criterion = filterCriterion
      items = []
      if criterion
        if filterStrategy == 'filter'
          if !filtered.filterFunction
            throw 'Attempted to use \'filter\' function, but none was defined.'
          filterFunction = filtered.filterFunction(criterion)
          items = collection.filter(filterFunction)
        else
          items = collection.where(criterion)
      else
        items = collection.models
      filtered._currentCriterion = criterion
      items

    filtered.filter = (filterCriterion) ->
      filtered._currentFilter = 'filter'
      items = applyFilter(filterCriterion, 'filter')
      # reset the filtered collection with the new items
      filtered.reset items
      filtered

    filtered.where = (filterCriterion) ->
      filtered._currentFilter = 'where'
      items = applyFilter(filterCriterion, 'where')
      # reset the filtered collection with the new items
      filtered.reset items
      filtered

    original.on 'reset', ->
      items = applyFilter(filtered._currentCriterion, filtered._currentFilter)
      # reset the filtered collection with the new items
      filtered.reset items
      return
    original.on 'add', (models) ->
      coll = new (original.constructor)
      coll.add models
      items = applyFilter(filtered._currentCriterion, filtered._currentFilter, coll)
      filtered.add items
      return
    filtered
  Entities.FilteredCollection
