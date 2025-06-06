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
  const openJobFormBtn = document.getElementById("open-job-form-btn");
  const jobFormModal = document.getElementById("job-form-modal");
  const closeJobFormBtn = document.getElementById("close-job-form");
  const openFeedBtn = document.getElementById("open-feed-btn");
  const feedVagasModal = document.getElementById("feed-vagas-modal");
  const closeFeedBtn = document.getElementById("close-feed-btn");

  // Dados das vagas
  const vagas = [
    {
      titulo: "Suporte Técnico",
      descricao: "Atendimento ao cliente e resolução de problemas técnicos",
      localizacao: "Salvador, BA",
      salario: 2800.00,
      tipoContrato: "FREELANCER",
      nomeEmpresa: "InovaTech Sistemas S.A.",
      dataPublicacao: "2025-05-24"
    },
    {
      titulo: "Mobile Developer",
      descricao: "Desenvolvimento de aplicativos Android e iOS",
      localizacao: "Porto Alegre, RS",
      salario: 6000.00,
      tipoContrato: "CLT",
      nomeEmpresa: "InovaTech Sistemas S.A.",
      dataPublicacao: "2025-05-24"
    },
    {
      titulo: "DevOps Engineer",
      descricao: "Automação de pipelines e monitoramento de infraestrutura",
      localizacao: "Recife, PE",
      salario: 7500.00,
      tipoContrato: "PJ",
      nomeEmpresa: "InovaTech Sistemas S.A.",
      dataPublicacao: "2025-05-24"
    },
    {
      titulo: "Desenvolvedor Java",
      descricao: "Experiência com Spring Boot",
      localizacao: "São Paulo, SP",
      salario: 300.00,
      tipoContrato: "FREELANCER",
      nomeEmpresa: "InovaTech Sistemas S.A.",
      dataPublicacao: "2025-05-24"
    }
  ];

  let currentIndex = 0;
  const container = document.getElementById('card-container');

  function renderCards() {
    if (!container) {
      console.error("Elemento #card-container não encontrado.");
      return;
    }
    container.innerHTML = '';

    for (let i = 0; i < 1; i++) {
      const index = (currentIndex + i) % vagas.length;
      const vaga = vagas[index];
      const card = document.createElement('div');
      card.className = 'card';
      card.style.height = '100%'; // Garantir que o card ocupe a altura do contêiner

      const info = document.createElement('div');
      info.className = 'card-info';
      info.innerHTML = `
        <h2>${vaga.titulo}</h2>
        <p class="centered-text">${vaga.descricao}</p>
        <p class="centered-text"><strong>Empresa:</strong> ${vaga.nomeEmpresa}</p>
        <p class="left-aligned"><strong>Local:</strong> ${vaga.localizacao}</p>
        <p class="left-aligned"><strong>Contrato:</strong> ${vaga.tipoContrato}</p>
        <p class="left-aligned"><strong>Salário:</strong> R$ ${vaga.salario.toFixed(2)}</p>
        <p class="left-aligned"><strong>Publicado em:</strong> ${vaga.dataPublicacao.slice(0, 10)}</p>
      `;

      const instructions = document.createElement('div');
      instructions.className = 'card-instructions';
      instructions.innerHTML = `
        <p class="discard">Arraste para descartar</p>
        <p class="accept">Arraste para aceitar</p>
      `;

      card.appendChild(info);
      card.appendChild(instructions);

      let offsetX = 0;
      let isDragging = false;

      card.addEventListener('mousedown', (e) => {
        isDragging = true;
        offsetX = e.clientX;
        card.style.transition = 'none';
      });

      card.addEventListener('touchstart', (e) => {
        isDragging = true;
        offsetX = e.touches[0].clientX;
        card.style.transition = 'none';
      });

      document.addEventListener('mouseup', (e) => {
        if (!isDragging) return;
        const diff = e.clientX - offsetX;

        card.style.transition = 'transform 0.3s ease, opacity 0.3s ease';

        if (Math.abs(diff) > 100) {
          card.style.transform = `translateX(${diff > 0 ? '500px' : '-500px'}) rotate(${diff > 0 ? 30 : -30}deg)`;
          card.style.opacity = '0';
          setTimeout(() => {
            currentIndex = (currentIndex + 1) % vagas.length;
            renderCards();
          }, 300);
        } else {
          card.style.transform = 'translateX(0px) rotate(0deg)';
          card.style.opacity = '1';
        }

        isDragging = false;
      });

      document.addEventListener('touchend', (e) => {
        if (!isDragging) return;
        const diff = (e.changedTouches[0].clientX || e.clientX) - offsetX;

        card.style.transition = 'transform 0.3s ease, opacity 0.3s ease';

        if (Math.abs(diff) > 100) {
          card.style.transform = `translateX(${diff > 0 ? '500px' : '-500px'}) rotate(${diff > 0 ? 30 : -30}deg)`;
          card.style.opacity = '0';
          setTimeout(() => {
            currentIndex = (currentIndex + 1) % vagas.length;
            renderCards();
          }, 300);
        } else {
          card.style.transform = 'translateX(0px) rotate(0deg)';
          card.style.opacity = '1';
        }

        isDragging = false;
      });

      document.addEventListener('mousemove', (e) => {
        if (!isDragging) return;
        const diff = e.clientX - offsetX;
        card.style.transform = `translateX(${diff}px) rotate(${diff / 10}deg)`;
      });

      document.addEventListener('touchmove', (e) => {
        if (!isDragging) return;
        const diff = e.touches[0].clientX - offsetX;
        card.style.transform = `translateX(${diff}px) rotate(${diff / 10}deg)`;
      });

      container.appendChild(card);
    }
  }

  // Mostrar feed de vagas
  openFeedBtn.addEventListener("click", function (e) {
    e.preventDefault();
    feedVagasModal.style.display = "flex";
    renderCards();
  });

  // Fechar feed de vagas
  closeFeedBtn.addEventListener("click", function () {
    feedVagasModal.style.display = "none";
    container.innerHTML = '';
  });

  // Alternar tema
  toggleThemeBtn.addEventListener("click", function () {
    document.body.classList.toggle("dark-theme");
    const icon = toggleThemeBtn.querySelector("i");
    icon.classList.toggle("fa-moon");
    icon.classList.toggle("fa-sun");
  });

  // Mostrar formulário de dados da empresa
  openFormBtn.addEventListener("click", function () {
    multiStepForm.style.display = "flex";
  });

  // Avançar etapas do formulário de dados da empresa
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

  // Voltar etapas do formulário de dados da empresa
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

  // Envio do formulário de dados da empresa
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

  // Mostrar formulário de vaga
  openJobFormBtn.addEventListener("click", function () {
    jobFormModal.style.display = "flex";
  });

  // Fechar formulário de vaga
  closeJobFormBtn.addEventListener("click", function () {
    jobFormModal.style.display = "none";
    document.getElementById("jobForm").reset();
  });

  // Envio do formulário de vaga
  document.getElementById("jobForm").addEventListener("submit", function(event) {
    event.preventDefault();
    const formData = {
      companyName: document.getElementById("companyName").value,
      description: document.getElementById("description").value,
      location: document.getElementById("location").value,
      contract: document.getElementById("contract").value,
      salary: document.getElementById("salary").value,
      publicationDate: document.getElementById("publicationDate").value
    };
    console.log("Vaga salva:", formData);
    alert("Vaga salva e publicada com sucesso!");
    jobFormModal.style.display = "none";
    this.reset();
  });

  function limparCamposEndereco() {
    document.getElementById("logradouro").value = "";
    document.getElementById("bairro").value = "";
    document.getElementById("cidade").value = "";
    document.getElementById("estado").value = "";
  }
});