// Global variables
let produtos = [];
let categorias = [];
let editingId = null;

// Load data on page load
window.addEventListener('DOMContentLoaded', async () => {
    await loadCategorias();
    await loadProdutos();
});

// Load all produtos
async function loadProdutos() {
    showLoading();
    try {
        produtos = await produtoAPI.getAll();
        renderProdutos();
        updateTotalCount();
    } catch (error) {
        showAlert('Erro ao carregar produtos: ' + error.message, 'danger');
    } finally {
        hideLoading();
    }
}

// Update total count
function updateTotalCount() {
    const totalElement = document.getElementById('totalProdutos');
    const badgeElement = document.getElementById('badgeCount');
    if (totalElement) {
        totalElement.textContent = produtos.length;
    }
    if (badgeElement) {
        badgeElement.textContent = `${produtos.length} produto(s)`;
    }
}

// Search produtos
function searchProdutos() {
    const searchTerm = document.getElementById('searchInput').value.toLowerCase();
    const filteredProdutos = produtos.filter(p => 
        p.nome.toLowerCase().includes(searchTerm) || 
        (p.descricao && p.descricao.toLowerCase().includes(searchTerm))
    );
    renderProdutos(filteredProdutos);
}

// Clear search
function clearSearch() {
    document.getElementById('searchInput').value = '';
    renderProdutos();
}

// Load categorias for dropdown
async function loadCategorias() {
    try {
        categorias = await categoriaAPI.getAll();
        populateCategoriasDropdown();
    } catch (error) {
        console.error('Error loading categorias:', error);
        showAlert('Erro ao carregar categorias: ' + error.message, 'warning');
    }
}

// Populate categorias dropdown
function populateCategoriasDropdown() {
    const select = document.getElementById('categoriaId');
    select.innerHTML = '<option value="">Selecione uma categoria...</option>';
    
    categorias.forEach(categoria => {
        const option = document.createElement('option');
        option.value = categoria.id;
        option.textContent = categoria.nome;
        select.appendChild(option);
    });
}

// Render produtos table
function renderProdutos(produtosToRender = produtos) {
    const tbody = document.getElementById('produtosTableBody');
    
    if (produtosToRender.length === 0) {
        tbody.innerHTML = `
            <tr>
                <td colspan="6" class="text-center py-5">
                    <i class="bi bi-inbox" style="font-size: 3rem; color: #ccc;"></i>
                    <p class="mt-3 text-muted">Nenhum produto encontrado</p>
                </td>
            </tr>
        `;
        return;
    }
    
    tbody.innerHTML = produtosToRender.map(produto => `
        <tr>
            <td>${produto.id}</td>
            <td><strong>${produto.nome}</strong></td>
            <td>${produto.descricao || '-'}</td>
            <td><span style="color: #86BC25; font-weight: bold;">R$ ${formatCurrency(produto.preco)}</span></td>
            <td>
                ${produto.categoria ? 
                    `<span class="badge" style="background-color: #86BC25; color: white;">${produto.categoria.nome}</span>` : 
                    '<span class="badge bg-secondary">-</span>'
                }
            </td>
            <td class="text-center">
                <button class="btn btn-sm" style="background-color: #86BC25; color: white; border: none;" onclick="editProduto(${produto.id})" title="Editar">
                    <i class="bi bi-pencil"></i>
                </button>
                <button class="btn btn-sm" style="background-color: #86BC25; color: white; border: none;" onclick="deleteProduto(${produto.id})" title="Excluir">
                    <i class="bi bi-trash"></i>
                </button>
            </td>
        </tr>
    `).join('');
    
    updateTotalCount();
}

// Open create modal
async function openCreateModal() {
    editingId = null;
    document.getElementById('modalTitle').textContent = 'Novo Produto';
    document.getElementById('produtoForm').reset();
    document.getElementById('produtoId').value = '';
    await loadCategorias();
}

// Edit produto
async function editProduto(id) {
    editingId = id;
    showLoading();
    
    try {
        const produto = await produtoAPI.getById(id);
        await loadCategorias();
        
        document.getElementById('modalTitle').textContent = 'Editar Produto';
        document.getElementById('produtoId').value = produto.id;
        document.getElementById('nome').value = produto.nome;
        document.getElementById('descricao').value = produto.descricao || '';
        document.getElementById('preco').value = produto.preco;
        document.getElementById('categoriaId').value = produto.categoria?.id || '';
        
        const modal = new bootstrap.Modal(document.getElementById('produtoModal'));
        modal.show();
    } catch (error) {
        showAlert('Erro ao carregar produto: ' + error.message, 'danger');
    } finally {
        hideLoading();
    }
}

// Save produto (create or update)
async function saveProduto() {
    const form = document.getElementById('produtoForm');
    
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
        }
    };
    
    showLoading();
    
    try {
        if (editingId) {
            await produtoAPI.update(editingId, data);
            showAlert('Produto atualizado com sucesso!', 'success');
        } else {
            await produtoAPI.create(data);
            showAlert('Produto criado com sucesso!', 'success');
        }
        
        // Close modal
        const modal = bootstrap.Modal.getInstance(document.getElementById('produtoModal'));
        modal.hide();
        
        // Reload table
        await loadProdutos();
    } catch (error) {
        showAlert('Erro ao salvar produto: ' + error.message, 'danger');
    } finally {
        hideLoading();
    }
}

// Delete produto
async function deleteProduto(id) {
    if (!confirm('Tem certeza que deseja excluir este produto?')) {
        return;
    }
    
    showLoading();
    
    try {
        await produtoAPI.delete(id);
        showAlert('Produto excluído com sucesso!', 'success');
        await loadProdutos();
    } catch (error) {
        showAlert('Erro ao excluir produto: ' + error.message, 'danger');
    } finally {
        hideLoading();
    }
}
