// Función para agregar un nuevo post-it
function addPostIt(title, description) {
    const postItContainer = document.getElementById('postItContainer');
    const postIt = document.createElement('div');
    postIt.classList.add('post-it');
    postIt.draggable = true;
    postIt.setAttribute('ondragstart', 'dragStart(event)');

    // Establecer el título del post-it
    const titleSpan = document.createElement('span');
    titleSpan.classList.add('postIt-text');
    titleSpan.textContent = title || 'Nueva Nota'; // Si no se proporciona un título, se usa 'Nueva Nota'
    postIt.appendChild(titleSpan);

    // Establecer el contenido del post-it
    const contentInput = document.createElement('textarea');
    contentInput.placeholder = 'Contenido';
    contentInput.classList.add('postIt-content');
    contentInput.textContent = description || ''; // Si no se proporciona una descripción, se deja en blanco
    postIt.appendChild(contentInput);

    // Crear botones para guardar, editar y borrar
    const saveButton = document.createElement('button');
    saveButton.textContent = 'Guardar';
    const editButton = document.createElement('button');
    editButton.textContent = 'Editar';
    /*const deleteButton = document.createElement('button');
    deleteButton.textContent = 'Borrar';
    deleteButton.classList.add('delete-button');
    deleteButton.onclick = function() {
        // Aquí iría la lógica para borrar el post-it de la base de datos
        postIt.remove(); // Eliminar el post-it del DOM
    };*/

    postIt.appendChild(saveButton);
    postIt.appendChild(editButton);
    //postIt.appendChild(deleteButton);

    // Agregar el post-it al contenedor
    postItContainer.appendChild(postIt);
}

// Llamar a la función para agregar post-its guardados por el usuario
// Suponiendo que "postItsData" es un arreglo de objetos con los post-its guardados
postItsData.forEach(function(postItData) {
    addPostIt(postItData.title, postItData.description);

    // Crear botones para guardar, editar y borrar
    const saveButton = document.createElement('button');
    saveButton.textContent = 'Guardar';
    const editButton = document.createElement('button');
    editButton.textContent = 'Editar';
    const deleteButton = document.createElement('button');
    deleteButton.textContent = 'Borrar';
    deleteButton.classList.add('delete-button');
    deleteButton.onclick = function() {
        // Aquí iría la lógica para borrar el post-it de la base de datos
        postIt.remove(); // Eliminar el post-it del DOM
    };

    postIt.appendChild(saveButton);
    postIt.appendChild(editButton);
    postIt.appendChild(deleteButton);

    // Agregar el post-it al contenedor
    postItViewer.appendChild(postIt);
});
