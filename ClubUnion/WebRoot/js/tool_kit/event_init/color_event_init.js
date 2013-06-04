$(function(){
	$.extend(eventInit,{
		
		backColorPalette:function(e){
		palette.colorSelect(e,function(s){
	    	$("#left_back,#right_back").css("background-image","url('')");
	        $("#bg").css("background-color",s);
	        pageStyle.setContent({name:"backColor",content:s.substring(1,s.length)});
	        pageStyle.setContent({name:"leftBackURL",content:" "});
	        pageStyle.setContent({name:"rightBackURL",content:" "});
	        });
	        return false;
	        },
	mainColorPalette:function(e){
	            palette.colorSelect(e,function(s){
	                $("#main").css("background-color",s);
	                $("#main").css("background-image","url('')");
	                pageStyle.setContent({name:"mainBackColor",content:s.substring(1,s.length)});
	                pageStyle.setContent({name:"mainBackURL",content:" "});
	                });
	                return false;
	                },
	topColorPalette:function(e){
	                	palette.colorSelect(e,function(s){
	                        $("#top_back").css("background-color",s);
	                        $("#top_back").css("background-image","url('')");
	                        pageStyle.setContent({name:"topBackColor",content:s.substring(1,s.length)});
	                        pageStyle.setContent({name:"topBackURL",content:" "});
	                        });
	                        return false;
	                },
	normalColorPalette:function(e){
	                	palette.colorSelect(e,function(s){
	                              $(".design .design_content *").not("a").css("color",s);
	                              pageStyle.setContent({name:"fontColor",content:s.substring(1,s.length)})
	                              $.each(widgets,function(){
	                              this.setContent({name:"fontColor",content:" "})
	                              })
	                              });
	                              return false;
	                              },
	linkColorPalette:function(e){
	                            	  palette.colorSelect(e,function(s){
	                                      
	                                      $(".design .design_content a").css("color",s);
	                                      pageStyle.setContent({name:"linkColor",content:s.substring(1,s.length)})
	                                       $.each(widgets,function(){
	                                      this.setContent({name:"linkColor",content:" "})
	                                      })
	                                      });
	                                      return false;
	                                      },
	borderColorPalette:function(e){
	                                    	  palette.colorSelect(e,function(s){

	                                  $(".design").css("border-color",s);
	                                  pageStyle.setContent({name:"borderColor",content:s.substring(1,s.length)})
	                                    $.each(widgets,function(){
	                                      this.setContent({name:"borderColor",content:" "})
	                                      })
	                                  });
	                                return false;
	                                },
cBorderColorPalette:function(e){
	                                	palette.colorSelect(e,function(s){
	                                        var no=topId.substring(10,topId.length);
	                                       $("#"+topId).css("border-color",s);
	                                       widgets[no].setContent({name:"borderColor",content:s.substring(1,s.length)})
	                                       });
	                                       return false;
	                                       },
	cNormalColorPalette:function(e){
	                                    	   palette.colorSelect(e,function(s){
	                                               
	                      var no=topId.substring(10,topId.length);
	                    $("#"+topId+" .design_content *").not("a").css("color",s);
	                   widgets[no].setContent({name:"fontColor",content:s.substring(1,s.length)})
	                           });
	                        return false;
	                           },
	cLinkColorPalette:function(e){
	                        	   palette.colorSelect(e,function(s){
	                                   
	                                 var no=topId.substring(10,topId.length);
	                                 $("#"+topId+" .design_content a").css("color",s);
	                                 widgets[no].setContent({name:"linkColor",content:s.substring(1,s.length)})
	                                 });
	                                 return false;
	                                 },
	titleColorPalette:function(e){
	                                	 palette.colorSelect(e,function(s){
	                                            $(".design .design_title").css("color",s);
	                                            pageStyle.setContent({name:"titleColor",content:s.substring(1,s.length)})
	                                            $.each(widgets,function(){
	                                              this.setContent({name:"titleColor",content:" "})
	                                              })

	                                           });
	                                           return false;  	 
	                            },
	cTitleColorPalette:function(e){
	                            	palette.colorSelect(e,function(s){
	                                       
	                                       var no=topId.substring(10,topId.length);
	                                       $("#"+topId+" .design_title").css("color",s);
	                                       widgets[no].setContent({name:"titleColor",content:s.substring(1,s.length)})
	                                       });
	                                       return false;
	                            }
	})
})
