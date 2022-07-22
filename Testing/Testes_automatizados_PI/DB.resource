*** Variables ***
#----------------------------------Teste 1
${SITE}                  http://localhost:3000/
${NAVEGADOR}             Chrome
${BOTAO_LOGIN}           css=.container__nav_right > a:nth-child(2) > span
${BOTAO_REGISTRO}        css=#root > div.container.container-login > div.card-login > form > p > a
${CAMPO_NOME}            css=#name
${NOME}                  Rodrigo
${CAMPO_SOBRENOME}       css=#lastname
${SOBRENOME}             Oliveira
${CAMPO_EMAIL}           css=#email
${EMAIL}                 orodrigo@gmail.com
${CAMPO_CONF_EMAIL}      css=#confirmEmail
${CONF_EMAIL}            orodrigo@gmail.com
${CAMPO_SENHA}           css=#password
${SENHA}                 112233
${CAMPO_CONF_SENHA}      css=#confirmPassword
${CONF_SENHA}            112233
${BOTAO_CRIAR_CONTA}     css=#root > div.container.container-register > div.register-form-car > div.form-register-fields > form > button
#----------------------------------Teste 2
${BOTAO_LOGAR}           css=#root > div.container.container-login > div.card-login > form > button
#----------------------------------Teste 3
${BOTAO_COUPES}          id=coupes
${BOTAO_VOLTAR}          css=#root > div.description_header_all > nav > a > div > svg
#----------------------------------Teste 4
${BOTAO_CONVERSIVEIS}    id=conversiveis
#----------------------------------Teste 5
${BOTAO_SEDANS}          id=sedans
#----------------------------------Teste 6
${BOTAO_SUV}             id=suv
#----------------------------------Teste 7
${BOTAO_BUSCAR}          xpath=//*[@id="search-bar"]/div/div[2]/div[3]/button
${CARRO1}                css=.col-xs-12:nth-child(1) .card-img
${CARRO1_BT_VER}         xpath=//*[@id="root"]/div[3]/div/div/div[1]/div/div[2]/div[2]/div[2]/div/button
${CARRO1_FT1}            css=.container_images_preview > div:nth-child(1) > .d-block
${CARRO1_FT2}            css=.container_images_preview > div:nth-child(2) > .d-block
${CARRO1_FT3}            css=.container_images_preview > div:nth-child(3) > .d-block
${CARRO1_FT4}            css=.container_images_preview > div:nth-child(4) > .d-block
${CARRO1_FT5}            css=.container_images_preview > div:nth-child(5) > .d-block
${CARRO1_FT6}            css=.container_images_preview > div:nth-child(6) > .d-block
${CARRO1_BT_CLOSED}      css=.btn-close
${BT_INICIAR_RESERVA}    css=#root > div.date_free > div > div > div.card-add-date > a > button
${CAMPO_HORA}            css=#root > div:nth-child(3) > div.Header_desc > form > div > div > div.reserv_page_left > div.reserv_page_time_all > div > div > div > select
${HORA_ESCOLHIDA}        08:00:00
${BOTAO_RESERVA}         css=.btn-search
${BOTAO_HOME}            css=#responsive-navbar-nav > div > a
#----------------------------------Teste 8
${CARRO2}                css=#root > div.container.category_container > div > div.col-md-9 > div > div:nth-child(1) > a > div > div.singleCard_img_content > img
#----------------------------------Teste 9
${CARRO3}                css=#root > div.container.category_container > div > div.col-md-9 > div > div:nth-child(1) > a > div > div.singleCard_img_content > img
#----------------------------------Teste 10
${CARRO4}                xpath=//div[@id='root']/div[3]/div/div[2]/div/div/a/div/div/img
#----------------------------------Teste 11
${CARRO5}                css=.col-xs-12:nth-child(1) .card-img


*** Keywords ***
#----------------------------------Teste 1
Abrir o Site da aplicação  
  Open Browser              ${SITE}              ${NAVEGADOR}
  Maximize Browser Window

Clicar no botão de Login
  Wait and Click            ${BOTAO_LOGIN}

Clicar no botão de Registro
  Wait and Click            ${BOTAO_REGISTRO}

Escrever o nome Rodrigo
  Wait and Input Text       ${CAMPO_NOME}        ${NOME}

Escrever o sobrenome Oliveira
  Wait and Input Text       ${CAMPO_SOBRENOME}   ${SOBRENOME}

Escrever e-mail orodrigo@gmail.com
  Wait and Input Text       ${CAMPO_EMAIL}       ${EMAIL}

Confirmar e-mail orodrigo@gmail.com
  Wait and Input Text       ${CAMPO_CONF_EMAIL}  ${CONF_EMAIL}

Escrever senha: 112233
  Wait and Input Text       ${CAMPO_SENHA}       ${SENHA}

Confirmar senha com 6 digitos: 112233
  Wait and Input Text       ${CAMPO_CONF_SENHA}   ${CONF_SENHA}

Clicar no botão criar conta
  Wait and Click            ${BOTAO_CRIAR_CONTA}
  Sleep  4

#----------------------------------Teste 2
Clicar no botão enviar
  Wait and Click           ${BOTAO_LOGAR}
  Sleep  5

#----------------------------------Teste 3
Buscar a categoria Coupes na Homepage
  Execute JavaScript       window.scrollTo(2,2000)  

Selecionar categoria Coupes
  Sleep  2
  Wait and Click          ${BOTAO_COUPES}
  Sleep  5

Voltar para Homepage
  Wait and Click           ${BOTAO_VOLTAR}

#----------------------------------Teste 4
Buscar a categoria Conversiveis na Homepage
  Execute JavaScript       window.scrollTo(2,2300)  
  Sleep  5

