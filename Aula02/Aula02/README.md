# Aula 02 — Git e GitHub

## Visão geral
Nesta aula foram apresentados os conceitos essenciais de controle de versão usando Git e a colaboração remota via GitHub. Aprendeu-se a registrar alterações, trabalhar com branches, integrar mudanças e colaborar por meio de repositórios remotos e pull requests.

## Objetivos de aprendizagem
- Entender o propósito e benefícios do controle de versão.
- Executar operações básicas com Git: init, add, commit, status, log.
- Criar e gerenciar branches; entender merge e rebase.
- Conectar repositórios locais a remotos (GitHub) e enviar/puxar mudanças.
- Trabalhar com pull requests e fluxos colaborativos básicos.

## Conceitos principais
- Controle de versão: histórico de alterações do código, permitindo reverter e colaborar com segurança.
- Repositório local: cópia do projeto no seu computador (pasta .git).
- Repositório remoto: hospedagem (GitHub, GitLab, etc.) para compartilhar e colaborar.
- Snapshot vs delta: commits armazenam estado do projeto (snapshot) e suas diferenças.

### Branches
- Branch é um ponteiro para commits; permite desenvolver funcionalidades isoladas.
- `main`/`master` — linha principal de desenvolvimento; crie branches para features, correções e experimentos.

### Merge vs Rebase
- Merge cria um commit de integração que preserva o histórico divergente.
- Rebase reaplica commits em uma base diferente, mantendo histórico linear (use com cuidado em branches compartilhadas).

### Pull request (PR)
- Mecanismo do GitHub para revisar, discutir e integrar mudanças de um branch para outro (geralmente para `main`).
- Inclui revisão de código, comentários e testes antes da integração.

## Comandos úteis
Configurar identidade:

```bash
git config --global user.name "Seu Nome"
git config --global user.email seu.email@example.com
```

Fluxo básico:

```bash
# iniciar repositório local
git init

# clonar repositório remoto
git clone https://github.com/usuario/repositorio.git

# ver status e histórico
git status
git log --oneline --graph --decorate

# adicionar e commitar alterações
git add .
git commit -m "Mensagem clara e concisa"

# trabalhar com branches
git checkout -b feature/minha-nova-funcionalidade
# após mudanças
git add .
git commit -m "Implementa X"

# enviar branch para remoto
git push -u origin feature/minha-nova-funcionalidade
```

Integração e atualização:

```bash
# trazer mudanças do remoto para o branch atual
git pull --rebase origin main

# integrar via merge
git checkout main
git merge feature/minha-nova-funcionalidade

# ou rebase (regravar commits)
git checkout feature/minha-nova-funcionalidade
git rebase main
```

Resolver conflitos:
- Editar arquivos conflitantes, marcar como resolvido com `git add <arquivo>`, e continuar (`git rebase --continue` ou `git commit` para merge).

## Fluxo de trabalho recomendado (feature branch)
1. Atualize `main` local: `git checkout main` e `git pull origin main`.
2. Crie um branch de feature: `git checkout -b feature/nome`.
3. Faça commits pequenos e atômicos com mensagens claras.
4. Envie o branch para o remoto: `git push -u origin feature/nome`.
5. Abra um Pull Request no GitHub apontando para `main`.
6. Peça revisão, corrija feedback, e então faça o merge via PR.
7. Atualize seu repositório local e remova o branch remoto se não for mais necessário.

```
# IntelliJ
*.iml
.idea/

# Maven
/target/

# IDEs
.vscode/

# Logs e arquivos temporários
*.log
```


---
Conteúdo preparado para registro da Aula 02: conceitos e práticas fundamentais de Git e GitHub. Se desejar, eu adiciono exemplos práticos extraídos do seu repositório ou um guia passo a passo para criar um PR no GitHub.