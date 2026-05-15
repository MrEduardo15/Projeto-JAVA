/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.view;


import br.edu.fei.model.dao.UsuarioDAO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author Pichau
 */
public class PlaylistFrame extends JFrame {
    private JButton botaoRemover;
    private UsuarioDAO usuarioDAO;
    private String nomePlaylist;
    
    private JTable tabelaPlaylist;

    public PlaylistFrame(String nomePlaylist, UsuarioDAO usuarioDAO) {

        this.nomePlaylist = nomePlaylist;
        
        this.nomePlaylist = nomePlaylist;

        this.usuarioDAO = usuarioDAO;
         
        setTitle(nomePlaylist);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // TABELA
        tabelaPlaylist = new JTable();

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID do Filme");
        modelo.addColumn("Nome");
        modelo.addColumn("Produtora");
        modelo.addColumn("Descrição");

        tabelaPlaylist.setModel(modelo);

        JScrollPane scrollPane = new JScrollPane(tabelaPlaylist);

        // BOTÃO REMOVER
        botaoRemover = new JButton("Remover da Playlist");

        botaoRemover.addActionListener(e -> {

            int linha = tabelaPlaylist.getSelectedRow();

            if (linha != -1) {

                int idFilme = (int)
                    tabelaPlaylist.getValueAt(linha, 0);
                

                // remove do banco
                usuarioDAO.removerFilmeDaPlaylist(
                    nomePlaylist,
                    idFilme
                );

                // remove da tabela
                
                ((DefaultTableModel)
                    tabelaPlaylist.getModel())
                    .removeRow(linha);

                JOptionPane.showMessageDialog(
                    null,
                    "Filme removido da playlist!"
                );

            } else {

                JOptionPane.showMessageDialog(
                    null,
                    "Selecione um filme!"
                );
            }
        });

        // LAYOUT
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(botaoRemover, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JTable getTabelaPlaylist() {
        return tabelaPlaylist;
    }

    public void adicionarFilmeNaTabela(
        int id,
        String nome,
        String canal,
        String descricao
    ) {

        DefaultTableModel modelo =
            (DefaultTableModel) tabelaPlaylist.getModel();

        modelo.addRow(new Object[]{
            id,
            nome,
            canal,
            descricao
        });
    }
}
