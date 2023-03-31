Feature: Teste para pessoa
  Background: Dado um banco de dados com Charles Xavier cadastrado

  Scenario: Listar pessoas cadastradas
    Given Um novo usuário
      | nome      | sobrenome | dataNascimento | cpf            |
      | Wolverine | Logan     | 01/01/1900     | 999.999.999-01 |

    Then A quantidade de usuários é 2