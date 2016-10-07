define [
  "cs!app"
  "handlebars"
  "text!apps/distributions/show/templates/list.hbs"
  "text!apps/distributions/show/templates/item.hbs"
], (CDSCeunes, Handlebars, listLayoutTpl, itemTpl) ->
  CDSCeunes.module "DistributionsApp.Show.View", (View, CDSCeunes, Backbone, Marionette, $, _) ->
    View.ClassL = Marionette.ItemView.extend(
      template: Handlebars.compile(itemTpl)
      templateHelpers:
        checkTeacherExistance: (model) ->
          button = '<button class="">Selecionar professor</button>'
          if model.teacher
            return "#{model.teacher} | #{button}"
          else
            return button
          return
    )

    View.Classes = Marionette.CollectionView.extend(
      template: Handlebars.compile(listLayoutTpl)
      childView: View.ClassL
      childViewContainer: "#list-item-distribution"
    )



    return


  CDSCeunes.DistributionsApp.Show.View;
