document.getElementById("cadastroForm").addEventListener("submit", function (e) {
  e.preventDefault();
  window.location.href = "form02emp.html"; // Caminho correto dentro da pasta /index
});
document.getElementById("btn-voltar").addEventListener("click", function () {
  window.location.href = "form01emp.html"; // caminho da etapa anterior
});
