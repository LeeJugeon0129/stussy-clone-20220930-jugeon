const registerForwardButton = document.querySelectorAll(".account-button")[0]; //첫번째 버튼 (회원가입 버튼)

registerForwardButton.onclick = () => {
    location.href = "/account/register"; // /: 절대경로
}