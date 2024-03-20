package com.example.barbearia;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.GridLayout;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.barbearia.dao.ClienteDao;
import com.example.barbearia.models.Cliente;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(scanBasePackages = "com.example.barbearia")
@EntityScan("com.example.barbearia.models")
@EnableJpaRepositories("com.example.barbearia.dao")
@Slf4j
public class BarbeariaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(BarbeariaApplication.class);
        builder.headless(false).run(args);
    }

    @Autowired
    private ClienteDao baseCliente;

    @Override
    public void run(String... args) throws Exception {

        JLabel labelNomeCliente = new JLabel("Nome do Cliente:");
        JLabel labelNomeBarbeiro = new JLabel("Nome do Barbeiro:");
        JLabel labelHorario = new JLabel("Horário:");

        JComboBox<String> comboBoxBarbeiro = new JComboBox<>();
        comboBoxBarbeiro.addItem("Barbeiro 1");
        comboBoxBarbeiro.addItem("Barbeiro 2");

        SpinnerModel spinnerModel = new SpinnerDateModel();
        JSpinner spinnerHorario = new JSpinner(spinnerModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerHorario, "HH:mm");
        spinnerHorario.setEditor(editor);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(labelNomeCliente);
        panel.add(new JTextField());
        panel.add(labelNomeBarbeiro);
        panel.add(comboBoxBarbeiro);
        panel.add(labelHorario);
        panel.add(spinnerHorario);

        int result = JOptionPane.showConfirmDialog(null, panel, "Agendamento de Serviço", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String nomeCliente = ((JTextField) panel.getComponent(1)).getText();
            if (nomeCliente.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, insira o nome do cliente.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nomeBarbeiro = (String) comboBoxBarbeiro.getSelectedItem();
            Date horario = (Date) spinnerHorario.getValue();

            System.out.println("Nome do Cliente: " + nomeCliente);
            Cliente cliente = new Cliente(nomeCliente);
            baseCliente.save(cliente);
            System.out.println("Nome do Barbeiro: " + nomeBarbeiro);
            System.out.println("Horário: " + horario);
        } else {
            System.out.println("Operação cancelada pelo usuário.");
        }
    }
}
