define [
  'cs!app'
  'marionette'
  'cs!apps/views/teacher_view'
  'jquery'
], (CDSCeunes, Marionette, View, $) ->
  Controller =
    Teacher: Marionette.Object.extend(
      list: (criterion) ->
        require [
          'cs!entities/common'
          'cs!entities/teacher'
          'cs!apps/radios/data/teacher_notify'
        ], (FilteredCollection) ->
          list_layout = new (View.Layout)
          list_panel = new (View.Panel)
          list_teachers = undefined
          filtered_teachers = undefined

          $.when(CDSCeunes.dataRequest 'teacher:entities').done (teachers) ->
            filtered_teachers = FilteredCollection(
              collection: teachers
              filter: (criterion) ->
                (teacher) ->
                  teacher.get('name').indexOf(criterion) > -1 or teacher.get('login').indexOf(criterion) > -1
            )

            list_teachers = new (View.TeachersList)(collection: filtered_teachers)

            list_layout.on 'render', ->
              list_layout.showChildView 'panelRegion', list_panel
              list_layout.showChildView 'teachersRegion', list_teachers
              return # end show

            CDSCeunes.regions.showChildView 'main', list_layout
            return # end promise


          return # end require
        return # end list
    )

  Controller.Teacher
