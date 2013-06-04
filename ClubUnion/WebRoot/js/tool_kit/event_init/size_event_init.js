$(function(){
	$.extend(eventInit,{
borderSolidSlide:function(event,ui){
		   var solid=TKH.percentToSolid(ui.value);
	    $(".design").css("border-width",solid);
	    pageStyle.setContent({name:"borderSolid",content:solid});
	     $.each(widgets,function(){
	    this.setContent({name:"borderSolid",content:" "})
	    })
	},
	fontSizeSlide:function(event,ui){
		
	    var size=TKH.percentToFontSize(ui.value);
		     $(".design :all,.design").css("font-size",size);
		       pageStyle.setContent({name:"fontSize",content:size});
		         $.each(widgets,function(){
		       this.setContent({name:"fontSize",content:" "})
		       })
		       StyleFixer.adjustFontStyle();
	},
	cBorderSolidSlide: function(event, ui) {
	var id=topId.substring(10,topId.length);
	var solid=TKH.percentToSolid(ui.value);
	$("#"+topId).css("border-width",solid);
	widgets[id].setContent({name:"borderSolid",content:solid});
	},
	cFontSizeSlide:function(event, ui) {
	var id=topId.substring(10,topId.length);
	var size=TKH.percentToFontSize(ui.value);
	$("#"+topId+" :all,#"+topId).css("font-size",size);
	widgets[id].setContent({name:"fontSize",content:size});
	StyleFixer.adjustFontStyle();
	}
	})
})
