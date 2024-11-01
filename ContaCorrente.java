package sistemaBancario.model;
public class ContaCorrente extends Conta {
	
	private double chequeEspecial;

    public ContaCorrente(int numeroConta, String titular, double saldo) {
        super(numeroConta, titular, saldo);
    }
    public double getChequeEspecial() {
		return chequeEspecial;
	}
	public void setChequeEspecial(double chequeEspecial) {
		this.chequeEspecial = chequeEspecial;
	}	
	
	 public boolean sacar(double valor) {
	        if (valor > 0 && valor <= saldo + chequeEspecial) {
	            saldo -= valor;
	            System.out.println("Saque de R$" + valor + " realizado com sucesso. Saldo atual: R$" + saldo);
	            return true;
	        } else {
	            System.out.println("Saldo insuficiente para saque, mesmo com cheque especial.");
	            return false;
	        }
	    }

}