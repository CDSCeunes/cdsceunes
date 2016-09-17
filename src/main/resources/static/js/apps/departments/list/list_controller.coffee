define [
  'cs!app'
  'cs!apps/departments/list/list_view'
  'q'
], (CDSCeunes, View, Q) ->
  CDSCeunes.module 'DepartmentsApp.List', (List, CDSCeunes, Backbone, Marionette, $, _) ->
    List.Controller = listDepartments: (criterion) ->
      require [ 'cs!entities/department' ], ->
        listLayout = new (View.Layout)
        listPanel = new (View.Panel)
        departmentsListView = undefined
        Q.all(CDSCeunes.request('department:entities')).then (departments) ->
          departmentsListView = new (View.Departments)(collection: departments)
          if criterion
          else
          listLayout.on 'show', ->
            listLayout.panelRegion.show listPanel
            listLayout.departmentsRegion.show departmentsListView
            return
          listPanel.on 'department:new', ->
            require [
              'cs!apps/departments/new/new_view'
              'cs!entities/department'
            ], (NewView) ->
              department = CDSCeunes.request('department:entity:new')
              Q.all(CDSCeunes.request('department:entities')).then (departments) ->
                newView = new (NewView.Department)(model: department)
                CDSCeunes.regions.dialog.show newView
                newView.on 'department:form:submit', (data) ->
                  if department.save(data)
                    departments.add department
                    newView.trigger 'dialog:close'
                  return
                return
              return
            return
          departmentsListView.on 'childview:department:edit', (childview, args) ->
            require [ 'cs!apps/departments/edit/edit_view' ], (EditView) ->
              model = args.model
              Q.all(CDSCeunes.request('department:entities')).then (departments) ->
                editView = new (EditView.Department)(model: model)
                editView.on 'department:form:submit', (data) ->
                  if model.save(data)
                    childview.render()
                    editView.trigger 'dialog:close'
                  return
                CDSCeunes.regions.dialog.show editView
                return
              return
            return
          departmentsListView.on 'childview:department:delete', (childview, args) ->
            args.model.destroy()
            return
          CDSCeunes.regions.main.show listLayout
          return
        return
      return
    return
  CDSCeunes.DepartmentsApp.List.Controller
