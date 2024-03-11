/* header */
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

/* post */
function addComment(event) {
    event.preventDefault();
    var commentInput = event.target.querySelector("input");
    var commentText = commentInput.value;
    var date = new Date().toLocaleString();

    var commentDiv = document.createElement("div");
    commentDiv.className = "card my-3";
    commentDiv.innerHTML = `
    <div class="card-body">
      <p class="card-text">${commentText}</p>
      <p class="card-text"><small class="text-muted">${date}</small></p>
    </div>
  `;

    var commentsDiv = event.target.parentNode.querySelector(".comments");
    commentsDiv.insertBefore(commentDiv, commentsDiv.firstChild);

    commentInput.value = "";
}

/* memberinfo */
document.getElementById("dropmodal_opne_btn").onclick = function() {
    document.getElementById("dropmodal").style.display="block";
};

document.getElementById("dropmodal_close_btn").onclick = function() {
    document.getElementById("dropmodal").style.display="none";
};
