define(["app",
        "handlebars",
        "text!apps/teachers/list/templates/layout.hbs",
        "text!apps/teachers/list/templates/panel.hbs",
        "text!apps/teachers/list/templates/list.hbs",
        "text!apps/teachers/list/templates/list_item.hbs"],
          function(CDSCeunes, Handlebars, layoutTpl, panelTpl, listTpl, listItemTpl) {
  CDSCeunes.module("TeachersApp.List.View", function(View, CDSCeunes, Backbone, Marionette, $, _) {

    var LockEdit = false;

    View.Layout = Marionette.LayoutView.extend({
      template: Handlebars.compile(layoutTpl),

      regions: {
        panelRegion: "#panel-region",
        teachersRegion: "#teachers-region"
      }

    });

    View.Panel = Marionette.ItemView.extend({
      template: Handlebars.compile(panelTpl),

      triggers: {
        //"click button.js-new-teacher": "teacher:new"
      },

      events: {
        "submit #filter-teacher": "filterTeachers",
        "keypress .js-filter-teacher": "filterTeachers",
        "click button.js-new-teacher": "newTeacher"
      },

      ui: {
        "search": "input.js-filter-teacher"
      },

      newTeacher: function(e) {
        this.triggerMethod("teacher:new", $("meta[name=_csrf_header]").attr('content'),
          $("meta[name=_csrf]").attr('content'));
      },

      filterTeachers: function(e) {
        var search = this.ui.search.val();
        if (((e.key >= "A" && e.key <= "Z") || (e.key >= "a" && e.key <= "z"))
            && e.key.length === 1) {
          search = search + e.key;
        }
        this.trigger("teacher:filter", search);
      },

      onSetFilterCriterion: function(criterion){
        this.ui.search.val(criterion);
      }
    });

    View.Teacher = Marionette.ItemView.extend({
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
        "click a.js-edit-teacher": "teacher:edit",
        "click a.js-show-teacher": "teacher:show",
        "click button.js-delete-teacher": "teacher:delete"
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
      },

      filter: function(search) {
        if (this.model.shouldBeShown(search)) {
          this.$el.show();
        } else {
          this.$el.hide();
        }
      }
    });

    View.Teachers = Marionette.CompositeView.extend({
      template: Handlebars.compile(listTpl),
      childView: View.Teacher,
      childViewContainer: "#list-item-teacher"
    });

  });

  return CDSCeunes.TeachersApp.List.View;
});
