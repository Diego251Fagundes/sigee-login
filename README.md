# SIGEE Login

Atividade prática de Desenvolvimento de Software Corporativo: sistema de login seguro com Java 21, Spring Boot, Spring Security, Thymeleaf e MongoDB Atlas.

Este repositório implementa somente autenticação, sessões, cadastro administrativo de usuários e autorização por perfil. Ele não contém inventário, reservas ou outras funcionalidades do SIGEE completo.

## Requisitos

- Java 21;
- acesso a um cluster MongoDB Atlas;
- uma connection string com permissão de leitura e escrita no banco `sigee_login`.

O Maven Wrapper acompanha o projeto, portanto não é obrigatório instalar o Maven separadamente.

## Configuração do MongoDB Atlas

A conexão é lida da variável de ambiente `MONGODB_URI`. Nenhuma credencial deve ser gravada no `application.properties` ou enviada ao GitHub.

Exemplo para a sessão atual do PowerShell:

```powershell
$env:MONGODB_URI="mongodb+srv://<usuario>:<senha>@<cluster>/sigee_login"
```

Use a connection string fornecida pelo Atlas e substitua somente os valores indicados. No Atlas, permita apenas os endereços IP necessários e conceda ao usuário do banco somente a permissão `readWrite` em `sigee_login`.

As sessões HTTP são armazenadas no MongoDB e expiram após 30 minutos.

## Primeiro administrador

Não existe cadastro público. Para criar a primeira conta administrativa, configure temporariamente as variáveis abaixo antes da primeira execução:

Essa conta pertence à aplicação e é diferente do usuário técnico utilizado na connection string do MongoDB Atlas.

```powershell
$env:SIGEE_ADMIN_INICIAL_ENABLED="true"
$env:SIGEE_ADMIN_INICIAL_NOME="Seu nome"
$env:SIGEE_ADMIN_INICIAL_SOBRENOME="Seu sobrenome"
$env:SIGEE_ADMIN_INICIAL_EMAIL="admin@example.com"
$env:SIGEE_ADMIN_INICIAL_NOME_USUARIO="admin.sigee"
$env:SIGEE_ADMIN_INICIAL_SENHA="substitua-por-uma-senha-segura"
```

Depois que a conta for criada, desative a inicialização para as execuções seguintes:

```powershell
$env:SIGEE_ADMIN_INICIAL_ENABLED="false"
```

A senha inicial é processada com BCrypt antes de ser armazenada. Os valores reais dessas variáveis não devem ser adicionados ao repositório.

## Execução

No Windows:

```powershell
./mvnw.cmd spring-boot:run
```

Em Linux ou macOS:

```bash
./mvnw spring-boot:run
```

A aplicação ficará disponível em `http://localhost:8080`.

A interface de login está disponível em `http://localhost:8080/login`. O acesso utiliza nome de usuário e senha, e não existe cadastro público.

## Testes

```powershell
./mvnw.cmd test
```

Os testes verificam autenticação dos três perfis, credenciais inválidas, conta inativa, bloqueio por tentativas, autorização do cadastro, validação do perfil, duplicidade, hash de senha, CSRF e logout.

## Perfis e autorização

- `ADMINISTRADOR`: pode cadastrar usuários e escolher o perfil da nova conta;
- `OPERADOR`: pode autenticar-se, mas não pode cadastrar usuários;
- `PROFESSOR`: pode autenticar-se, mas não pode cadastrar usuários.

O cadastro está disponível somente em `/admin/usuarios/cadastro`. O perfil usado na autorização é sempre carregado do usuário persistido no MongoDB.

## Decisões de segurança

- senhas armazenadas somente como hash BCrypt;
- nomes de usuário e e-mails normalizados para letras minúsculas;
- índices únicos para nome de usuário e e-mail;
- somente contas ativas podem autenticar-se;
- cinco credenciais inválidas bloqueiam a conta por 15 minutos;
- login válido reinicia a contagem de falhas;
- CSRF permanece habilitado nos formulários;
- logout invalida a sessão e remove o cookie `SESSION`;
- rotas protegidas redirecionam usuários anônimos ao login;
- nenhuma connection string ou senha real é versionada.

## Estrutura principal

- `config`: Spring Security, sessões e criação opcional do administrador inicial;
- `controller`: página inicial e cadastro administrativo;
- `dto`: validação dos dados recebidos pelo formulário;
- `model`: usuário e perfis;
- `repository`: persistência no MongoDB;
- `security`: integração entre os usuários e o Spring Security;
- `service`: regras de cadastro e controle de tentativas de login;
- `templates`: páginas Thymeleaf de login, página inicial e cadastro administrativo;
- `static`: estilos e scripts simples da interface.
