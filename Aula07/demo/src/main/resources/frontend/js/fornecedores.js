let fornecedores = [];
let editingId = null;

window.addEventListener('DOMContentLoaded', loadFornecedores);

async function loadFornecedores() {
    showLoading();
    try {
        fornecedores = await fornecedorAPI.getAll();
        renderFornecedores();
    } catch (error) {
        showAlert('Erro ao carregar fornecedores: ' + error.message, 'danger');
    } finally {
        hideLoading();
    }
}

function renderFornecedores(fornecedoresToRender = fornecedores) {
    const tbody = document.getElementById('fornecedoresTableBody');
    
    if (fornecedoresToRender.length === 0) {
        tbody.innerHTML = `
            <tr>
                <td colspan="7" class="text-center py-5">
                    <i class="bi bi-inbox" style="font-size: 3rem; color: #ccc;"></i>
                    <p class="mt-3 text-muted">Nenhum fornecedor encontrado</p>
                </td>
            </tr>
        `;
        return;
    }
    
    tbody.innerHTML = fornecedoresToRender.map(fornecedor => `
        <tr>
            <td>${fornecedor.id}</td>
            <td><strong>${fornecedor.nome}</strong></td>
            <td>${fornecedor.cnpj || '-'}</td>
            <td>${fornecedor.email || '-'}</td>
            <td>${fornecedor.telefone || '-'}</td>
            <td>${fornecedor.enderecoCompleto || fornecedor.endereco || '-'}</td>
            <td class="text-center">
                <button class="btn btn-sm" style="background-color: #86BC25; color: white; border: none;" onclick="editFornecedor(${fornecedor.id})" title="Editar">
                    <i class="bi bi-pencil"></i>
                </button>
                <button class="btn btn-sm" style="background-color: #86BC25; color: white; border: none;" onclick="deleteFornecedor(${fornecedor.id})" title="Excluir">
                    <i class="bi bi-trash"></i>
                </button>
            </td>
        </tr>
    `).join('');
}

function openCreateModal() {
    editingId = null;
    document.getElementById('modalTitle').textContent = 'Novo Fornecedor';
    document.getElementById('fornecedorForm').reset();
}

async function editFornecedor(id) {
    editingId = id;
    showLoading();
    
    try {
        const fornecedor = await fornecedorAPI.getById(id);
        console.log('Carregando fornecedor para edição:', fornecedor);
        
        document.getElementById('modalTitle').textContent = 'Editar Fornecedor';
        document.getElementById('fornecedorId').value = fornecedor.id;
        document.getElementById('nome').value = fornecedor.nome || '';
        document.getElementById('cnpj').value = fornecedor.cnpj || '';
        document.getElementById('cpf').value = fornecedor.cpf || '';
        document.getElementById('email').value = fornecedor.email || '';
        document.getElementById('telefone').value = fornecedor.telefone || '';
        document.getElementById('endereco').value = fornecedor.endereco || (fornecedor.enderecoCompleto ? fornecedor.enderecoCompleto.split(' - ')[0] : '');
        
        console.log('EditingId setado para:', editingId);
        
        const modal = new bootstrap.Modal(document.getElementById('fornecedorModal'));
        modal.show();
    } catch (error) {
        console.error('Erro ao carregar fornecedor:', error);
        showAlert('Erro ao carregar fornecedor: ' + error.message, 'danger');
    } finally {
        hideLoading();
    }
}

async function saveFornecedor() {
    const form = document.getElementById('fornecedorForm');
    
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    
    const data = {
        nome: document.getElementById('nome').value.trim(),
        cnpj: document.getElementById('cnpj').value.trim(),
        cpf: document.getElementById('cpf').value.trim()
    };
    
    const email = document.getElementById('email').value.trim();
    if (email) data.email = email;
    
    const telefone = document.getElementById('telefone').value.trim();
    if (telefone) data.telefone = telefone;
    
    const endereco = document.getElementById('endereco').value.trim();
    if (endereco) data.endereco = endereco;
    
    console.log('Salvando fornecedor:', editingId ? 'EDITAR ID ' + editingId : 'CRIAR NOVO');
    console.log('Dados a enviar:', data);
    
    showLoading();
    
    try {
        if (editingId) {
            await fornecedorAPI.update(editingId, data);
            showAlert('Fornecedor atualizado com sucesso!', 'success');
        } else {
            await fornecedorAPI.create(data);
            showAlert('Fornecedor criado com sucesso!', 'success');
        }
        
        const modal = bootstrap.Modal.getInstance(document.getElementById('fornecedorModal'));
        modal.hide();
        await loadFornecedores();
    } catch (error) {
        console.error('Erro ao salvar:', error);
        showAlert('Erro ao salvar fornecedor: ' + error.message, 'danger');
    } finally {
        hideLoading();
    }
}

async function deleteFornecedor(id) {
    if (!confirm('Tem certeza que deseja excluir este fornecedor?')) {
        return;
    }
    
    showLoading();
    
    try {
        await fornecedorAPI.delete(id);
        showAlert('Fornecedor excluído com sucesso!', 'success');
        await loadFornecedores();
    } catch (error) {
        showAlert('Erro ao excluir fornecedor: ' + error.message, 'danger');
    } finally {
        hideLoading();
    }
}
