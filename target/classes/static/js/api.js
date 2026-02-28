// API Configuration - Usa URL relativa (mesmo servidor)
const API_BASE_URL = `${window.location.origin}/api`;

// Generic API Class
class ApiService {
    constructor(endpoint) {
        this.endpoint = endpoint;
        this.baseUrl = `${API_BASE_URL}/${endpoint}`;
    }

    async parseResponse(response) {
        if (response.status === 204) {
            return null;
        }
        const contentType = response.headers.get('content-type') || '';
        if (contentType.includes('application/json')) {
            return response.json();
        }
        return response.text();
    }

    async throwForStatus(response) {
        let message = `HTTP error! status: ${response.status}`;
        let details = null;
        try {
            const data = await this.parseResponse(response);
            details = data;
            if (data && typeof data === 'object' && data.message) {
                message = data.message;
            }
        } catch (error) {
            details = null;
        }
        const err = new Error(message);
        err.status = response.status;
        err.details = details;
        throw err;
    }

    // GET all
    async getAll() {
        try {
            const response = await fetch(this.baseUrl);
            if (!response.ok) {
                await this.throwForStatus(response);
            }
            return await this.parseResponse(response);
        } catch (error) {
            console.error(`Error fetching ${this.endpoint}:`, error);
            throw error;
        }
    }

    // GET by ID
    async getById(id) {
        try {
            const response = await fetch(`${this.baseUrl}/${id}`);
            if (!response.ok) {
                await this.throwForStatus(response);
            }
            return await this.parseResponse(response);
        } catch (error) {
            console.error(`Error fetching ${this.endpoint} by ID:`, error);
            throw error;
        }
    }

    // POST - Create
    async create(data) {
        try {
            const response = await fetch(this.baseUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data)
            });
            if (!response.ok) {
                await this.throwForStatus(response);
            }
            return await this.parseResponse(response);
        } catch (error) {
            console.error(`Error creating ${this.endpoint}:`, error);
            throw error;
        }
    }

    // PUT - Update
    async update(id, data) {
        try {
            const response = await fetch(`${this.baseUrl}/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data)
            });
            if (!response.ok) {
                await this.throwForStatus(response);
            }
            return await this.parseResponse(response);
        } catch (error) {
            console.error(`Error updating ${this.endpoint}:`, error);
            throw error;
        }
    }

    // DELETE
    async delete(id) {
        try {
            const response = await fetch(`${this.baseUrl}/${id}`, {
                method: 'DELETE'
            });
            if (!response.ok) {
                await this.throwForStatus(response);
            }
            return true;
        } catch (error) {
            console.error(`Error deleting ${this.endpoint}:`, error);
            throw error;
        }
    }
}

// Specific API Services
const produtoAPI = new ApiService('produtos');
const categoriaAPI = new ApiService('categorias');
const fornecedorAPI = new ApiService('fornecedores');
const pecaAPI = new ApiService('pecas');

// Utility Functions
function showLoading(elementId = 'loading') {
    const loadingElement = document.getElementById(elementId);
    if (loadingElement) {
        loadingElement.style.display = 'block';
    }
}

function hideLoading(elementId = 'loading') {
    const loadingElement = document.getElementById(elementId);
    if (loadingElement) {
        loadingElement.style.display = 'none';
    }
}

function showAlert(message, type = 'success', containerId = 'alertContainer') {
    const container = document.getElementById(containerId);
    if (!container) return;

    const alert = document.createElement('div');
    alert.className = `alert alert-${type} alert-dismissible fade show`;
    alert.role = 'alert';
    alert.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    
    container.appendChild(alert);
    
    setTimeout(() => {
        alert.remove();
    }, 5000);
}

function formatCurrency(value) {
    return new Intl.NumberFormat('pt-BR', {
        style: 'currency',
        currency: 'BRL'
    }).format(value);
}

function formatDate(dateString) {
    if (!dateString) return '-';
    const date = new Date(dateString);
    return new Intl.DateTimeFormat('pt-BR').format(date);
}

// Check API Connection
async function checkApiConnection() {
    try {
        const response = await fetch(`${API_BASE_URL}/produtos`);
        return response.ok;
    } catch (error) {
        console.error('API connection failed:', error);
        return false;
    }
}

// Initialize API check on page load
window.addEventListener('DOMContentLoaded', async () => {
    const isConnected = await checkApiConnection();
    if (!isConnected) {
        showAlert('⚠️ Não foi possível conectar à API. Verifique se o servidor Spring Boot está rodando em http://localhost:8080', 'warning', 'alertContainer');
    }
});
