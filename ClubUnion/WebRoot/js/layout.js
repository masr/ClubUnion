$(function(){
     
	$(".document_ele div.content img").each(function(){
			
            
			$t=$(this).clone();
			$t.get(0).style.width="";
			$t.height(90);
			$(this).parents("div.content").before($t);
			$(this).remove();
		
	})
	
	$(".document_ele").each(function(){
		html=$("div.content",this).html()
		if (html.length>300)
			html=html.substring(0,300)+"......"
		$("div.content",this).html(html)
			
	})
})