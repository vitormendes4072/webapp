document.addEventListener("DOMContentLoaded", function () {
  const openFormBtn = document.getElementById("open-form-btn");
  const multiStepForm = document.getElementById("multi-step-form");
  const formSteps = document.querySelectorAll(".form-step");
  const nextBtns = document.querySelectorAll(".next-btn");
  const prevBtns = document.querySelectorAll(".prev-btn");
  const submitBtn = document.querySelector(".submit-btn");
  const toggleThemeBtn = document.getElementById("toggle-theme");
  const btnCambiarFundo = document.getElementById("btnCambiarFundo");
  const inputFundo = document.getElementById("input-fundo");
  const avatarImg = document.getElementById("avatar-img");
  const inputAvatar = document.getElementById("input-avatar");
  const cepInput = document.getElementById("cep");

  // Alternar tema
  toggleThemeBtn.addEventListener("click", function () {
    document.body.classList.toggle("dark-theme");
    const icon = toggleThemeBtn.querySelector("i");
    icon.classList.toggle("fa-moon");
    icon.classList.toggle("fa-sun");
  });

  // Mostrar formulário
  openFormBtn.addEventListener("click", function () {
    multiStepForm.style.display = "flex";
  });

  // Avançar etapas
  nextBtns.forEach((button) => {
    button.addEventListener("click", () => {
      const currentStep = document.querySelector(".form-step.active");
      const nextStep = currentStep.nextElementSibling;
      if (nextStep) {
        currentStep.classList.remove("active");
        nextStep.classList.add("active");
      }
    });
  });

  // Voltar etapas
  prevBtns.forEach((button) => {
    button.addEventListener("click", () => {
      const currentStep = document.querySelector(".form-step.active");
      const prevStep = currentStep.previousElementSibling;
      if (prevStep) {
        currentStep.classList.remove("active");
        prevStep.classList.add("active");
      }
    });
  });

  // Envio do formulário
  submitBtn.addEventListener("click", () => {
    alert("Formulário enviado com sucesso!");
    multiStepForm.style.display = "none";
    formSteps.forEach((step) => step.classList.remove("active"));
    formSteps[0].classList.add("active");
  });

  // Alterar imagem de fundo
  btnCambiarFundo.addEventListener("click", function () {
    inputFundo.click();
  });

  inputFundo.addEventListener("change", function (e) {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = function (event) {
        const portada = document.querySelector(".perfil-usuario-portada");
        portada.style.backgroundImage = `url(${event.target.result})`;
      };
      reader.readAsDataURL(file);
    }
  });

  // Alterar avatar
  inputAvatar.addEventListener("change", function (e) {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = function (event) {
        avatarImg.src = event.target.result;
      };
      reader.readAsDataURL(file);
    }
  });

  // Buscar dados do CEP
  cepInput.addEventListener("blur", function () {
    const cep = this.value.replace(/\D/g, "");
    if (cep.length === 8) {
      fetch(`https://viacep.com.br/ws/${cep}/json/`)
        .then(response => response.json())
        .then(data => {
          if (!data.erro) {
            document.getElementById("logradouro").value = data.logradouro || "";
            document.getElementById("bairro").value = data.bairro || "";
            document.getElementById("cidade").value = data.localidade || "";
            document.getElementById("estado").value = data.uf || "";
          } else {
            alert("CEP não encontrado.");
            limparCamposEndereco();
          }
        })
        .catch(error => {
          console.error("Erro ao buscar o CEP:", error);
          alert("Erro ao consultar o CEP.");
          limparCamposEndereco();
        });
    } else {
      alert("CEP inválido. Digite 8 números.");
      limparCamposEndereco();
    }
  });

  function limparCamposEndereco() {
    document.getElementById("logradouro").value = "";
    document.getElementById("bairro").value = "";
    document.getElementById("cidade").value = "";
    document.getElementById("estado").value = "";
  }
});
