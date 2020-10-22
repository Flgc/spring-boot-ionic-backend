package biz.fabiotecnico1.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import biz.fabiotecnico1.cursomc.domain.Categoria;
import biz.fabiotecnico1.cursomc.domain.Cidade;
import biz.fabiotecnico1.cursomc.domain.Cliente;
import biz.fabiotecnico1.cursomc.domain.Endereco;
import biz.fabiotecnico1.cursomc.domain.Estado;
import biz.fabiotecnico1.cursomc.domain.ItemPedido;
import biz.fabiotecnico1.cursomc.domain.Pagamento;
import biz.fabiotecnico1.cursomc.domain.PagamentoComBoleto;
import biz.fabiotecnico1.cursomc.domain.PagamentoComCartao;
import biz.fabiotecnico1.cursomc.domain.Pedido;
import biz.fabiotecnico1.cursomc.domain.Produto;
import biz.fabiotecnico1.cursomc.domain.emums.EstadoPagamento;
import biz.fabiotecnico1.cursomc.domain.emums.TipoCliente;
import biz.fabiotecnico1.cursomc.repositories.CategoriaRepository;
import biz.fabiotecnico1.cursomc.repositories.CidadeRepository;
import biz.fabiotecnico1.cursomc.repositories.ClienteRepository;
import biz.fabiotecnico1.cursomc.repositories.EnderecoRepository;
import biz.fabiotecnico1.cursomc.repositories.EstadoRepository;
import biz.fabiotecnico1.cursomc.repositories.ItemPedidoRepository;
import biz.fabiotecnico1.cursomc.repositories.PagamentoRepository;
import biz.fabiotecnico1.cursomc.repositories.PedidoRepository;
import biz.fabiotecnico1.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{	

	@Autowired
	private CategoriaRepository categoriaRepository;		
	@Autowired
	private ProdutoRepository produtoRepository; 			
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;			
	@Autowired
	private EnderecoRepository enderecoRepository;	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
		
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception { 
		
		
		//Hard code Sample - inserted in database
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));	
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3)); 	
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		Estado est3 = new Estado(null, "Rio de Janeiro");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		Cidade c4 = new Cidade(null, "Itaguaí", est3);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		est3.getCidades().addAll(Arrays.asList(c4));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));	
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4));	
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null, "Barbosa Otoni", "botoni@gmail.com", "05664178971", TipoCliente.PESSOAJURIDICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		cli2.getTelefones().addAll(Arrays.asList("34456879", "99879845"));
		
		Endereco e1 = new Endereco(null, "Rua Elores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2); 
		Endereco e3 = new Endereco(null, "Avenida Betos", "05", "Casa 80", "Centro", "23578544", cli2, c3);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
 
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));		
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
				
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
			
	}
}