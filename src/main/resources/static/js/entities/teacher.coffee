define [
  'cs!app'
  'backbone'
], (CDSCeunes, Backbone) ->
  Entities = ->
    Teacher = Backbone.Model.extend(
      urlRoot: '/api/v1/teachers'
      defaults:
        name: ''
        login: ''
      shouldBeShown: (search) ->
        name = @get('name').toLowerCase()
        login = @get('login').toLowerCase()
        name.indexOf(search) > -1 or login.indexOf(search) > -1
      workload: ->
        workload = 0
        if @get('classes')
          _.each @get('classes'), (class_, index, list) ->
            discipline = class_.discipline
            workload += (discipline.teoricLoad || 0) + (discipline.labLoad || 0 ) +
              (discipline.exerciseLoad || 0)
            return
        return workload/15
    )

    TeachersCollection = Backbone.Collection.extend(
      url: '/api/v1/teachers'
      model: Teacher
      comparator: 'name'
      fetchWithClasses: (args, opts) ->
        opts = opts || {}
        if opts.url == undefined
          opts.url = "#{@url}/with_classes"
          if args.year and args.semester
            opts.url = "#{opts.url}/#{args.year}/#{args.semester}"
        Backbone.Collection::fetch.call(this, opts)

    )

    return {
      Teacher: Teacher
      TeachersCollection: TeachersCollection
    }


  Entities()
