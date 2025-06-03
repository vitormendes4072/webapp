document.getElementById("recuperarForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const email = document.getElementById("email").value;

    if (!email) {
      alert("Por favor, preencha o e-mail.");
      return;
    }

    // Simula envio do código
    alert(`Código de verificação enviado para: ${email}`);
    // Aqui você pode redirecionar ou prosseguir com lógica real
  });
