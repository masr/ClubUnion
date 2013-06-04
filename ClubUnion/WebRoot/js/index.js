




$(function(){
	Index.themeChange=function(theme){
		   if (! theme)
			     return;
					var cssLink = $('<link href="/ClubUnion/js/jquery-ui-1.7.2.custom/css/'+theme+'/jquery-ui.css" type="text/css" rel="Stylesheet" class="ui-theme" />');
				
					$("head").append(cssLink);
					if( $("link.ui-theme").size() > 3)
						$("link.ui-theme:first").remove();
	}

	Index.loadCalendarNotices=function(date){
		var $node=$("<div id='calendar_notice_list' />");
		$node.load("platform/calendar.jsf?date="+date);
		$node.dialog({position:'top',title:"活动通知"});
	}
	
	$(".svw").slideView();
	$("#post_scroll").bind("click",function(){window.open ("/ClubUnion/platform/poster_gallery.jsf")})
	Gallery.rollAllGallery();
	$("#document_list").tabs();
	$("#club_list").accordion();
	$("#friend_list").accordion();
    
	$("#calendar").datepicker({
		onSelect:function(dateText){
		Index.loadCalendarNotices(dateText);
	}
	});
	$("#theme_switcher").themeswitcher({onSelect:Index.themeChange})
});