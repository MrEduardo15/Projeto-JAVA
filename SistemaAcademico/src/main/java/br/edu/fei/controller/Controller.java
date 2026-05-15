/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.controller;

import br.edu.fei.model.Usuario;
import br.edu.fei.model.dao.Conexao;
import br.edu.fei.model.dao.UsuarioDAO;
import br.edu.fei.view.CadastroJFrame;
import br.edu.fei.view.LoginJFrame;
import br.edu.fei.view.Filmes;
import br.edu.fei.view.curtidosView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import br.edu.fei.view.PlaylistFrame;
/**
 *
 * @author Pichau
 */
public class Controller {

    private CadastroJFrame cadastroView;
    private LoginJFrame loginView;
    private UsuarioDAO usuarioDAO;
    private Filmes filmesView;
    private curtidosView curtidosView;

    public Controller(CadastroJFrame cadastroView, LoginJFrame loginView) {

        this.cadastroView = cadastroView;
        this.loginView = loginView;
        this.filmesView = new Filmes();
        this.curtidosView = new curtidosView();
        this.filmesView.setControllerLista(this);
        this.curtidosView.setControllerCurtidos(this);
 
        try {
            Connection conn = new Conexao().getConnection();
            this.usuarioDAO = new UsuarioDAO(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void cadastrarUsuario() {

        String nome = cadastroView.getTfNome().getText();
        String usuario = cadastroView.getTfUsuario().getText();
        String senha = cadastroView.getTfSenha().getText();

        Usuario u = new Usuario(nome, usuario, senha);

        try {

            usuarioDAO.cadastrar(u);

            System.out.println("USUÁRIO CADASTRADO");

            cadastroView.dispose();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//----------------------------------------------------------------------------------------------------------------------------------------------------------
    public void login() {

        String usuario = loginView.getTfUsuario().getText();
        String senha = loginView.getTfSenha().getText();

        try {

            boolean existe = usuarioDAO.login(usuario, senha);

            if (existe) {

                System.out.println("LOGIN OK");
                loginView.dispose();

                


            } else {

                System.out.println("LOGIN INCORRETO");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    public void carregarTabelaFilmes() {
    try {
        DefaultTableModel modelo = (DefaultTableModel) this.filmesView.getTabelaFilmes().getModel();
        modelo.setNumRows(0);

        ResultSet resultado = this.usuarioDAO.consultarVideos();

        while (resultado.next()) {
            modelo.addRow(new Object[]{
                resultado.getInt("id_video"),
                resultado.getString("nome"),
                resultado.getString("canal"),
                resultado.getString("descricao")
            });
        }
        this.usuarioDAO.getConn();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public void acaoCurtir() {
    // 1. Pegar o índice da linha selecionada
    int linhaSelecionada = this.filmesView.getTabelaFilmes().getSelectedRow();

    // 2. Verificar se existe uma linha selecionada (evita erro de clicar no vazio)
    if (linhaSelecionada != -1) {
        // 3. Extrair os dados da JTable (coluna por coluna)
        int numero_curtidas = (int) this.filmesView.getTabelaFilmes().getValueAt(linhaSelecionada, 0);
        String nome = (String) this.filmesView.getTabelaFilmes().getValueAt(linhaSelecionada, 1);
        String canal = (String) this.filmesView.getTabelaFilmes().getValueAt(linhaSelecionada, 2);
        String descricao = (String) this.filmesView.getTabelaFilmes().getValueAt(linhaSelecionada, 3);

        // 4. Enviar para o banco de dados
        this.usuarioDAO.curtirFilme(numero_curtidas,nome, canal, descricao);
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, selecione um filme na tabela primeiro.");
    }
}
    
    public void listarCurtidos() {
    try {
        // 1. Pega o modelo da tabela de curtidos (que deve estar na sua curtidosView)
        DefaultTableModel modelo = (DefaultTableModel) this.curtidosView.getTabelaCurtidos().getModel();
        modelo.setNumRows(0);

        ResultSet resultado = this.usuarioDAO.listarCurtidos();
if (resultado == null) {
    JOptionPane.showMessageDialog(null, "Erro ao buscar curtidos.");
    return;
}
        // 4. Preenche a tabela linha por linha
        while (resultado.next()) {
            modelo.addRow(new Object[]{
                resultado.getInt("numero_curtidas"),
                resultado.getString("nome"),
                resultado.getString("canal"),
                resultado.getString("descricao")
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar favoritos: " + e.getMessage());
        e.printStackTrace();
    }
}
    
public void acaoDescurtir() {
    int linhaSelecionada =
        this.curtidosView.getTabelaCurtidos().getSelectedRow();

    if (linhaSelecionada != -1) {

        int id = (int)
            this.curtidosView.getTabelaCurtidos()
            .getValueAt(linhaSelecionada, 0);

        this.usuarioDAO.descurtirFilme(id);

        listarCurtidos();

    } else {

        JOptionPane.showMessageDialog(null,
            "Selecione um filme para remover.");
    }
}

//--------------------------------------------------------------------------------------------------------------------------------------------------------

public void acaoCriarPlaylist() {

    String nomePlaylist = JOptionPane.showInputDialog(
        null,
        "Digite o nome da playlist:"
    );

    if (nomePlaylist == null ||
        nomePlaylist.trim().isEmpty()) {

        JOptionPane.showMessageDialog(
            null,
            "Nome inválido!"
        );

        return;
    }



    // Pega linhas selecionadas
    int[] linhasSelecionadas =
        this.filmesView.getTabelaFilmes()
        .getSelectedRows();

    if (linhasSelecionadas.length == 0) {

        JOptionPane.showMessageDialog(
            null,
            "Selecione filmes."
        );

        return;
    }

    // Salva todos no banco
    for (int linha : linhasSelecionadas) {

        int id = (int)
            this.filmesView.getTabelaFilmes()
            .getValueAt(linha, 0);

        String nome = (String)
            this.filmesView.getTabelaFilmes()
            .getValueAt(linha, 1);

        String canal = (String)
            this.filmesView.getTabelaFilmes()
            .getValueAt(linha, 2);

        String descricao = (String)
            this.filmesView.getTabelaFilmes()
            .getValueAt(linha, 3);

        this.usuarioDAO.adicionarFilmeNaPlaylist(
            nomePlaylist,
            id,
            nome,
            canal,
            descricao
        );
    }

    // Cria janela
PlaylistFrame frame =
    new PlaylistFrame(nomePlaylist, this.usuarioDAO);

    // Busca dados salvos
    PlaylistFrame playlistFrame = new PlaylistFrame(nomePlaylist, this.usuarioDAO);

    // Busca dados salvos
    ResultSet resultado = this.usuarioDAO.buscarFilmesPlaylist(nomePlaylist);

    try {
        while (resultado.next()) {
            // Agora usamos o nome correto: playlistFrame
            playlistFrame.adicionarFilmeNaTabela(
                resultado.getInt("id_filme"),
                resultado.getString("nome_filme"),
                resultado.getString("canal"),
                resultado.getString("descricao")
            );
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao carregar playlist.");
    }

    playlistFrame.setVisible(true);
}
public void acaoPesquisarPlaylist() {

    // Pergunta nome da playlist
    String nomePlaylist = JOptionPane.showInputDialog(
        null,
        "Digite o nome da playlist:"
    );

    // Verifica nome inválido
    if (nomePlaylist == null ||
        nomePlaylist.trim().isEmpty()) {

        JOptionPane.showMessageDialog(
            null,
            "Nome inválido!"
        );

        return;
    }

    // Busca no banco
    ResultSet resultado =
        this.usuarioDAO.pesquisarPlaylist(
            nomePlaylist
        );

    try {

        // Cria janela
PlaylistFrame playlistFrame = new PlaylistFrame(nomePlaylist, this.usuarioDAO);

        boolean encontrou = false;

        // Percorre resultados
        while (resultado.next()) {

            encontrou = true;

            playlistFrame.adicionarFilmeNaTabela(

                resultado.getInt("id_filme"),

                resultado.getString("nome_filme"),

                resultado.getString("canal"),

                resultado.getString("descricao")
            );
        }

        // Verifica se encontrou
        if (!encontrou) {

            JOptionPane.showMessageDialog(
                null,
                "Playlist não encontrada."
            );

            playlistFrame.dispose();

            return;
        }

        // Mostra janela
        playlistFrame.setVisible(true);

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            null,
            "Erro ao carregar playlist."
        );
    }
}



//-------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void abrirCadastro() {
        cadastroView.setVisible(true);
    }
    public void abrirFilmes() {
        carregarTabelaFilmes(); // Chama o preenchimento antes de mostrar
        filmesView.setVisible(true);
    }
    public void abrirCurtidos() {
        listarCurtidos(); // Chama o preenchimento antes de mostrar
        curtidosView.setVisible(true);
    }
    
//----------------------------------------------------------------------------------------------------------------------------------
    
    }
