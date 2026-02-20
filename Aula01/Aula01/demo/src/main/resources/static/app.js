const API_URL = '/api/usuarios';

let usuarioEmEdicao = null;
let usuarioParaExcluir = null;
let todosUsuarios = [];

document.addEventListener('DOMContentLoaded', function() {
    configurarFormulario();
    configurarBotaoCancelar();
});

function abrirAba(nomeAba) {
    document.querySelectorAll('.tab-button').forEach(btn => {
        btn.classList.remove('active');
    });
    document.querySelectorAll('.tab-panel').forEach(panel => {
        panel.classList.remove('active');
    });

    event.target.classList.add('active');
    document.getElementById('tab-' + nomeAba).classList.add('active');

    if (nomeAba === 'pesquisa') {
        carregarUsuarios();
    }
}

function configurarFormulario() {
    const form = document.getElementById('usuario-form');
    form.addEventListener('submit', async function(e) {
        e.preventDefault();
        await salvarUsuario();
    });
}

function configurarBotaoCancelar() {
    const btnCancelar = document.getElementById('btn-cancelar');
    btnCancelar.style.display = 'none';
}

async function carregarUsuarios() {
    mostrarLoading(true);
    ocultarMensagem();

    try {
        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error(`Erro ao carregar usuários: ${response.status}`);
        }

        const data = await response.json();
        todosUsuarios = data.usuarios || [];

        exibirUsuarios(todosUsuarios);

    } catch (error) {
        console.error('Erro ao carregar usuários:', error);
        mostrarMensagem('Erro ao carregar lista de usuários. Tente novamente.', 'erro');
    } finally {
        mostrarLoading(false);
    }
}

function filtrarUsuarios() {
    const searchTerm = document.getElementById('search-input').value.toLowerCase();

    if (searchTerm === '') {
        exibirUsuarios(todosUsuarios);
        return;
    }

    const usuariosFiltrados = todosUsuarios.filter(usuario => {
        return usuario.nome.toLowerCase().includes(searchTerm) ||
               usuario.email.toLowerCase().includes(searchTerm) ||
               usuario.id.toString().includes(searchTerm);
    });

    exibirUsuarios(usuariosFiltrados);
}

