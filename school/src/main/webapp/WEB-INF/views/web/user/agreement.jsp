<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="스마투스코리아" />
<meta name="description" content="Smartooth" />
<title>㈜스마투스코리아 - 구강상태 측정 및 개인정보 수집 이용 동의서</title>
<link rel="shortcut icon" type="image/x-icon" href="/imgs/common/logo_img_ori.png">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="/css/common/layout.css">
<link rel="stylesheet" href="/css/web/user/agreement.css">
<style type="text/css">

.ui-autocomplete {
	border: none;
	position: relative;
	background-color: #ffffff; 
	list-style:none;
	font-size: 14px;
	margin-bottom: 5px;
	padding-top: 2px;
	border: 1px solid #DDD !important;
	padding-top: 0px !important;
	z-index: 1511;
	width: 220px;
	line-height: 30px;
}

.ui-menu-item{
	margin-left: -30px;
	border-bottom: 1px dotted;
   	border-collapse: collapse;
}

.ui-helper-hidden-accessible{
	display: none;
}
	
</style>
</head>
<body>
	<div class="container" id="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<div class="title-wrap">
					<div class="title-left">
						<img alt="㈜스마투스코리아 로고" src="/imgs/common/logo_origin.png">
					</div>
					<div class="title-right">
						구강상태 측정 및 개인정보<br/>수집 이용 동의서
					</div>
				</div>
				<div class="commonHeight25"></div>
				<form method="post" id="frm" action="/web/user/insertUserInfo.do">
					<div class="row category-wrap">
						<div class="category">1. 구강 상태 측정 동의 및 개인 정보 제공을 받는 회사 : </div>
						<div class="company-title" >㈜스마투스코리아</div>
					</div>
					<div class="commonHeight20"></div>
					<div class="row category-wrap">
						<div class="category">2. 구강상태 측정에 대한 동의</div>
						<div class="commonHeight5"></div>
						<div class="box-wrap">
							<div class="a1">
								본인 및 그 법정대리인은 본인의 올바른 구강관리를 위해 주식회사 스마투스코리아의 장비를 통하여 본인의 구강상태를 측정함에 동의합니다.
							</div>
							<div class="checkBox">
								<input type="checkbox" id="measureAgreement_agree" name="measureAgreement" onclick="isMeasureAgreementChecked(0);">
								<label class="label">동의함</label>
								<input type="checkbox" id="measureAgreement_disagree" name="measureAgreement" onclick="isMeasureAgreementChecked(1);">
								<label class="label">동의하지 않음</label>
							</div>
						</div>
					</div>
					<div class="commonHeight20"></div>
					<div class="category">3. 개인정보 수집 · 이용에 관한 동의</div>
					<table class="outter-table">
						<tr>
							<td class="col1">수집 · 이용 목적</td>
							<td class="col2">· 구강 상태 측정을 통한 구강관리 서비스 지원</td>
						</tr>
						<tr>
							<td class="col1">수집 · 이용 목적</td>
							<td class="col2">· 개인정보 : 성명, 생년월일, 보호자 전화번호</td>
						</tr>
						<tr>
							<td class="col1">보유 및 이용 기간</td>
							<td class="col2">· 수집일로부터 1년</div></td>
						</tr>
						<tr>
							<td class="td-content"  colspan="2">
								※ 귀하는 개인정보 수집 및 이용에 대하여 거부할 권리가 있습니다. 다만 거부 시 구강 관리 서비스의 제공이 불가함을 알려드립니다.
								<br/>
								※ 본인은 <u>개인정보보호법 제 15조 제1항</u>의 규정에 의거하여 본인의 <b><u>개인정보</u></b>를 제공할 것을 동의합니다.
								<div class="checkBox">
									<input type="checkbox" id="personalInfomationAgreement_agree" name="personalInfomationAgreement" onclick="isPersonalInfomationAgreementChecked(0);">
									<label class="label">동의함</label>
									<input type="checkbox" id="personalInfomationAgreement_disagree" name="personalInfomationAgreement" onclick="isPersonalInfomationAgreementChecked(1);">
									<label class="label">동의하지 않음</label>
								</div>
							</td>
						</tr>
					</table>
					<div class="commonHeight15"></div>
					
					
