localStorage.setItem("id", "")
console.log(localStorage.getItem("id"))
$(".txtb input").on("focus",function(){

                $(this).addClass("focus");
            });

            $(".txtb input").on("blur",function(){
                if($(this).val() == "")
                $(this).removeClass("focus");
            });
            
            $(".txtlogin").on("click",function(){
                $(".space").css("height","145px");
                $(".login").css("height","570px");
                $(".txtb.register").css("display","block");
                function x() {
                    $(".txtb.register").css("opacity","100%");
                    $(".login h1").text("註冊");
                    $(".txtlogin").text("");
                    $(".txtregister").text("登入");
                    $(".logbtn").attr("value","註冊");
                }
                setTimeout(x,800);
                
            });
            $(".txtregister").on("click",function(){
                $(".txtb.register").css("opacity","0%");
                function y() {
                    $(".login").css("height","450px");
                    $(".space").css("height","0px");
                    $(".login h1").text("登入");
                    $(".txtregister").text("");
                    $(".txtlogin").text("註冊");
                    $(".logbtn").attr("value","登入");
                    $(".txtb.register").css("display","none");
                }
                setTimeout(y,600);
            });
function login(id,password) {
                    // 發出POST的GET請求取得所有會員列表
                    $.ajax({
                            type: "GET",
                            url: "api/membercontroller.do",
                            crossDomain: true,
                            cache: false,
                            dataType: 'json',
                            timeout: 5000,
                            data:{
                                email :  id,
                                password: password
                            },
                            success: function (response) {
                                if(response['response'].data[0]['verification']==true)
                                {
                                    $('#idmember').val("");
                                    localStorage.setItem("id", response['response'].data[0]['idtbMember'])
                                    if(response['response'].data[0]['permission']==0){
                                    	location.href="af_login.html";
                                    }
                                    else{
                                    	location.href="guild.html";
                                    }
                                    
                                }
                                else
                                {
                                    alert("學號或密碼錯誤")
                                }
                                console.log(response);
                            },
                            error: function (response) {
                                console.log("fault");
                            }
                    });
                };     
function submit() {
    var email = $('#email').val();
    var name = $('#register_name').val();
    var password = $('#password').val();
    var password_check = $('#dcheck_password').val();
    var email_rule = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
    var password_rule = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

    if($(".logbtn").attr("value")=="登入")
    {
        if(email.length==0)
        {
            alert("請輸入信箱");
        }
        else if(password.length==0)
        {
            alert("請輸入密碼");
        }
        else
        {
            login(email,password);
        }
        
    }
    else if($(".logbtn").attr("value")=="註冊")
    {
        if(!password_rule.test(password)) 
        {
            alert("密碼格式不符，長度至少8，且至少包含一個數字和英文字母！");
        }
        else if(password != password_check)
        {
            alert("請確認密碼");
        }
        else if (!email_rule.test(email))
       {
            alert("Email格式不符！");
        }
        else
        {
        	var data_object = {
                    "name": name,
                    "email": email,
                    "password": password
                };

                // 將JSON格式轉換成字串
                var data_string = JSON.stringify(data_object);

                // 發出POST的AJAX請求
                $.ajax({
                        type: "POST",
                        url: "api/membercontroller.do",
                        data: data_string,
                        crossDomain: true,
                        cache: false,
                        dataType: 'json',
                        timeout: 5000,
                        success: function (response) {
                            $('#flashMessage').html(response.message);
                            $('#flashMessage').show();
                            if(response.status == 200){
                            	alert("註冊成功，請重新登入")
                                console.log(response);
                            }
                        },
                        error: function (response) {
                            alert("無法連線到伺服器！");
                            console.log(response);
                        }
                });
        }
    }
}
$('.logbtn').click(submit); 