define [
  "backbone"
], (Backbone) ->
  RequestErrorHandler = (CDSCeunes) ->
    oldSync = Backbone.sync
    Backbone.sync = (method, model, opts) ->
      xhr = oldSync.call(this, method, model, opts);
      xhr.fail (jqXHR) ->
        status = jqXHR.status;
        if status == 401 || status == 500
          CDSCeunes.route 'login:home'
        return
      return xhr;
    return Backbone.sync
  return RequestErrorHandler
