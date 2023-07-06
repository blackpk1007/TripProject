// 현재 날짜를 가져옵니다.
	var currentDate = new Date().toISOString().split("T")[0];
	
	// 최대 날짜를 현재 날짜로 설정합니다.
	document.getElementById("userBirth").max = currentDate;

//regist_check 함수로 유효성 검사
function regist_check() {
  //변수에 담아주기
  var userName = document.getElementById("userName");
  var userID = document.getElementById("userID");
  var userPW = document.getElementById("userPW");
  var userPW2 = document.getElementById("userPW2");
  var male = document.getElementById("male");
  var female = document.getElementById("female");
  var userPhone = document.getElementById("userPhone");
  var userEmail = document.getElementById("userEmail");
  var userBirth = document.getElementById("userBirth");
  var bDate = new Date();
  
  if (userName.value == "") {
    alert("이름을 입력하세요.");
    userName.focus();
    return false;
  };
  
   // 한글영어,두글자이상12글자이
 	var namecheck = /^[가-힣a-zA-Z]{2,12}$/;
  if (!namecheck.test(userName.value)){
  	alert("한글 및 영어 두글자 이상 12글자 이하 이름만 가능.");
  	userName.focus();
  	return false;
  };
  
  if (userBirth.value == ""){
	  alert("생년월일 입력하세요.")
	  return false;
  };
	  
  /*
  if (!bDate.test(userBirth.value)) {
  	alert("오늘태어났니?");
  	userBirth.focus();
  	return false;
  }
  */
  
  
  if (userID.value == "") { //해당 입력값이 없을 경우 같은말: if(!uid.value)
    alert("아이디를 입력하세요.");
    userID.focus(); //focus(): 커서가 깜빡이는 현상, blur(): 커서가 사라지는 현상
    return false; //return: 반환하다 return false:  아무것도 반환하지 말아라 아래 코드부터 아무것도 진행하지 말것
  };
  
  //아이디 영어,숫자 6~20자
  var idcheck = /^[a-zA-Z0-9]{6,20}$/;
  if (!idcheck.test(userID.value)) {
  	alert("아이디는 영어,숫자 6~20자로 설정해주세요.");
  	userID.focus();
  	return false;
  };
  
  if (userPW.value == "") {
    alert("비밀번호를 입력하세요.");
    userPW.focus();
    return false;
  };

  //비밀번호 영문자+숫자+특수조합(8~16자리 입력) 정규식
  var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;

  if (!pwdCheck.test(userPW.value)) {
    alert("비밀번호는 영문자+숫자+특수문자 조합으로 8~16자리 사용해야 합니다.");
    userPW.focus();
    return false;
  };

  if (userPW2.value !== userPW.value) {
    alert("비밀번호가 일치하지 않습니다.");
    userPW2.focus();
    return false;
  };


  /* if (!female.checked && !male.checked) { //둘다 미체크시
    alert("성별을 선택해 주세요.");
    female.focus();
    return false;
  } */



  if (userEmail.value == "") {
    alert("이메일 주소를 입력하세요.");
    userEmail.focus();
    return false;
  }
  
  var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
  if (!emailRegExp.test(userEmail.value)) {
      alert("이메일 형식이 올바르지 않습니다!");
      userEmail.focus();
      return false;
  }
  
  
  alert("회원가입이 완료되었습니다!");

  //입력 값 전송
  document.registerform.submit(); //유효성 검사의 포인트
  location.href="/login";
}





//아이디 중복체크 팝업창(현재 공백문서)
function id_check() {
  //window.open("팝업될 문서 경로", "팝업될 문서 이름", "옵션");
  window.open("", "", "width=600, height=200, left=200, top=100");
}

//이메일 옵션 선택후 주소 자동 완성
function change_email() {
  var email_add = document.getElementById("email_add");
  var email_sel = document.getElementById("email_sel");

  //지금 골라진 옵션의 순서와 값 구하기
  var idx = email_sel.options.selectedIndex;
  var val = email_sel.options[idx].value;

  email_add.value = val;
}

//우편번호 검색 팝업창(현재 공백문서)
function search_address() {
  window.open("", "b", "width=600, height=300, left=200, top=100");
}
function idCheck(){
	var userID = document.getElementById("userID");
	var useridvalue = document.getElementById("userID").value;
	
	if (userID.value == "") { //해당 입력값이 없을 경우 같은말: if(!uid.value)
    alert("아이디를 입력하세요.");
    userID.focus(); //focus(): 커서가 깜빡이는 현상, blur(): 커서가 사라지는 현상
    return false; //return: 반환하다 return false:  아무것도 반환하지 말아라 아래 코드부터 아무것도 진행하지 말것
  	};
  
  //아이디 영어,숫자 6~20자
  var idcheck = /^[a-zA-Z0-9]{6,20}$/;
  if (!idcheck.test(userID.value)) {
  	alert("아이디는 영어,숫자 6~20자로 설정해주세요.");
  	userID.focus();
  	return false;
  };
	
	$.ajax({
          url : "/login/idcheck",
          type : "post",
          data : { userID : useridvalue
          }
       })
       		.done(function (result) {
       		console.log(result);
                if(result == 0 ){
                alert(useridvalue+"는 사용가능한 아이디입니다");
				}else{
				alert("중복된 아이디가 존재합니다!");
				userID.focus();
				return false;
				}                
            })
            .fail(function(jqXHR) {
                console.log(jqXHR);
                alert(jqXHR);
            })
	
}

  /*
  var reg = /^[0-9]+/g; //숫자만 입력하는 정규식
  if (!reg.test(userPhone.value)) {
    alert("전화번호는 숫자만 입력할 수 있습니다.");
    userPhone.focus();
    return false;
  }
  */

  /* if (!agree.checked) { //체크박스 미체크시
    alert("약관 동의를 체크하세요.");
    agree.focus();
    return false;
  } */