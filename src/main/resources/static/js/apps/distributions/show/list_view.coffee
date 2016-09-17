define [
    'cs!app'
    'handlebars'
    'text!apps/distributions/show/templates/list.hbs'
    'text!apps/distributions/show/templates/list_item.hbs'
], (CDSCeunes, Handlebars, listTpl, listItemTpl) ->
  CDSCeunes.module 'DistributionsApp.Show.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    LockEdit = false

    Handlebars.registerHelper('checkTeacherExistance', (teacher, options) ->
      if teacher == undefined
        return '<a href="#distributions/{{ id }}" class="tiny hollow button js-show-distribution">
          <i class="fa fa-eye"></i>
          Selecionar
        </a>'
      else
        return '#{teacher.name}<a href="#distributions/{{ id }}" class="tiny hollow button js-show-distribution">
          <i class="fa fa-eye"></i>
          Re-selecionar
        </a>'
    )

    View.Distribution = Marionette.ItemView.extend(
      className: 'row'
      template: Handlebars.compile(listItemTpl,
        knownHelpers: ['checkTeacherExistance'])
      modelEvents: 'change': 'fieldChanged')
      childViewContainer: '#list-item-distribution'
    return
    CDSCeunes.DistributionsApp.Show.View
