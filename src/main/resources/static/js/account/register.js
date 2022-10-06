const registerButton = document.querySelector(".account-button");

registerButton.onclick = () => {
    const accountInputs = document.querySelectorAll(".account-input");

    let user = { //객체화  user 객체 생성
        lastName: accountInputs[0].value,
        firstName: accountInputs[1].value,
        email: accountInputs[2].value,
        password: accountInputs[3].value
    }

    //JSON.stringify() -> js 객체를 JSON 문자열로 변환
    //JSON.parse()     -> JSON 문자열을 js 객체로 변환
    $.ajax({
       async: false,                                               //필수
       type: "post", //method 타입                          //필수
       url: "/api/account/register",                       //필수
       contentType: "application/json",                    //전송할 데이터가 json인 경우
       data: JSON.stringify(user),                         //전송할 데이터가 있으면
       dataType: "json", //응답받을 데이터 타입 리턴타입        //json외 text등을 사용할 수 있지만 js
       success: (response) => { //요청 성공 시                    //성공시에 실행 될 메소드
           alert("회원가입 요청 성공");
       },
       error: (error) => {                                           //실패시
           console.log(error.responseJSON.data);
           loadErrorMessage(error.responseJSON.data);
       }
    }); //jquery = $표시 //ajax 호출


}

function loadErrorMessage(errors) {
    const errorList = document.querySelector(".errors");
    const errorMsgs = document.querySelector(".error-msgs");
    const errorArray = Object.values(errors) //배열이 된다.
    
    errorMsgs.innerHTML = "";

    errorArray.forEach(error => {
        errorMsgs.innerHTML += `
            <li>${error}</li>
        `;
    })

    errorList.classList.remove("errors-invisible");
}