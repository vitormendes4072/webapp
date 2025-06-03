document.addEventListener("DOMContentLoaded", () => {
  const inputCodigo = document.getElementById("codigo");
  const btnAvancar = document.getElementById("btn-avancar");
  const form = document.getElementById("form-codigo");

  // Ativa o botão quando o campo não estiver vazio
  inputCodigo.addEventListener("input", () => {
    btnAvancar.disabled = inputCodigo.value.trim() === "";
  });

  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const codigo = inputCodigo.value.trim();

    try {
      const resposta = await fetch("/verificar-codigo", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ codigo })
      });

      if (!resposta.ok) {
        throw new Error("Código inválido ou erro na verificação.");
      }

      //ATENÇÃO IGOR: Altere a URL abaixo se a rota do backend for diferente.
    // Exemplo: "/api/verificar-codigo" ou "https://meusite.com/verificar-codigo"
      const resultado = await resposta.json();

      if (resultado.sucesso) {
        // Redireciona após sucesso
        window.location.href = "rednovasenha.html";
      } else {
        alert("Código incorreto. Tente novamente.");
      }
    } catch (erro) {
      console.error(erro);
      alert("Ocorreu um erro ao verificar o código. Tente novamente.");
    }
  });
});
