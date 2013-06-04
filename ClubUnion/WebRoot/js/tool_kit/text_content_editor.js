

$(function(){
	 TextEditor.editTextContent=function(title,text)
	  {
	    $("#text_content_creator").dialog("destroy");

	    
	    var cid=TextEditor.cid;
        var no=cid.substring(10,cid.length);       

	    var widget=widgets[no];
	    
	    var $node=$("#"+cid)
        $("div.text",$node).html(text);
	    $(".design_title",$node).text(title);


	    widget.setContent({name:"text",content:text});
	    widget.setContent({name:"title",content:title});
	    widget.setContent({name:"title",content:title});
	    widget.setContent({name:"w",content:"200px"});
	    widget.setContent({name:"h",content:"200px"});

	    
	    StyleFixer.adjustFontStyle();
	    
	  
	    
	  }
  TextEditor.createNewTextContent=function (title,text)
	  {
	    $("#text_content_creator").dialog("destroy");
	    var no=widgets.length;
	    var widget=widgets[no]=new Widget(-1);
	    
	    var $node=$("<div />").attr("id","container_"+no).width(200).height(200);
	    var $textNode=$("<div class='design_content' />").html("<p class='text'>"+text+"</p>");
	    var $titleNode=$("<div class='design_title' />").text(title);
	    $node.append($titleNode).append($textNode);
	    
	    
	    widget.doCreateAction();
	    widget.contentType=TKH.TEXT_TYPE;
	    widget.setContent({name:"text",content:text});
	    widget.setContent({name:"title",content:title});
	    widget.setContent({name:"title",content:title});
	    widget.setContent({name:"w",content:"200px"});
	    widget.setContent({name:"h",content:"200px"});

	    StyleFixer.initContainerTheme($node);
	    StyleFixer.initCreatedWidget($node);
	    StyleFixer.adjustFontStyle();
	  
	    
	  }
	   TextEditor.clearTextContent=function(){
	       $("#text_content_creator input").each(function(){this.value=""});
	  
	  }
	  
	  TextEditor.restoreTextInfo=function(){
	     var contentHtml=$("#"+TextEditor.cid).find(".design_content .text").html();
	     var titleHtml=$("#"+TextEditor.cid).find(".design_title").text();
	     $("#text_content_creator .creator_title").get(0).value=titleHtml;
	     $("#text_content_creator .creator_text").get(0).value=contentHtml;
	    
	  }
	  
	  TextEditor.init=function(){
		  $("#text_content_creator a").bind('click',function(){
			  var text=$("#text_creator_editor").get(0).contentWindow.getHTML();
			  var title=$("#text_content_creator .creator_title").attr("value");
			  if (! editting)
			  TextEditor.createNewTextContent(title,text);
			  else
				  TextEditor.editTextContent(title,text);
		       
			  return false;
			  });
	  }
	  
	 
	
})
	 

  
