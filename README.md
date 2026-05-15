 # 🎓 Sistema Acadêmico (Projeto-JAVA)

 ![status-badge](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
 ![license-badge](https://img.shields.io/badge/license-academica-lightgrey)

 Descrição
 ---------

 Aplicação desktop Java (Swing) criada como projeto acadêmico para praticar
 conceitos de Arquitetura de Software e Programação Orientada a Objetos. A
 aplicação simula um sistema acadêmico simples: gerenciamento de usuários,
 visualização de vídeos/filmes e playlists.

LINK DO PROJETO --> github.com/MrEduardo15/Projeto-JAVA
 Índice
 -----

 - [🎬 Demonstração](#demonstração)
 - [✨ Principais recursos](#principais-recursos)
 - [⚙️ Requisitos](#requisitos)
 - [🚀 Instalação e execução](#instalação-e-execução)
 - [📁 Estrutura do projeto](#estrutura-do-projeto)
 - [🛠️ Como contribuir](#como-contribuir)
 - [📜 Licença](#licença)
 - [✉️ Contato](#contato)
 - [🏷️ Tópicos](#tópicos)

 Demonstração
 -----------

 Abra a aplicação na sua IDE executando a classe principal. (Sugestão: adicione
 screenshots em `docs/` e vincule aqui.)

 ✨ Principais recursos
 ---------------------

 - ✅ Login de usuário
 - 🎬 Visualização de filmes/vídeos
 - ❤️ Playlists e curtidas
 - 🧩 Arquitetura em camadas: modelo, controller, DAO e view (Swing)

 ⚙️ Requisitos
 ------------

 - Java JDK 11 ou superior
 - Maven 3.6+ (opcional, para build/testes)

 🚀 Instalação e execução
 -----------------------

 1) Build (via Maven):

 ```bash
 mvn clean package
 ```

 2) Executar no IDE:

 Abra a classe `br.edu.fei.sistemaacademico.SistemaAcademico` e execute-a como
 aplicação Java.

 3) Executar via Maven (opcional):

 ```bash
 mvn exec:java -Dexec.mainClass="br.edu.fei.sistemaacademico.SistemaAcademico"
 ```

 🧪 Testes
 -------

 ```bash
 mvn test
 ```

 📁 Estrutura do projeto
 ----------------------

 Principais caminhos e classes:

 - [src/main/java/br/edu/fei/sistemaacademico/SistemaAcademico.java](src/main/java/br/edu/fei/sistemaacademico/SistemaAcademico.java#L1) — ponto de entrada
 - [src/main/java/br/edu/fei/controller/Controller.java](src/main/java/br/edu/fei/controller/Controller.java#L1) — controlador principal
 - [src/main/java/br/edu/fei/model/Usuario.java](src/main/java/br/edu/fei/model/Usuario.java#L1) — modelo `Usuario`
 - [src/main/java/br/edu/fei/model/Video.java](src/main/java/br/edu/fei/model/Video.java#L1) — modelo `Video`
 - [src/main/java/br/edu/fei/model/dao/Conexao.java](src/main/java/br/edu/fei/model/dao/Conexao.java#L1) — classe de conexão / DAO
 - [src/main/java/br/edu/fei/model/dao/UsuarioDAO.java](src/main/java/br/edu/fei/model/dao/UsuarioDAO.java#L1) — exemplo de DAO
 - [src/main/java/br/edu/fei/view](src/main/java/br/edu/fei/view) — formulários e telas (Swing)

 Boas práticas
 ------------

 - Substitua a implementação de DAO por uma conexão real (JDBC/Hibernate) para
   persistência permanente.
 - Separe recursos (imagens, vídeos) em `resources/` e documentação em `docs/`.

 🛠️ Como contribuir
 ------------------

 - Abra uma issue descrevendo a proposta.
 - Faça um fork e envie um pull request com mudanças pequenas e commits claros.

 📜 Licença
 ---------

 Projeto para fins acadêmicos; confirme com o instrutor antes de uso público.

 ✉️ Contato
 ---------

 Abra uma issue neste repositório para dúvidas ou sugestões.

 ---

 Se quiser, eu posso:

 - ➕ adicionar badges reais (build/coverage) se você usar integração CI;
 - 🖼️ gerar screenshots e adicionar em `docs/`;
 - 📦 criar um JAR executável no `target/` e instruções para rodar via `java -jar`.

 🏷️ Tópicos
 ---------

 - Java
 - Swing
 - POO
 - Arquitetura de Software
 - Projetos Acadêmicos




