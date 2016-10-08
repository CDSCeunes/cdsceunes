define [
  'cs!app'
], (CDSCeunes) ->
  Entities = ->
    model = Backbone.Model.extend(
      urlRoot: '/api/v1/semesters'
    )

    collection = Backbone.Collection.extend(
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
  Semester: model, SemesterCollection: collection
