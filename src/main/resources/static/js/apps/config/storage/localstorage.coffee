define [
  'app'
  'localstorage'
], (CDSCeunes) ->
  CDSCeunes.module 'Entities', (Entities, CDSCeunes, Backbone, Marionette, $, _) ->

    findStorageKey = (entity) ->
      if entity.urlRoot
        return _.result(entity, 'urlRoot')
      if entity.url
        return _.result(entity, 'url')
      throw new Error('Unable to determine storage key.')
      return

    storageCache = {}

    getStorage = (key) ->
      storage = storageCache[key]
      if storage
        return storage
      newStorage = new (Backbone.LocalStorage)(key)
      storageCache[key] = newStorage
      newStorage

    StorageMixin = (entityPrototype) ->
      storageKey = findStorageKey(entityPrototype)
      { localstorage: getStorage(storageKey) }

    Entities.configureStorage = (constructorString, constructor) ->
      OldConstructor = constructor

      NewConstructor = ->
        obj = new OldConstructor(arguments[0], arguments[1])
        _.extend obj, new StorageMixin(OldConstructor.prototype)
        obj

      NewConstructor.prototype = OldConstructor.prototype
      eval constructorString + ' = NewConstructor;'
      return

    return
  CDSCeunes.Entities.configureStorage
