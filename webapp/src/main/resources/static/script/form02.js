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
        }
      })
      .catch(error => {
        console.error('Erro ao buscar CEP:', error);
        alert('Erro ao buscar CEP.');
      });
  } else {
    alert('CEP inválido. Insira 8 dígitos numéricos.');
  }
});

// Redireciona para a próxima página
document.getElementById('cadastroForm').addEventListener('submit', function (event) {
  event.preventDefault();
  window.location.href = "form03.html";
});

// Voltar para página anterior
function voltarPagina() {
  window.location.href = "form01.html";
}
