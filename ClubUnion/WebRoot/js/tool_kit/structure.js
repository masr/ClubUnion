			         

 var Class = {
        create: function() {
         return function() {
           this.initialize.apply(this, arguments);
    }
  }
}

var Extend = function(destination, source) {
    for (var property in source) {
        destination[property] = source[property];
    }
    return destination;   
}

    var Style=Class.create();
    var Widget = Class.create();
    var PageStyle=Class.create();
   
   
    
    Style.prototype={
    initialize:function(n){
    this.name=n;
    },
    setContent:function(c)
    {
      this.content=c;
    },
    getContent:function()
    {
     return this.content;
    }
    }
    
   
    
    
      Widget.prototype={
    initialize:function(i)
      {
    	this.containerId=i;
        this.edited=false;
        this.created=false;
        this.deleted=false;
        this.editting=false;
        this.contentType=null;
        this.styles=new Array();
        this.styles["backColor"]=new Style("backColor");
        this.styles["fontSize"]=new Style("fontSize");
        this.styles["fontColor"]=new Style("fontColor");
        this.styles["linkColor"]=new Style("linkColor");
        this.styles["fontFamily"]=new Style("fontFamily");
        this.styles["borderColor"]=new Style("borderColor");
        this.styles["borderSolid"]=new Style("borderSolid");
        this.styles["w"]=new Style("w");
        this.styles["h"]=new Style("h");
        this.styles["x"]=new Style("x");
        this.styles["y"]=new Style("y");
        this.styles["text"]=new Style("text");
        this.styles["imgURL"]=new Style("imgURL")
        this.styles["title"]=new Style("title");
        this.styles["titleBackColor"]=new Style("titleBackColor");
        this.styles["titleFontFamily"]=new Style("titleFontFamily");
        this.styles["titleColor"]=new Style("titleColor");
        this.styles["themeName"]=new Style("themeName");
        this.styles["galleryURLs"]=new Style("galleryURLs");
      
       
        

                
      },
    setContent:function(option)
      {
    	  

      if (option.name && option.content)
      {
    	  this.doEditAction();
    	  this.styles[option.name].setContent(option.content);
         }
      },
      getContent:function(option)
      {
    	return this.styles[option].getContent();  
      },
       doEditAction:function()
      {
      this.edited=true;
      },
      doCreateAction:function()
      {
      this.created=true;
      },
      deleteMe:function()
      {
    	  this.edited=true;
    	  this.deleted=true;
      }
      }
   
    
    PageStyle.prototype= {
     initialize:function(i)
      {
    	this.clubId=i;
        this.edited=false;
           this.styles=new Array();
           this.styles["backColor"]=new Style("backColor");
           this.styles["fontSize"]=new Style("fontSize");
           this.styles["fontColor"]=new Style("fontColor");
           this.styles["linkColor"]=new Style("linkColor");
           this.styles["fontFamily"]=new Style("fontFamily");
           this.styles["borderColor"]=new Style("borderColor");
           this.styles["borderSolid"]=new Style("borderSolid");
           this.styles["mainBackColor"]=new Style("mainBackColor");
           this.styles["titleBackColor"]=new Style("titleBackColor");
           this.styles["titleFontFamily"]=new Style("titleFontFamily");
           this.styles["titleColor"]=new Style("titleColor");
           this.styles["leftBackURL"]=new Style("backURL");
           this.styles["mainBackURL"]=new Style("mainBackURL");
           this.styles["leftBackURL"]=new Style("leftBackURL");
           this.styles["rightBackURL"]=new Style("rightBackURL");
           this.styles["topBackURL"]=new Style("topBackURL");
           this.styles["logoURL"]=new Style("logoURL");
           this.styles["themeName"]=new Style("themeName");
           this.styles["topBackColor"]=new Style("topBackColor");
           this.styles["topBackRepeat"]=new Style("topBackRepeat");
           this.styles["mainBackRepeat"]=new Style("mainBackRepeat");
           this.styles["leftBackRepeat"]=new Style("leftBackRepeat");
           this.styles["rightBackRepeat"]=new Style("rightBackRepeat");

      },
        setContent:function(option)
      {
      if (option.name && option.content)
      {
    	  this.doEditAction();
         this.styles[option.name].setContent(option.content);
         }
      },
      getContent:function(option)
      {
    	return this.styles[option].getContent();  
      },
       doEditAction:function()
      {
      this.edited=true;
      }
     
     }
    

     
         