package Model;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.protocol.a.authentication.MysqlClearPasswordPlugin;

import DAO.PagamentoDAO;
import DAO.ProdutoDAO;
import DAO.VendaDAO;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner leia = new Scanner(System.in);
		
		Produto produto = new Produto();
        Venda venda = new Venda();
        PagamentoDAO pagamentoDAO =  new PagamentoDAO();
        Pagamento pag = new Pagamento();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        VendaDAO vendaDAO = new VendaDAO();
		
        int opcao, opcaoSubMenu;
        
        do {
        listarMenuGeral();
        opcao = leia.nextInt();
        
        switch (opcao) {
        	case 1:
        		// PRODUTO
        		do {
        			listarMenuProduto();
        			opcaoSubMenu = leia.nextInt();
        			switch (opcaoSubMenu) {	
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
        				listarProdutos();
        				break;
        			case 3:
        				System.out.println("Informe o id do produto");
                		produto.setIdProduto(leia.nextInt());
        				System.out.println("Informe o novo nome do produto");
                		produto.setNome(leia.next());
                		System.out.println("Informe o novo preço do produto");
                		produto.setPreco(leia.nextDouble());
                		System.out.println("Informe a nova quantidade do produto");
                		produto.setQuantidadeEstoque(leia.nextInt());
                		produtoDAO.update(produto);
                		break;
        			case 4:
        				listarProdutos();
        				System.out.println("Informe o id do produto");
        				produto.setIdProduto(leia.nextInt());
        				produtoDAO.delete(produto);
        				break;
                	case 5:
                		System.out.println("");
                		break;
        			}
        			
        		} while (opcaoSubMenu != 5);
        		System.out.println("_______________________");
        		break;
        	case 2:
        		// PAGAMENTO
        		listarMenuPagamento();
    			opcaoSubMenu = leia.nextInt();
    			switch (opcaoSubMenu) {	
    				case 1:
    					System.out.println("Informe o tipo de pagamento");
                		pag.setTipoPagamento(leia.nextInt());
                		pagamentoDAO.creat(pag);
    				case 2:
    					listarPagamentos();
    				case 3:
    					listarPagamentos();
        				System.out.println("Informe o id do pagamento");
                		pag.setIdTipoPagamento(leia.nextInt());
                		System.out.println("Informe o novo tipo de pagamento");
                		pag.setTipoPagamento(leia.nextInt());
                		pagamentoDAO.update(pag);
                		System.out.println("Pagamento editado.");
                		break;
    				case 4:
    					listarPagamentos();
        				System.out.println("Informe o id do pagamento");
        				pag.setIdTipoPagamento(leia.nextInt());
        				pagamentoDAO.delete(pag);
        				System.out.println("Pagamento Deletado.");
        				break;
    				case 5:
    					System.out.println("");
                		break;
    			}
        		
    			break;
        	case 3:
        		// VENDA
        		do {
        			listarMenuVenda();
        			opcaoSubMenu = leia.nextInt();
        			switch (opcaoSubMenu) {	
        			case 1:
        				if(venda.getVlrTotal() == null) {
        					System.out.println("Não há produtos nessa venda.");
        				} else {
        					vendaDAO.creat(venda);
        					System.out.println("Venda Registrada no Banco de Dados.");
        				}
                		break;
        			case 2:
        				listaVendas();
        				//venda.visualizarVenda();
        				break;
        			case 3:
        				listaVendas();
        				System.out.println("Informe o id da venda");
                		venda.setIdVenda(leia.nextInt());
                		System.out.println("Informe o novo valor da venda");
                		venda.setVlrTotal(leia.nextFloat());
                		vendaDAO.update(venda);
                		System.out.println("Venda editada.");
                		break;
        			case 4:
        				listaVendas();
        				System.out.println("Informe o id da venda");
        				venda.setIdVenda(leia.nextInt());
        				vendaDAO.delete(venda);
        				System.out.println("Venda Deletada.");
        				break;
                	case 5:
                		if(venda.getListaProdutos().isEmpty()) {
                			System.out.println("Impossível concluir a venda pois não há nenhum produto adicionado!");
                		} else {
                			System.out.println("Forma de pagamento: 1-Dinheiro | 2-Cheque | 3-Cartão");
                			int tipoPagamento = leia.nextInt();
                			pag.selecionarTipoPagamento(tipoPagamento);
                			venda.concluirVenda(pag);
                		}
                	case 6:	
                		System.out.println("");
                		break;
        			}
        			
        		} while (opcaoSubMenu != 6);
        		break;
        	case 4:
        		System.out.println("Sistema Finalizado.");
        		break;
        }
        } while (opcao != 4);
	leia.close();
	}
	
	public static void listarMenuGeral() {
		 System.out.println("SELECIONE UMA DAS OPÇÕES ABAIXO");
	     System.out.println("1 - Menu Produtos\n" +
	        				"2 - Menu Pagamento\n"+
	        				"3 - Menu Venda\n"+
	        				"4 - Sair");
	}
	
	public static void listarMenuProduto() {
		System.out.println("1 - Cadastrar Produtos\n" +
				   "2 - Listar Produtos\n"+
				   "3 - Alterar Produto\n"+
				   "4 - Deletar Produto\n"+
				   "5 - Voltar menu principal.");
	}
	
	public static void listarMenuPagamento() {
		System.out.println("1 - Cadastrar Pagamento\n" +
				   "2 - Listar Pagamentos\n"+
				   "3 - Alterar Pagamento\n"+
				   "4 - Deletar Pagamento\n"+
				   "5 - Voltar menu principal.");
	}
	
	public static void listarMenuVenda() {
		System.out.println("1 - Cadastrar Venda\n" +
				   "2 - Listar Vendas\n"+
				   "3 - Alterar Venda\n"+
				   "4 - Deletar Venda\n"+
				   "5 - Finalizar Venda\n"+
				   "6 - Voltar menu principal");
	}
	
	public static void listarProdutos() {
		ArrayList<Produto> resultadoProdutosBD = new ArrayList<Produto>();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		resultadoProdutosBD = produtoDAO.read();
		for(Produto prod : resultadoProdutosBD) {
			System.out.println("Produto: Id - "+prod.getIdProduto()+", "+prod.getNome()+". Preço: "+prod.getPreco()+" Quantidade: "+prod.getQuantidadeEstoque());
		}
		System.out.println("___________________");
	}
	
	public static void listaVendas() {
		ArrayList<Venda> resultadovendasBD = new ArrayList<Venda>();
		VendaDAO vendaDAO = new VendaDAO();
		resultadovendasBD = vendaDAO.read();
		for(Venda venda : resultadovendasBD) {
			System.out.println("Venda: Id - "+venda.getIdVenda()+", "+venda.getVlrTotal());
		}
		System.out.println("___________________");
	}
	
	public static void listarPagamentos() {
		ArrayList<Pagamento> resultadoPagamentosBD = new ArrayList<Pagamento>();
		PagamentoDAO pagamentoDAO = new PagamentoDAO();
		resultadoPagamentosBD = pagamentoDAO.read();
		for(Pagamento pag : resultadoPagamentosBD) {
			System.out.println("Pagamento: Id - "+pag.getIdTipoPagamento()+", "+pag.getTipoPagamento());
		}
		System.out.println("___________________");
	}
	

}
