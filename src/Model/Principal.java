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
        Pagamento pag = new Pagamento();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        VendaDAO vendaDAO = new VendaDAO();
		
        int opcao, opcaoSubMenu;
        
        do {
        System.out.println("SELECIONE UMA DAS OPÇÕES ABAIXO");
        System.out.println("1 - Menu Produtos\n" +
        				   "2 - Menu Pagamento\n"+
        				   "3 - Menu Venda\n"+
        				   "4 - Sair");
        opcao = leia.nextInt();
        
        switch (opcao) {
        	case 1:
        		// PRODUTO
        		do {
        			System.out.println("1 - Cadastrar Produtos\n" +
        							   "2 - Listar Produtos\n"+
        							   "3 - Alterar Produto\n"+
        							   "4 - Deletar Produto\n"+
        							   "5 - Voltar menu principal.");
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
                		 System.out.print("\033[H\033[2J");
                         System.out.flush();
                		
                		break;
        			}
        			
        		} while (opcaoSubMenu != 5);
        		System.out.println("_______________________");
        		break;
        	case 2:
        		// PAGAMENTO
        		break;
        	case 3:
        		// VENDA
        		do {
        			System.out.println("1 - Cadastrar Venda\n" +
        							   "2 - Listar Vendas\n"+
        							   "3 - Alterar Venda\n"+
        							   "4 - Deletar Venda\n"+
        							   "5 - Finalizar Venda\n"+
        							   "6 - Voltar menu principal");
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
	

}
