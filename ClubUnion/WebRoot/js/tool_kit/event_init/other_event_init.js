$(function(){
	$.extend(eventInit,{
		cEditTitle:function(e){
	    $("#title_editor").dialog();
	        titleEditor.cid=topId;
	        $("#title_editor").bind("dialogclose",function(){
	          $(this).dialog('destroy');      
	        	});

	        	return false; 	
	        },
	cEditContent:function(e){
	var no=topId.substring(10,topId.length); 
	
	if(widgets[no].contentType==TKH.TEXT_TYPE)
	{
		TextEditor.cid=topId;
		TextEditor.clearTextContent();
		editting=true;
		TextEditor.restoreTextInfo();


		$("#text_content_creator").dialog({width:"670px"});
	   $("#text_content_creator").bind("dialogclose",function(){
	    $(this).dialog('destroy');    
	});
		
	}
	if(widgets[no].contentType==TKH.GALLERY_TYPE)
	{
		GalleryManager.cid=topId;
		GalleryManager.clearGallery();
		GalleryManager.restoreGallery();
		editting=true;
		
		$("#gallery_manager").dialog({width:"670px"});
	 $("#gallery_manager").bind("dialogclose",function(){
	  $(this).dialog('destroy');    
	});

	}
	return false;
	       },
	cDelete:function(e) {

	var no=topId.substring(10,topId.length);
	if (widgets[no].contentType>TKH.DEFAULT_TYPE)
	{

	$("#"+topId).hide();
	widgets[no].deleteMe();
	}
	return false;
	},

	themeSelect:function(theme){

	if (! theme)
	return;
	var cssLink = $('<link href="/ClubUnion/js/jquery-ui-1.7.2.custom/css/'+theme+'/jquery-ui.css" type="text/css" rel="Stylesheet" class="ui-theme" />');

	$("head").append(cssLink);
	if( $("link.ui-theme").size() > 3)
	$("link.ui-theme:first").remove();

	$(".unique_theme").removeClass("unique_theme");
	pageStyle.setContent({name:"themeName",content:theme});
	$.each(widgets,function(){this.setContent({name:"themeName",content:" "})});

	},
	cThemeSelect:function(theme){


	if (! (theme) || (!topId))
	return;

	var selector=$("#"+topId).parent("div:first").addClass('unique_theme').attr("id");
	$.ajax({
	url: "/ClubUnion/servlet/ContainerThemeChange?selector_id="+selector+".unique_theme"+"&theme_name="+theme,
	dataType: "html",
	success:function(source){$("head").append($("<style>"+source+"</style>"));}
	});
	var no=topId.substring(10,topId.length);
	widgets[no].setContent({name:"themeName",content:theme});
	},
	})
})
