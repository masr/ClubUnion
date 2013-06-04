$(function(){
	$.fn.extend({
	  top:function(){
		top=$(this).css("top");
		index=top.indexOf("p");
		return parseInt(top.substring(0,index));
	},
	left:function(){
		left=$(this).css("left");
		index=left.indexOf("p");
		return parseInt(left.substring(0,index));
	}
	});
	
	
})