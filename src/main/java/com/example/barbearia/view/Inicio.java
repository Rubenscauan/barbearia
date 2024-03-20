package com.example.barbearia.view;

import javax.swing.JOptionPane;

public class Inicio {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Bem-vindo à barbearia!");

        String nomeCliente = JOptionPane.showInputDialog("Digite seu nome:");

        JOptionPane.showMessageDialog(null, "Olá, " + nomeCliente + "!");

        String[] opcoes = { "Corte de Cabelo", "Barba", "Corte e Barba" };
        int escolha = JOptionPane.showOptionDialog(null, "O que você gostaria de fazer?", "Escolha um serviço",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        String servicoEscolhido = opcoes[escolha];
        JOptionPane.showMessageDialog(null, "Você escolheu: " + servicoEscolhido);
    }
}
