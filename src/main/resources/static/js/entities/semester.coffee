define [
  "cs!app"
  "q"
], (CDSCeunes, Q) ->
  CDSCeunes.module 'Entities', (Entities, CDSCeunes, Backbone, Marionette, $, _) ->
    Entities.Semester = Backbone.Model.extend(
      urlRoot: '/api/v1/semesters'
    )

    Entities.SemesterCollection = Backbone.Collection.extend(
      url: '/api/v1/semesters'
      model: Entities.Semester
      comparator: (s1, s2) ->
        year1 = parseInt(s1.get('year'))
        year2 = parseInt(s2.get('year'))
        semes1 = parseInt(s1.get('semester'))
        semes2 = parseInt(s2.get('semester'))

        if year1 == year2
          if semes1 > semes2
            -1
          else if semes1 < semes2
            1
          else
            0
        else if year1 > year2
          -1
        else
          1
    )

    API =
      getSemesterEntity: (year, semes) ->
        semester = new (Entities.Semester)(
          year: year
          semester: semes
        )
        Q.promise (resolve) ->
          semester.fetch
            success: (data) ->
              resolve(data)
              return
            error: (data) ->
              resolve(undefined)
              return
          return

      getSemestersEntities: ->
        semesters = new (Entities.SemesterCollection)
        Q.promise (resolve) ->
          semesters.fetch
            success: (data) ->
              resolve(data)
              return
            error: (data) ->
              resolve(undefined)
              return
          return

    CDSCeunes.reqres.setHandler 'semesters:entity', (year, semester) ->
      API.getSemesterEntity(year,semester)
    CDSCeunes.reqres.setHandler 'semesters:entities', ->
      API.getSemestersEntities()
    CDSCeunes.reqres.setHandler 'semesters:entity:new', ->
      new (Entities.Semester)
    return

  return