<!-- 					<div class="ageCheckBox"> -->
<!-- 						<div class="ageCheckBox-left"> -->
<!-- 							<input type="radio" id="under_fourteen" name="userAgeCheck" value="N" onclick="agreementAgeChk('N')"> -->
<!-- 							<input type="checkbox" id="under_fourteen" name="userAgeCheck" value="N" onclick="agreementAgeChk('N')"> -->
<!-- 							<label class="ageLable">14세 미만</label> -->
<!-- 						</div> -->
<!-- 						<div class="ageCheckBox-right"> -->
<!-- 							<input type="radio" id="over_fourteen" name="userAgeCheck" value="Y"onclick="agreementAgeChk('Y')"> -->
<!-- 							<input type="checkbox" id="over_fourteen" name="userAgeCheck" value="Y"onclick="agreementAgeChk('Y')"> -->
<!-- 							<label class="ageLable">14세 이상</label> -->
<!-- 						</div> -->
<!-- 					</div>	 -->
					
					
<!-- 만 14세 미만 -->
					<div class="under_fourteen" style="display: none;">
						<div class="category">4. 미성년자(만14세 미만)의 개인정보 동의 여부</div>
						<table class="outter-table">
							<tr>
								<td>
									<table class="innerTable">
										<tr style="border: 0px">
											<td class="css">법정대리인<br/>성명<font style="color: red; font-weight: bold;">*</font></td>
											<td><input type="text" id="paUserName" name="paUserName" class ="form-control" ></td>
										</tr>
										<tr style="border: 0px">
											<td class="css">법정대리인<br/>연락처<font style="color: red; font-weight: bold;">*</font></td>
											<td style="text-align: center;">
												<select id="paUserTelNo1" name="paUserTelNo1" class="form-control telNo" >
													<option value="010" selected="selected">010</option>
													<option value="011">011</option>
													<option value="016">016</option>
													<option value="017">017</option>
													<option value="018">018</option>
													<option value="019">019</option>
												</select>
												-<input type="text" id="paUserTelNo2" name="paUserTelNo2" class ="form-control telNo" onkeyup="isNumeric(this)" onkeydown="isNumeric(this)" maxlength="4">
												-<input type="text" id="paUserTelNo3" name="paUserTelNo3" class ="form-control telNo" onkeyup="isNumeric(this)" onkeydown="isNumeric(this)" maxlength="4">
											</td>
										</tr>
										<tr style="border: 0px">
											<td class="css">법정대리인과의<br/>관계</td>
											<td><input type="text" id="relationship" class ="form-control"></td>
										</tr>														
										<tr style="border: 0px">
											<td class="css ">기관(학교)<font style="color: red; font-weight: bold;">*</font></td>
											<td><input type="text" id="schoolName1" name="schoolName1" class ="form-control schoolName" placeholder="기관(학교)를 검색 후 선택하세요."></td>
										</tr>														
										<tr style="border: 0px">
											<td class="css">부서(반)<font style="color: red; font-weight: bold;">*</font></td>
											<td>
												<select id="departList1" name="classCode1" class="departList">
													<option selected='selected'>부서(반)을 선택해주세요</option>
												</select>
											</td>
										</tr>
										<tr style="border: 0px">
											<td class="css">
												자녀 성별<font style="color: red; font-weight: bold;">*</font>
											</td>
											<td>
												<select id="stUserSex" name="stUserSex" class="userSex">
													<option selected='selected'>성별을 선택해주세요</option>
													<option value="M">남</option>
													<option value="F">여</option>
												</select>
											</td>
										</tr>
										<tr style="border: 0px">
											<td class="css">
												자녀 생년월일<font style="color: red; font-weight: bold;">*</font>
											</td>
											<td>
												<input type="date" id="stUserBirthday" name="stUserBirthday" class="userBirthday"data-placeholder="날짜 선택">
											</td>
										</tr>																						
									</table>
								</td>
							</tr>
							<tr>
								<td class="td-content"  colspan="3">
									※ 본인<font style="color: red; font-weight: bold;">*</font>은 ( <input type ="text" id="stUserName" name="stUserName" class ="form-control stUserName" placeholder="자녀의 이름"> )의 법정 대리인으로, <u>개인정보보호법 제22조 제6항 및 동법 시행령 제 17조</u>에 따라
									개인정보의 수집·이용·제공 등에 대하여 확인하였습니다.
									<div class="checkBox">
										<input type="checkbox" id="ageAgreement_agree" name="submitAgreement" onclick="isSubmitAgreementChecked(0);">
										<label class="label">동의함</label>
										<input type="checkbox" id="ageAgreement_disagree" name="submitAgreement" onclick="isSubmitAgreementChecked(1);">
										<label class="label">동의하지 않음</label>
									</div>
								</td>
							</tr>
						</table>
					</div>
