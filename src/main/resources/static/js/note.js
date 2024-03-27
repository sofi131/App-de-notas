function addPostIt() {
    const postItContainer = document.getElementById('postItContainer');
    const postIt = document.createElement('div');
    postIt.classList.add('post-it');
    postIt.draggable = true;
    postIt.setAttribute('ondragstart', 'dragStart(event)');
    postIt.innerHTML = '<span class="postIt-text">Nueva Nota</span>';
    postItContainer.appendChild(postIt);

    // Crear entrada de texto para el título
    const titleInput = document.createElement('input');
    titleInput.type = 'text';
    titleInput.placeholder = 'Título';
    titleInput.classList.add('postIt-title');
    postIt.appendChild(titleInput);

    // Crear entrada de texto para el contenido de la nota
    const contentInput = document.createElement('textarea');
    contentInput.placeholder = 'Contenido';
    contentInput.classList.add('postIt-content');
    postIt.appendChild(contentInput);

    // Crear botón para guardar la nota en la BDD
    const saveButton = document.createElement('button');
    saveButton.textContent = 'Guardar';

    // Crear botón para editar la nota en la BDD
    const editButton = document.createElement('button');
    editButton.textContent = 'Editar';

    // Crear botón para borrar la nota de la BDD
    const deleteButton = document.createElement('button');
    deleteButton.textContent = 'Borrar';
    deleteButton.classList.add('delete-button');
    deleteButton.onclick = function() {
        postIt.remove(); // Eliminar el post-it cuando se hace clic en el botón
    };


    postIt.appendChild(saveButton);
    postIt.appendChild(editButton);
    postIt.appendChild(deleteButton);

    // Agregar el post-it al contenedor
    postItContainer.appendChild(postIt);

}

// Función para manejar el evento de inicio de arrastre
function dragStart(event) {
    event.dataTransfer.setData('text/plain', event.target.id);
}

// Función para manejar el evento de soltar
function dragOver(event) {
    event.preventDefault();
}

// Función para manejar el evento de soltar
function drop(event) {
    event.preventDefault();
    const data = event.dataTransfer.getData('text/plain');
    const draggableElement = document.getElementById(data);
    const dropzone = event.target.closest('.postit-container');
    dropzone.insertBefore(draggableElement, event.target);
}