Selecionar categoria Conversiveis
  Sleep  2
  Wait and Click           ${BOTAO_CONVERSIVEIS}
  Sleep  5

#----------------------------------Teste 5
Buscar a categoria Sedans na Homepage
  Execute JavaScript       window.scrollTo(2,2600)  
  Sleep  5

Selecionar categoria Sedans
  Sleep  2
  Wait and Click           ${BOTAO_SEDANS}
  Sleep  5

#----------------------------------Teste 6
Buscar a categoria Suv na Homepage
  Execute JavaScript       window.scrollTo(2,3000)  
  Sleep  5

Selecionar categoria Suv
  Sleep  2
  Wait and Click           ${BOTAO_SUV}
  Sleep  5
 
#----------------------------------Teste 7
Buscar a barra de Pesquisa e abrir lista opção 1
  Click element    xpath=//*[@id="search-bar"]/div/div[2]/div[1]/select/option[2]

Clicar no botão buscar
  Sleep  3
  Wait and Click           ${BOTAO_BUSCAR}

Selecionar o carro
  Wait and Click           ${CARRO1}

Visualizar Galeria
  Sleep  3
  Wait and Click           ${CARRO1_BT_VER}
  Wait and Click           ${CARRO1_FT1}
  Wait and Click           ${CARRO1_FT2}
  Wait and Click           ${CARRO1_FT3}
  Wait and Click           ${CARRO1_FT4}
  Wait and Click           ${CARRO1_FT5}
  Wait and Click           ${CARRO1_FT6}
  Wait and Click           ${CARRO1_BT_CLOSED}

Visualizar Características
  Sleep  3

Selecionar data 1
  Execute JavaScript       window.scrollTo(2,1100)  
  Sleep  5
  Click element          xpath=//*[@id="root"]/div[4]/div/div/div[1]/div/div/div/div[2]/div[1]/div[2]/div[2]/div[2]/span
Selecionar data 2
  Click element          xpath=//*[@id="root"]/div[4]/div/div/div[1]/div/div/div/div[2]/div[1]/div[2]/div[2]/div[3]/span

Clicar no botão Iniciar Reserva
  Wait and Click            ${BT_INICIAR_RESERVA}

Escolher o horário de retirada
  Select From List by Value    ${CAMPO_HORA}         ${HORA_ESCOLHIDA}

Confirmar reserva
  Wait and Click           ${BOTAO_RESERVA}

#----------------------------------Teste 8
Buscar a barra de Pesquisa e abrir lista opção 2
  Click element    xpath=//*[@id="search-bar"]/div/div[2]/div[1]/select/option[3]

Selecionar data 3
  Execute JavaScript       window.scrollTo(2,1100)  
  Sleep  5
  Click element          xpath=//*[@id="root"]/div[4]/div/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div[5]/div[2]/span
Selecionar data 4
  Click element          xpath=//*[@id="root"]/div[4]/div/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div[5]/div[3]/span

#----------------------------------Teste 9
Buscar a barra de Pesquisa e abrir lista opção 3
  Click element    xpath=//*[@id="search-bar"]/div/div[2]/div[1]/select/option[4]

Selecionar data 5
  Execute JavaScript       window.scrollTo(2,1100)  
  Sleep  5
  Click element          xpath=//*[@id="root"]/div[4]/div/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div[2]/div[6]/span
Selecionar data 6
  Click element          xpath=//*[@id="root"]/div[4]/div/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div[2]/div[7]/span
#----------------------------------Teste 10
Buscar a barra de Pesquisa e abrir lista opção 4
  Click element    xpath=//*[@id="search-bar"]/div/div[2]/div[1]/select/option[5]

Selecionar data 7
  Execute JavaScript       window.scrollTo(2,1100)  
  Sleep  5
  Click element          xpath=//*[@id="root"]/div[4]/div/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div[3]/div[1]/span
Selecionar data 8
  Click element          xpath=//*[@id="root"]/div[4]/div/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div[3]/div[2]/span

#----------------------------------Teste 11
Buscar a barra de Pesquisa e abrir lista opção 5
  Click element    xpath=//*[@id="search-bar"]/div/div[2]/div[1]/select/option[6]

Selecionar data 9
  Execute JavaScript       window.scrollTo(2,1100)  
  Sleep  5
  Click element          xpath=//*[@id="root"]/div[4]/div/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div[6]/div[1]/span
Selecionar data 10
  Click element          xpath=//*[@id="root"]/div[4]/div/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div[6]/div[2]/span

















# **********Keywords Personalizadas**********        
Wait and Click
  [Arguments]                          ${ELEMENTO}          
  Wait Until Element is Visible        ${ELEMENTO}
  Wait Until Element is Enabled        ${ELEMENTO}
  Click Element                        ${ELEMENTO}

Wait and Input Text
  [Arguments]                          ${ELEMENTO}           ${TEXTO}
  Wait Until Element is Visible        ${ELEMENTO}
  Wait Until Element is Enabled        ${ELEMENTO}
  Input Text                           ${ELEMENTO}           ${TEXTO}

Wait and Select Label
  [Arguments]                          ${ELEMENTO}           ${LABEL}
  Wait Until Element is Visible        ${ELEMENTO}
  Wait Until Element is Enabled        ${ELEMENTO}
  Input Text                           ${ELEMENTO}           ${LABEL}

Scroll Wait and Click

  [Arguments]                          ${ELEMENTO}
  Wait Until Element is Visible        ${ELEMENTO}
  Wait Until Element is Enabled        ${ELEMENTO}
  Scroll Element Into View             ${ELEMENTO}
  Click Element                        ${ELEMENTO}