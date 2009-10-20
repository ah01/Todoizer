
$(function(){
	
	$("#tasks .task").each(function(){
		
		var task = $(this);
			
		task.find(":checkbox").click(function(){
			var checkbox = $(this);
			var id = checkbox.attr("id").substr(4);			
			
			checkbox.attr("disabled", true);
			
			$.post("/list/mark", {"id": id}, function(data){

				if(data == "1"){
					checkbox.attr('checked', true);
				}else if(data == "0"){
					checkbox.attr('checked', false);
				}
								
				checkbox.removeAttr("disabled");
			});
			
			return false;
		})
		
	});
	
});