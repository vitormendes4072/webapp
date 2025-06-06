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

// NÃO BLOQUEIE o submit com preventDefault
// O Spring cuidará da navegação com th:action no form

// Voltar para página anterior
function voltarPagina() {
  window.history.back();
}
