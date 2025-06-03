document.addEventListener("DOMContentLoaded", () => {
  const novaSenha = document.getElementById('novaSenha');
  const confirmarSenha = document.getElementById('confirmarSenha');
  const btnSalvar = document.getElementById('btnSalvar');
  const form = document.getElementById('formRedefinirSenha');

  function validarSenhas() {
    const senhaValida = novaSenha.value.trim().length >= 6;
    const iguais = novaSenha.value === confirmarSenha.value;
    btnSalvar.disabled = !(senhaValida && iguais);
  }

  novaSenha.addEventListener('input', validarSenhas);
  confirmarSenha.addEventListener('input', validarSenhas);

  form.addEventListener('submit', function (e) {
    e.preventDefault();

    // Simulação de envio para o servidor (substitua isso com fetch se quiser integração real)
    alert('Senha alterada com sucesso!');

    // Redirecionamento após sucesso
    window.location.href = 'escolhalogin.html';
  });
});
