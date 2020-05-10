$("li").on("click",function(){
    $("li").removeClass("active");
    $(this).addClass("active");
});
$( document ).ready(function() {
    console.log(localStorage.getItem("id"))
});