
          
$( function() {

	UploadResponse.insertNewGalleryImg = function(url) {

		$(
				"<li class='ui-widget-content ui-corner-tr'><img src='"
						+ url
						+ "'   width='96' height='72'  /><a href='"
						+ url
						+ "' title='放大' class='ui-icon ui-icon-zoomin'>放大</a><a href='link/to/trash/script/when/we/have/js/off' title='删除' class='ui-icon ui-icon-trash'>删除</a></li>")
				.appendTo("#gallery");
		$("#img_uploader").dialog("destroy");
		$('#divProcessing').hide();
		GalleryManager.initGalleryImg();
	}
	
	GalleryManager.insertNewGalleryImg = UploadResponse.insertNewGalleryImg

	
	  GalleryManager.recycle_icon = '<a href="link/to/recycle/script/when/we/have/js/off" title="Recycle this image" class="ui-icon ui-icon-refresh">Recycle image</a>',
      GalleryManager.trash_icon = '<a href="link/to/trash/script/when/we/have/js/off" title="Delete this image" class="ui-icon ui-icon-trash">Delete image</a>',
      GalleryManager.ulHTML='<div class="dynamic_gallery" "> <div class="design_title"></div><div class="design_content svw"><ul></ul></div></div>',


	
	GalleryManager.editGallery=function(){
		$("#gallery_manager").dialog("destroy");
          var cid=GalleryManager.cid;

             no=cid.substring(10,cid.length );
		var widget=widgets[no];
		
		paramURL = "";
		urls = $("#gallery img").map( function() {
			return $(this).attr('src')
		});
		for (i = 0; i < urls.length; i++) {
			paramURL = paramURL + urls[i] + "@e@";

		}

		var $node = $("#"+cid)
		$node.html(GalleryManager.ulHTML);

		for( i=0;i<urls.length;i++)
		{
			$('ul',$node).append("<li><img src='"+urls[i]+"'/></a</li>");
		}
		
        
		StyleFixer.adjustImgStyle();

		
		widget.setContent( {
			name : "galleryURLs",
			content : paramURL
		});
		widget.contentType = TKH.GALLERY_TYPE;


		$('.svw',$node).slideView();
		Gallery.rollAllGallery();
	}

	GalleryManager.createNewGallery = function() {
		$("#gallery_manager").dialog("destroy");
		var no = widgets.length;
		var widget = widgets[no] = new Widget(-1);

		paramURL = "";
		urls = $("#gallery img").map( function() {
			return $(this).attr('src')
		});
		for (i = 0; i < urls.length; i++) {
			paramURL = paramURL + urls[i] + "@e@";

		}

		var $node = $("<div />").attr("id", "container_" + no);
		$node.html(GalleryManager.ulHTML);
		
		for( i=0;i<urls.length;i++)
		{
			$('ul',$node).append("<li><img src='"+urls[i]+"'/></a</li>");
		}
		
		StyleFixer.initContainerTheme($node);
		StyleFixer.initCreatedWidget($node);
		StyleFixer.adjustImgStyle();

		widget.doCreateAction();
		widget.setContent( {
			name : "galleryURLs",
			content : paramURL
		});
		widget.contentType = TKH.GALLERY_TYPE;
		$("img", $node).add($node).css("width", "200px");
		widget.setContent( {
			name : "w",
			content : "200px"
		});

		widget.setContent( {
			name : "h",
			content : "200px"
		});
		
		$('.svw',$node).slideView();
		Gallery.rollAllGallery();

	}
	GalleryManager.initGalleryImg = function() {
		$('#gallery li').draggable( {
			cancel : 'a.ui-icon',// clicking an icon won't initiate dragging
			revert : 'invalid', // when not dropped, the item will revert back
								// to its initial position
			containment : "#gallery_manager", // stick to demo-frame if
												// present
			helper : 'clone',
			cursor : 'move'
		});

		$('ul.gallery > li').click( function(ev) {
			var $item = $(this);
			var $target = $(ev.target);

			if ($target.is('a.ui-icon-trash')) {
				GalleryManager.deleteImage($item);
			} else if ($target.is('a.ui-icon-zoomin')) {
				GalleryManager.viewLargerImage($target);
			} else if ($target.is('a.ui-icon-refresh')) {
				GalleryManager.recycleImage($item);
			}

			return false;
		});
	}
	GalleryManager.initGalleryManager = function() {
		$('#trash').droppable( {
			accept : '#gallery > li',
			activeClass : 'ui-state-highlight',
			drop : function(ev, ui) {
			GalleryManager.deleteImage(ui.draggable);
			}
		});

		$('#gallery').droppable( {
			accept : '#trash li',
			activeClass : 'custom-state-active',
			drop : function(ev, ui) {
			GalleryManager.recycleImage(ui.draggable);
			}
		});
		$('#gallery_manager .upload_img').bind("click",
				function() {
					$("#img_uploader").dialog();
					$("#img_uploader").bind("dialogclose", function() {
						$(this).dialog('destroy');
					});
					UploadResponse.img_form_action = "/ClubUnion/servlet/GalleryImgUpload";
					return false;
				});

		$('#gallery_manager .submit').bind("click",
				function() {
		
             if (!editting)
			GalleryManager.createNewGallery();
             else
            	 GalleryManager.editGallery()
					return false;
				});
	}
	GalleryManager.deleteImage = function($item) {
		$item.fadeOut( function() {
			var $list = $('ul', $('#trash')).length ? $('ul', $('#trash')) : $(
					'<ul class="gallery ui-helper-reset"/>').appendTo(
					$('#trash'));

			$item.find('a.ui-icon-trash').remove();
			$item.append(GalleryManager.recycle_icon).appendTo($list).fadeIn( function() {
				$item.animate( {
					width : '48px'
				}).find('img').animate( {
					height : '36px'
				});
			});
		});

	}
	GalleryManager.recycleImage = function($item) {
		$item.fadeOut( function() {
			$item.find('a.ui-icon-refresh').remove();
			$item.css('width', '96px').append(GalleryManager.trash_icon).find('img').css(
					'height', '72px').end().appendTo($('#gallery')).fadeIn();
		});
	}
	GalleryManager.adjustDialogImg = function($modal) {
		$modal.find("img").width($model.width());
	}
	GalleryManager.viewLargerImage = function($link) {

		var src = $link.attr('href');

		var $modal = $("<div id='manager_dialog'><img src='" + src
				+ "'  /></div>");

		if ($modal.length) {
			$modal.dialog( {

				drag : function(event, ui) {
				GalleryManager.adjustDialogImg();
				}
			});
			$modal.bind("dialogclose", function() {
				$(this).dialog('destroy');
			});
			width = $modal.find("img").width() > 500 ? 500 : $modal.find("img")
					.width();
			$modal.find("img").width(width);
			$modal.dialog("option", "width", (width + 20) + "px");

		} else {
			var img = $(
					'<img  width="384" height="288" style="display:none;padding: 8px;" />')
					.attr('src', src).appendTo('body');
			setTimeout( function() {
				img.dialog( {
					title : title,
					width : 400,
					modal : true
				});
			}, 1);
		}
	}
	
	GalleryManager.restoreGallery=function(){
		$node=$("#"+GalleryManager.cid);
		$("img",$node).each(function(){
			GalleryManager.insertNewGalleryImg( $(this).attr("src"));
		});
		
	}
	
	GalleryManager.clearGallery=function(){
		$("#gallery").html('');
		$(".ui-widget-header","#trash").siblings().html('');
	}
	

});
