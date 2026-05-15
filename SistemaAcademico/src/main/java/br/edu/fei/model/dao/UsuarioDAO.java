/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.model.dao;

import br.edu.fei.model.Usuario;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Pichau
 */
public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO(Connection conn) {
        
        this.conn = conn;
    }

    public void cadastrar(Usuario usuario) throws SQLException {

        String sql = """
        INSERT INTO usuarios(nome, usuario, senha)
        VALUES (?, ?, ?)
        """;

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getUsuario());
        stmt.setString(3, usuario.getSenha());

        stmt.execute();
    }

    public boolean login(String usuario, String senha)
            throws SQLException {

        String sql = """
        SELECT * FROM usuarios
        WHERE usuario = ? AND senha = ?
        """;

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, usuario);
        stmt.setString(2, senha);

        ResultSet rs = stmt.executeQuery();

        return rs.next();
    }
    public ResultSet consultarVideos() {
        String sql = "SELECT id_video, nome, canal, descricao FROM videos";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            return pstm.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erro no DAO: " + e.getMessage());
            return null;
        }
    }


    public Object getConn() {
        return this.conn;
    }

public void curtirFilme(Integer numero_curtidas, String nome, String canal, String descricao) {

    String sql = "INSERT INTO curtidas (nome, canal, descricao) VALUES (?, ?, ?)";

    try {
System.out.println(conn.getCatalog());
System.out.println(conn.getMetaData().getURL());


        PreparedStatement pstm = conn.prepareStatement(sql);
        System.out.println(conn);
        pstm.setString(1, nome);
        pstm.setString(2, canal);
        pstm.setString(3, descricao);
        

         pstm.executeUpdate();


        JOptionPane.showMessageDialog(null, "Filme curtido com sucesso!");

        pstm.close();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(null, "Erro ao curtir: " + e.getMessage());
    }
}
public ResultSet listarCurtidos() {

    String sql = "SELECT * FROM curtidas";

    try {

        PreparedStatement pstm = conn.prepareStatement(sql);

        return pstm.executeQuery();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(null, "Erro no banco: " + e.getMessage());
        e.printStackTrace();

        return null;
    }
}
public void descurtirFilme(int numero_curtidas) { // Só precisa do ID para deletar
    String sql = "DELETE FROM curtidas WHERE numero_curtidas = ?";

    try {

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, numero_curtidas);

        int rowsAffected = pstm.executeUpdate();

        pstm.close();

        if (rowsAffected > 0) {

            JOptionPane.showMessageDialog(null,
                "Curtida removida com sucesso!");

        } else {

            JOptionPane.showMessageDialog(null,
                "Este vídeo não estava nas curtidas.");
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(null,
            "Erro ao descurtir: " + e.getMessage());
    }
}
//---------------------------------------------------------------
public void criarPlaylist(String nomeFilme) {

    String sql = "INSERT INTO playlists_filmes (nome) VALUES (?)";

    try {

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, nomeFilme);

        pstm.executeUpdate();


        JOptionPane.showMessageDialog(
            null,
            "Playlist criada com sucesso!"
        );



    } catch (SQLException e) {
 
        }
}
public void adicionarFilmeNaPlaylist(

    String nomePlaylist,
    int idFilme,
    String nomeFilme,
    String canal,
    String descricao
) {

    String sql =
        "INSERT INTO playlist_filmes " +
        "(nome_playlist, id_filme, nome_filme, canal, descricao) " +
        "VALUES (?, ?, ?, ?, ?)";

    try {

        PreparedStatement pstm =
            conn.prepareStatement(sql);

        pstm.setString(1, nomePlaylist);
        pstm.setInt(2, idFilme);
        pstm.setString(3, nomeFilme);
        pstm.setString(4, canal);
        pstm.setString(5, descricao);

        pstm.executeUpdate();

        pstm.close();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            null,
            "Erro ao salvar filme: " + e.getMessage()
        );
    }
}


public ResultSet buscarFilmesPlaylist(
    String nomePlaylist
) {

    String sql =
        "SELECT * FROM playlist_filmes " +
        "WHERE nome_playlist = ?";

    try {

        PreparedStatement pstm =
            conn.prepareStatement(sql);

        pstm.setString(1, nomePlaylist);

        return pstm.executeQuery();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            null,
            "Erro ao buscar playlist: "
            + e.getMessage()
        );

        return null;
    }
}

public ResultSet pesquisarPlaylist(
    String nomePlaylist
) {

    String sql =
        "SELECT * FROM playlist_filmes " +
        "WHERE nome_playlist = ?";

    try {

        PreparedStatement pstm =
            conn.prepareStatement(sql);

        pstm.setString(1, nomePlaylist);

        return pstm.executeQuery();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            null,
            "Erro ao pesquisar playlist: "
            + e.getMessage()
        );

        return null;
    }
}
public void removerFilmeDaPlaylist(

    String nomePlaylist,
    int idFilme
) {

    String sql =
        "DELETE FROM playlist_filmes " +
        "WHERE nome_playlist = ? " +
        "AND id_filme = ?";

    try {

        PreparedStatement pstm =
            conn.prepareStatement(sql);

        pstm.setString(1, nomePlaylist);

        pstm.setInt(2, idFilme);

        pstm.executeUpdate();

        pstm.close();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            null,
            "Erro ao remover filme: "
            + e.getMessage()
        );
    }
}
}