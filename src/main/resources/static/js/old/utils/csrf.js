define(["app"], function(CDSCeunes) {
  CDSCeunes.module("Utils", function(Utils, CDSCeunes, Backbone, Marionette, $, _) {
    Utils.CSRF: function(view) {
      var token = view.$("meta[name='_csrf']").attr("content");
      var header = view.$("meta[name='_csrf_header']").attr("content");
      console.log(token);
      console.log(header);
    }
  });
});
