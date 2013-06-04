
	  $(function() {
	  //文档加载完执行
	  //进行初始化
	  
			   ToolKitInit.initDesignClass=function(){
    $(".design").draggable();
    $(".design").resizable();
	
	$('.design').bind('dragstop', eventInit.containerDragStop);
	$('.design').bind('resize', eventInit.containerResize);
	$('.design').bind('resizestop', eventInit.containerResizeStop);
    $(".design").bind('click',eventInit.containerClick);
    }
			   ToolKitInit.initSlider=function(){
     $("#border_solid_slider .slider").slider({ step: 20 });
     $("#font_size_slider .slider").slider({ step:5 ,value:TKH.fontSizeToPercent($("#main").css("font-size"))});
     $("#c_font_size_slider .slider").slider({ step:5 });
     $("#c_border_solid_slider .slider").slider({ step:20 });
     
     $("#border_solid_slider .slider").bind("slide", eventInit.borderSolidSlide);
     $("#font_size_slider .slider").bind("slide", eventInit.fontSizeSlide);
     $("#c_border_solid_slider .slider").bind("slide",eventInit.cBorderSolidSlide);
     $("#c_font_size_slider .slider").bind("slide",eventInit.cFontSizeSlide );
    }
    ToolKitInit.initPageColorPalette=function(){
        $("#back_color_palette a").bind("click",eventInit.backColorPalette);
         $("#top_color_palette a").bind("click",eventInit.topColorPalette);
        $("#main_color_palette a").bind("click",eventInit.mainColorPalette);
        $("#border_color_palette a").bind("click",eventInit.borderColorPalette);
       //将design中的所有元素（除链接）的color属性设为某个值
        $("#normal_color_palette a").bind("click",eventInit.normalColorPalette);
        $("#link_color_palette a").bind("click",eventInit.linkColorPalette);
        $("#title_color_palette a").bind("click",eventInit.titleColorPalette);
        
    }
    ToolKitInit.initContainerColorPalette=function(){

        $("#c_border_color_palette a").bind("click",eventInit.cBorderColorPalette);
        $("#c_normal_color_palette a").bind("click",eventInit.cNormalColorPalette);
        $("#c_link_color_palette a").bind("click",eventInit.cLinkColorPalette);
        $("#c_title_color_palette").bind("click",eventInit.cTitleColorPalette);
    }
    ToolKitInit.initContainerAction=function(){
     $("#c_delete").bind("click", eventInit.cDelete);
     $("#c_edit_title").bind("click",eventInit.cEditTitle);
     $("#c_edit_content").bind("click",eventInit.cEditContent);
    }
    ToolKitInit.initOtherAction=function()
    {
    $("#upload_new_logo a").bind("click",eventInit.uploadLogo);
    $("#upload_new_left_back a").bind("click",eventInit.uploadLeftBack);
    $("#upload_new_right_back a").bind("click",eventInit.uploadRightBack);
    $("#upload_new_img a").bind("click",eventInit.uploadImgContent);
	$("#upload_new_main_back a").bind("click",eventInit.uploadMainBack);
	$("#upload_new_top_back a").bind("click",eventInit.uploadTopBack);
	$("#upload_new_text a").bind("click",eventInit.uploadText);
	$("#upload_new_gallery a").bind("click",eventInit.uploadGallery);
	 $(".upload_all a").bind("click",eventInit.uploadAll);
    }
    ToolKitInit.initThemeSwitcher=function()
    {
     $("#theme_switcher").themeswitcher({onSelect:eventInit.themeSelect});
      $("#c_theme_switcher").themeswitcher({onSelect:eventInit.cThemeSelect});
    }
    ToolKitInit.initOther=function(){
    	$(".no_effect_link").bind("click",function(){return false});
    }
    ToolKitInit.initBackRepeat=function(){
    	
    	$("#top_back_repeat").bind("click",eventInit.topBackRepeat);
    	$("#left_back_repeat").bind("click",eventInit.leftBackRepeat);
    	$("#main_back_repeat").bind("click",eventInit.mainBackRepeat);
    	$("#right_back_repeat").bind("click",eventInit.rightBackRepeat);
    }
    ToolKitInit.init=function()
    {
   	 $('#tabs').tabs();

       this.initDesignClass();
       this.initOtherAction();
       this.initPageColorPalette();
       this.initContainerColorPalette();
       this.initContainerAction();
       this.initSlider();
       this.initThemeSwitcher();
       this.initBackRepeat();
       this.initOther();
    }
    
    
    
    
	  
	 

       
       
	})