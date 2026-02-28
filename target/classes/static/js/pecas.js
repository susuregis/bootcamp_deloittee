let pecas = [];
let categorias = [];
let fornecedores = [];
let editingId = null;

window.addEventListener('DOMContentLoaded', async () => {
    await Promise.all([loadCategorias(), loadFornecedores()]);
    await loadPecas();
});

async function loadPecas() {
    showLoading();
    try {
        pecas = await pecaAPI.getAll();
        renderPecas();
    } catch (error) {
        showAlert('Erro ao carregar peças: ' + error.message, 'danger');
    } finally {
        hideLoading();
    }
}

async function loadCategorias() {
    try {
        categorias = await categoriaAPI.getAll();
        populateDropdown('categoriaId', categorias);
    } catch (error) {
        console.error('Error loading categorias:', error);
        showAlert('Erro ao carregar categorias: ' + error.message, 'warning');
    }
}

async function loadFornecedores() {
    try {
        fornecedores = await fornecedorAPI.getAll();
        populateDropdown('fornecedorId', fornecedores);
    } catch (error) {
        console.error('Error loading fornecedores:', error);
        showAlert('Erro ao carregar fornecedores: ' + error.message, 'warning');
    }
}

function populateDropdown(selectId, items) {
    const select = document.getElementById(selectId);
    select.innerHTML = '<option value="">Selecione...</option>';
    items.forEach(item => {
        const option = document.createElement('option');
        option.value = item.id;
        option.textContent = item.nome;
        select.appendChild(option);
    });
}

function renderPecas() {
    const tbody = document.getElementById('pecasTableBody');
    
    if (pecas.length === 0) {
        tbody.innerHTML = `
            <tr>
                <td colspan="7" class="text-center py-5">
                    <div class="empty-state">
                        <i class="bi bi-inbox"></i>
                        <p class="mt-3">Nenhuma peça cadastrada</p>
                    </div>
                </td>
            </tr>
        `;
        return;
    }
    
    tbody.innerHTML = pecas.map(peca => `
        <tr class="fade-in">
            <td>${peca.id}</td>
            <td><strong>${peca.nome}</strong></td>
            <td>${peca.descricao || '-'}</td>
            <td>${formatCurrency(peca.preco)}</td>
            <td>
                ${peca.categoria ? 
                    `<span class="badge bg-primary">${peca.categoria.nome}</span>` : 
                    '<span class="badge bg-secondary">-</span>'
                }
            </td>
            <td>
                ${peca.fornecedor ? 
                    `<span class="badge bg-warning">${peca.fornecedor.nome}</span>` : 
                    '<span class="badge bg-secondary">-</span>'
                }
            </td>
            <td class="text-center">
                <button class="btn btn-sm btn-warning me-1" onclick="editPeca(${peca.id})" title="Editar">
                    <i class="bi bi-pencil"></i>
                </button>
                <button class="btn btn-sm btn-danger" onclick="deletePeca(${peca.id})" title="Excluir">
                    <i class="bi bi-trash"></i>
                </button>
            </td>
        </tr>
    `).join('');
}

function openCreateModal() {
    editingId = null;
    document.getElementById('modalTitle').textContent = 'Nova Peça';
    document.getElementById('pecaForm').reset();
}

async function editPeca(id) {
    editingId = id;
    showLoading();
    
    try {
        const peca = await pecaAPI.getById(id);
        document.getElementById('modalTitle').textContent = 'Editar Peça';
        document.getElementById('pecaId').value = peca.id;
        document.getElementById('nome').value = peca.nome;
        document.getElementById('descricao').value = peca.descricao || '';
        document.getElementById('preco').value = peca.preco;
        document.getElementById('categoriaId').value = peca.categoria?.id || '';
        document.getElementById('fornecedorId').value = peca.fornecedor?.id || '';
        
        const modal = new bootstrap.Modal(document.getElementById('pecaModal'));
        modal.show();
    } catch (error) {
        showAlert('Erro ao carregar peça: ' + error.message, 'danger');
    } finally {
        hideLoading();
    }
}

async function savePeca() {
    const form = document.getElementById('pecaForm');
    
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    
    const data = {
        nome: document.getElementById('nome').value,
        descricao: document.getElementById('descricao').value,
        preco: parseFloat(document.getElementById('preco').value),
        categoria: {
            id: parseInt(document.getElementById('categoriaId').value)
        },
        fornecedor: {
            id: parseInt(document.getElementById('fornecedorId').value)
        }
    };
    
    showLoading();
    
    try {
        if (editingId) {
            await pecaAPI.update(editingId, data);
            showAlert('Peça atualizada com sucesso!', 'success');
        } else {
            await pecaAPI.create(data);
            showAlert('Peça criada com sucesso!', 'success');
        }
        
        const modal = bootstrap.Modal.getInstance(document.getElementById('pecaModal'));
        modal.hide();
        await loadPecas();
    } catch (error) {
        showAlert('Erro ao salvar peça: ' + error.message, 'danger');
    } finally {
        hideLoading();
    }
}

async function deletePeca(id) {
    if (!confirm('Tem certeza que deseja excluir esta peça?')) {
        return;
    }
    
    showLoading();
    
    try {
        await pecaAPI.delete(id);
        showAlert('Peça excluída com sucesso!', 'success');
        await loadPecas();
    } catch (error) {
        showAlert('Erro ao excluir peça: ' + error.message, 'danger');
    } finally {
        hideLoading();
    }
}
