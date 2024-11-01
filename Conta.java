package sistemaBancario.model;

	public class Conta {
	    private	int numeroConta;
	    private String titular;
	    protected double saldo;

	      public Conta(int numeroConta, String titular, double saldo) {
	           this.numeroConta = numeroConta;
	           this.titular = titular;
	            this.saldo = saldo;
	        }

	        public void setNumeroConta(int numeroConta) {
	            this.numeroConta = numeroConta;
	        }

	        public String getTitular() {
	            return titular;
	        }

	        public void setTitular(String titular) {
	            this.titular = titular;
	        }

	        public boolean sacar(double valor) {
	            if (valor > 0 && valor <= saldo) {
	                saldo -= valor;
	                System.out.println("Saque de R$" + valor + " realizado com sucesso.");
	                return true;
	            } else {
	                System.out.println("Saldo insuficiente.");
	                return false;
	            }
	        }

	        public void depositar(double valor) {
	            if (valor > 0) {
	                saldo += valor;
	                System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
	            } else {
	                System.out.println("Valor de depósito inválido.");
	            }
	        }

	        public boolean transferir(double valor, Conta destino) {
	            if (sacar(valor)) {
	                destino.depositar(valor);
	                System.out.println("Transferência de R$" + valor + " realizada para a conta " + destino.getNumeroConta());
	                return true;
	            }
	            return false;
	        }

	        public double getSaldo() {
	            return saldo;
	        }

	        public int getNumeroConta() {
	            return numeroConta;
	        }

}
