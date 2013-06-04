  $(function(){
	  ajaxAction.sendContainerInfos=function(){
		  var paramString="";
		  for(i=0;i<widgets.length;i++)
	      {
	         if ((widgets[i].edited==false)&&(widgets[i].created==false))
	             continue;
		
	        
	         
	         
	          paramString=paramString+"clubId="+clubId+"&contentType="+widgets[i].contentType;
	         if (widgets[i].created==false)
	           paramString=paramString+"&containerId="+widgets[i].containerId+"&edited=true";
	         else
	             paramString=paramString+"&created=true"
	         
	        if (widgets[i].deleted)
	        paramString=paramString+"&deleted=true";

	         for(key in widgets[i].styles)
	           {
	        	 content=widgets[i].styles[key].getContent();
	           if (content)
	           {
	             paramString=paramString+"&"+key+"="+escape(escape(content));
	           }
	           }
	         paramString=paramString+"&e&"
	        
	      }
		  $.get("/ClubUnion/servlet/ContainerStyleUpload?"+paramString)
	  }
	 ajaxAction.updatePageStyle=function(){
		 if (pageStyle.edited==false)
	            return;
	           var paramString="";
	           paramString=paramString+"clubId="+pageStyle.clubId;
	           for(key in pageStyle.styles)
	           {
	        	   content=pageStyle.styles[key].getContent();
	           if (content)
	           {
	             paramString=paramString+"&"+key+"="+escape(escape(content));
	           }
	           }
	         

	         $.get('/ClubUnion/servlet/PageStyleUpload?'+paramString);
	    }
	
  })
