// Atualiza o nome do arquivo selecionado
document.getElementById('fileInput').addEventListener('change', function (e) {
    const fileName = e.target.files.length > 0 ? e.target.files[0].name : 'Nenhum arquivo escolhido';
    document.getElementById('fileName').textContent = fileName;
  });
  
  // Redireciona para a página de perfil ao clicar em "voltar"
  document.getElementById('voltarBtn').addEventListener('click', function () {
    window.location.href = 'pag1emp.html'; // caminho correto
  });
  
  
  // Exibe mensagem de confirmação ao clicar em "enviar"
  document.getElementById('enviarBtn').addEventListener('click', function () {
    const fileInput = document.getElementById('fileInput');
    if (fileInput.files.length === 0) {
      alert('Por favor, selecione um arquivo antes de enviar.');
    } else {
      alert('Currículo enviado com sucesso!');
      // Aqui você pode adicionar lógica para envio ao servidor, se desejar
    }
  });
  