
$(function(){
	$.extend(eventInit,{
		containerDragStop:function(){
	    var no=this.id.substring(10,this.id.length);
	    widgets[no].setContent({name:"x",content:$(this).css("left")});
	    widgets[no].setContent({name:"y",content:$(this).css("top")})
	   StyleFixer.adjustHeight();
	    StyleFixer.adjustWidth();
	   },
	containerResize:function(){
		   StyleFixer.adjustHeight();
		   StyleFixer.adjustResizingImg();
		   $('.stripViewer').slideViewResize();
	   },
	containerResizeStop:function(){
		   var no=this.id.substring(10,this.id.length);
	       widgets[no].setContent({name:"w",content:$(this).css("width")});
	       widgets[no].setContent({name:"h",content:$(this).css("height")})     
	          StyleFixer.adjustHeight();
	       StyleFixer.adjustWidth();
	   },
	containerClick:function(){
		   var no=this.id.substring(10,this.id.length);
	       $("#link_to_tab2 a").click();
	       topId=this.id;
	       $("#c_font_size_slider .slider").slider("option","value",TKH.fontSizeToPercent($(this).css("font-size")));
	       var borderColor=$(this).css("border-color");
	       $(this).fadeOut(100).fadeIn(100);
	       
	       if(widgets[no].contentType > TKH.DEFAULT_TYPE)
	       $("#c_delete").show();
	       else
	       $("#c_delete").hide();
	   }
	})
})
