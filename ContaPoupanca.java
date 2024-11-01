package sistemaBancario.model;

public class ContaPoupanca extends Conta{
	public ContaPoupanca(int numeroConta, String titular, double saldo) {
		super(numeroConta, titular, saldo);
	}
	   public boolean sacar(double valor) {
	        System.out.println("Não pode saque direto na Poupança.");
	        return false;
	 }
}


