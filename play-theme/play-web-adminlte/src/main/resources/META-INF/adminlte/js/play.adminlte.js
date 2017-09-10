;(function($, window, undefined) {
	"use strict";

	var namespace = ".rs.jquery.play";

	var Play = function(element, options) {
		
	};

	Play.defaults = {
		
	};

	Play.prototype.getTotalRowCount = function() {
		return this.total;
	};

	var old = $.fn.play;

	$.fn.play = function(option) {
		var args = Array.prototype.slice.call(arguments, 1), returnValue = null, elements = this.each(function(index) {
			var $this = $(this), instance = $this.data(namespace), options = typeof option === "object" && option;

			if (!instance && option === "destroy") {
				return;
			}
			if (!instance) {
				$this.data(namespace, (instance = new Play(this, options)));
				init.call(instance);
			}
			if (typeof option === "string") {
				if (option.indexOf("get") === 0 && index === 0) {
					returnValue = instance[option].apply(instance, args);
				} else if (option.indexOf("get") !== 0) {
					return instance[option].apply(instance, args);
				}
			}
		});
		return (typeof option === "string" && option.indexOf("get") === 0) ? returnValue : elements;
	};

	$.fn.play.Constructor = Grid;

	$.fn.play.noConflict = function() {
		$.fn.play = old;
		return this;
	};
})(jQuery, window);
