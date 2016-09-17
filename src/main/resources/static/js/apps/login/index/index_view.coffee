define [
  'cs!app'
  'handlebars'
  'text!apps/login/index/templates/index.hbs'
], (CDSCeunes, Handlebars, indexTpl) ->
  CDSCeunes.module 'LoginApp.Index.View', (View, CDSCeunes, Backbone, Marionette, $, _) ->
    View.Layout = Marionette.LayoutView.extend(
      template: Handlebars.compile(indexTpl)
      events: 'submit form': 'submit'
      submit: (e) ->
        e.preventDefault()
        console.log 't from submit fn'
        props = document.getElementsByTagName('input')
        user = props.login.value
        password = props.password.value
        @triggerMethod 'login:auth', user, password
        return
    )
    return
  CDSCeunes.LoginApp.Index.View
