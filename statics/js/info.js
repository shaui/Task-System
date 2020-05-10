$('#commission_panel_delegator').on('click', '.col-sm-2.col-div-style', function()
{
	var id = $(this).attr("id");
    $.ajax({
        type: "GET",
        url: "api/evaluation_detail_delegator.do",
        crossDomain: true,
        cache: false,
        dataType: 'json',
        timeout: 5000,
        data:
        {
        	member_dID : $(this).attr("id")
        },
        success: function (response) {
            console.log(response);
            $(".det_delegator").text(response.response.data[0].name);
            $("#rank").text("困難度：");
            $("#rank").append(countstar(response.response.data[0].star_level))
        	$(".exp").text("經驗:"+response.response.data[0].exp);
       		$(".detail_des").text(response.response.data[0].detail_description);
       		$("#del_eval").html("委託評價:<br>" + response.response.data[0].delegator_eval);
       		$("#trustee_eval").html("受託評價:<br>" + response.response.data[0].trustee_eval);
       	    localStorage.setItem("member_id",0);
        },
        error: function (response) {
            console.log("fault");
        }
    });
    $(".sub_button").click(function(event) {
		window.location.href = "evaluation.html" + '?record_id=' + id;
		//console.log(id);
   }); 
    
    $(".container.detail").css("opacity","100%");
    $(".container.detail").css("top","50%");
    $(".container.detail").css("transform","translateY(-50%)");
    $(".white").css("background","rgb(255,255,255,0.8)");
    $(".white").css("pointer-events","auto");
});
$('#commission_panel_trustee').on('click', '.col-sm-2.col-div-style', function()
{
	var id = $(this).attr("id");
    $.ajax({
        type: "GET",
        url: "api/evaluation_detail_trustee.do",
        crossDomain: true,
        cache: false,
        dataType: 'json',
        timeout: 5000,
        data:
        {
        	member_tID : $(this).attr("id")
        },
        success: function (response) {
            console.log(response);
            $(".det_delegator").text(response.response.data[0].name);
            $("#rank").text("困難度：");
            $("#rank").append(countstar(response.response.data[0].star_level))
        	$(".exp").text("經驗:"+response.response.data[0].exp);
       		$(".detail_des").text(response.response.data[0].detail_description);
       		$("#del_eval").html("委託評價:<br>" + response.response.data[0].delegator_eval);
       		$("#trustee_eval").html("受託評價:<br>" + response.response.data[0].trustee_eval);
       		localStorage.setItem("member_id",1);
        },
        error: function (response) {
            console.log("fault");
        }
    });

    $(".sub_button").click(function(event) {
    	window.location.href = "evaluation.html" + '?record_id=' + id;
    	console.log(id);
    }); 
    
	$(".container.detail").css("opacity","100%");
    $(".container.detail").css("top","50%");
    $(".container.detail").css("transform","translateY(-50%)");
    $(".white").css("background","rgb(255,255,255,0.8)");
    $(".white").css("pointer-events","auto");
});
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
$(".fas.fa-times").on("click",function(){
    $(".container.detail").css("opacity","0%");
    $(".container.detail").css("top","-100%");
    $(".white").css("background","none");
    $(".white").css("pointer-events","none");
});
$(document).on('click', '.btn-grad', function(){
    $(".container.commission").css("opacity","100%");
    $(".container.commission").css("top","50%");
    $(".container.commission").css("transform","translateY(-50%)");
    $(".white").css("background","rgb(255,255,255,0.8)");
    $(".white").css("pointer-events","auto");
});
$(".fas.fa-times").on("click",function(){
    $(".container.commission").css("opacity","0%");
    $(".container.commission").css("top","-100%");
    $(".white").css("background","none");
    $(".white").css("pointer-events","none");
});