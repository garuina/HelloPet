$(function(){
	
	$('.reserve_div > table tr > td .btnConfirm').click(function(){
        
	    var revNo = $(this).closest("tr").find(".revNo").text();
	    console.log(revNo);
	     
	    var urlParams = new URL(location.href).searchParams;
	    var medNo = urlParams.get('medNo');
    		console.log(medNo);
    	
    	var newUrl = "/HelloPet/admin/confirm/list?medNo="+medNo+"&revNo="+revNo;
    	window.history.pushState("", "", newUrl); // URL 변경
    	
    	$('#popConfirm').addClass('on');
    	
    	$.ajax({
			url : "/HelloPet/admin/confirm/list",
	    	method : 'post',
	    	data : {'revNo': revNo},
	    	success: function(data){
				if(data.result.petNum == 1){
					$('.popupTable tr:eq(1) > td:eq(0)').text(data.result.revNo);
					$('.popupTable tr:eq(1) > td:eq(1)').text(data.result.oname);
					$('.popupTable tr:eq(1) > td:eq(2)').text(data.result.oph);
					$('.popupTable tr:eq(1) > td:eq(3)').text(data.result.petName1+'/'+data.result.petType1+'/'+data.result.petAge1);
					$('.popupTable tr:eq(1) > td:eq(4)').text(data.result.department);
					$('.popupTable tr:eq(1) > td:eq(5)').text(data.result.memo);
					$('.popupTable tr:eq(1) > td:eq(6)').text(data.result.revDate);
					$('.popupTable tr:eq(1) > td:eq(7)').text(data.result.revTime.substring(0,5));
					
				}else{
					$('.popupTable tr:eq(1) > td:eq(0)').text(data.result.revNo);
					$('.popupTable tr:eq(1) > td:eq(1)').text(data.result.oname);
					$('.popupTable tr:eq(1) > td:eq(2)').text(data.result.oph);
					$('.popupTable tr:eq(1) > td:eq(3)').text(data.result.petName1+'/'+data.result.petType1+'/'+data.result.petAge1+', '+data.result.petName2+'/'+data.result.petType2+'/'+data.result.petAge2);
					$('.popupTable tr:eq(1) > td:eq(4)').text(data.result.department);
					$('.popupTable tr:eq(1) > td:eq(5)').text(data.result.memo);
					$('.popupTable tr:eq(1) > td:eq(6)').text(data.result.revDate);
					$('.popupTable tr:eq(1) > td:eq(7)').text(data.result.revTime.substring(0,5));
				}
				
			}
		});
    });
    
   
    
    $('.reserve_div > table tr > td .btnReject').click(function(){
        
	    var revNo = $(this).closest("tr").find(".revNo").text();
	    console.log(revNo);
	     
	    var urlParams = new URL(location.href).searchParams;
	    var medNo = urlParams.get('medNo');
    		console.log(medNo);
    	
    	var newUrl = "/HelloPet/admin/confirm/view?medNo="+medNo+"&revNo="+revNo;
    	window.history.pushState("", "", newUrl); // URL 변경
    	
    	$('#popReject').addClass('on');
    	
    	$.ajax({
			url : "/HelloPet/admin/confirm/view",
	    	method : 'get',
	    	data : {'revNo': revNo},
	    	success: function(data){
				if(data.result.petNum == 1){
					$('.popupTable2 tr:eq(1) > td:eq(0)').text(data.result.revNo);
					$('.popupTable2 tr:eq(1) > td:eq(1)').text(data.result.oname);
					$('.popupTable2 tr:eq(1) > td:eq(2)').text(data.result.oph);
					$('.popupTable2 tr:eq(1) > td:eq(3)').text(data.result.petName1+'/'+data.result.petType1+'/'+data.result.petAge1);
					$('.popupTable2 tr:eq(1) > td:eq(4)').text(data.result.department);
					$('.popupTable2 tr:eq(1) > td:eq(5)').text(data.result.memo);
					$('.popupTable2 tr:eq(1) > td:eq(6)').text(data.result.revDate);
					$('.popupTable2 tr:eq(1) > td:eq(7)').text(data.result.revTime.substring(0,5));
					
				}else{
					$('.popupTable2 tr:eq(1) > td:eq(0)').text(data.result.revNo);
					$('.popupTable2 tr:eq(1) > td:eq(1)').text(data.result.oname);
					$('.popupTable2 tr:eq(1) > td:eq(2)').text(data.result.oph);
					$('.popupTable2 tr:eq(1) > td:eq(3)').text(data.result.petName1+'/'+data.result.petType1+'/'+data.result.petAge1+', '+data.result.petName2+'/'+data.result.petType2+'/'+data.result.petAge2);
					$('.popupTable2 tr:eq(1) > td:eq(4)').text(data.result.department);
					$('.popupTable2 tr:eq(1) > td:eq(5)').text(data.result.memo);
					$('.popupTable2 tr:eq(1) > td:eq(6)').text(data.result.revDate);
					$('.popupTable2 tr:eq(1) > td:eq(7)').text(data.result.revTime.substring(0,5));
				}
				
			}
		});
    	
    });

    $('.btnClose').click(function(){
		var urlParams = new URL(location.href).searchParams;
	    var medNo = urlParams.get('medNo');
		var backUrl = "/HelloPet/admin/confirm/list?medNo="+medNo;
		
        $(this).closest('.popup').removeClass('on'); 
        
        window.location.href = backUrl;               
    });
    
    $('.msg-div > .msg-list> li').click(function(e){
        e.preventDefault();
        $('#popMsg').addClass('on');
    });

});