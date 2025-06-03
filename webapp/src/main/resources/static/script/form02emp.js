// Busca automática de endereço com o ViaCEP
document.getElementById('cep').addEventListener('blur', function () {
  const cep = this.value.replace(/\D/g, '');

  if (cep.length === 8) {
    fetch(`https://viacep.com.br/ws/${cep}/json/`)
      .then(response => response.json())
      .then(data => {
        if (!data.erro) {
          document.getElementById('logradouro').value = data.logradouro || '';
          document.getElementById('bairro').value = data.bairro || '';
          document.getElementById('cidade').value = data.localidade || '';
          document.getElementById('estado').value = data.uf || '';
        } else {
          alert('CEP não encontrado.');
          limparCamposEndereco();
        }
      })
      .catch(error => {
        console.error('Erro ao buscar CEP:', error);
        alert('Erro ao buscar CEP.');
        limparCamposEndereco();
      });
  } else {
    alert('CEP inválido. Insira 8 dígitos numéricos.');
    limparCamposEndereco();
  }
});

function limparCamposEndereco() {
  document.getElementById('logradouro').value = '';
  document.getElementById('bairro').value = '';
  document.getElementById('cidade').value = '';
  document.getElementById('estado').value = '';
}

// Redireciona para a próxima página
document.getElementById('cadastroForm').addEventListener('submit', function (event) {
  event.preventDefault();
  // Aqui você pode adicionar validação extra antes de redirecionar, se quiser
  window.location.href = "form03emp.html"; // Caminho correto dentro da pasta /index
});
function voltarPagina() {
  // Redireciona para a página anterior no histórico ou uma URL específica
  window.history.back();
  // ou, se preferir um redirecionamento fixo:
  // window.location.href = "form01.html"; // exemplo
}
