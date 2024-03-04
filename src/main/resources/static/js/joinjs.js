const joinBox = document.getElementById('joinBox');
const joinBox2 = document.getElementById('joinBox2');
const joinNext = document.getElementById('joinNext');

joinNext.addEventListener('click', function() {
    joinBox.style.display = 'none';
    joinBox2.style.display = 'block';
});
