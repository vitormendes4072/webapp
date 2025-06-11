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

document.getElementById('btn-voltar').addEventListener('click', function () {
  window.location.href = "/login";
});
