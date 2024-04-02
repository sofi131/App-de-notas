window.onload = () => {
    let editarBotones = document.querySelectorAll(".edit-button");
    editarBotones.forEach((boton) => {
        boton.addEventListener("click", (e) => {
            const divEditar = e.target.parentElement.parentElement.parentElement.querySelector(".editar");
            const divDatos = e.target.closest(".post-it").querySelector(".datos");
            if (divDatos && divEditar) {
                // Oculta el div de datos y muestra el formulario de edición
                divDatos.style.display = "none";
                divEditar.style.display = "block";

                // Obtener el botón de cancelar dentro del formulario de edición
                const cancelButton = divEditar.querySelector(".cancel-button");

                // Agregar evento de clic al botón de cancelar
                cancelButton.addEventListener("click", () => {
                    // Ocultar el formulario de edición y mostrar el div de datos
                    divDatos.style.display = "block";
                    divEditar.style.display = "none";
                });
            } else {
                console.error("Elemento con clase 'datos' o 'editar' no encontrado dentro del padre del botón de edición");
            }
        });
    });
}

// Función para mostrar u ocultar el contenedor de post-its
function addPostIt() {
    const postItContainer = document.getElementById('postItContainer');
    const addButton = document.querySelector('.add-button');

    // Si el contenedor está oculto, lo mostramos; de lo contrario, lo ocultamos
    if (postItContainer.classList.contains('ocultar')) {
        postItContainer.classList.remove('ocultar');
        addButton.textContent = 'Ocultar Post-It';
    } else {
        postItContainer.classList.add('ocultar');
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
