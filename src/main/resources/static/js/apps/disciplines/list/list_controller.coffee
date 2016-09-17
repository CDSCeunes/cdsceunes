define [
  'cs!app'
  'cs!apps/disciplines/list/list_view'
  'q'
], (CDSCeunes, View, Q) ->
  CDSCeunes.module 'DisciplinesApp.List', (List, CDSCeunes, Backbone, Marionette, $, _) ->
    List.Controller = listDisciplines: (criterion) ->
      require [ 'cs!entities/discipline' ], ->
        listLayout = new (View.Layout)
        listPanel = new (View.Panel)
        disciplinesListView = undefined
        
        Q.all(CDSCeunes.request('discipline:entities')).then (disciplines) ->
          disciplinesListView = new (View.Disciplines)(collection: disciplines)
          if criterion
          else
          listLayout.on 'show', ->
            listLayout.panelRegion.show listPanel
            listLayout.disciplinesRegion.show disciplinesListView
            return
          listPanel.on 'discipline:new', ->
            require [
              'cs!apps/disciplines/new/new_view'
              'cs!entities/discipline'
            ], (NewView) ->
              discipline = CDSCeunes.request('discipline:entity:new')
              Q.all(CDSCeunes.request('discipline:entities')).then (disciplines) ->
                newView = new (NewView.Discipline)(model: discipline)
                CDSCeunes.regions.dialog.show newView
                newView.on 'discipline:form:submit', (data) ->
                  if discipline.save(data)
                    disciplines.add discipline
                    newView.trigger 'dialog:close'
                  return
                return
              return
            return
          disciplinesListView.on 'childview:discipline:edit', (childview, args) ->
            require [ 'cs!apps/disciplines/edit/edit_view' ], (EditView) ->
              model = args.model
              Q.all(CDSCeunes.request('discipline:entities')).then (disciplines) ->
                editView = new (EditView.Discipline)(model: model)
                editView.on 'discipline:form:submit', (data) ->
                  if model.save(data)
                    childview.render()
                    editView.trigger 'dialog:close'
                  return
                CDSCeunes.regions.dialog.show editView
                return
              return
            return
          disciplinesListView.on 'childview:discipline:delete', (childview, args) ->
            args.model.destroy()
            return
          CDSCeunes.regions.main.show listLayout
          return
        return
      return
    return
  CDSCeunes.DisciplinesApp.List.Controller
