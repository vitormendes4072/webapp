function somenteNumeros(event) {
  let charCode = event.which ? event.which : event.keyCode;
  if (charCode < 48 || charCode > 57) {
    event.preventDefault();
  }
}

function mascaraCPF(cpf) {
  cpf = cpf.replace(/\D/g, "");
  cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
  cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
  cpf = cpf.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
  return cpf;
}

function mascaraRG(rg) {
  rg = rg.replace(/\D/g, "");
  rg = rg.replace(/(\d{2})(\d)/, "$1.$2");
  rg = rg.replace(/(\d{3})(\d)/, "$1.$2");
  rg = rg.replace(/(\d{3})(\d)/, "$1-$2");
  return rg;
}

document.getElementById('cpf').addEventListener('keypress', somenteNumeros);
document.getElementById('rg').addEventListener('keypress', somenteNumeros);

document.getElementById('cpf').addEventListener('input', function () {
  this.value = mascaraCPF(this.value);
});

document.getElementById('rg').addEventListener('input', function () {
  this.value = mascaraRG(this.value);
});

document.getElementById("cadastroForm").addEventListener("submit", function (e) {
  e.preventDefault();
  window.location.href = "form02.html"; // Caminho correto dentro da pasta /index
});

document.getElementById("voltarBtn").addEventListener("click", function () {
  window.history.back(); // Volta para a página anterior no histórico do navegador
});
