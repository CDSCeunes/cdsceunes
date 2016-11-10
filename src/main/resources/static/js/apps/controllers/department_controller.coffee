define [
  'cs!app'
  'marionette'
  'cs!apps/views/department_view'
  'jquery'
], (CDSCeunes, Marionette, View, $) ->
  Controller =
    Department: Marionette.Object.extend(
      list: (criterion) ->
        require [
          'cs!entities/common'
          'cs!apps/radios/data/department_notify'
        ], (FilteredCollection) ->
          list_layout = new (View.Layout)
          list_panel = new (View.Panel)
          list_departments = undefined
          filtered_departments = undefined

          $.when(CDSCeunes.dataRequest 'department:entities').done (departments) ->
            filtered_departments = FilteredCollection(
              collection: departments
              filter: (criterion) ->
                (department) ->
                  department.get('name').indexOf(criterion) > -1
            )
            list_layout.on 'childview:department:new', ->
              console.log 'Showing new department dialog'
              CDSCeunes.regions.showChildView 'dialog', new (View.Form)(
                model: CDSCeunes.dataRequest 'department:entity:new'
              )
              return

            list_departments = new (View.DepartmentsList)(collection: filtered_departments)

            list_layout.on 'render', ->
              list_layout.showChildView 'panelRegion', list_panel
              list_layout.showChildView 'departmentsRegion', list_departments
              return # end show

            CDSCeunes.regions.showChildView 'main', list_layout
            return # end promise


          return # end require
        return # end list
    )

  Controller.Department
