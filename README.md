# FIAP Tech Challenge - Microsserviço de pagamento e pagamento-mock

[![Terraform Deploy](https://github.com/ns-fiap-tc/tech_challenge_fiap_ms_pagamento/actions/workflows/deploy.yml/badge.svg)](https://github.com/ns-fiap-tc/tech_challenge_fiap_ms_pagamento/actions/workflows/deploy.yml)
[![SonarQube](https://github.com/ns-fiap-tc/tech_challenge_fiap_ms_pagamento/actions/workflows/sonarcloud.yml/badge.svg)](https://github.com/ns-fiap-tc/tech_challenge_fiap_ms_pagamento/actions/workflows/sonarcloud.yml)
[![Build and Push Docker Images](https://github.com/ns-fiap-tc/tech_challenge_fiap_ms_pagamento/actions/workflows/docker-build.yml/badge.svg)](https://github.com/ns-fiap-tc/tech_challenge_fiap_ms_pagamento/actions/workflows/docker-build.yml)

Este é o repositório que contém o código fonte do serviço de pagamento e pagamento-mock da aplicação [Lanchonete App](https://github.com/ns-fiap-tc/tech_challenge_fiap). Nele você também encontrará arquivos de configuração do Terraform que fazem o deploy da aplicação na AWS.

## Cobertura de Testes
O projeto contém testes automatizados e Sonar integrado, abaixo evidência de cobertura dos testes + link de referência para os dados do Sonar
<img width="1759" height="243" alt="Testes aplicação" src="https://github.com/user-attachments/assets/499e8e44-114d-47b3-845f-f91d70aed9eb" />
<img width="1415" height="947" alt="Print Sonar" src="https://github.com/user-attachments/assets/fbae5ec3-eef4-49a0-a441-73762940fd9c" />


- Link Sonar: https://sonarcloud.io/project/overview?id=ns-fiap-tc_tech_challenge_fiap_ms_pagamento

## Passos para o provisionamento

> Para completo funcionamento da plataforma, é necessário seguir o seguinte fluxo de provisionamento:
>
> 1. A provisão deste repositório; [infra-base](https://github.com/ns-fiap-tc/infra-base)
> 2. A provisão do repositório dos bancos de dados: [infra-bd](https://github.com/ns-fiap-tc/infra-bd)
> 3. A provisão do repositório do microsserviço de categoria: [tech_challenge_fiap_ms_categoria](https://github.com/ns-fiap-tc/tech_challenge_fiap_ms_categoria);
> 4. A provisão do repositório do microsserviço de produto: [tech_challenge_fiap_ms_produto](https://github.com/ns-fiap-tc/tech_challenge_fiap_ms_produto);
> 5. A provisão do repositório do microsserviço de pagamento e pagamento-mock: [tech_challenge_fiap_ms_pagamento](#como-rodar-o-projeto);
> 6. A provisão da aplicação principal: [tech_challenge_fiap](https://github.com/ns-fiap-tc/tech_challenge_fiap).

## 🚀 Como rodar o projeto

### 🤖 Via GitHub Actions

<details>
  <summary>Passo a passo</summary>

#### 📖 Resumo

Este repositório possui uma pipeline automatizada chamada `Terraform Deploy` que permite **provisionar a aplicação do microsserviço de pagamento e pagamento-mock** sempre que houver um push na branch `main`.

A branch é protegida e só aceita alterações que venham de PRs previamente aprovadas.

> ⚠️ Apenas usuários com acesso ao repositório e às **GitHub Secrets** corretas conseguem utilizar esse fluxo.

#### 🔐 Pré-requisitos

Certifique-se de que as seguintes **secrets** estejam configuradas no repositório do GitHub (`Settings > Secrets and variables > Actions`):

- `AWS_ACCESS_KEY_ID`
- `AWS_SECRET_ACCESS_KEY`
- `AWS_SESSION_TOKEN` _(se estiver usando AWS Academy)_
- `TF_VAR_DB_USERNAME`
- `TF_VAR_DB_PASSWORD`

Essas variáveis são utilizadas pelo Terraform para autenticação e execução dos planos na AWS.

#### ⚙️ Etapas da pipeline `Terraform Deploy`

1. 🧾 **Checkout do código**: A action clona este repositório.
2. ⚒️ **Setup do Terraform**: Instala a ferramenta na máquina runner.
3. 📂 **Acesso ao diretório atual**: Todos os arquivos `.tf` são lidos da raiz do repositório.
4. 🔐 **Carregamento das variáveis sensíveis** via secrets.
5. 🧪 **Execução do `terraform init`**: Inicializa o backend e os providers.
6. 🚀 **Execução do `terraform apply`**: Cria ou atualiza a instância de banco de dados no Amazon RDS.

#### 🧭 Diagrama do fluxo

```mermaid
flowchart TD
    G[Push na branch main] --> A[Workflow: Terraform Deploy]

    subgraph Pipeline
        A1[Checkout do código]
        A2[Setup do Terraform]
        A3[Carrega Secrets da AWS]
        A4[terraform init]
        A5[terraform plan]
        A6[terraform apply]
    end

    A --> A1 --> A2 --> A3 --> A4 --> A5 --> A6 --> Container EKS[Instância EKS no AWS rodando a aplicação]
```

#### Benefícios desse fluxo

- 🤖 Automatização do deploy do banco de dados
- ✅ Redução de erros manuais
- 🔐 Segurança no uso de credenciais via GitHub Secrets
- 🔁 Reprodutibilidade garantida
- 💬 Transparência nos logs via GitHub Actions

</details>

### 💻 Localmente

<details>
  <summary>Passo a passo</summary>

#### Pré-requisitos

Antes de começar, certifique-se de ter os seguintes itens instalados e configurados em seu ambiente:

1. **Terraform**: A ferramenta que permite definir, visualizar e implantar a infraestrutura de nuvem.
2. **AWS CLI**: A interface de linha de comando da AWS.
3. **Credenciais AWS válidas**: Você precisará de uma chave de acesso e uma chave secreta para autenticar com a AWS (no momento, o repositório usa chaves e credenciais fornecidas pelo [AWS Academy](https://awsacademy.instructure.com/) e que divergem de contas padrão). Tais credenciais devem ser inseridas no arquivo `credentials` que fica dentro da pasta `.aws`

## Como usar

1. **Clone este repositório**:

```bash
git clone https://github.com/ns-fiap-tc/tech_challenge_fiap_ms_pagamento
```

2. **Acesse o diretório do repositório**:

```bash
cd tech_challenge_fiap_ms_pagamento
```

3. **Defina as variáveis necessárias ao nível de ambiente, criando um arquivo `.env` de acordo com o arquivo `.env.exemplo`. Exemplo:**:

```bash
DB_PAGAMENTO_USERNAME="lanch_pag_user"
DB_PAGAMENTO_PASSWORD="lanchpagpass"
DB_PAGAMENTO_NAME="lanch_pag_db"
DB_PAGAMENTO_PORT="27017"
DOCKERHUB_USERNAME="meuusuariodockerhub"
DOCKERHUB_ACCESS_TOKEN=
```

4. **Inicialize o diretório Terraform**:

```bash
terraform init
```

5. **Visualize as mudanças que serão feitas**:

```bash
./terraform.sh plan
```

6. **Provisione a infraestrutura**:

```bash
./terraform.sh apply -auto-approve
```

7. **Para destruir a aplicação provisionada**:

```bash
./terraform.sh destroy -auto-approve
```

</details>

## ✨ Contribuidores

- Guilherme Fausto - RM 359909
- Nicolas Silva - RM 360621
- Rodrigo Medda Pereira - RM 360575
