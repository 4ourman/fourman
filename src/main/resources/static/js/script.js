const beforeLogin = document.getElementById('beforeLogin');
const afterLogin = document.getElementById('afterLogin');
const loginButton = document.getElementById('loginButton');
const logoutButton = document.getElementById('logoutButton');

loginButton.addEventListener('click', function() {
    // 로그인 버튼 클릭 시 컨테이너 변경
    beforeLogin.style.display = 'none';
    afterLogin.style.display = 'block';
});

logoutButton.addEventListener('click', function() {
    // 로그아웃 버튼 클릭 시 컨테이너 변경
    beforeLogin.style.display = 'block';
    afterLogin.style.display = 'none';
});