<!-- 만 14세 미만 -->
<!-- 만 14세 이상 -->
					<div class="over_fourteen" style="display: none;">
						<table class="outter-table" style="border: 0px solid #ffffff">
							<tr>
								<td>
									<table class="inner-table">
										<tr style="border: 0px">
											<td class="css">성명<font style="color: red; font-weight: bold;">*</font></td>
											<td><input type="text" class ="form-control" id="userName" name="userName"></td>
										</tr>
										<tr style="border: 0px">
											<td class="css">연락처<font style="color: red; font-weight: bold;">*</font></td>
											<td style="text-align: center;">
												<select class="form-control telNo" id="userTelNo1" name="userTelNo1">
													<option value="010" selected="selected">010</option>
													<option value="011">011</option>
													<option value="016">016</option>
													<option value="017">017</option>
													<option value="018">018</option>
													<option value="019">019</option>
												</select>-
												<input type="text" class ="form-control telNo" id="userTelNo2" name="userTelNo2" onkeyup="isNumeric(this)" onkeydown="isNumeric(this)" maxlength="4">-
												<input type="text" class ="form-control telNo" id="userTelNo3" name="userTelNo3" onkeyup="isNumeric(this)" onkeydown="isNumeric(this)" maxlength="4">
											</td>
										</tr>
										<tr style="border: 0px">
											<td class="css ">기관<font style="color: red; font-weight: bold;">*</font></td>
											<td><input type="text" id="schoolName2" name="schoolName2" class ="form-control" placeholder="기관을 검색 후 선택하세요."></td>
										</tr>														
										<tr style="border: 0px">
											<td class="css">부서<font style="color: red; font-weight: bold;">*</font></td>
											<td>
												<select id="departList2" name="classCode2" class="departList">
													<option selected='selected'>부서를 선택해주세요</option>
												</select>
											</td>
										</tr>
										<tr style="border: 0px">
											<td class="css">
												성별<font style="color: red; font-weight: bold;">*</font>
											</td>
											<td>
												<select id="userSex" name="userSex" class="userSex">
													<option selected='selected'>성별을 선택해주세요</option>
													<option value="M">남</option>
													<option value="F">여</option>
												</select>
											</td>
										</tr>
										<tr style="border: 0px">
											<td class="css">
												생년월일<font style="color: red; font-weight: bold;">*</font>
											</td>
											<td>
												<input type="date" id="userBirthday" name="userBirthday" class="userBirthday" data-placeholder="날짜 선택">
											</td>
										</tr>																						
									</table>
								</td>
							</tr>
						</table>
					</div>
<!-- 만 14세 이상 -->
				<input type="hidden" id="schoolCode" name="schoolCode" value="">
				<input type="hidden" id="userType" name="userType" value="">
				<div class="commonHeight15"></div>
				<button class="btn btn-primary btn-lg btn-block" id="submit">동의서 제출</button>
				</form>
			</div>
		</div>
		<footer class="my-3 text-center text-small">
			<p class="mb-1">Copyright &copy; Smartooth korea 2023</p>
		</footer>
	</div>
