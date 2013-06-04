$(function(){
	$.extend(eventInit,{
		uploadLogo:function(e){
	    $("#img_uploader").dialog();
	    $("#img_uploader").bind("dialogclose",function(){
	       $(this).dialog('destroy');      
	       });
	         UploadResponse.img_form_action="/ClubUnion/servlet/LogoUpload";//这里有问题
	        return false;
	},
	uploadLeftBack:function(e){
	  $("#img_uploader").dialog();
	  $("#img_uploader").bind("dialogclose",function(){
	           $(this).dialog('destroy');      
	  });
	  UploadResponse.img_form_action="/ClubUnion/servlet/LeftBackImgUpload";
	  return false;
	  },
	uploadRightBack:function(e){
	      $("#img_uploader").dialog();
	      $("#img_uploader").bind("dialogclose",function(){
	               $(this).dialog('destroy');      
	      });
	      UploadResponse.img_form_action="/ClubUnion/servlet/RightBackImgUpload";
	      return false;
	      },
	uploadImgContent:function(){
	  	    $("#img_uploader").dialog();
		     $("#img_uploader").bind("dialogclose",function(){
	               $(this).dialog('destroy');      
		});
		  UploadResponse.img_form_action="/ClubUnion/servlet/ImgContentUpload";

		return false;
		},
	uploadMainBack:function(){
		    $("#img_uploader").dialog();
		     $("#img_uploader").bind("dialogclose",function(){
	           $(this).dialog('destroy');      
		});
		     UploadResponse.img_form_action="/ClubUnion/servlet/MainBackImgUpload";

		return false;
		},
	uploadTopBack:function(){
		    $("#img_uploader").dialog();
		     $("#img_uploader").bind("dialogclose",function(){
	           $(this).dialog('destroy');      
		});
		     UploadResponse.img_form_action="/ClubUnion/servlet/TopBackImgUpload";

		return false;
		},
	uploadText:function(){
			TextEditor.clearTextContent();
			  editting=false;

		     $("#text_content_creator").dialog({width:"670px"});
		     $("#text_content_creator").bind("dialogclose",function(){
	       $(this).dialog('destroy');    
		});
			return false;
		
		},
	uploadGallery:function(){
			editting=false;


		     $("#gallery_manager").dialog({width:"670px"});
		     $("#gallery_manager").bind("dialogclose",function(){
	       $(this).dialog('destroy');    
		});
			return false;
		},
	uploadAll:function(e){
	           ajaxAction.updatePageStyle();
	           ajaxAction.sendContainerInfos();
	           window.location.replace(location.href);
	           return false;
	   	}
	})
})
