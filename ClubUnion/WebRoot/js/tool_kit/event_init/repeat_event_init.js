$(function(){
	$.extend(eventInit,{
		topBackRepeat:function(e){
			
		if($("#top_back").css("background-repeat")=="repeat")
		{
			$("#top_back").css("background-repeat","no-repeat");
			pageStyle.setContent({name:"topBackRepeat",content:"no-repeat"});
		}
			else
			{
				$("#top_back").css("background-repeat","repeat");
				pageStyle.setContent({name:"topBackRepeat",content:"repeat"});

			}
		return false;

	},
	leftBackRepeat:function(e){
		if($("#left_back").css("background-repeat")=="repeat")
	{
	$("#left_back").css("background-repeat","no-repeat");
	pageStyle.setContent({name:"leftBackRepeat",content:"no-repeat"});

	}
	else
		{
		$("#left_back").css("background-repeat","repeat");
		pageStyle.setContent({name:"leftBackRepeat",content:"repeat"});

		}
	return false;
	},
	mainBackRepeat:function(e){
		if($("#main").css("background-repeat")=="repeat")
	{
	$("#main").css("background-repeat","no-repeat");
	pageStyle.setContent({name:"mainBackRepeat",content:"no-repeat"});

	}
	else
		{
		$("#main").css("background-repeat","repeat");
		pageStyle.setContent({name:"mainBackRepeat",content:"repeat"});

		}
	return false;
	},
	rightBackRepeat:function(e){
		
		if($("#right_back").css("background-repeat")=="repeat")
			{
			$("#right_back").css("background-repeat","no-repeat");
			pageStyle.setContent({name:"rightBackRepeat",content:"no-repeat"});

			}
			else
				{
				$("#right_back").css("background-repeat","repeat");
				pageStyle.setContent({name:"rightBackRepeat",content:"repeat"});

				}
		return false;

	}
	})
})
