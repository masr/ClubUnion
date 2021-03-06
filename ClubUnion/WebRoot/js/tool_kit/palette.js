$(function(){
	palette.colorSelect=function(e,fn){
		if(document.getElementById("colorBoard")){
		   return;
		}
		//关于出现位置
		e=e||event;
		var scrollpos = getScrollPos();
		var l = scrollpos.l + e.clientX;
		    var t = scrollpos.t + e.clientY + 10;
		    if (l > getBody().clientWidth-253){
		        l = getBody().clientWidth-253;
		   }
		//创建DOM
		var ColorHex=new Array('00','33','66','99','CC','FF');
		var SpColorHex=new Array('FF0000','00FF00','0000FF','FFFF00','00FFFF','FF00FF');
		var colorBank = document.createElement("div");
		colorBank.setAttribute("id","colorBank");
		var colorViews = document.createElement("div");
		colorViews.setAttribute("id","colorViews");
		var colorInput = document.createElement("input");
		colorInput.setAttribute("id","colorInput");
		colorInput.setAttribute("type","text");
		colorInput.setAttribute("disabled","disabled");
		var colorClose = document.createElement("input");
		colorClose.setAttribute("id","colorClose");
		colorClose.setAttribute("value","取消");
		colorClose.setAttribute("type","button");
		colorClose.onclick=function(){document.body.removeChild(colorBoard)};
		var colorBoard =document.createElement("div");
		colorBoard.id="colorBoard";
		colorBoard.style.left = l+"px";
		colorBoard.style.top = t+ "px";
		colorBoard.appendChild(colorViews);
		colorBoard.appendChild(colorInput);
		colorBoard.appendChild(colorClose);
		colorBoard.appendChild(colorBank);
		document.body.appendChild(colorBoard);
		//循环出调色板
		for(b=0;b<6;b++){
		   for(a=0;a<3;a++){
		    for(i=0;i<6;i++){
		     colorItem = document.createElement("div");
		     colorItem.style.backgroundColor="#"+ColorHex[a]+ColorHex[i]+ColorHex[b];
		     colorBank.appendChild(colorItem);
		    }
		   }
		}
		for(b=0;b<6;b++){
		   for(a=3;a<6;a++){
		    for(i=0;i<6;i++){
		     colorItem = document.createElement("div");
		     colorItem.style.backgroundColor="#"+ColorHex[a]+ColorHex[i]+ColorHex[b];
		     colorBank.appendChild(colorItem);
		    }
		   }
		}
		for(i=0;i<6;i++){
		   colorItem = document.createElement("div");
		   colorItem.style.backgroundColor="#"+ColorHex[0]+ColorHex[0]+ColorHex[0];
		   colorBank.appendChild(colorItem);
		}
		for(i=0;i<6;i++){
		   colorItem = document.createElement("div");
		   colorItem.style.backgroundColor="#"+ColorHex[i]+ColorHex[i]+ColorHex[i];
		   colorBank.appendChild(colorItem);
		}
		for(i=0;i<6;i++){
		   colorItem = document.createElement("div");
		   colorItem.style.backgroundColor="#"+SpColorHex[i];
		   colorBank.appendChild(colorItem);
		}
		var colorItems = colorBank.getElementsByTagName("div");
		for(i=0; i<colorItems.length;i++){
		   colorItems[i].onmouseover = function(){
		    a = this.style.backgroundColor;
		    if(a.length>7){
		     a = formatRgb(a);//
		    }
		    colorViews.style.background = a.toUpperCase();
		    colorInput.value = a.toUpperCase();
		   }
		   colorItems[i].onclick = function(){
		    a = this.style.backgroundColor;
		    if(a.length>7){
		     a = formatRgb(a);//
		    }
			
		   
		    document.body.removeChild(colorBoard);
			fn(a.toUpperCase());
		   }
		   
		}

		}
		//格式化函数
		function formatRgb(rgb){
		rgb = rgb.replace("rgb","");rgb = rgb.replace("(","");rgb = rgb.replace(")","");
		format = rgb.split(",");
		a = eval(format[0]).toString(16);
		b = eval(format[1]).toString(16);
		c = eval(format[2]).toString(16);
		rgb = "#"+checkFF(a)+checkFF(b)+checkFF(c);
		function checkFF(str){
		   if(str.length == 1){
		    str = str+""+str;
		    return str;
		   }else{
		    return str;
		   }
		}
		return rgb;
		}
		//getBody()
		function getBody(){
		    var Body;
		    if (typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat') {
		       Body = document.documentElement;
		    }
		    else if (typeof document.body != 'undefined') {
		       Body = document.body;
		    }
		    return Body;
		}
		//scrollPos
		function getScrollPos(){
		var t,l;
		if (typeof window.pageYOffset != 'undefined'){
		    t = window.pageYOffset;
		    l = window.pageXOffset;
		}
		else{
		t = getBody().scrollTop;
		l = getBody().scrollLeft;
		}
		return {t:t,l:l};
		}
})
