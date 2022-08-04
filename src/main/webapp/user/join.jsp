<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>CodePen - form</title>
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="shadow/js/shadowbox.js"></script>


<link rel="stylesheet" href="joindist/join_style.css">

<script type="text/javascript">
	Shadowbox.init({
		players : [ 'iframe' ]
	})
	$(function() {
		$('#checkBtn').click(function() {
			Shadowbox.open({
				content : '../user/idcheck.do',
				player : 'iframe',
				title : '아이디 중복체크',
				width : 400,
				height : 200
			})
		})
		$('#postBtn').click(function() {
			// 우편번호 검색 처리 
			new daum.Postcode({
				oncomplete : function(data) {
					$('#post').val(data.zonecode)
					$('#addr1').val(data.address)
				}
			}).open()
		})
		
	// 유효성 검사
    $('#eBtn').click(function(){
	    let email=$('#email').val();
	    if(email.trim()=="")
		{
		 $("email").focus();
		 $('#ePrint').text("이메일을 입력하세요")
		 return;
		}
	    $.ajax({
		    type:'post',
		    url:'../user/email_check.do',
		    data:{"email":email},
		    success:function(result)
		    {
			    let count=parseInt(result.trim())
			    if(count==0)
				{
				    $('#ePrint').text("사용가능한 이메일입니다");
				    $('#email').attr('disabled',true);
				}
			    else
				{
				    $('#ePrint').text("사용중인 이메일입니다");
				    $('#email').val("")
				    $('#email').focus()
				}
		    }
	    })
    })
    
     $('#tBtn').click(function(){
        let phone=$('#phone').val();
        if(phone.trim()=="")
        {
         $("phone").focus();
         $('#tPrint').text("전화번호를 입력하세요")
         return;
        }
        $.ajax({
            type:'post',
            url:'../user/phone_check.do',
            data:{"phone":"010-"+phone},
            success:function(result)
            {
                let count=parseInt(result.trim())
                if(count==0)
                {
                    $('#ePrint').text("사용가능한 번호입니다");
                    $('#phone').attr('disabled',true);
                }
                else
                {
                    $('#tPrint').text("사용중인 번호입니다");
                    $('#phone').val("")
                    $('#phone').focus()
                }
            }
        })
    })
	})
</script>
</head>
<body>
	<!-- partial:index.partial.html -->
	<div class="joincontainer">
		<form>
			<div class="row">
			
				<h4>Account</h4>
				
				<div class="input-group">
					<div style="width: 420px; margin-right: 20px;">
						<input type="text" name=id id=id placeholder="아이디" />
					</div>
					<div class="col-third" style="width: 80px">
						<input type="button" id="checkBtn" value="중복확인" />
					</div>
				</div>
				

				<div class="input-group input-group-icon">
					<input type="email" placeholder="비밀번호" />
					<div class="input-icon">
						<i class="fa fa-envelope"></i>
					</div>
				</div>
				<div class="input-group input-group-icon">
					<input type="password" placeholder="비밀번호 확인" />
					<div class="input-icon">
						<i class="fa fa-key"></i>
					</div>
				</div>
			</div>
			<div class="row">
				<!-- 생년월일 -->
				<h4>Private Info</h4>
				<div class="col-half">
					<input type="date" name=birthday placeholder="생년월일" />
				</div>

				<!-- 성별 -->
				<div class="col-half">
					<div class="input-group">
						<input id="gender-male" type="radio" name="gender" value="male" />
						<label for="gender-male">Male</label> <input id="gender-female"
							type="radio" name="gender" value="female" /> <label
							for="gender-female">Female</label>
					</div>
				</div>
			</div>


			<div class="input-group input-group-icon">
				<input type="tel" id=phone placeholder="전화번호" />
				<div class="input-icon">
					<i class="fa fa-envelope"></i>
				</div>
				<input type="button" id="tBtn" value="전화번호 확인">
        		&nbsp;<span style="color:blue" id="tPrint"></span>
			</div>
			
			<div class="input-group input-group-icon">
				<input type="email" id="email" placeholder="이메일" />
				<div class="input-icon">
					<i class="fa fa-key"></i>
				</div>
				<input type="button" id="eBtn" value="이메일 확인" />
				&nbsp;<span style="color:blue" id="ePrint"></span>
			</div>

			<!-- 주소 -->
			<div class="row">
				<div class="row">
					<h4>Address</h4>
					<div class="input-group input-group-icon">
						<div style="width:80%">
							<input type="text" name=postcode id=postcode placeholder="우편번호"
								readonly />
						</div>
						<div class="col-third" style="width: 80px">
							<input type="button" value="검색" id="postBtn" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="input-group input-group-icon">
							<input type="text" name=addr1 id=addr1 placeholder="주소" readonly />
					</div>
				</div>
				<div class="row">
					<div class="input-group input-group-icon ">
						<input type="text" name=addr2 id=addr2 placeholder="상세주소" />
					</div>
				</div>
			</div>

			<!-- 동의사항 -->
			<div class="row">
				<h4>Terms and Conditions</h4>
				<div class="input-group">
					<input id="terms" type="checkbox" /> <label for="terms">I
						accept the terms and conditions for signing up to this service,
						and hereby confirm I have read the privacy policy.</label>
				</div>
			</div>
		</form>
		
		<input type="button" value="회원가입" />
	</div>

	<!-- partial -->
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src="joindist/script.js"></script>

</body>
</html>
