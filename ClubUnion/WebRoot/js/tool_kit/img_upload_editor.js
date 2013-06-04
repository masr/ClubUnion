


$(function(){
	imgUploader.chk_imgpath=function(){
		
			if ($('#radio_url').get(0).checked == true) {
				if ($("#imgpath").attr("value") == "http://"
						|| $("#imgpath").attr("value") == "") {

					window.close();
					return;
				}

				// 迅速读取，并迅速提交请求
				if (UploadResponse.img_form_action.indexOf("LeftBackImgUpload") > 0) {
					UploadResponse.LoadLeftBackIMG($("#imgpath").attr("value"));
					return;
				}

				if (UploadResponse.img_form_action.indexOf("TopBackImgUpload") > 0) {
					UploadResponse.LoadTopBackIMG($("#imgpath").attr("value"));
					return;
				}

				if (UploadResponse.img_form_action.indexOf("RightBackImgUpload") > 0) {
					UploadResponse.LoadRightBackIMG($("#imgpath").attr("value"));
					return;
				}

				if (UploadResponse.img_form_action.indexOf("Logo") > 0) {
					UploadResponse.LoadLogoIMG($("#imgpath").attr("value"));
					return;
				}

				if (UploadResponse.img_form_action.indexOf("ImgContent") > 0) {

					UploadResponse.createNewImgContent($("#imgpath").attr("value"));
					return;
				}

				if (UploadResponse.img_form_action.indexOf("Gallery") > 0) {

					UploadResponse.insertNewGalleryImg($("#imgpath").attr("value"));
					return;
				}

				if (UploadResponse.img_form_action.indexOf("MainBack") > 0) {

					UploadResponse.LoadMainBackIMG($("#imgpath").attr("value"));
					return;
				}
				
				

			} else {
				if ($("#imgfile").attr("value") == "") {
					window.close();
					return;
				}
				// LoadIMG($("imgfile").value);

				var pos = ($("#imgfile").attr("value")).lastIndexOf(".");
				var lastname = ($("#imgfile").attr("value")).substring(pos, $(
						'#imgfile').attr("value").length);
				if (lastname.toLowerCase() != ".jpg"
						&& lastname.toLowerCase() != ".gif"
						&& lastname.toLowerCase() != ".bmp"
						&& lastname.toLowerCase() != ".png") {
					alert('文件类型错误！请选择图片类型的文件！');
					return false;
				}

				$('#divProcessing').show();
				$("#form_1").attr(
						"action",
						UploadResponse.img_form_action);
				$("#form_1").submit();

			}
		}
	imgUploader.init=function(){
		$(".img_uploader button.submit").click(imgUploader.chk_imgpath)
	}
	
})
$( function() {
	UploadResponse.createNewImgContent = function(url) {
		$("#img_uploader").dialog('destroy');
		$('#divProcessing').hide();

		var no = widgets.length;
		var widget = widgets[no] = new Widget(-1);

		var node = $("<div />").attr("id", "container_" + no).html(
				"<div class='img_block'><img src='" + url + "' /></div>")

		StyleFixer.initCreatedWidget(node);

		StyleFixer.adjustImgStyle();

		widget.doCreateAction();
		widget.contentType = TKH.IMG_TYPE;
		widget.setContent( {
			name : "imgURL",
			content : url
		});
		$("img", node).add(node).css("width", "200px");
		widget.setContent( {
			name : "w",
			content : "200px"
		});
	}

	UploadResponse.LoadLogoIMG = function(imgpath) {

		$("#logo_block img").attr("src", imgpath).css("width", "200px");
		$("#logo_block").parent(".design").css("width", "200px");
		var cId = $("#logo_block").parent(".design").get(0).id;
		var no = cId.substring(10, cId.length);
		widgets[no].setContent( {
			name : "w",
			content : "200px"
		});
		pageStyle.setContent( {
			name : "logoURL",
			content : imgpath
		});
		$("#img_uploader").dialog("destroy");
		$('#divProcessing').hide();
	}

	UploadResponse.LoadRightBackIMG = function(imgpath) {
		$("#right_back").css("background-image", "url('" + imgpath + "')");
		$("#right_back").css("background-color", "#ffffff");
		pageStyle.setContent( {
			name : "rightBackURL",
			content : imgpath
		});
		pageStyle.setContent( {
			name : "backColor",
			content : "#ffffff"
		});
		$("#img_uploader").dialog("destroy");
		$('#divProcessing').hide();

	}

	UploadResponse.LoadLeftBackIMG = function(imgpath) {
		$("#left_back").css("background-image", "url('" + imgpath + "')");
		$("#left_back").css("background-color", "#ffffff");
		pageStyle.setContent( {
			name : "leftBackURL",
			content : imgpath
		});
		pageStyle.setContent( {
			name : "backColor",
			content : "#ffffff"
		});

		$("#img_uploader").dialog("destroy");
		$('#divProcessing').hide();

	}

	UploadResponse.LoadTopBackIMG = function(imgpath) {
		$("#top_back").css("background-image", "url('" + imgpath + "')");
		$("#top_back").css("background-color", "#ffffff");

		pageStyle.setContent( {
			name : "topBackURL",
			content : imgpath
		});
		pageStyle.setContent( {
			name : "topBackColor",
			content : "#ffffff"
		});

		$("#img_uploader").dialog("destroy");
		$('#divProcessing').hide();
	}
	UploadResponse.LoadMainBackIMG = function(imgpath) {
		$("#main").css("background-image", "url('" + imgpath + "')");
		$("#main").css("background-color", "#ffffff");

		pageStyle.setContent( {
			name : "mainBackURL",
			content : imgpath
		});
		pageStyle.setContent( {
			name : "mainBackColor",
			content : "#ffffff"
		});

		$("#img_uploader").dialog("destroy");
		$('#divProcessing').hide();
	}



	

});



