define [
  'cs!app'
  'marionette'
  'text!apps/templates/login/layout.hbs'
], (CDSCeunes, Marionette, layoutTpl) ->
  View =
    Layout: Marionette.View.extend(
      template: layoutTpl
      className: 'login-view'
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

  View
