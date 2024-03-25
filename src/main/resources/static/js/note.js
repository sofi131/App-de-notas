function addPostIt() {
    const postItContainer = document.getElementById('postItContainer');
    const postIt = document.createElement('div');
    postIt.classList.add('postIt');
    postIt.draggable = true;
    postIt.setAttribute('ondragstart', 'dragStart(event)');
    postIt.innerHTML = '<span class="postIt-text">Post-It</span>';
    postItContainer.appendChild(postIt);
}

function dragStart(event) {
    event.dataTransfer.setData('text/plain', event.target.id);
}