<script>

	var msg = "${msg}";
	if(msg!=null && msg!=""){
		alert(msg);
	}

	// 숫자 정규식 검증
	function isNumeric(obj){
		var regExp = /[^0-9]/g;
		if(regExp.test(obj.value)){
			alert("숫자만 입력이 가능합니다.")
			obj.value = obj.value.substring(0, obj.value.length -1);
		}
	}
	
	// 체크박스 중복되지 않도록 하는 기능
	function isMeasureAgreementChecked(isAgree) {
		if(isAgree==0){
			$('#measureAgreement_disagree').prop('checked',false);
	    }
		if(isAgree==1){
			$('#measureAgreement_agree').prop('checked',false);
	    }
	}
	function isPersonalInfomationAgreementChecked(isAgree) {
		if(isAgree==0){
			$('#personalInfomationAgreement_disagree').prop('checked',false);
	    }
		if(isAgree==1){
			$('#personalInfomationAgreement_agree').prop('checked',false);
	    }
	}
	function isSubmitAgreementChecked(isAgree) {
		if(isAgree==0){
			$('#ageAgreement_disagree').prop('checked',false);
	    }
		if(isAgree==1){
			$('#ageAgreement_disagree').prop('checked',false);
	    }
	}
	
// 	// 14세 미만 혹은 이상 체크
// 	function agreementAgeChk(isOverFourteen){
// 		if(isOverFourteen == 'N'){
	
	$('#over_fourteen').prop('checked',false);
	$('.under_fourteen').css('display', 'block');
	$('.over_fourteen').css('display', 'none');
	
