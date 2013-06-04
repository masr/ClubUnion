$(function(){
	StyleFixer.adjustHeight=function()
	{

    
    $(".design").each(function(){
    	
    	if ($("#main").height()<($(this).height()+$(this).top()))
    		$("#main").height($(this).height()+$(this).top()+100);
    	
    	if ($(this).top()<=0)
    	{
    		$(this).css("top","0px");
    	}
    });
	}
	StyleFixer.adjustWidth=function()
	{
		
		$(".design").each(function(){
			
               allWidth=$(this).left()+$(this).width();
               mainWidth=$("#main").width();
               
             
               if (allWidth>mainWidth)
            	   $(this).width(mainWidth-$(this).left());
               if($(this).left()<0)
            	   $(this).css("left","0px");
               
		});
	}
	
	StyleFixer.adjustFontStyle=function()
	        {
	        	
	        //调整小导航栏，使文字垂直居中
	        	$("#sub_header").each(function(){
	        		$(this).css("line-height",$(this).parent(".design").css("height"));
	           //为什么在这里#sub_header的height为0，而上层的.design却有height，而且就height有问题。是不是和li的浮动有关系       
	        	});
	        	//逐个调整container中的字体相对大小，涉及到标题和内容
	        	$(".design").each(function(){
	        		
	        		size=$(this).css("font-size");
	            	size=size.substring(0,size.indexOf("p"));
	            	size=parseInt(size);
	        		$('.design_title',this).css("font-size",Math.round(size*1.4)+"px");
	        	});
	          //将img_content 设为无padding
	        	
	        },
	        StyleFixer.adjustResizingImg=function()//使图片跟着边框走
	        {
	          $(".design:has(#logo_block),").add(".design:has(.img_block),").each(function(){
	        	  $("img",this).width($(this).width())
	          })
	          $(".design:has(.dynamic_gallery)").each(function(){

	              $("img",this).each(function(){
	                $(this).width($(this).parents(".design").width())
	              })
	            });
	        
	          
	        }
	        StyleFixer.adjustImgStyle=function()
        { 
	        	 $(".design:has(.img_block)").add(".design:has(#logo_block)").each(function(){
	    	          $("img",this).css("width",$(this).css("width"));
	    	          $(this).css("padding","0px");
	    	          });
	        	$(".dynamic_gallery").parents(".design").each(function(){
    	                $("img",this).each(function(){
    	                $(this).width($(this).parents('.design').width())
    	                });
    	        	});
	        	
        }
	        
	        
	      
	    
        StyleFixer.initCreatedWidget=function($node){

	        	     var id=$node.attr("id");
	        	  
	        	     var no=id.substring(10,id.length);
	        	     $node.addClass("design").appendTo("#club_style_edit").wrap("<div id='theme_container_"+no+"'/>");

	        	     $node.css("top","120px");
	        	     widgets[no].setContent({name:"y",content:"120px"})
	        	     $node.css("left","0px");
	        	     widgets[no].setContent({name:"x",content:"0px"})
	        	   
	        	     
	        	     $node.resizable();
	        	     $node.draggable();
	        	    $node.bind('dragstop', eventInit.containerDragStop);
	        		$node.bind('resize', eventInit.containerResize);
	        		$node.bind('resizestop', eventInit.containerResizeStop);
	        	    $node.bind('click',eventInit.containerClick);
	        	  
	        	 
	        }
	        StyleFixer.initPageTheme=function()
	        {
	        	
	        	   
	        	    
	        		$(".design").not('.design:has(.img_block)').addClass("ui-widget ui-widget-content ui-helper-clearfix ui-corner-all")
	        			.find(".design_title")
	        				.addClass("ui-widget-header ui-corner-all");

	        	

	        }
	        StyleFixer.initContainerTheme=function($node){
	        	
	        	$node.addClass("ui-widget ui-widget-content ui-helper-clearfix ui-corner-all")
				.find(".design_title")
					.addClass("ui-widget-header ui-corner-all");
	        }
	        



	

})
		
