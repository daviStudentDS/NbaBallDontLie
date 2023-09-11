# Atualização de Trabalho - Luís Davi e Maurício Leandro - 3°DS 
# Criação de API:
- Pesquisa de jogadores e times, assim como a api externa do aplicativo focadas ambas em basquete.
- Link da API externa: https://app.balldontlie.io;
- Funções e Activitys novas possíveis: Possivelmente mais três activitys - Duas para as pesquisas dos respectivos dados e uma para uma possível implementação de imagens que ao se interagir com a tela, irá gerar a imgagem de um jogador aleatório da Etec Professor Basilides de Godoy;
- Descrição da nossa API: Assim como a api externa, ela armazenará dados sobre times e jogadores de times diferentes e será possível efetuar a pesquissa de seus dados no app;
- Alterações: Acredito que além das activitys e provavelmente a mecânica da imagem aleatória, não seja alterado muitas coisas no aplicativo.

# API BallDo - Localizada na Branch Api_Change:
# Coach:
<img src= "https://github.com/daviStudentDS/NbaBallDontLie/assets/101807467/3dbf7caa-8150-4714-8c8b-d383f5b7ae0e.png"/>
# Player:
<img src= "https://github.com/daviStudentDS/NbaBallDontLie/assets/101807467/32b26ada-3265-4beb-9eb5-5627524acbd9.png"/>
# Team:
<img src= "https://github.com/daviStudentDS/NbaBallDontLie/assets/101807467/30ce709b-4c6a-4662-8710-fc29ad79ff47.png"/>

# Modelo de Entidade e Relacionamento(MER) API que será criada:

<img src="https://github.com/daviStudentDS/NbaBallDontLie/assets/101807467/630fffae-c473-4fcb-ac4c-d86e01a7032b.png"/>
</br>
</br>
<img src="https://github.com/daviStudentDS/NbaBallDontLie/assets/101807467/45c99c4e-b91c-4f91-813d-bd28470a6d0d.png"/>

# Diagrama de Classes - API que será criada:

<img src="https://github.com/daviStudentDS/NbaBallDontLie/assets/101807467/455f3817-b201-43d2-8e0c-6b0b7f388c0e.png"/>

# NbaBallDontLie
# Documentação da API BallDontLie - Luís Davi e Maurício Leandro - 3°DS - LINK PARA O VÍDEO DE APRESENTAÇÃO: https://youtu.be/9A3apgiAePI

BallDontLie - API que fornece estatísticas de basquete sobre a NBA. Esta API é gratuita e aberta para uso público.

A API BallDontLie é uma API pública gratuita e aberta para uso público que fornece estatísticas de basquete da NBA. Com esta API, é possível obter informações sobre jogos, equipes, jogadores e estatísticas de jogadores em jogos específicos.

URL Base
O URL base para todas as solicitações é: https://www.balldontlie.io/api/v1/

Recursos
A API fornece quatro recursos principais: jogos (Games), equipes (Teams), jogadores (Players) e estatísticas (Stats). Cada recurso possui endpoints específicos para solicitações.

Jogos (Games)
O recurso Games fornece informações sobre os jogos da NBA. É possível filtrar por data (start_date e end_date), pela equipe da casa (home_team) e pela equipe visitante (visitor_team). Os endpoints para solicitações deste recurso são:

GET /games: Retorna uma lista de jogos.
GET /games/:id: Retorna um jogo específico pelo seu ID.
Equipes (Teams)
O recurso Teams fornece informações sobre as equipes da NBA. Os endpoints para solicitações deste recurso são:

GET /teams: Retorna uma lista de equipes.
GET /teams/:id: Retorna uma equipe específica pelo seu ID.
Jogadores (Players)
O recurso Players fornece informações sobre os jogadores da NBA. Os endpoints para solicitações deste recurso são:

GET /players: Retorna uma lista de jogadores.
GET /players/:id: Retorna um jogador específico pelo seu ID.
Estatísticas (Stats)
O recurso Stats fornece estatísticas de jogadores em jogos específicos. É possível filtrar por data (start_date e end_date), pelo ID do jogador (player_ids) e pelo ID do jogo (game_ids). Os endpoints para solicitações deste recurso são:

