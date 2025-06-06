document.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.getElementById('loginForm');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const emailError = document.getElementById('emailError');
    const passwordError = document.getElementById('passwordError');
    const emailGroup = document.getElementById('emailGroup');
    const passwordGroup = document.getElementById('passwordGroup');

    const switchToggle = document.getElementById('switch');

    // Redireciona para login de cooperado ao desativar o botão
    const switchLabel = document.querySelector('label[for="switch"]');

    switchLabel.addEventListener('click', function () {
        setTimeout(() => {
            if (!switchToggle.checked) {
                window.location.href = 'login.html';
            }
        }, 100);
    });


    loginForm.addEventListener('submit', function (event) {
        let isValid = true;

        emailError.style.display = 'none';
        emailGroup.classList.remove('error');
        passwordError.style.display = 'none';
        passwordGroup.classList.remove('error');

        if (emailInput.value.trim() === '') {
            emailError.textContent = 'Por favor, insira seu email.';
            emailError.style.display = 'block';
            emailGroup.classList.add('error');
            isValid = false;
        } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(emailInput.value)) {
            emailError.textContent = 'Formato de email inválido.';
            emailError.style.display = 'block';
            emailGroup.classList.add('error');
            isValid = false;
        }

        if (passwordInput.value.trim() === '') {
            passwordError.textContent = 'Por favor, insira sua senha.';
            passwordError.style.display = 'block';
            passwordGroup.classList.add('error');
            isValid = false;
        }

        if (!isValid) {
            event.preventDefault(); // impede o envio só se houver erro
        }

    });
});
