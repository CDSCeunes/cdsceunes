define(["app"], function(CDSCeunes) {
  CDSCeunes.module("Entities", function(Entities, CDSCeunes, Backbone, Marionette, $, _) {
    Entities.FilteredCollection = function(options) {
      var original = options.collection;
      var filtered = new original.constructor();

      filtered.add(original.models);
      filtered.filterFunction = options.filter;

      var applyFilter = function(filterCriterion, filterStrategy, collection) {
        var collection = collection || original;
        var criterion;
        if (filterStrategy === "filter") {
          criterion = filterCriterion.trim();
        } else {
          criterion = filterCriterion;
        }

        var items = [];
        if (criterion) {
          if (filterStrategy === "filter") {
            if (! filtered.filterFunction) {
              throw("Attempted to use 'filter' function, but none was defined.");
            }

            var filterFunction = filtered.filterFunction(criterion);
            items = collection.filter(filterFunction);
          } else {
            items = collection.where(criterion);
          }
        } else {
          items = collection.models;
        }

        filtered._currentCriterion = criterion;

        return items;
      }

      filtered.filter = function(filterCriterion) {
        filtered._currentFilter = "filter";
        var items = applyFilter(filterCriterion, "filter");

        // reset the filtered collection with the new items
        filtered.reset(items);
        return filtered;
      }

      filtered.where = function(filterCriterion){
        filtered._currentFilter = "where";
        var items = applyFilter(filterCriterion, "where");

        // reset the filtered collection with the new items
        filtered.reset(items);
        return filtered;
      };

      original.on("reset", function(){
        var items = applyFilter(filtered._currentCriterion, filtered._currentFilter);

        // reset the filtered collection with the new items
        filtered.reset(items);
      });

      original.on("add", function(models){
        var coll = new original.constructor();
        coll.add(models);
        var items = applyFilter(filtered._currentCriterion, filtered._currentFilter, coll);
        filtered.add(items);
      });

      return filtered;
    };
  });
  return CDSCeunes.Entities.FilteredCollection;
});