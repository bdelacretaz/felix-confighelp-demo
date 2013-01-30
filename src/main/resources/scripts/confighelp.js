/** Example script that adds help links
 *  to the Felix webconsole config forms.
 *  
 *  Uses jQuery to dynamically build links
 *  for any elements having the configHelpLink CSS
 *  class on the page. The webconsole config page
 *  creates forms dynamically, so we need to 
 *  react to element creation.
 */

(function($) {
	// Add a link to a configHelpLink element,
	// based on its data-config-* attributes
	function addHelpLink(el) {
		// Example using google.com for help
		var param = el.attr("data-config-pid") + "/" + el.attr("data-config-param"); 
		var url = "https://google.com?q=" + param;
		var title = "Get help for the " + el.attr("data-config-name") + " parameter";
		el.html("<a title='" + title + "' target='_blank' href='" + url + "'>(?)</a>");
	}

	// There's probably a better way to detect when the config page
	// creates form elements...this works for our demo
	var ignoreEvents = false;
	$(document).ready(function() {
	    $(document).bind('DOMSubtreeModified',function(){
	        if(ignoreEvents) return;
	        ignoreEvents = true;
	    	$(".configHelpLink").each(
	    			function() {
	    				addHelpLink($(this));
	    			}
	    	);
	        ignoreEvents = false;
	    })
	});
} ) (jQuery);

