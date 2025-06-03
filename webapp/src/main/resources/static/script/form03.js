document.getElementById('dadosBancariosForm').addEventListener('submit', function (event) {
  event.preventDefault();
  // Redireciona para form04.html ao concluir
  window.location.href = "form04.html";
});
document.getElementById('dadosBancariosForm').addEventListener('submit', function (event) {
  event.preventDefault();
  // Redireciona para próxima etapa
  window.location.href = "form04.html";
});

document.getElementById('btn-voltar').addEventListener('click', function () {
  // Redireciona para a etapa anterior
  window.location.href = "form02.html";
});
