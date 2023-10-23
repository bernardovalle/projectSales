package Model;

public class Pagamento {
	
	private int idTipoPagamento;
	private int tipoPagamento;
	private String statusPagamento;

	public Pagamento() {
	}
	public Pagamento(int tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
	public void selecionarTipoPagamento(int tpag) {
		if (tpag == 1 || tpag == 2 || tpag == 3) {
			this.tipoPagamento = tpag;
		} else {
			System.out.println("OPÇÃO INVÁLIDA\nOPERAÇÃO CANCELADA");
            System.out.println("________________");
		}
			
	}
	public void realizarPagamento(Pagamento pag) {
		 if (pag.getTipoPagamento() == 1) {
	            System.out.println("Pagamento realizado com dinheiro");
	        }
	        if (pag.getTipoPagamento() == 2) {
	            System.out.println("Pagamento realizado com cheque");
	        }
	        if (pag.getTipoPagamento() == 3) {
	            System.out.println("Pagamento realizado com cartão");
	        }
	}
	
	public int getIdTipoPagamento() {
		return idTipoPagamento;
	}
	public void setIdTipoPagamento(int idTipoPagamento) {
		this.idTipoPagamento = idTipoPagamento;
	}
	public int getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(int tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
	

}
