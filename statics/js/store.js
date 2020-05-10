function getAllCommission(argument) {
	$.ajax({
		type: "GET",
		url: "/api/commission.do",
		crossDomain: true,
		cache: false,
		dataType: 'json',
		timeout: 5000,
		success: function (response) {
        	console.log(response.status);

            if(response.status == 200){
            	var commission_panel = "";
            	
            	$.each(response.response.data, function(index, val) {
            		 commission_panel += add_commission(val);
            	});
            }
            $("#commission_panel").append(commission_panel);
            
            console.log(response);
        },
        error: function () {
            alert("無法連線到伺服器！");
        }
	});
}

function add_commission(data) {
	let inner_html = '';
	inner_html += '<div class="col-sm-2 col-div-style">';
	inner_html += '<div class="contents store" id="' + data.idtbcommission + '">';
	inner_html += '<div class="delegator">' + data.id_delegator + '</div>';
	inner_html += '<div class="head_img">' + '<img src="img/' + data.img + '">' + '</div>';
	inner_html += '<div class="brief_des">' + data.brief_description + '</div>';
	inner_html += '</div>';
	inner_html += '</div>';
	return inner_html;
}


$(document).ready(function() {
	getAllCommission();
	
	$(".stores").on('click', '.contents', function(event) {
		// console.log(event.target)
		//parents(".contents")會向上找到class = contents的DOM元素
		var id = $(event.target).parents(".contents").attr("id")
		console.log(id)
		window.location.href = "Commission.html" + "?id=" + id;
	});
});