define [
  'cs!app'
  'marionette'
], (CDSCeunes, Marionette) ->
  View =
    Layout: Marionette.View.extend(
      template: 'login/layout'
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
