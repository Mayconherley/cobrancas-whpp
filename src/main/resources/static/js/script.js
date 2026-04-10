const email = "admin@teste.com";
const senha = "123456";

const auth = "Basic " + btoa(email + ":" + senha);

async function salvarOuAtualizarLoja() {
    const lojaId = document.getElementById("lojaId").value;

    const loja = {
        nome: document.getElementById("nome").value,
        nomeResponsavel: document.getElementById("nomeResponsavel").value,
        telefoneWhatsapp: document.getElementById("telefoneWhatsapp").value,
        email: document.getElementById("email").value,
        ativo: document.getElementById("ativo").checked,
        receberResumoWhatsapp: document.getElementById("receberResumoWhatsapp").checked
    };

    if (!loja.nome || !loja.nomeResponsavel || !loja.telefoneWhatsapp) {
        alert("Preencha nome, responsável e telefone.");
        return;
    }

    let response;

    if (lojaId) {
        response = await fetch(`${BASE_URL}/lojas/${lojaId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": AUTH
            },
            body: JSON.stringify(loja)
        });
    } else {
        response = await fetch(`${BASE_URL}/lojas`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": AUTH
            },
            body: JSON.stringify(loja)
        });
    }

    if (!response.ok) {
        const textoErro = await response.text();
        alert("Erro ao salvar loja: " + textoErro);
        return;
    }

    alert(lojaId ? "Loja atualizada com sucesso!" : "Loja cadastrada com sucesso!");
    limparFormulario();
    listarLojas();
}

async function listarLojas() {
    const response = await fetch(`${BASE_URL}/lojas`, {
        headers: {
            "Authorization": AUTH
        }
    });

    if (!response.ok) {
        document.getElementById("lista-lojas").innerHTML =
            `<p>Erro ao listar lojas. Status: ${response.status}</p>`;
        return;
    }

    const lojas = await response.json();
    const container = document.getElementById("lista-lojas");
    container.innerHTML = "";

    lojas.forEach(loja => {
        const div = document.createElement("div");
        div.className = "loja-card";

        div.innerHTML = `
            <strong>${loja.nome}</strong><br>
            Responsável: ${loja.nomeResponsavel}<br>
            Telefone: ${loja.telefoneWhatsapp}<br>
            E-mail: ${loja.email ?? "-"}<br>
            Ativa: ${loja.ativo ? "Sim" : "Não"}<br>
            Recebe resumo: ${loja.receberResumoWhatsapp ? "Sim" : "Não"}<br>

            <div class="acoes">
                <button onclick='editarLoja(${JSON.stringify(loja)})'>✏️ Editar</button>
                <button onclick="deletarLoja(${loja.id})">❌ Deletar</button>
            </div>
        `;

        container.appendChild(div);
    });
}

function editarLoja(loja) {
    document.getElementById("lojaId").value = loja.id;
    document.getElementById("nome").value = loja.nome ?? "";
    document.getElementById("nomeResponsavel").value = loja.nomeResponsavel ?? "";
    document.getElementById("telefoneWhatsapp").value = loja.telefoneWhatsapp ?? "";
    document.getElementById("email").value = loja.email ?? "";
    document.getElementById("ativo").checked = loja.ativo ?? true;
    document.getElementById("receberResumoWhatsapp").checked = loja.receberResumoWhatsapp ?? true;

    window.scrollTo({ top: 0, behavior: "smooth" });
}

async function deletarLoja(id) {
    const confirmar = confirm("Tem certeza que deseja deletar esta loja?");
    if (!confirmar) return;

    const response = await fetch(`${BASE_URL}/lojas/${id}`, {
        method: "DELETE",
        headers: {
            "Authorization": AUTH
        }
    });

    if (!response.ok) {
        const textoErro = await response.text();
        alert("Erro ao deletar loja: " + textoErro);
        return;
    }

    alert("Loja deletada com sucesso!");
    listarLojas();
}

function cancelarEdicao() {
    limparFormulario();
}

function limparFormulario() {
    document.getElementById("lojaId").value = "";
    document.getElementById("nome").value = "";
    document.getElementById("nomeResponsavel").value = "";
    document.getElementById("telefoneWhatsapp").value = "";
    document.getElementById("email").value = "";
    document.getElementById("ativo").checked = true;
    document.getElementById("receberResumoWhatsapp").checked = true;
}

listarLojas();