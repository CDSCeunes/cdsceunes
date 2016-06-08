define(["app", "handlebars", "text!apps/positions/list/templates/layout.hbs", "text!apps/positions/list/templates/panel.hbs",
  "text!apps/positions/list/templates/list.hbs", "text!apps/positions/list/templates/list_item.hbs"],
  function(CDSCeunes, Handlebars, layoutTpl, panelTpl, listTpl, listItemTpl) {
    CDSCeunes.module("PositionsApp.List.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {
      var LockEdit = false;

      View.Layout = Marionette.LayoutView.extend({
        template: Handlebars.compile(layoutTpl),

        regions: {
          panelRegion: "#panel-region",
          positionsRegion: "#positions-region"
        }
      });

      View.Panel = Marionette.ItemView.extend({
        template: Handlebars.compile(panelTpl),

        triggers: {
          "click button.js-new-position": "position:new"
        }
      });

      View.Position = Marionette.ItemView.extend({
        className: "row",
        template: Handlebars.compile(listItemTpl),

        ui: {
          "edit": "span.js-edit-name",
          "endEdit": "input.js-edit-name"
        },

        events: {
          "dblclick @ui.edit" : "editName",
          "blur @ui.endEdit" : "endEdit",
          "keydown @ui.endEdit": "endEditByKey"
        },

        triggers: {
          "click a.js-edit-position": "position:edit",
          "click a.js-show-position": "position:show",
          "click button.js-delete-position": "position:delete"
        },

        modelEvents: {
          "change": "fieldChanged"
        },

        editName: function(e) {
          if (LockEdit === false) {
            var myEl = this.$el.find(".js-edit-name");
            var cont = "<input type='text' class='js-edit-name' value='" + myEl.text() + "'>";
            myEl.replaceWith(cont);
            LockEdit = true;
          }
        },

        endEdit: function(e) {
          LockEdit = false;
          var myEl = this.$el.find(".js-edit-name");
          var input = $("input.js-edit-name").val();
          this.model.set("name", input);
          this.model.save();
        },

        endEditByKey: function(e) {
          var ENTER_KEY = 13;
          if (e.which === ENTER_KEY) {
            this.endEdit();
          }
        },

        onDomRefresh: function() {
          this.ui.endEdit.focus();
        },

        fieldChanged: function() {
          this.render();
        }
      });

      View.Positions = Marionette.CompositeView.extend({
        template: Handlebars.compile(listTpl),
        childView: View.Position,
        childViewContainer: "#list-item-position",
      });
  });
  return CDSCeunes.PositionsApp.List.View;
});
