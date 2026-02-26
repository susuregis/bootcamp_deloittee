Aula 07 - Frontend com Bootstrap

Visão Geral

Nesta aula o foco foi o desenvolvimento do frontend com Bootstrap para consumir a API REST do sistema de gestão. O frontend possui telas de CRUDs completos de produtos, categorias, fornecedores e peças.

Funcionalidades do Frontend

- Dashboard com atalhos para as telas
- CRUD de Produtos (listar, criar, editar, excluir)
- CRUD de Categorias (listar, criar, editar, excluir)
- CRUD de Fornecedores (listar, criar, editar, excluir)
- CRUD de Peças (listar, criar, editar, excluir)
- Integração com API via Fetch

Tecnologias

- HTML5
- CSS3
- JavaScript
- Bootstrap 5
- Bootstrap Icons

Estrutura de Pastas

```
frontend/
├── index.html
├── css/
│   └── style.css
├── js/
│   ├── api.js
│   ├── produtos.js
│   ├── categorias.js
│   ├── fornecedores.js
│   └── pecas.js
└── pages/
    ├── produtos.html
    ├── categorias.html
    ├── fornecedores.html
    └── pecas.html
```

Como Executar

1) Inicie o backend Spring Boot

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

