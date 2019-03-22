	$(function(){
		$(".toggle").click(function(){	
			var _this= $(this).attr("href");					
			if($(_this).css("display")=="none"){
				$(_this).slideDown();
				
			}else{
				$(_this).slideUp();
				
			}			
			return false;			
		});		
	});