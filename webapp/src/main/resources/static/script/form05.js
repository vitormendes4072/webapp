document.addEventListener('DOMContentLoaded', function () {
  const senha = document.getElementById('senha');
  const confirmaSenha = document.getElementById('confirmaSenha');
  const termos = document.getElementById('termos');
  const btnAvancar = document.getElementById('btnAvancar');
  const form = document.getElementById('signupForm');

  function validarFormulario() {
    const senhaValida = senha.value.length >= 8;
    const senhasIguais = senha.value === confirmaSenha.value;
    const termosMarcados = termos.checked;

    btnAvancar.disabled = !(senhaValida && senhasIguais && termosMarcados);
  }

  senha.addEventListener('input', validarFormulario);
  confirmaSenha.addEventListener('input', validarFormulario);
  termos.addEventListener('change', validarFormulario);

  form.addEventListener('submit', function(e) {
    e.preventDefault();

    if (senha.value !== confirmaSenha.value) {
      alert('As senhas não coincidem.');
      return;
    }

    alert('Cadastro concluído com sucesso!');
    window.location.href = "login.html"; // ajustado para a mesma pasta
  });
});