function exibirUsuarios(usuarios) {
    const tbody = document.getElementById('usuarios-tbody');
    const table = document.getElementById('usuarios-table');
    const listaVazia = document.getElementById('lista-vazia');
    const totalEncontrados = document.getElementById('total-encontrados');

    tbody.innerHTML = '';
    totalEncontrados.textContent = `${usuarios.length} usuário(s) encontrado(s)`;

    if (usuarios.length === 0) {
        table.classList.add('oculto');
        listaVazia.classList.remove('oculto');
        return;
    }

    table.classList.remove('oculto');
    listaVazia.classList.add('oculto');

    usuarios.forEach(usuario => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td><strong>#${usuario.id}</strong></td>
            <td>${escapeHtml(usuario.nome)}</td>
            <td>${escapeHtml(usuario.email)}</td>
            <td>
                <button class="btn btn-warning btn-sm" onclick="editarUsuario(${usuario.id})">
                    Editar
                </button>
                <button class="btn btn-danger btn-sm" onclick="confirmarExclusao(${usuario.id}, '${escapeHtml(usuario.nome)}')">
                    Excluir
                </button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

async function salvarUsuario() {
    const nome = document.getElementById('nome').value.trim();
    const email = document.getElementById('email').value.trim();

    if (!validarNome(nome)) return;
    if (!validarEmail(email)) return;

    const usuario = { nome, email };

    try {
        let response;

        if (usuarioEmEdicao) {
            response = await fetch(`${API_URL}/${usuarioEmEdicao.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(usuario)
            });
        } else {
            response = await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(usuario)
            });
        }

        if (!response.ok) {
            const errorData = await response.json();
            const mensagemErro = errorData.mensagem || 'Erro ao salvar usuário';
            mostrarMensagem(mensagemErro, 'erro');
            throw new Error(mensagemErro);
        }

        const data = await response.json();
        const mensagemSucesso = data.mensagem || 'Usuário salvo com sucesso!';
        mostrarMensagem(mensagemSucesso, 'sucesso');

        limparFormulario();
        if (document.getElementById('tab-pesquisa').classList.contains('active')) {
            await carregarUsuarios();
        }

    } catch (error) {
        console.error('Erro ao salvar usuário:', error);
        mostrarMensagem(error.message || 'Erro ao salvar usuário. Verifique os dados e tente novamente.', 'erro');
    }
}

async function editarUsuario(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);

        if (!response.ok) {
            throw new Error('Usuário não encontrado');
        }

        const data = await response.json();
        const usuario = data.usuario;

        usuarioEmEdicao = usuario;

        document.getElementById('edit-nome').value = usuario.nome;
        document.getElementById('edit-email').value = usuario.email;

        document.getElementById('edit-nome-error').textContent = '';
        document.getElementById('edit-email-error').textContent = '';
        document.getElementById('edit-nome').classList.remove('error');
        document.getElementById('edit-email').classList.remove('error');

        document.getElementById('modal-edicao').classList.remove('oculto');

    } catch (error) {
        console.error('Erro ao carregar usuário:', error);
        mostrarMensagem('Erro ao carregar dados do usuário.', 'erro');
    }
}

function fecharModalEdicao() {
    document.getElementById('modal-edicao').classList.add('oculto');
    document.getElementById('form-edicao').reset();
    usuarioEmEdicao = null;
}

document.addEventListener('DOMContentLoaded', function() {
    const formEdicao = document.getElementById('form-edicao');
    if (formEdicao) {
        formEdicao.addEventListener('submit', async function(e) {
            e.preventDefault();
            await salvarEdicao();
        });
    }
});

async function salvarEdicao() {
    const nome = document.getElementById('edit-nome').value.trim();
    const email = document.getElementById('edit-email').value.trim();

    if (!validarNomeModal(nome)) return;
    if (!validarEmailModal(email)) return;

    const usuario = { nome, email };

    try {
        const response = await fetch(`${API_URL}/${usuarioEmEdicao.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(usuario)
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.mensagem || 'Erro ao atualizar usuário');
        }

        const data = await response.json();
        mostrarMensagem(data.mensagem || 'Usuário atualizado com sucesso!', 'sucesso');

        fecharModalEdicao();

        if (document.getElementById('tab-pesquisa').classList.contains('active')) {
            await carregarUsuarios();
        }

    } catch (error) {
        console.error('Erro ao atualizar usuário:', error);
        mostrarMensagem(error.message || 'Erro ao atualizar usuário.', 'erro');
    }
}

function validarNomeModal(nome) {
    const nomeError = document.getElementById('edit-nome-error');
    const nomeInput = document.getElementById('edit-nome');

    if (nome.length < 3) {
        nomeError.textContent = 'O nome deve ter no mínimo 3 caracteres.';
        nomeInput.classList.add('error');
        return false;
    }

    nomeError.textContent = '';
    nomeInput.classList.remove('error');
    return true;
}

function validarEmailModal(email) {
    const emailError = document.getElementById('edit-email-error');
    const emailInput = document.getElementById('edit-email');
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailRegex.test(email)) {
        emailError.textContent = 'Por favor, insira um email válido.';
        emailInput.classList.add('error');
        return false;
    }

    emailError.textContent = '';
    emailInput.classList.remove('error');
    return true;
}

function confirmarExclusao(id, nome) {
    usuarioParaExcluir = id;
    document.getElementById('modal-mensagem').textContent = `Tem certeza que deseja excluir o usuário \"${nome}\"? Esta ação não pode ser desfeita.`;

    const modal = document.getElementById('modal-confirmacao');
    modal.classList.remove('oculto');

    document.getElementById('btn-confirmar-exclusao').onclick = () => excluirUsuario(id);
}

async function excluirUsuario(id) {
    fecharModal();
    mostrarLoading(true);

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.mensagem || 'Erro ao excluir usuário');
        }

        const data = await response.json();
        mostrarMensagem(data.mensagem || 'Usuário excluído com sucesso!', 'sucesso');

        await carregarUsuarios();

    } catch (error) {
        console.error('Erro ao excluir usuário:', error);
        mostrarMensagem(error.message || 'Erro ao excluir usuário.', 'erro');
    } finally {
        mostrarLoading(false);
        usuarioParaExcluir = null;
    }
}

function cancelarEdicao() {
    limparFormulario();
}

function limparFormulario() {
    document.getElementById('usuario-form').reset();
    usuarioEmEdicao = null;
    document.getElementById('form-titulo').textContent = 'Cadastrar Novo Usuário';
    document.getElementById('btn-submit').textContent = 'Cadastrar';
    document.getElementById('btn-cancelar').style.display = 'none';
    limparErros();
}

function cancelarEdicao() {
    limparFormulario();
}

function validarNome(nome) {
    const nomeError = document.getElementById('nome-error');
    const nomeInput = document.getElementById('nome');

    if (nome.length < 3) {
        nomeError.textContent = 'O nome deve ter no mínimo 3 caracteres.';
        nomeInput.classList.add('error');
        return false;
    }

    nomeError.textContent = '';
    nomeInput.classList.remove('error');
    return true;
}

function validarEmail(email) {
    const emailError = document.getElementById('email-error');
    const emailInput = document.getElementById('email');
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailRegex.test(email)) {
        emailError.textContent = 'Por favor, insira um email válido.';
        emailInput.classList.add('error');
        return false;
    }

    emailError.textContent = '';
    emailInput.classList.remove('error');
    return true;
}

