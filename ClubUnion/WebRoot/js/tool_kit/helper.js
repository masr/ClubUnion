$(function(){
	
			TKH.percentToSolid=function(x)
			    		{
			                return (x/20)+"px";	
			    		}

			    		TKH.percentToFontSize=function(i)
			        {
			        		return i/5+10+"px";
			        }
			    		TKH.solidToPercent=function(s){
			        	s=s.substring(0,s.indexOf("p"));
			        	s=parseInt(s);
			        	return s*20;
			        }
			        TKH.fontSizeToPercent=function(size)
			        {
			        	size=size.substring(0,size.indexOf("p"));
			        	size=parseInt(size);
			        	return (size-10)*5;
			        }
			        TKH.isAlpha=function(cCheck){ return ((('a' <= cCheck) && (cCheck <= 'z'))||(('A'<=cCheck) && (cCheck <= 'Z')))}
			
			     
			  
})  

  
  
 
  
  
  