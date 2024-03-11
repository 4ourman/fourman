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

function confirmDelete() {
    // 현재 URL에서 postId 추출
    var postId = window.location.pathname.split('/').pop();
    console.log('현재 URL의 postId:', postId);

    if (!postId) {
        console.error('postId가 올바르게 전달되지 않았습니다.');
        return;
    }

    if (confirm('정말로 삭제하시겠습니까?')) {
        deletePost(postId);
    }
}

function deletePost(postId) {
    fetch('/api/v1/posts/post/' + postId, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                // 성공적으로 삭제된 경우
                console.log('게시물이 성공적으로 삭제되었습니다.');
                // 메인 페이지로 이동
                window.location.href = '/api/v1/posts/main';
            } else {
                // 삭제 실패한 경우
                console.error('게시물 삭제에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('네트워크 오류:', error);
        });
}

/* memberinfo */
document.getElementById("dropmodal_opne_btn").onclick = function() {
    document.getElementById("dropmodal").style.display="block";
};

document.getElementById("dropmodal_close_btn").onclick = function() {
    document.getElementById("dropmodal").style.display="none";
};
