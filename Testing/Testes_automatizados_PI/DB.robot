*** Settings ***
Library  SeleniumLibrary
Resource  DB.resource
Test Setup       Abrir o Site da aplicação
Test Teardown    Close Browser

*** Test Cases ***
#----------------------------------Teste 1
Cadastro
  Clicar no botão de Login
  Clicar no botão de Registro
  Escrever o nome Rodrigo
  Escrever o sobrenome Oliveira
  Escrever e-mail orodrigo@gmail.com
  Confirmar e-mail orodrigo@gmail.com
  Escrever senha: 112233
  Confirmar senha com 6 digitos: 112233
  Clicar no botão criar conta
#----------------------------------Teste 2
Login
  Clicar no botão de Login
  Escrever e-mail orodrigo@gmail.com
  Escrever senha: 112233
  Clicar no botão enviar
#----------------------------------Teste 3
Categoria Coupes
  Buscar a categoria Coupes na Homepage
  Selecionar categoria Coupes
  Voltar para Homepage
#----------------------------------Teste 4
Categoria Conversiveis
  Buscar a categoria Conversiveis na Homepage
  Selecionar categoria Conversiveis
  Voltar para Homepage
#----------------------------------Teste 5
Categoria Sedans
  Buscar a categoria Sedans na Homepage
  Selecionar categoria Sedans
  Voltar para Homepage
#----------------------------------Teste 6
Categoria Suv
  Buscar a categoria Suv na Homepage
  Selecionar categoria Suv
  Voltar para Homepage
  
#----------------------------------Teste 7
Processo de reserva em SP
  Clicar no botão de Login
  Escrever e-mail orodrigo@gmail.com
  Escrever senha: 112233
  Clicar no botão enviar
  Buscar a barra de Pesquisa e abrir lista opção 1
  Clicar no botão buscar
  Selecionar o carro
  Visualizar Galeria
  Visualizar Características
  Selecionar data 1
  Selecionar data 2
  Clicar no botão Iniciar Reserva
  Escolher o horário de retirada
  Confirmar reserva

#----------------------------------Teste 8
Processo de reserva em RJ
  Clicar no botão de Login
  Escrever e-mail orodrigo@gmail.com
  Escrever senha: 112233
  Clicar no botão enviar
  Buscar a barra de Pesquisa e abrir lista opção 2
  Clicar no botão buscar
  Selecionar o carro
  Visualizar Galeria
  Visualizar Características
  Selecionar data 3
  Selecionar data 4
  Clicar no botão Iniciar Reserva
  Escolher o horário de retirada
  Confirmar reserva

#----------------------------------Teste 9
Processo de reserva em Recife
  Clicar no botão de Login
  Escrever e-mail orodrigo@gmail.com
  Escrever senha: 112233
  Clicar no botão enviar
  Buscar a barra de Pesquisa e abrir lista opção 3
  Clicar no botão buscar
  Selecionar o carro
  Visualizar Galeria
  Visualizar Características
  Selecionar data 5
  Selecionar data 6
  Clicar no botão Iniciar Reserva
  Escolher o horário de retirada
  Confirmar reserva

#----------------------------------Teste 10
Processo de reserva em Manaus
  Clicar no botão de Login
  Escrever e-mail orodrigo@gmail.com
  Escrever senha: 112233
  Buscar a barra de Pesquisa e abrir lista opção 4
  Clicar no botão buscar
  Selecionar o carro
  Visualizar Galeria
  Visualizar Características
  Selecionar data 7
  Selecionar data 8
  Clicar no botão Iniciar Reserva
  Escolher o horário de retirada
  Confirmar reserva

#----------------------------------Teste 11
Processo de reserva em Florianópolis
  Clicar no botão de Login
  Escrever e-mail orodrigo@gmail.com
  Escrever senha: 112233
  Clicar no botão enviar
  Buscar a barra de Pesquisa e abrir lista opção 5
  Clicar no botão buscar
  Selecionar o carro
  Visualizar Galeria
  Visualizar Características
  Selecionar data 9
  Selecionar data 10
  Clicar no botão Iniciar Reserva
  Escolher o horário de retirada
  Confirmar reserva