package Model;

import java.util.ArrayList;

public class Venda {
	
	private float vlrTotal;
	private ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
	
	public Venda() {
	}
	
	public void adicionarItemVenda(Produto prod) {
		if (prod.verificarEstoque(prod)) {
			listaProdutos.add(new Produto(prod.getNome(),prod.getPreco(),prod.getQuantidadeEstoque()));
			this.vlrTotal += prod.getPreco();
			System.out.println("Produto adicionado.");
		} else {
			System.out.println("Não foi possível adicionar o produto " + prod.getNome() + " pois seu estoque está zerado.");
		}
		System.out.println("______________");
	}
	
	public void visualizarVenda() {
		System.out.println("________________");
        System.out.println("ITENS DA VENDA:");
		for(Produto x : listaProdutos) {
			System.out.println("PRODUTO: "+x.getNome()+", R$ "+x.getPreco());
		}
		System.out.println("Valor total:"+getVlrTotal());
	}
	
	public void concluirVenda(Pagamento pag) {
		System.out.println("________________");
        System.out.println("Valor total da venda: " + this.vlrTotal);
        pag.realizarPagamento(pag);
        listaProdutos.clear();
        System.out.println("________________");
	}
	
	public Float getVlrTotal() {
		return vlrTotal;
	}
	public void setVlrTotal(Float vlrTotal) {
		this.vlrTotal = vlrTotal;
	}
	
	public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaprodutos(ArrayList<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }
	
	
	
	
}
