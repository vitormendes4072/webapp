document.addEventListener('DOMContentLoaded', function () {
  const senha = document.getElementById('senha');
  const confirmaSenha = document.getElementById('confirmaSenha');
  const termos = document.getElementById('aceitou_termos'); // ✅ corrigido
  const btnAvancar = document.getElementById('btnAvancar');

  function validarFormulario() {
    const senhaValida = senha.value.length >= 8;
    const senhasIguais = senha.value === confirmaSenha.value;
    const termosMarcados = termos.checked;

    btnAvancar.disabled = !(senhaValida && senhasIguais && termosMarcados);
  }

  senha.addEventListener('input', validarFormulario);
  confirmaSenha.addEventListener('input', validarFormulario);
  termos.addEventListener('change', validarFormulario);
});
