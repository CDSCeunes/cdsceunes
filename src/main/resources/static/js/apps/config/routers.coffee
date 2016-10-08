define (require) ->
  Routers =
    Teacher: new (require 'cs!apps/routers/teacher_router')
    Login: new (require 'cs!apps/routers/login_router')
  Routers
