$(function(){
	Gallery.rollAllGallery=function(){              
    	setInterval(function(){
    		$(".dynamic_gallery .stripTransmitter").each(function(){
        		num=$('li',this).length;
        		$('li a',this).eq(parseInt(Math.random()*num)).click();
        	});
    	},4000);
    	
    }
})
