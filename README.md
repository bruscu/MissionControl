# Mission Control

Projeto Spring Boot de gerenciamento de missões espaciais, sondas e recursos relacionados, com implementação adicional do CRUD de astronautas solicitado no desafio.

## Requisitos

- Java 25 (versão definida no `pom.xml`)
- Maven Wrapper incluído no projeto

## Executar

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

No Linux/macOS:

```bash
./mvnw spring-boot:run
```

A API inicia na porta padrão 8080.

## CRUD de astronautas

Rota principal: `/astronautas`

| Método | Endpoint | Comportamento |
| --- | --- | --- |
| POST | `/astronautas` | Cadastrar astronauta |
| GET | `/astronautas` | Listar astronautas |
| GET | `/astronautas/{id}` | Buscar por ID |
| PUT | `/astronautas/{id}` | Atualizar dados |
| DELETE | `/astronautas/{id}` | Remover astronauta |
| GET | `/astronautas?status=DISPONIVEL` | Filtrar por status |

Status aceitos: `DISPONIVEL`, `EM_MISSAO` e `AFASTADO`.

### Exemplo de cadastro

```json
{
  "id": 999,
  "nome": "Carlos Mendes",
  "pais": "Brasil",
  "idade": 32,
  "anosExperiencia": 6,
  "status": "DISPONIVEL"
}
```

O ID enviado no corpo é ignorado e um ID é gerado automaticamente.

### Regras aplicadas

- Nome e país obrigatórios e não vazios.
- Idade obrigatória e maior ou igual a 18 anos.
- Anos de experiência não podem ser negativos.
- Status obrigatório.
- Não permite nomes duplicados (sem diferenciar maiúsculas/minúsculas).
- IDs zero ou negativos retornam 400.
- IDs inexistentes em busca, atualização e remoção retornam 404.
- Cadastro retorna 201; listagem, busca e atualização retornam 200; remoção retorna 204.

Os dados de astronautas ficam em memória e reiniciam junto com a aplicação.

## Testes

```powershell
.\mvnw.cmd test
```
