document.getElementById("recuperarForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      alert("Por favor, insira um e-mail válido.");
      return;
    }


    // Simula envio do código
    alert(`Código de verificação enviado para: ${email}`);
    window.location.href = "redcodigo.html"; // ajuste conforme necessário

  });
