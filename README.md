# SIGEE Login

Sistema de login seguro desenvolvido para a atividade de Desenvolvimento de Software Corporativo. O projeto utiliza Java 21, Spring Boot, Spring Security, Thymeleaf e MongoDB Atlas.

## Funcionalidades

- login e logout de usuários;
- cadastro de usuários realizado somente pelo perfil `ADMINISTRADOR`;
- autorização baseada nos perfis `ADMINISTRADOR`, `OPERADOR` e `PROFESSOR`;
- validação dos dados de cadastro;
- senhas armazenadas com hash BCrypt;
- usuários e sessões armazenados no MongoDB Atlas.

## Pré-requisitos

Antes de iniciar, instale ou providencie:

- Git;
- Java 21;
- uma conta no MongoDB Atlas.

O Maven não precisa ser instalado separadamente, pois o Maven Wrapper está incluído no repositório.

## 1. Clonar o repositório

Abra um terminal e execute:

```bash
git clone https://github.com/Diego251Fagundes/sigee-login.git
cd sigee-login
```

## 2. Configurar o MongoDB Atlas

No MongoDB Atlas:

1. Crie um projeto e um cluster.
2. Em **Database Access**, crie um usuário do banco de dados.
3. Conceda a esse usuário permissão de leitura e escrita no banco `sigee_login`.
4. Em **Network Access**, autorize o endereço IP do computador que executará a aplicação.
5. Na opção **Connect**, selecione o driver Java e copie a connection string.
6. Substitua o usuário e a senha indicados na connection string e defina `sigee_login` como nome do banco.

O usuário do banco de dados criado no Atlas serve somente para a conexão da aplicação. Ele não é o usuário utilizado na tela de login do sistema.

Nunca grave a connection string real no `application.properties` ou em outro arquivo versionado. A aplicação recebe essa informação pela variável de ambiente `MONGODB_URI`.

### Windows PowerShell

```powershell
$env:MONGODB_URI="mongodb+srv://<usuario-do-banco>:<senha-do-banco>@<cluster>/sigee_login"
```

Utilize a connection string fornecida pelo próprio Atlas, preservando os parâmetros adicionados por ele. Essa variável vale para a sessão atual do terminal e precisa ser configurada novamente em uma nova sessão.

## 3. Criar o primeiro administrador

Não existe cadastro público. Para criar a primeira conta da aplicação, configure temporariamente os dados do administrador inicial no mesmo terminal usado para executar o projeto.

### Windows PowerShell

```powershell
$env:SIGEE_ADMIN_INICIAL_ENABLED="true"
$env:SIGEE_ADMIN_INICIAL_NOME="Seu nome"
$env:SIGEE_ADMIN_INICIAL_SOBRENOME="Seu sobrenome"
$env:SIGEE_ADMIN_INICIAL_EMAIL="admin@example.com"
$env:SIGEE_ADMIN_INICIAL_NOME_USUARIO="admin.sigee"
$env:SIGEE_ADMIN_INICIAL_SENHA="substitua-por-uma-senha-segura"
```

A senha deve possuir entre 8 e 72 caracteres. Ela será transformada em hash BCrypt antes de ser armazenada.

## 4. Executar a aplicação

No Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Na primeira execução, a aplicação criará a conta administrativa configurada anteriormente. Quando o terminal informar que a aplicação foi iniciada, acesse:

```text
http://localhost:8080/login
```

Entre com o nome de usuário e a senha definidos para o administrador inicial.

Para encerrar a aplicação, pressione `Ctrl+C` no terminal.

## 5. Desativar a criação do administrador inicial

Depois que a primeira conta for criada, desative essa configuração nas próximas execuções.

No Windows PowerShell:

```powershell
$env:SIGEE_ADMIN_INICIAL_ENABLED="false"
Remove-Item Env:SIGEE_ADMIN_INICIAL_SENHA -ErrorAction SilentlyContinue
```

Mantenha `MONGODB_URI` configurada e inicie novamente a aplicação pelo comando da etapa 4. O administrador autenticado poderá cadastrar os demais usuários em:

```text
http://localhost:8080/admin/usuarios/cadastro
```

Cada usuário deve receber exatamente um dos três perfis disponíveis:

- `ADMINISTRADOR`: pode cadastrar usuários e atribuir perfis;
- `OPERADOR`: pode realizar login, mas não pode cadastrar usuários;
- `PROFESSOR`: pode realizar login, mas não pode cadastrar usuários.

## Executar os testes

No Windows PowerShell:

```powershell
.\mvnw.cmd test
```

Os testes verificam autenticação, autorização dos três perfis, validação do cadastro, hash de senha, proteção CSRF e encerramento da sessão no logout.

## Estrutura do projeto

- `config`: configurações de segurança, sessão e administrador inicial;
- `controller`: rotas de login, página inicial e cadastro administrativo;
- `dto`: dados e validações do formulário de cadastro;
- `model`: usuário e perfis de acesso;
- `repository`: persistência dos usuários no MongoDB;
- `security`: integração dos usuários com o Spring Security;
- `service`: regras de cadastro e autenticação;
- `templates`: páginas Thymeleaf;
- `static`: arquivos CSS e JavaScript da interface.

## Decisões de segurança e design

- credenciais do MongoDB são fornecidas por variável de ambiente;
- senhas são armazenadas somente como hash BCrypt;
- nome de usuário e e-mail possuem restrição de unicidade;
- autorização é validada pelo Spring Security no servidor;
- proteção CSRF permanece habilitada;
- logout invalida a sessão autenticada;
- sessões HTTP são armazenadas no MongoDB Atlas e expiram após 30 minutos;
- templates Thymeleaf permanecem separados das regras de negócio;
- cores compartilhadas ficam em `static/css/theme.css` e elementos reutilizáveis em `templates/fragments`, permitindo alterações visuais sem modificar autenticação ou persistência.