// 	    }
// 		if(isOverFourteen=='Y'){
// 			$('#under_fourteen').prop('checked',false);
// 			$('.over_fourteen').css('display', 'block');
// 			$('.under_fourteen').css('display', 'none');
// 	    }
// 	}	
	
	// 기관 이름 검색
	$('#schoolName1').autocomplete({
		source : function(request, response) { //source: 입력시 보일 목록
		     $.ajax({
		           url : "/web/organ/ajaxSelectOrganList.do"   
		         , type : "POST"
		         , dataType: "JSON"
		         , data : {value: request.term}	// 검색 키워드
		         , success : function(data){ 	// 성공
		             response(
		                 $.map(data.organList, function(item) {
		                	 
		                	 var seq = item.SEQ;
		                	 var schoolCode = item.SCHOOL_CODE;
		                	 var schoolName = item.SCHOOL_NAME;
		                	 var organAddress = item.ORGAN_ADDRESS;
		                	 
		                     return {
									label : schoolName + '\n\n('+organAddress+')'     	// 목록에 표시되는 값
									,value : schoolName		// 선택 시 input창에 표시되는 값
									,idx : seq // index
									,schoolCode : schoolCode
		                     };
		                 })
		             );    //response
		         }
		         ,error : function(){ //실패
		             alert("관리자에게 문의해주세요.");
		         }
		         
		     });
		}
		,minLength : 1
		,focus: function (event, ui) {
	        return false;
	    },
	    select: function (event, ui) {
	    	$('#schoolCode').attr("value", ui.item.schoolCode);
			$.ajax({
				url : "/web/organ/ajaxSelectDepartmentList.do",
				type : "POST",
				dataType : "JSON",
				data : {
					"schoolCode" : ui.item.schoolCode
				} // 검색 키워드
				,
				success : function(data) { // 성공
					// 기존에 목록이 있을 경우 select의 하위 자식 element 삭제
					$("#departList1").empty();
					var departList = data.departList;
					$("#departList1").append("<option selected='selected'>부서(반)을 선택해주세요</option>");
					for(var i=0; i<departList.length; i++){
						$("#departList1").append("<option value='"+departList[i].CLASS_CODE+"'>"+departList[i].CLASS_NAME+"</option>");
					}
				},
				error : function() { //실패
					alert("관리자에게 문의해주세요.");
				}
			});
		},
		delay : 300
	});
	
	
	$('#schoolName2').autocomplete({
		source : function(request, response) { //source: 입력시 보일 목록
		     $.ajax({
		           url : "/web/organ/ajaxSelectOrganList.do"   
		         , type : "POST"
		         , dataType: "JSON"
		         , data : {value: request.term}	// 검색 키워드
		         , success : function(data){ 	// 성공
		             response(
		                 $.map(data.organList, function(item) {
		                	 
		                	 var seq = item.SEQ;
		                	 var schoolCode = item.SCHOOL_CODE;
		                	 var schoolName = item.SCHOOL_NAME;
		                	 var organAddress = item.ORGAN_ADDRESS;
		                	 
		                     return {
									label : schoolName + '\n\n('+organAddress+')'     	// 목록에 표시되는 값
									,value : schoolName		// 선택 시 input창에 표시되는 값
									,idx : seq // index
									,schoolCode : schoolCode
		                     };
		                 })
		             );    //response
		         }
		         ,error : function(){ //실패
		             alert("관리자에게 문의해주세요.");
		         }
		     });
		}
		,minLength : 1
		,focus: function (event, ui) {
	        return false;
	    },
	    select: function (event, ui) {
	    	$('#schoolCode').attr("value", ui.item.schoolCode);

	    	$.ajax({
				url : "/web/organ/ajaxSelectDepartmentList.do",
				type : "POST",
				dataType : "JSON",
				data : {
					"schoolCode" : ui.item.schoolCode
				} // 검색 키워드
				,
				success : function(data) { // 성공
					// 기존에 목록이 있을 경우 select의 하위 자식 element 삭제
					$("#departList2").empty();
					var departList = data.departList;
					$("#departList2").append("<option selected='selected'>부서(반)을 선택해주세요</option>");
					for(var i=0; i<departList.length; i++){
						$("#departList2").append("<option value='"+departList[i].CLASS_CODE+"'>"+departList[i].CLASS_NAME+"</option>");
					}
				},
				error : function() { //실패
					alert("관리자에게 문의해주세요.");
				}
			});
		},
		delay : 300
	});
	
	
	$("#submit").click(function(){
		
		var isOverFourteen = "N";
		
// 	    $("input[name='userAgeCheck']:checked").each(function(e){
// 	    	isOverFourteen = $(this).val();
// 	    });
		
		
	    // 14세미만 일 경우 
	    if(isOverFourteen=='N'){
	    	
	    	// 회원 유형 : 환자	    	
	    	$('#userType').attr("value", 'ST');
	    	
	    	// checkbox 체크 여부 확인
			if($('#measureAgreement_agree').is(":checked") == false){
				alert("필수 약관에 동의해주세요.");
				return false;
			}
			if($('#personalInfomationAgreement_agree').is(":checked") == false){
				alert("필수 약관에 동의해주세요.");
				return false;
			}
			// 14세 미만일 경우 미성년자의 개인정보 동의 여부 확인
			if($('#ageAgreement_agree').is(":checked") == false){
				alert("필수 약관에 동의해주세요.");
				return false;	
			}
			
			if($("#paUserName").val() == ""){
				alert("법정대리인 성명을 입력해주세요.");
				$("#paUserName").focus();
				return false;
			}
			
			if($("#paUserTelNo2").val() == ""){
				alert("법정대리인 연락처를 입력해주세요.");
				$("#paUserTelNo2").focus();
				return false;
			}
			if($("#paUserTelNo3").val() == ""){
				alert("법정대리인 연락처를 입력해주세요.");
				$("#paUserTelNo3").focus();
				return false;
			}
			
			if($("#schoolName1").val() == ""){
				alert("기관(학교)을 입력해주세요.");
				$("#schoolName1").focus();
				return false;
			}
			
			if($("#departList1").val().indexOf("KR") == -1){
				alert("부서(반)을 선택해주세요.");
				$("#departList1").focus();
				return false;
			}
			
			if($("#stUserSex").val().indexOf("M") == -1 && $("#stUserSex").val().indexOf("F") == -1 ){
				alert("자녀의 성별을 선택해주세요.");
				$("#stUserSex").focus();
				return false;
			}
			
			if($("#stUserBirthday").val() == ""){
				alert("자녀의 성별을 선택해주세요.");
				$("#stUserBirthday").focus();
				return false;
			}
			
			if($("#stUserName").val() == ""){
				alert("자녀의 성명을 입력해주세요.");
				$("#stUserName").focus();
				return false;
			}
		
			alert("동의서에 작성해주신 전화번호를 사용하여\n부모님의 아이디, 자녀의 아이디가 생성되오니\n정확한 입력 부탁드립니다.");
			
			if(window.confirm("법정대리인 성명 : "+$("#paUserName").val()+"\n법정대리인 연락처: "+$("#paUserTelNo1").val()+"-"+$("#paUserTelNo2").val()+"-"+$("#paUserTelNo3").val()
				+"\n법정대리인과의 관계 : "+$("#relationship").val()+"\n기관(학교) : "+$("#schoolName1").val()+"\n부서(반) : "+$("#departList1 option:selected").text()
				+"\n자녀 성명 : "+$("#stUserName").val() +"\n자녀의 생년월일 : "+$("#stUserBirthday").val()+"\n자녀의 성별 : "+$("#stUserSex option:selected").text()+"\n\n입력하신 정보가 맞으실 경우 확인을 눌러주세요.\n아닐 경우 취소를 눌러주세요.") == true) {

				$("#frm").attr("action","/web/user/insertUserInfo.do").submit();
				
	   		 }else{
				return false;
			}
	    }else{
	    	
			// 회원 유형 : 환자	    	
	    	$('#userType').attr("value", 'PT');
	    	
	    	// checkbox 체크 여부 확인
			if($('#measureAgreement_agree').is(":checked") == false){
				alert("필수 약관에 동의해주세요.");
				return false;
			}
			if($('#personalInfomationAgreement_agree').is(":checked") == false){
				alert("필수 약관에 동의해주세요.");
				return false;
			}
			
			if($("#userName").val() == ""){
				alert("성명을 입력해주세요.");
				$("#userName").focus();
				return false;
			}
			
			if($("#userTelNo2").val() == ""){
				alert("연락처를 입력해주세요.");
				$("#userTelNo2").focus();
				return false;
			}
			if($("#userTelNo3").val() == ""){
				alert("연락처를 입력해주세요.");
				$("#userTelNo3").focus();
				return false;
			}
			
			if($("#schoolName2").val() == ""){
				alert("기관을 입력해주세요.");
				$("#schoolName2").focus();
				return false;
			}
			
			if($("#departList2").val().indexOf("KR") == -1){
				alert("부서을 선택해주세요.");
				$("#departList2").focus();
				return false;
			}
			
			if($("#userSex").val().indexOf("M") == -1 && $("#userSex").val().indexOf("F") == -1 ){
				alert("성별을 선택해주세요.");
				$("#userSex").focus();
				return false;
			}
			
			if($("#userBirthday").val() == ""){
				alert("생년월일을 선택해주세요.");
				$("#userBirthday").focus();
				return false;
			}
			
			alert("동의서에 작성해주신 전화번호를 사용하여\n아이디가 자동으로 생성되오니\n정확한 입력 부탁드립니다.");
			
			if(window.confirm("성명 : "+$("#userName").val()+"\n연락처: "+$("#userTelNo1").val()+"-"+$("#userTelNo2").val()+"-"+$("#userTelNo3").val()
				+"\n기관 : "+$("#schoolName2").val()+"\n부서(반) : "+$("#departList2 option:selected").text()
				+"\n생년월일 : "+$("#userBirthday").val()+"\n성별 : "+$("#userSex option:selected").text()+"\n\n입력하신 정보가 맞으실 경우 확인을 눌러주세요.\n아닐 경우 취소를 눌러주세요.") ==true) {
				
				$("#frm").attr("action","/web/user/insertUserInfo.do").submit();
				//$("#frm").submit();
			}else{
				return false;
			}
		}
	});
	
</script>
</body>

</html>