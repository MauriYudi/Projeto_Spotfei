# Projeto Spotifei

# Aviso
###### Caso ocorra um erro com o postgresql não estar em libraries, basta adicionar o arquivo "postgresql-42.7.5.jar" da pasta "Projeto_Spotfei".

# MVC
O projeto está organizado de acordo com a arquitetura MVC(model, view, control), também possuindo os pacotes DAO, responsável por conter as classes responsáveis pela conexão com o banco de dados e suas diversas operações, e o pacote Spotfei, que contém apenas a classe main().

# Login e Cadastro

O login e o cadastro são as primeiras opções do usuário, cada uma possui seu próprio controlador, assim como sua janela específica.


# Playlists
No menu principal, o usuário pode acessar opções relacionadas às playlists, como criar novas, ver as já existentes, editar playlists adicionando ou removendo músicas assim como removendo playlists indesejadas.

# Músicas

O usuário pode também, pelo menu principal, buscar músicas por nome, artista ou gênero, podendo depois ver suas informações, curtir ou descurtir, assim como adicioná-las à suas playlists.

# Histórico
A última opção do menu principal leva o usuário ao Histórico, onde ele pode ver as 10 últimas músicas que ele acessou assim como a lista de suas músicas curtidas e descurtidas.

# Sair

O botão sair leva o usuário de volta à tela de login, onde ele pode entrar com outra conta ou criar uma nova.
