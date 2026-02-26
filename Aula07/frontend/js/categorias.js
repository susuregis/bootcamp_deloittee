let categorias = [];
let editingId = null;

window.addEventListener('DOMContentLoaded', loadCategorias);

async function loadCategorias() {
    showLoading();
    try {
        categorias = await categoriaAPI.getAll();
        renderCategorias();
        updateTotalCount();
    } catch (error) {
        showAlert('Erro ao carregar categorias: ' + error.message, 'danger');
    } finally {
        hideLoading();
    }
}

// Update total count
function updateTotalCount() {
    const totalElement = document.getElementById('totalCategorias');
    const badgeElement = document.getElementById('badgeCount');
    if (totalElement) {
        totalElement.textContent = categorias.length;
    }
    if (badgeElement) {
        badgeElement.textContent = `${categorias.length} categoria(s)`;
    }
}

// Search categorias
function searchCategorias() {
    const searchTerm = document.getElementById('searchInput').value.toLowerCase();
    const filteredCategorias = categorias.filter(c => 
        c.nome.toLowerCase().includes(searchTerm) || 
        (c.descricao && c.descricao.toLowerCase().includes(searchTerm))
    );
    renderCategorias(filteredCategorias);
}

// Clear search
function clearSearch() {
    document.getElementById('searchInput').value = '';
    renderCategorias();
}

function renderCategorias(categoriasToRender = categorias) {
    const tbody = document.getElementById('categoriasTableBody');
    
    if (categoriasToRender.length === 0) {
        tbody.innerHTML = `
            <tr>
                <td colspan="5" class="text-center py-5">
                    <i class="bi bi-inbox" style="font-size: 3rem; color: #ccc;"></i>
                    <p class="mt-3 text-muted">Nenhuma categoria encontrada</p>
                </td>
            </tr>
        `;
        return;
    }
    
    tbody.innerHTML = categoriasToRender.map(categoria => `
        <tr>
            <td>${categoria.id}</td>
            <td><strong>${categoria.nome}</strong></td>
            <td>${categoria.descricao || '-'}</td>
            <td>
                <span class="badge" style="background-color: #86BC25; color: white;">${categoria.totalProdutos || 0} produto(s)</span>
            </td>
            <td class="text-center">
                <button class="btn btn-sm" style="background-color: #86BC25; color: white; border: none;" onclick="editCategoria(${categoria.id})" title="Editar">
                    <i class="bi bi-pencil"></i>
                </button>
                <button class="btn btn-sm" style="background-color: #86BC25; color: white; border: none;" onclick="deleteCategoria(${categoria.id})" title="Excluir">
                    <i class="bi bi-trash"></i>
                </button>
            </td>
        </tr>
    `).join('');
}

function openCreateModal() {
    editingId = null;
    document.getElementById('modalTitle').textContent = 'Nova Categoria';
    document.getElementById('categoriaForm').reset();
    const modal = new bootstrap.Modal(document.getElementById('categoriaModal'));
    modal.show();
}

async function editCategoria(id) {
    editingId = id;
    showLoading();
    
    try {
        const categoria = await categoriaAPI.getById(id);
        document.getElementById('modalTitle').textContent = 'Editar Categoria';
        document.getElementById('categoriaId').value = categoria.id;
        document.getElementById('nome').value = categoria.nome;
        document.getElementById('descricao').value = categoria.descricao || '';
        
        const modal = new bootstrap.Modal(document.getElementById('categoriaModal'));
        modal.show();
    } catch (error) {
        showAlert('Erro ao carregar categoria: ' + error.message, 'danger');
    } finally {
        hideLoading();
    }
}

async function saveCategoria() {
    const form = document.getElementById('categoriaForm');
    
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    
    const data = {
        nome: document.getElementById('nome').value,
        descricao: document.getElementById('descricao').value
    };
    
    showLoading();
    
    try {
        if (editingId) {
            await categoriaAPI.update(editingId, data);
            showAlert('Categoria atualizada com sucesso!', 'success');
        } else {
            await categoriaAPI.create(data);
            showAlert('Categoria criada com sucesso!', 'success');
        }
        
        const modal = bootstrap.Modal.getInstance(document.getElementById('categoriaModal'));
        modal.hide();
        await loadCategorias();
    } catch (error) {
        if (error.status === 409) {
            showAlert('Categoria já existe. Use outro nome.', 'warning');
        } else if (error.status === 400) {
            showAlert('Dados inválidos. Verifique os campos e tente novamente.', 'danger');
        } else {
            showAlert('Erro ao salvar categoria: ' + error.message, 'danger');
        }
    } finally {
        hideLoading();
    }
}

async function deleteCategoria(id) {
    if (!confirm('Tem certeza que deseja excluir esta categoria?')) {
        return;
    }
    
    showLoading();
    
    try {
        await categoriaAPI.delete(id);
        showAlert('Categoria excluída com sucesso!', 'success');
        await loadCategorias();
    } catch (error) {
        showAlert('Erro ao excluir categoria: ' + error.message, 'danger');
    } finally {
        hideLoading();
    }
}
