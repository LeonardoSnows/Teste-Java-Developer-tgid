# Teste Java Developer Tgid

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=black)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=black)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=black)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

For send SMS , I used the Twilio SDK

This API was made for the challenge of the company Tgid - Transform and grow in digital

## Table of Contents
- [Goals](#goals)
- [Installation](#installation)
- [Usage](#usage)
- [Swagger](#swagger)
- [API Endpoints](#api-endpoints)
- [Body Values](#body-values)


## Goals
1. O sistema deve conter ao menos dois usuários: Empresa e Cliente
2. CPF (para Cliente) e CNPJ (para Empresa) devem ser validados
3. Para cada Empresa, deve haver ao menos um tipo de taxa de sistema que será incidida no momento da transação (seja saque ou depósito)
4. As Empresas devem ter um saldo que advém dos depósitos e saques realizados por Clientes na sua empresa, e já com o abate das taxas de administração (sistema)
5. Clientes podem fazer depósitos e saques pelas Empresas (a depender dos saldos das empresas)
6. No momento em que a transação é realizada, deve ser enviado um callback para Empresa informando a transação (essa informação pode dar erro caso o sistema
   esteja instável, utilize o site webhook.site para simular envio), e algum tipo de  notificação para o Cliente (seja e-mail, SMS ou algo do tipo). 


## Installation
1. Download PostgreSQL or the PostgreSQL image of Docker.
2. Clone the repository:
```bash
git clone https://github.com/LeonardoSnows/Teste-Java-Developer-tgid.git
```
3. Install dependencies with Maven

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080

## Swagger
- To use the Swagger, access the url: http://localhost:8080/swagger-ui/index.html#/

## API Endpoints
The API provides the following endpoints:

```markdown
CLIENT
POST /servico/cliente/transacao/deposito - make the deposit
POST /servico/cliente/transacao/saque - make the withdrawal
POST /servico/cliente/registrar - register client
GET  /servico/cliente - get all clients

COMPANY
POST /servico/empresa/transacao/deposito - make deposit to other company
POST /servico/empresa/transacao/saque - make the withdrawal
POST /servico/empresa/registrar - register company
GET  /servico/empresa - get all companys
```

## Body Values
```markdown
CLIENT
/servico/cliente/transacao/deposito and /servico/cliente/transacao/saque
Cliente and Empresa are the IDs.
{
"Valor": 10,
"Cliente": 1,
"Empresa": 1:
}


POST /servico/cliente/registrar
{
"Nome": "Test",
"Nome Social": "Cliente",
"Saldo": 100,
"CPF": "375.628.390-93",
"Celular":"+65982669889",
"Empresa":[{
    "id": 1,
    "nameCompany": "TesteE 3",
    "cnpj": "60.701.190/0001-04",
    "taxas": 2.80,
    "balance": 97.20
    }]
}


POST /servico/empresa/registrar - register company
{
"Empresa": "TesteCompany",
"CNPJ": "60.701.190/0001-04",
"Saldo": 100,
"Clientes": []
}


POST /servico/empresa/transacao/deposito
{
"Valor": 10,
"ID Cliente": 30,
"Empresa": 30,
"Empresa Alvo": 2
}


POST /servico/empresa/transacao/saque
{
"Valor": 10,
"ID Cliente": 2,
"Empresa": 30
}
```




