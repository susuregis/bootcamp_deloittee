# Aula 07 - Frontend + Backend Integrado + Deploy Azure

## Visão Geral

Nesta aula o foco foi o desenvolvimento completo de uma aplicação **Full Stack** com:
- **Backend Spring Boot** com API REST
- **Frontend Bootstrap 5** integrado ao backend
- **Deploy na Azure** (App Service Gratuito)
- **Frontend e Backend na mesma URL**

## Funcionalidades

### Backend (Spring Boot + JPA)
- API REST completa (Produtos, Categorias, Fornecedores, Peças)
- Validação com Bean Validation
- Relacionamentos JPA (OneToMany, ManyToOne)
- Banco H2 (em arquivo para persistência)
- Princípios SOLID aplicados
- Swagger/OpenAPI documentação
- Perfis de ambiente (dev, test, prod)

### Frontend (HTML + CSS + JS + Bootstrap)
- Dashboard com cards interativos
- CRUD completo de Produtos
- CRUD completo de Categorias
- CRUD completo de Fornecedores
- CRUD completo de Peças
- Interface responsiva (Desktop, Tablet, Mobile)
- Animações e feedback visual
- Integração com API REST via Fetch

## Tecnologias Utilizadas

### Backend
- **Java 21**
- **Spring Boot 3.2.2**
- **Spring Data JPA**
- **H2 Database**
- **Bean Validation**
- **Springdoc OpenAPI (Swagger)**

### Frontend
- **HTML5**
- **CSS3** (Custom + Bootstrap)
- **JavaScript (ES6+)**
- **Bootstrap 5.3**
- **Bootstrap Icons**
- **Fetch API**

### Deploy
- **Azure App Service**
- **Azure CLI**
- **Maven**

## Estrutura do Projeto

```
Aula07/demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/demo/
│   │   │       ├── controller/      # REST Controllers
│   │   │       ├── model/           # Entidades JPA
│   │   │       ├── repository/      # Repositories
│   │   │       ├── service/         # Services (SOLID)
│   │   │       └── config/          # Configurações
│   │   └── resources/
│   │       ├── frontend/            # Frontend integrado
│   │       │   ├── index.html
│   │       │   ├── css/
│   │       │   ├── js/
│   │       │   └── pages/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       ├── application-test.properties
│   │       └── application-prod.properties
│   └── test/
├── target/                          # Arquivos compilados
├── pom.xml                          # Dependências Maven
├── deploy-azure.ps1                 # Script deploy (Windows)
├── deploy-azure.sh                  # Script deploy (Linux/Mac)
├── DEPLOY_AZURE.md                  # Guia completo Azure
└── DEPLOY_QUICKSTART.md             # Início rápido
```

## Como Executar Localmente

### 1. Pré-requisitos
- Java 21+
- Maven 3.6+

### 2. Clonar e Executar

```bash
# Navegar até o projeto
cd Aula07/demo

# Compilar
mvn clean install

# Executar (Perfil DEV)
mvn spring-boot:run

# OU com perfil específico
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

### 3. Acessar a Aplicação

- **Frontend:** http://localhost:8080
- **API REST:** http://localhost:8080/api/produtos
- **Swagger:** http://localhost:8080/swagger-ui.html
- **H2 Console:** http://localhost:8080/h2-console (apenas dev/test)

## Deploy na Azure (GRATUITO)

### Opção 1: Script Automático

**Windows (PowerShell):**
```powershell
cd demo
.\deploy-azure.ps1
```

**Linux/Mac (Bash):**
```bash
cd demo
chmod +x deploy-azure.sh
./deploy-azure.sh
```

### Opção 2: Comandos Manuais

```bash
# 1. Login
az login

# 2. Criar infraestrutura
az group create --name bootcamp-rg --location eastus
az appservice plan create --name bootcamp-plan --resource-group bootcamp-rg --sku F1 --is-linux
az webapp create --name bootcamp-java-app-2026 --resource-group bootcamp-rg --plan bootcamp-plan --runtime "JAVA:21-java21"

