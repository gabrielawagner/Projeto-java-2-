package org.serratec.residencia.projetofinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.serratec.residencia.projetofinal.dto.DtoCarrinho;
import org.serratec.residencia.projetofinal.dto.DtoPedido;
import org.serratec.residencia.projetofinal.dto.DtoProduto;
import org.serratec.residencia.projetofinal.exception.ClienteNotFoundException;
import org.serratec.residencia.projetofinal.exception.PedidoNotFoundException;
import org.serratec.residencia.projetofinal.exception.ProdutoNotFoundException;
import org.serratec.residencia.projetofinal.form.FormPedido;
import org.serratec.residencia.projetofinal.model.Carrinho;
import org.serratec.residencia.projetofinal.model.Cliente;
import org.serratec.residencia.projetofinal.model.Pedido;
import org.serratec.residencia.projetofinal.model.Produto;
import org.serratec.residencia.projetofinal.repository.CarrinhoRepository;
import org.serratec.residencia.projetofinal.repository.ClienteRepository;
import org.serratec.residencia.projetofinal.repository.PedidoRepository;
import org.serratec.residencia.projetofinal.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Transactional
	public void inserir(FormPedido form) throws ClienteNotFoundException, ProdutoNotFoundException {
		List<Integer> quantidades = form.getQuantidadesProdutos();
		List<Integer> idProdutos = form.getIdProdutos();
		List<Carrinho> carrinhos = new ArrayList<>();
		Optional<Cliente> cliente = clienteRepository.findById(form.getIdCliente());
		if (cliente.isPresent()) {
			Pedido pedido = form.toPedido(cliente.get());

			for (int i = 0; i < idProdutos.size(); i++) {
				Optional<Produto> optIdProd = produtoRepository.findById(idProdutos.get(i));
				if (optIdProd.isPresent()) {
					if (optIdProd.get().getQuantidadeEmEstoque() >= quantidades.get(i)) {
						Carrinho carrinho = new Carrinho(quantidades.get(i), optIdProd.get());
						optIdProd.get()
								.setQuantidadeEmEstoque(optIdProd.get().getQuantidadeEmEstoque() - quantidades.get(i));
						carrinhos.add(carrinho);
					} else {
						throw new ProdutoNotFoundException("Estoque insuficiente");
					}
					carrinhos.forEach(c -> {
						c.setPedido(pedido);
						carrinhoRepository.save(c);
					});
				} else {
					throw new ProdutoNotFoundException("Produto nao encontrado no banco");
				}
			}

			pedido.setCarrinho(carrinhos);
			pedidoRepository.save(pedido);
			System.out.println("Cheguei aqui");
		} else {
			throw new ClienteNotFoundException("Cliente nao cadastrado no sistema");
		}
	}

	public List<Pedido> listarTodos() {
		List<Pedido> pedidos = pedidoRepository.findAll();

		return pedidos;
	}
	

	public DtoPedido listarPorId(Integer id) throws PedidoNotFoundException, ClienteNotFoundException {
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);

		if (pedidoOpt.isPresent()) {
			Pedido pedido = pedidoOpt.get();
			List<Carrinho> c = pedido.getCarrinho();
			List<DtoCarrinho> cDto = new ArrayList<>();
			
			c.forEach(e -> {
				cDto.add(new DtoCarrinho(e.getQuantidadeProduto(), new DtoProduto(e.getProduto())));
			});
			
			
			return new DtoPedido(pedido,cDto);
		}
		throw new PedidoNotFoundException("Pedido nao encotrado.");
	}

	@Transactional
	public void deletar(Integer id) throws PedidoNotFoundException, ClienteNotFoundException {
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);

		if (pedidoOpt.isPresent()) {
			Pedido pedido = pedidoOpt.get();
			List<Carrinho> c = pedido.getCarrinho();

			c.forEach(car -> {
				car.getProduto()
				.setQuantidadeEmEstoque(car.getProduto().getQuantidadeEmEstoque() + car.getQuantidadeProduto());
				produtoRepository.save(car.getProduto());
			});
			
			carrinhoRepository.deleteAll(c);

			pedidoRepository.delete(pedido);

		} else {

			throw new PedidoNotFoundException("O pedido de id: " + id + " não existe.");
		}
	}
}
