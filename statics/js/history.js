
var url_string = window.location.href;
var url = new URL(url_string);
var member_ID2 = url.searchParams.get("id");
console.log("id:" + member_ID2)
$( document ).ready(function() {
	var member_ID = localStorage.getItem("id");


    if(member_ID2 != null){
    	member_ID = member_ID2;
    }
    console.log(member_ID);
	
	var member_status;
    $.ajax({
            type: "GET",
            url: "api/evaluation_delegator.do",
            crossDomain: true,
            cache: false,
            dataType: 'json',
            timeout: 5000,
            data:{
                member_ID : member_ID
            },
            success: function (response) {
                console.log(response);
                updateHTML_delegator(response.response.data);

                
            },
            error: function (response) {
                console.log("fault");
            }
    });
    $.ajax({
            type: "GET",
            url: "api/evaluation_trustee.do",
            crossDomain: true,
            cache: false,
            dataType: 'json',
            timeout: 5000,
            data:{
                member_ID : localStorage.getItem("id")
            },
            success: function (response) {
                console.log(response);
                updateHTML_trustee(response.response.data);

            },
            error: function (response) {
                console.log("fault");
            }
    });
    
});
function updateHTML_delegator(data) {
    var inner_html = '';
    var panel='';
    var obj ={};
    var member_ID = localStorage.getItem("id");
    if(member_ID2 != null){
    	member_ID = member_ID2;
    }
    $.each(data, function (key, value) {
    	if(value.id_delegator_member == member_ID){
    		console.log(666)
    		panel = '<div class="col-sm-2 col-div-style" id='+value.idtbHistory_record+'>';
            panel += '<div class="contents store">';
            panel += '<div class="head_img">';
            panel += '<img class="nor_img" src=statics/img/img2.jpg>';
            panel += '</div>';
            panel += '<div class="delegator">受託人：'+value.name;
            panel += '</div>';
            panel += '<div class="rank">';
            panel += countstar(value.star_level);
            panel += '</div>';
            panel += '<div class="brief_des">'+value.brief_description;
            panel += '</div></div></div>';
            inner_html = inner_html +panel;
    	}
        
    });
    $('#commission_panel_delegator').append(inner_html);
    
}
function updateHTML_trustee(data) {
    var inner_html = '';
    var panel='';
    var member_ID = localStorage.getItem("id");
    if(member_ID2 != null){
    	member_ID = member_ID2;
    }
    $.each(data, function (key, value) {
    	if(value.id_trustee_member == member_ID){
    		panel = '<div class="col-sm-2 col-div-style" id='+value.idtbHistory_record+'>';
            panel += '<div class="contents store">';
            panel += '<div class="head_img">';
            panel += '<img class="nor_img" src=statics/img/img2.jpg>';
            panel += '</div>';
            panel += '<div class="delegator">委託人：'+value.name;
            panel += '</div>';
            panel += '<div class="rank">';
            panel += countstar(value.star_level);
            panel += '</div>';
            panel += '<div class="brief_des">'+value.brief_description;
            panel += '</div></div></div>';
            inner_html = inner_html +panel;
    	}
    });
    $('#commission_panel_trustee').append(inner_html);
}
function countstar(count){
    var star=""
    for(i=0;i<count;i++){
        star+='<i class="fas fa-star"></i>'   
    }
    for(i=0;i<(5-count);i++){
        star+='<i class="far fa-star"></i>'   
    }
    return star;
}