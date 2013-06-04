

  $(function(){
	  titleEditor.init=function(){
		  $("#edit_title_form a").bind('click',function(){
			  var text=$("#edit_title_form .input_title").get(0).value;
			  titleEditor.changeTitle(text);
			  return false;
			  });
	  }
	  
	  titleEditor.changeTitle=function(text){
		  $("#title_editor").dialog("destroy");
		   no=titleEditor.cid.substring(10,titleEditor.cid.length);
		    $("#"+titleEditor.cid).find(".design_title").text(text);
		    widgets[no].setContent({name:"title",content:text});
		    StyleFixer.adjustFontStyle();
	  }

  });

  
  