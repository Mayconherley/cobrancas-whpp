const email = "admin@teste.com";
const senha = "123456";
const auth = "Basic " + btoa(email + ":" + senha);

const API_CLIENTES = "/clientes";
const API_LOJAS = "/lojas";

let clientes = [];
let editandoId = null;

window.onload = () => {
    carregarLojas();
    carregarClientes();
};

function carregarLojas() {
    fetch(API_LOJAS, {
        headers: {
            "Authorization": auth
        }
    })
    .then(res => res.json())
    .then(data => {
        const select = document.getElementById("lojaId");
        select.innerHTML = '<option value="">Selecione uma loja</option>';

        data.forEach(loja => {
            const option = document.createElement("option");
            option.value = loja.id;
            option.textContent = loja.nome;
            select.appendChild(option);
        });
    });
}

function carregarClientes() {
    fetch(API_CLIENTES, {
        headers: {
            "Authorization": auth
        }
    })
    .then(res => res.json())
    .then(data => {
        clientes = data;
        renderClientes();
    })
    .catch(err => console.error("Erro ao carregar clientes:", err));
}

function renderClientes() {
    const div = document.getElementById("lista-clientes");
    div.innerHTML = "";

    clientes.forEach(c => {
        const item = document.createElement("div");

        item.innerHTML = `
            <strong>${c.nome}</strong><br>
            CPF: ${c.cpf}<br>
            Telefone: ${c.telefone}<br>
            Loja: ${c.nomeLoja}<br>
            Recebe WhatsApp: ${c.receberNotificacaoWhatsapp ? "Sim" : "Não"}<br><br>

            <button type="button" onclick="editar(${c.id})">Editar</button>
            <button type="button" onclick="deletar(${c.id})">Excluir</button>
            <hr>
        `;

        div.appendChild(item);
    });
}

function salvarCliente() {
    const cliente = {
        nome: document.getElementById("nome").value,
        cpf: document.getElementById("cpf").value,
        telefone: document.getElementById("telefone").value,
        receberNotificacaoWhatsapp: document.getElementById("receberNotificacaoWhatsapp").checked,
        lojaId: Number(document.getElementById("lojaId").value)
    };

    const metodo = editandoId ? "PUT" : "POST";
    const url = editandoId ? `${API_CLIENTES}/${editandoId}` : API_CLIENTES;

    fetch(url, {
        method: metodo,
        headers: {
            "Content-Type": "application/json",
            "Authorization": auth
        },
        body: JSON.stringify(cliente)
    })
    .then(() => {
        limparFormulario();
        carregarClientes();
    })
    .catch(err => console.error("Erro ao salvar cliente:", err));
}

function editar(id) {
    const c = clientes.find(cliente => cliente.id === id);

    document.getElementById("nome").value = c.nome;
    document.getElementById("cpf").value = c.cpf;
    document.getElementById("telefone").value = c.telefone;
    document.getElementById("receberNotificacaoWhatsapp").checked = c.receberNotificacaoWhatsapp;
    document.getElementById("lojaId").value = c.lojaId;

    editandoId = id;
}

function deletar(id) {
    fetch(`${API_CLIENTES}/${id}`, {
        method: "DELETE",
        headers: {
            "Authorization": auth
        }
    })
    .then(() => carregarClientes())
    .catch(err => console.error("Erro ao deletar cliente:", err));
}

function cancelarEdicao() {
    limparFormulario();
}

function limparFormulario() {
    document.getElementById("clienteId").value = "";
    document.getElementById("nome").value = "";
    document.getElementById("cpf").value = "";
    document.getElementById("telefone").value = "";
    document.getElementById("receberNotificacaoWhatsapp").checked = true;
    document.getElementById("lojaId").value = "";
    editandoId = null;
}