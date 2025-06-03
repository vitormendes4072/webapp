document.getElementById('sobreVoceForm').addEventListener('submit', function (event) {
  event.preventDefault();
  alert('Informações enviadas com sucesso!');
  window.location.href = "form05emp.html";  // Redireciona para a próxima página na mesma pasta
});
