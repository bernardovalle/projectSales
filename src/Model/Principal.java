package Model;

import java.util.Scanner;

import DAO.PagamentoDAO;
import DAO.ProdutoDAO;
import DAO.VendaDAO;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner leia = new Scanner(System.in);
		
		Produto produto = new Produto();
        Venda venda = new Venda();
        Pagamento pag = new Pagamento();
        ProdutoDAO produtoDAO = new ProdutoDAO();
		
        int opcao;
        do {
        System.out.println("SELECIONE UMA DAS OPÇÕES ABAIXO");
        System.out.println("1 - Adicionar Produto\n" +
        				   "2 - Visualizar Venda\n"+
        				   "3 - Concluir Venda\n"+
        				   "4 - Sair");
        opcao = leia.nextInt();
        
        switch (opcao) {
        	case 1: 
        		System.out.println("Informe o nome do produto");
        		produto.setNome(leia.next());
        		System.out.println("Informe o preço do produto");
        		produto.setPreco(leia.nextDouble());
        		System.out.println("Informe a quantidade do produto");
        		produto.setQuantidadeEstoque(leia.nextInt());
        		produtoDAO.creat(produto);
        		venda.adicionarItemVenda(produto);
        		break;
        	case 2:
        		venda.visualizarVenda();
        		break;
        	case 3:
        		if(venda.getListaProdutos().isEmpty()) {
        			System.out.println("Impossível concluir a venda pois não há nenhum produto adicionado!");
        		} else {
        			System.out.println("Forma de pagamento: 1-Dinheiro | 2-Cheque | 3-Cartão");
        			int tipoPagamento = leia.nextInt();
        			pag.selecionarTipoPagamento(tipoPagamento);
        			venda.concluirVenda(pag);
        		}
        		break;
        	case 4:
        		System.out.println("Sistema Finalizado.");
        		break;
        }
 	
        } while (opcao != 4);
        
		
	}

}
