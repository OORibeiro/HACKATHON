package sistemaBancario.view;

import sistemaBancario.model.Conta;
import sistemaBancario.model.ContaCorrente;
import sistemaBancario.model.ContaPoupanca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContaInterface extends JFrame {
    private static final long serialVersionUID = 1L;
	private Conta contaCorrente;
    private Conta contaPoupanca;

    private JLabel saldoContaCorrenteLabel;
    private JLabel saldoContaPoupancaLabel;
    private JTextField valorOperacaoField;
    private JComboBox<String> contaComboBox;

    public ContaInterface() {
        contaCorrente = new ContaCorrente(1, "João", 1000.0);
        ((ContaCorrente) contaCorrente).setChequeEspecial(200.0);
        contaPoupanca = new ContaPoupanca(2, "Maria", 1500.0);

        setTitle("Sistema Bancário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        saldoContaCorrenteLabel = new JLabel("Saldo Conta Corrente (João): R$" + contaCorrente.getSaldo());
        saldoContaPoupancaLabel = new JLabel("Saldo Conta Poupança (Maria): R$" + contaPoupanca.getSaldo());
        valorOperacaoField = new JTextField();

        contaComboBox = new JComboBox<>(new String[]{"Conta Corrente", "Conta Poupança"});

        JButton sacarButton = new JButton("Sacar");
        JButton depositarButton = new JButton("Depositar");
        JButton transferirButton = new JButton("Transferir");

        sacarButton.addActionListener(new SacarAction());
        depositarButton.addActionListener(new DepositarAction());
        transferirButton.addActionListener(new TransferirAction());

        add(saldoContaCorrenteLabel);
        add(saldoContaPoupancaLabel);
        add(new JLabel("Selecionar Conta:"));
        add(contaComboBox);
        add(new JLabel("Valor da Operação:"));
        add(valorOperacaoField);
        add(sacarButton);
        add(depositarButton);
        add(transferirButton);
    }

    private void atualizarSaldos() {
        saldoContaCorrenteLabel.setText("Saldo Conta Corrente (João): R$" + contaCorrente.getSaldo());
        saldoContaPoupancaLabel.setText("Saldo Conta Poupança (Maria): R$" + contaPoupanca.getSaldo());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ContaInterface interfaceBancaria = new ContaInterface();
            interfaceBancaria.setVisible(true);
        });
    }

    private class SacarAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double valor = Double.parseDouble(valorOperacaoField.getText());
            Conta contaSelecionada = getContaSelecionada();
            if (contaSelecionada != null && contaSelecionada.sacar(valor)) {
                JOptionPane.showMessageDialog(null, "Saque realizado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Valor de saque inválido.");
            }
            atualizarSaldos();
        }
    }

    private class DepositarAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double valor = Double.parseDouble(valorOperacaoField.getText());
            Conta contaSelecionada = getContaSelecionada();
            if (valor <= 0) {
                JOptionPane.showMessageDialog(null, "Valor de depósito inválido.");
            } else if (contaSelecionada != null) {
                contaSelecionada.depositar(valor);
                JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso.");
            }
            atualizarSaldos();
        }
    }

    private class TransferirAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double valor = Double.parseDouble(valorOperacaoField.getText());
            Conta contaSelecionada = getContaSelecionada();
            Conta contaDestino = (contaSelecionada == contaCorrente) ? contaPoupanca : contaCorrente;

            if (contaSelecionada != null && contaSelecionada.transferir(valor, contaDestino)) {
                JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Valor de transferência inválido.");
            }
            atualizarSaldos();
        }
    }

    private Conta getContaSelecionada() {
        String contaEscolhida = (String) contaComboBox.getSelectedItem();
        return "Conta Corrente".equals(contaEscolhida) ? contaCorrente : contaPoupanca;
    }
}
