document.getElementById('dadosBancariosForm').addEventListener('submit', function (event) {
  event.preventDefault();
  // Redireciona para form04emp.html ao concluir
  window.location.href = "form04emp.html";
});
document.querySelector('.btn-voltar').addEventListener('click', function () {
  // Redireciona para a página anterior
  window.location.href = "form02emp.html";
});