function limparErros() {
    document.getElementById('nome-error').textContent = '';
    document.getElementById('email-error').textContent = '';
    document.getElementById('nome').classList.remove('error');
    document.getElementById('email').classList.remove('error');
}

function mostrarLoading(mostrar) {
    const loading = document.getElementById('loading');
    if (mostrar) {
        loading.classList.remove('oculto');
    } else {
        loading.classList.add('oculto');
    }
}

function mostrarMensagem(texto, tipo = 'info') {
    const mensagem = document.getElementById('mensagem');
    mensagem.textContent = texto;
    mensagem.className = `mensagem ${tipo}`;
    mensagem.classList.remove('oculto');

    setTimeout(() => {
        ocultarMensagem();
    }, 5000);
}

function ocultarMensagem() {
    const mensagem = document.getElementById('mensagem');
    mensagem.classList.add('oculto');
}

function fecharModal() {
    const modal = document.getElementById('modal-confirmacao');
    if (modal) {
        modal.classList.add('oculto');
    }
    usuarioParaExcluir = null;
}

function escapeHtml(text) {
    const map = {
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#039;'
    };
    return text.replace(/[&<>"']/g, m => map[m]);
}

document.addEventListener('click', function(e) {
    const modalConfirmacao = document.getElementById('modal-confirmacao');
    const modalEdicao = document.getElementById('modal-edicao');

    if (modalConfirmacao && e.target === modalConfirmacao) {
        fecharModal();
    }
    if (modalEdicao && e.target === modalEdicao) {
        fecharModalEdicao();
    }
});

document.addEventListener('keydown', function(e) {
    if (e.key === 'Escape') {
        fecharModal();
        fecharModalEdicao();
    }
});

function limparErros() {
    document.getElementById('nome-error').textContent = '';
    document.getElementById('email-error').textContent = '';
    document.getElementById('nome').classList.remove('error');
    document.getElementById('email').classList.remove('error');
}

// Mostrar/ocultar loading
function mostrarLoading(mostrar) {
    const loading = document.getElementById('loading');
    if (mostrar) {
        loading.classList.remove('oculto');
    } else {
        loading.classList.add('oculto');
    }
}

// Mostrar mensagem
function mostrarMensagem(texto, tipo = 'info') {
    // Verificar qual aba está ativa
    const abaCadastroAtiva = document.getElementById('tab-cadastro').classList.contains('active');
    const abaPesquisaAtiva = document.getElementById('tab-pesquisa').classList.contains('active');
    
    let mensagem;
    if (abaCadastroAtiva) {
        mensagem = document.getElementById('mensagem-cadastro');
    } else if (abaPesquisaAtiva) {
        mensagem = document.getElementById('mensagem');
    } else {
        // Fallback para mensagem de cadastro
        mensagem = document.getElementById('mensagem-cadastro');
    }
    
    if (mensagem) {
        // Adicionar ícone baseado no tipo
        let icone = '';
        if (tipo === 'sucesso') {
            icone = '✅ '; // check mark
        } else if (tipo === 'erro') {
            icone = '❌ '; // cross mark
        } else if (tipo === 'info') {
            icone = 'ℹ️ '; // info
        }
        
        mensagem.innerHTML = icone + texto;
        mensagem.className = `mensagem ${tipo}`;
        mensagem.classList.remove('oculto');
        
        // Scroll suave para a mensagem
        mensagem.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
        
        // Auto-ocultar após 6 segundos
        setTimeout(() => {
            ocultarMensagem();
        }, 6000);
    }
}

// Ocultar mensagem
function ocultarMensagem() {
    const mensagemCadastro = document.getElementById('mensagem-cadastro');
    const mensagemPesquisa = document.getElementById('mensagem');
    
    if (mensagemCadastro) {
        mensagemCadastro.classList.add('oculto');
    }
    if (mensagemPesquisa) {
        mensagemPesquisa.classList.add('oculto');
    }
}

// Fechar modal
function fecharModal() {
    const modal = document.getElementById('modal-confirmacao');
    if (modal) {
        modal.classList.add('oculto');
    }
    usuarioParaExcluir = null;
}

// Escape HTML para prevenir XSS
function escapeHtml(text) {
    const map = {
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#039;'
    };
    return text.replace(/[&<>"']/g, m => map[m]);
}

// Fechar modal ao clicar fora
document.addEventListener('click', function(e) {
    const modalConfirmacao = document.getElementById('modal-confirmacao');
    const modalEdicao = document.getElementById('modal-edicao');
    
    if (modalConfirmacao && e.target === modalConfirmacao) {
        fecharModal();
    }
    if (modalEdicao && e.target === modalEdicao) {
        fecharModalEdicao();
    }
});

// Tecla ESC fecha os modais
document.addEventListener('keydown', function(e) {
    if (e.key === 'Escape') {
        fecharModal();
        fecharModalEdicao();
    }
});


