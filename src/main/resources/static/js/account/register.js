const registerButton = document.querySelector(".account-button");

registerButton.onclick = () => {
    const accountInputs = document.querySelectorAll(".account-input");

    let user = { //객체화  user 객체 생성
        lastName: accountInputs[0].value,
        firstName: accountInputs[1].value,
        email: accountInputs[2].value,
        password: accountInputs[3].value
    }

    let ajaxOption = {
        async: false,                                               //필수
        type: "post", //method 타입                                 //필수
        url: "/api/account/register",                               //필수
        data: user,                                                 //전송할 데이터가 있으면
        dataType: "json", //응답받을 데이터 타입 리턴타입             //json외 text등을 사용할 수 있지만 js
        success: (response) => { //요청 성공 시                        //성공시에 실행 될 메소드 
            alert("회원가입 요청 성공");                             
        },
        error: (error) => {                                           //실패시
            alert("회원가입 요청 실패");
        }
    }

    $.ajax(ajaxOption); //jquery = $표시 //ajax 호출


}