GET /stats: Retorna uma lista de estatísticas de jogadores em jogos específicos.
Parâmetros de consulta (Query parameters)
A API suporta vários parâmetros de consulta que podem ser usados ​​para filtrar, classificar e limitar os resultados das solicitações. Os principais parâmetros de consulta incluem:

per_page: Número de resultados por página.
page: Número da página desejada.
sort: Ordenação dos resultados.
search: Busca por um termo específico.
fields: Campos específicos a serem retornados na resposta.
Limites de taxa (Rate limits)
A API BallDontLie possui um limite de taxa de 150 solicitações por minuto. Se você exceder esse limite, receberá uma resposta com o código de status 429 (Muitas solicitações). É importante que você respeite esse limite para evitar bloqueios de IP.

Erros
A API retornará erros com o código de status HTTP apropriado. O corpo da resposta incluirá uma mensagem de erro explicando o problema.

Autenticação
A API BallDontLie não requer autenticação para ser usada.



# Endpoints que serão utilizados ou considerados na aplicação e seus recursos


Endpoints de jogadores
1. /players
Este endpoint retorna uma lista de jogadores. Os resultados podem ser filtrados por nome, posição e equipe.

Exemplo:
GET https://www.balldontlie.io/api/v1/players?search=LeBron James&per_page=5
Este exemplo retorna uma lista de até 5 jogadores que contêm "LeBron James" em seu nome.

2. /players/{id}
Este endpoint retorna informações detalhadas sobre um jogador específico com o ID especificado.

Exemplo:
GET https://www.balldontlie.io/api/v1/players/237

Endpoints de times
3. /teams
Este endpoint retorna uma lista de times. Os resultados podem ser filtrados por nome e cidade.

Exemplo: 
GET https://www.balldontlie.io/api/v1/teams?per_page=10
Este exemplo retorna uma lista de até 10 times.

4. /teams/{id}
Este endpoint retorna informações detalhadas sobre um time específico com o ID especificado.

Exemplo:
GET https://www.balldontlie.io/api/v1/teams/1

# Planificação - Telas e funcionalidades da aplicação

O aplicativo é uma plataforma de pesquisa e consulta de dados da NBA, focando em jogadores, times e jogos. Possui as seguintes telas:

1. Tela de carregamento (Splash)
2. Menu, com botões direcionando para as telas de busca e consulta
3. Tela de pesquisa, busca e consulta sobre jogadores
Respectivo Resultado//
4. Tela de pesquisa, busca e consulta sobre times
Respectivo Resultado//
5. Tela de histórico, com dados sobre as pesquisas recentes em ambas as telas.
6. Tela de favoritos que guardará as pesquisas e resultados pela opção favoritar

A aplicação tem o intuito de ser um aplicativo de pesquisa sobre jogadores e times da nba, onde a partir
da tela de menu, se partirá para as seguintes de pesquisa relativas a Times e Jogadores, e a tela do Histórico. Cada tela de resultado
para os dados pesquisados e enviados, terá uma opção de favoritar que salvará o dados da pesquisa em questão, podendo ser 
acessada através da tela de Histórico o mandando para a tela Pesquisas favoritas, mostrando os mesmos e respectivos dados guardados.

# Navegação

<img src="https://user-images.githubusercontent.com/101807467/236359232-73ae02c1-c021-4e33-ad57-751374add293.png">

# Diagrama de Classes Diagrama de Banco

##### Diagrama de Classes:
<img src="https://user-images.githubusercontent.com/101807467/236364928-1250ffa6-842f-4a15-a38c-8bea5e49fd25.png">

##### Diagrama do Banco:
<img src="https://user-images.githubusercontent.com/101807467/236358918-1bfe73ae-f074-40b8-9811-77605ff34983.png">


# Protótipo Design

## Link para o design do Figma: https://www.figma.com/file/9GZipho6COcutL8qIipKAP/PROT%C3%93TIPO---BALL-DONT-LIE?node-id=0%3A1&t=4mdtKz97yTnJy0zq-1