# 3. Build + Deploy
mvn clean package -DskipTests
az webapp deploy --name bootcamp-java-app-2026 --resource-group bootcamp-rg --src-path target/demo-0.0.1-SNAPSHOT.jar --type jar
```

### Acessar na Azure

Após o deploy, acesse:
```
https://bootcamp-java-app-2026.azurewebsites.net
```

**Nota:** O nome `bootcamp-java-app-2026` deve ser único. Se já existir, escolha outro!

## Documentação Completa

- **[DEPLOY_AZURE.md](demo/DEPLOY_AZURE.md)** - Guia completo de deploy
- **[DEPLOY_QUICKSTART.md](demo/DEPLOY_QUICKSTART.md)** - Início rápido

## Funcionalidades do Frontend

### Dashboard (index.html)
- Cards interativos com links
- Estatísticas e navegação rápida
- Design responsivo com gradientes

### Produtos (pages/produtos.html)
- Listagem com tabela responsiva
- Modal para criar/editar
- Seleção de categoria
- Formatação de preço (R$)
- Validação de formulários

### Categorias (pages/categorias.html)
- CRUD completo
- Contador de produtos por categoria
- Interface simplificada

### Fornecedores (pages/fornecedores.html)
- Cadastro completo (nome, email, telefone, endereço)
- Ícones para informações de contato
- Validação de email

### Peças (pages/pecas.html)
- Associação com categoria e fornecedor
- Preço formatado
- Badges para relacionamentos

## API Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/produtos` | Lista todos os produtos |
| POST | `/api/produtos` | Cria novo produto |
| GET | `/api/produtos/{id}` | Busca produto por ID |
| PUT | `/api/produtos/{id}` | Atualiza produto |
| DELETE | `/api/produtos/{id}` | Deleta produto |
| GET | `/api/categorias` | Lista todas as categorias |
| POST | `/api/categorias` | Cria nova categoria |
| GET | `/api/fornecedores` | Lista todos os fornecedores |
| POST | `/api/fornecedores` | Cria novo fornecedor |
| GET | `/api/pecas` | Lista todas as peças |
| POST | `/api/pecas` | Cria nova peça |

## Perfis de Ambiente

### Development (dev)
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
- H2 Console habilitado
- Logs detalhados
- Banco em memória

### Test (test)
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=test
```
- Banco em arquivo
- Logs moderados

### Production (prod)
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```
- H2 Console desabilitado
- Logs mínimos
- Otimizado para performance

## Build do Projeto

```bash
# Compilar sem testes
mvn clean package -DskipTests

# Com testes
mvn clean package

# Apenas compilar
mvn clean compile

# Limpar tudo
mvn clean
```

## Tecnologias e Padrões Implementados

- **REST API** com Spring Boot
- **JPA/Hibernate** para persistência
- **Bean Validation** para validações
- **SOLID Principles** aplicados
- **Repository Pattern**
- **Service Layer Pattern**
- **DTO Pattern** (opcional)
- **Exception Handling** centralizado
- **CORS** configurado
- **Swagger/OpenAPI** documentação
- **Frontend SPA** integrado

## Troubleshooting

### Erro: Porta 8080 em uso
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -ti:8080 | xargs kill -9
```

### Erro: Frontend não carrega
- Verifique se o frontend está em `src/main/resources/frontend/`
- Limpe o projeto: `mvn clean`
- Recompile: `mvn clean install`

### Erro: API não responde
- Verifique logs no console
- Teste endpoints: `curl http://localhost:8080/api/produtos`
- Verifique banco de dados: `http://localhost:8080/h2-console`

## Próximos Passos

- [ ] Implementar autenticação JWT
- [ ] Adicionar testes unitários
- [ ] Implementar Cache com Redis
- [ ] Migrar para PostgreSQL
- [ ] Adicionar Docker/Kubernetes
- [ ] CI/CD com GitHub Actions

---

**Desenvolvido durante o Bootcamp Java - Deloitte 2026**

```bash
mvn spring-boot:run
```

Backend disponível em: http://localhost:8080

2) Inicie o frontend

```bash
cd frontend
python -m http.server 8000
```

Acesse o frontend em: http://localhost:8000

Configuração da API no Frontend

O frontend está configurado para consumir a API em:

```
http://localhost:8080/api
```

Para alterar a URL, edite:

```
frontend/js/api.js
```

