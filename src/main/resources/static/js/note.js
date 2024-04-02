window.onload = () => {
    let editarBotones = document.getElementsByClassName("edit-button");
    for (let i = 0; i < editarBotones.length; i++) {
        editarBotones[i].onclick = (e) => {
            const divEditar = e.currentTarget.parentElement.parentElement.querySelector(".editar");
            const divDatos = e.currentTarget.closest(".post-it").querySelector(".datos");
            if (divDatos && divEditar) {
                // Oculta el div de datos y muestra el formulario de edición
                divDatos.style.display = "none";
                divEditar.style.display = "block";
            } else {
                console.error("Elemento con clase 'datos' o 'editar' no encontrado dentro del padre del botón de edición");
            }
        }
    }
}



// Función para mostrar u ocultar el contenedor de post-its
function togglePostItContainer() {
    const postItContainer = document.getElementById('postItContainer');
    const addButton = document.querySelector('.add-button');

    // Si el contenedor está oculto, lo mostramos; de lo contrario, lo ocultamos
    if (postItContainer.classList.contains('hidden')) {
        postItContainer.classList.remove('hidden');
        addButton.textContent = 'Ocultar Post-It';
    } else {
        postItContainer.classList.add('hidden');
        addButton.textContent = 'Añadir Post-It';
    }
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

// Función para mostrar u ocultar el formulario de edición
function toggleForm(e) {
    var form = e.currentTarget.parentElement.querySelector("form");
    if (form) {
        if (form.style.display === "none" || form.style.display === "") {
            form.style.display = "block";
        } else {
            form.style.display = "none";
        }
    } else {
        console.error("Formulario no encontrado dentro del padre del botón de edición");
    }

    //CANCELAR EDICIÓN
    function cancelEdit(button) {
        const form = button.closest('form');
        if (form) {
            form.style.display = 'none';
            // Aquí puedes realizar cualquier otra acción que desees al cancelar la edición
        }
    }

}
