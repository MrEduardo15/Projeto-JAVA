/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.edu.fei.sistemaacademico;

import br.edu.fei.controller.Controller;
import br.edu.fei.view.CadastroJFrame;
import br.edu.fei.view.LoginJFrame;

/**
 *
 * @author Pichau
 */
public class SistemaAcademico {

    public static void main(String[] args) {

        CadastroJFrame cadastroView =
                new CadastroJFrame();

        LoginJFrame loginView =
                new LoginJFrame();


        Controller controller =
                new Controller(
                        cadastroView,
                        loginView);
                

        cadastroView.setController(controller);

        loginView.setController(controller);


        loginView.setVisible(true);
    }
}