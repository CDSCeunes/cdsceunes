define [
  'cs!app'
  'cs!apps/teachers/list/list_view'
  'q'
], (CDSCeunes, View, Q) ->
  CDSCeunes.module 'TeachersApp.List', (List, CDSCeunes, Backbone, Marionette, $, _) ->
    List.Controller = listTeachers: (criterion) ->
      require [
        'cs!entities/common'
        'cs!entities/teacher'
      ], (FilteredCollection) ->
        listLayout = new (View.Layout)
        listPanel = new (View.Panel)
        teachersListView = undefined
        Q.all(CDSCeunes.request('teacher:entities')).then (teachers) ->
          filteredTeachers = FilteredCollection(
            collection: teachers
            filter: (criterion) ->
              (teacher) ->
                teacher.get('name').indexOf(criterion) > -1 or teacher.get('login').indexOf(criterion) > -1
          )
          listLayout.on 'show', ->
            listLayout.panelRegion.show listPanel
            listLayout.teachersRegion.show teachersListView
            return
          if criterion
            filteredTeachers.filter criterion
            listPanel.once 'show', ->
              listPanel.triggerMethod 'set:filter:criterion', criterion
              return
          teachersListView = new (View.Teachers)(collection: filteredTeachers)
          listPanel.on 'teacher:new', ->
            console.log header
            console.log token
            console.log $('meta[name=_csrf_header]').attr('content')
            require [
              'cs!apps/teachers/new/new_view'
              'cs!entities/department'
              'cs!entities/teacher'
            ], (NewView) ->
              teacher = CDSCeunes.request('teacher:entity:new')
              Q.all(CDSCeunes.request('department:entities')).then (departments) ->
                h = new (Backbone.Model)(name: header)
                console.log h
                newView = new (NewView.Teacher)(
                  header: h.toJSON()
                  token1: token
                  model: teacher
                  departments: departments)
                CDSCeunes.regions.dialog.show newView
                newView.on 'teacher:form:submit', (data) ->
                  if teacher.save(data)
                    teachers.add teacher
                    newView.trigger 'dialog:close'
                  return
                return
              return
            return
          listPanel.on 'teacher:filter', (search) ->
            teachersListView.trigger 'teacher:filter', search
            CDSCeunes.trigger 'teachers:filter', search
            return
          teachersListView.on 'teacher:filter', (search) ->
            filteredTeachers.filter search
            return
          teachersListView.on 'childview:teacher:edit', (childview, args) ->
            require [
              'cs!apps/teachers/edit/edit_view'
              'cs!entities/department'
            ], (EditView) ->
              model = args.model
              Q.all(CDSCeunes.request('department:entities')).then (departments) ->
                editView = new (EditView.Teacher)(
                  model: model
                  departments: departments)
                editView.on 'teacher:form:submit', (data) ->
                  if model.save(data)
                    childview.render()
                    editView.trigger 'dialog:close'
                  return
                CDSCeunes.regions.dialog.show editView
                return
              return
            return
          teachersListView.on 'childview:teacher:delete', (childview, args) ->
            args.model.destroy()
            return
          teachersListView.on 'childview:teacher:show', (childview, args) ->
            CDSCeunes.trigger 'teachers:show', args.model.get('id')
            return
          CDSCeunes.regions.main.show listLayout
          return
        return
      return
    return
  CDSCeunes.TeachersApp.List.Controller
