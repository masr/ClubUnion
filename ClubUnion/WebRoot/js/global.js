 var eventInit={} 
 var imgUploader={
			chk_imgpath:function(){},
           init:function(){}
	}
var UploadResponse={
			  img_form_action:'',
			  createNewImgContent:function ()  {},
	        LoadLogoIMG :function (){},
	        LoadRightBackIMG:function (){},
	        insertNewGalleryImg:function(){},
	        LoadLeftBackIMG:function (){},
	        
	        LoadTopBackIMG:function (){},
	        LoadMainBackIMG:function (){}
	}
var ajaxAction={
		  sendContainerInfos:function(){},

         updatePageStyle:function(){}
 }
 var titleEditor={
			cid:null,
			changeTitle:function(text){},
			init:function(){}
	        
	}
 var  GalleryManager={
			cid:null,
			recycle_icon : '',
			trash_icon : '',
			ulHTML:'',
			createNewGallery : function(urls) {
			},
			initGalleryImg : function() {
			},
			initGalleryManager : function() {
			},
			deleteImage : function() {
			},
			recycleImage : function($item) {
			},
			adjustDialogImg : function($modal) {
			},
			viewLargerImage : function($link) {
			},
			restoreGallery:function(){
			},
			clearGallery:function(){
				
			},
			insertNewGalleryImg : UploadResponse.insertNewGalleryImg

		}
 
 var TKH={
			percentToSolid:function(x){},

			percentToFontSize:function(i){ },
			solidToPercent:function(s){},
			fontSizeToPercent:function(size){},
			isAlpha:function(cCheck){},
		  	USER_LIST_TYPE : 0,
	    	DOCUMENT_LIST_TYPE : 1,
	    	GUIDE_HEADER_TYPE : 2,
	    	LOGO_TYPE :3,
	        MESSAGE_TYPE:4,
	        DEFAULT_TYPE:5,
	    	IMG_TYPE :6,
	    	 TEXT_TYPE:7,
	    	 LINK_LIST_TYPE:8,
	    	 GALLERY_TYPE:9,
 }
 
 var palette={
		colorSelect: function (e,fn){},
		
 }
 
 var TextEditor={
			cid:null,
			editTextContent:function(title,text){},
			createNewTextContent:function(title,text){},
			clearTextContent:function(){},
			restoreTextInfo:function(){},
			init:function(){}
	}
 var ToolKitInit={
		 initDesignClass:function(){},
		 initSlider:function(){},
		 initPageColorPalette:function(){},
		 initContainerColorPalette:function(){},
		 initContainerAction:function(){},
		 initOtherAction:function(){},
		 initThemeSwitcher:function(){},
		 initBackRepeat:function(){},
		 init:function(){}
 }
 var StyleFixer={
			
		 adjustHeight:function(){},
		 adjustWidth:function(){},
		     	
		 adjustFontStyle:function(){},
		 adjustResizingImg:function(){},
		 adjustImgStyle:function(){},
		 initCreatedWidget:function($node){},
		 initPageTheme:function() {},
		 initContainerTheme:function($node){ },
		     	        



		 		
		 }
 var Gallery={
			rollAllGallery:function(){}
	};
 var Index={
			loadCalendarNotices:function(date){},
			themeCahnge:function(theme){}
	};
 
var PosterUploader={
		LoadPosterIMG:function(url){}
}
	
 var clubId
 var pageStyle;
 var topId;
 var editting;
 var widgets;
		

	
	
	
	
	