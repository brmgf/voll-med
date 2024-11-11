# Voll.med ⚕️
API Rest desenvolvida durante a trilha de formação Java e Spring Boot da [Alura](https://www.alura.com.br/);

A API possui as funcionalidades de cadastramento, consulta e inativação de médicos e pacientes, agendamento e cancelamento de consultas.
O usuário precisa estar autenticado para conseguir realizar as operações. A API também fica disponível no [Swagger](http://localhost:8080/swagger-ui/index.html).

## Usuários
### Cadastrar
**POST/** localhost:8080/usuarios

**body:** 
```
{
    "login": "admin.vollmed",
    "senha": 123456
}
```

## Autenticação
### Login
**POST/** localhost:8080/login

**body:** 
```
{
    "login": "admin.vollmed",
    "senha": 123456
}
```

## Médicos
### Listar
**GET/** localhost:8080/medicos

### Detalhar
**GET/** localhost:8080/medicos/{id}

### Cadastrar
**POST/** localhost:8080/medicos

**body:** 
```
{
    "nome": "Matheus Silva",
    "email": "mat.silva@vollmed.com.br",
    "crm": "870051",
    "especialidade": "GINECOLOGIA",
    "telefone": "3735412000",
    "endereco": {
        "logradouro":"Rua Treze de Maio
        "bairro": "Centro",
        "cep": "35620000",
        "cidade": "Abaeté",
        "uf": "MG",
        "numero": "1",
        "complemento": "Sala 101"
    }
}
```

### Atualizar
**PUT/** localhost:8080/medicos

**body:** 
```
{
    "id": 1,
    "nome": "Mateus Silva",
    "telefone": "3735412001",
    "endereco": {
        "logradouro":"Rua Treze de Maio
        "bairro": "Centro",
        "cep": "35620000",
        "cidade": "Abaeté",
        "uf": "MG",
        "numero": "1",
        "complemento": "Sala 101"
    }
}
```

### Inativar
**DELETE/** localhost:8080/medicos/{id}

## Pacientes
### Listar
**GET/** localhost:8080/pacientes

### Detalhar
**GET/** localhost:8080/pacientes/{id}

### Cadastrar
**POST/** localhost:8080/pacientes

**body:** 
```
{
    "nome": "Roberto Silva", 
    "email": "roberto@gmail.com",
    "telefone": "3799880022",
    "cpf": "26378580014",
    "endereco": {
        "logradouro":"Rua Getúlio Vargas",
        "bairro": "Centro",
        "cep": "35620000",
        "cidade": "Abaeté",
        "uf": "MG",
        "numero": "108"
    }
}
```

### Atualizar
**PUT/** localhost:8080/pacientes

**body:** 
```
{
    "id": 1, 
    "nome": "Roberto Souza",
    "telefone": "3799880022",
    "endereco": {
        "logradouro":"Rua Getúlio Vargas",
        "bairro": "Centro",
        "cep": "35620000",
        "cidade": "Abaeté",
        "uf": "MG",
        "numero": "108",
        "complemento": "Apartamento 105"
    }
}
```

### Inativar
**DELETE/** localhost:8080/pacientes/{id}

## Consultas
### Agendar
* Uma consulta deve ser agendada com antecedência mínima de 30 minutos
* O intervalo mínimo entre uma consulta e outra é de 1 hora
* Não é possível agendar consultas antes das 7:00 e depois das 18:00 de segunda-feira aos sábados
* Não é possível agendar consultas no domingo

**POST/** localhost:8080/consultas

**body:** 
```
{
    "idMedico": 1,
    "idPaciente": 1,
    "dataHora": "2024-11-06T16:00:00"
}
```

### Cancelar
* Uma consulta deve ser cancelada com antecedência mínima de 24 horas

**DELETE/** localhost:8080/consultas

**body:** 
```
{
    "idConsulta": 1,
    "motivoCancelamento": "Motivo pessoal."
}
```
