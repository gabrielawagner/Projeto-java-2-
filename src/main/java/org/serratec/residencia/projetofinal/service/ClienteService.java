package org.serratec.residencia.projetofinal.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.serratec.residencia.projetofinal.exception.ClienteNotFoundException;
import org.serratec.residencia.projetofinal.exception.ErroNoCadastroException;
import org.serratec.residencia.projetofinal.exception.ParametroObrigatorioException;
import org.serratec.residencia.projetofinal.form.FormCliente;
import org.serratec.residencia.projetofinal.model.Cliente;
import org.serratec.residencia.projetofinal.model.Pedido;
import org.serratec.residencia.projetofinal.repository.ClienteRepository;
import org.serratec.residencia.projetofinal.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	PedidoRepository pedidoRepository;

	public Cliente inserir(FormCliente formCliente) throws ErroNoCadastroException {
		
		Optional<Cliente> optionalClientePorCpf = clienteRepository.findClienteByCpf(formCliente.getCpf());
		Optional<Cliente> optionalClientePorEmail = clienteRepository.findClienteByEmail(formCliente.getEmail());
		Optional<Cliente> optionalClientePorNomeDeUsuario = clienteRepository.findClienteByNomeDeUsuario(formCliente.getNomeDeUsuario());
		if(optionalClientePorCpf.isPresent()) {
			throw new ErroNoCadastroException("Cpf já cadastrado no sistema entre em contato com o administrador do site.");
		}
		if(optionalClientePorEmail.isPresent()) {
			throw new ErroNoCadastroException("Email já cadastrado no sistema entre em contato com o administrador do site.");
		}
		if(optionalClientePorNomeDeUsuario.isPresent()) {
			throw new ErroNoCadastroException("Usuario já cadastrado no sistema entre em contato com o administrador do site.");
		}
		ArrayList<Pedido> pedidos = pedidoRepository.findByClienteCpf(formCliente.getCpf());
		
		return clienteRepository.save(formCliente.toCliente(pedidos));
	}

	public List<Cliente> listarTodos() {
		List<Cliente> clientes = clienteRepository.findAll();
		List<FormCliente> formClientes = new ArrayList<FormCliente>();
		Collections.sort(clientes);
		for (Cliente cliente : clientes) {
			formClientes.add(new FormCliente(cliente));
		}
		
		return clientes;
		}

	public Cliente listarPorId(Integer id) throws ClienteNotFoundException {
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);

		if (optionalCliente.isPresent()) {
			return optionalCliente.get();
		}

		throw new ClienteNotFoundException("Cliente com o id: " + id + " não encontrado.");

	}

	public Cliente substituir(Integer id, FormCliente formCliente)
			throws ParametroObrigatorioException, ClienteNotFoundException {
		if (formCliente == null)
			throw new ParametroObrigatorioException("Campo 'cliente' é obrigatório");
		
		List<Pedido> pedidos = pedidoRepository.findByClienteCpf(formCliente.getCpf());
		
		Cliente cliente = formCliente.toCliente(pedidos);
		Cliente clienteNoBanco = listarPorId(id);

		if (cliente.getNome() != null) {
			clienteNoBanco.setNome(cliente.getNome());
		}

		if (cliente.getEndereco() != null) {
			clienteNoBanco.setEndereco(cliente.getEndereco());
		}

		if (cliente.getTelefone() != null) {
			clienteNoBanco.setTelefone(cliente.getTelefone());
		}

		if (cliente.getNomeDeUsuario() != null) {
			clienteNoBanco.setNomeDeUsuario(cliente.getNomeDeUsuario());
		}

		if (cliente.getEmail() != null) {
			clienteNoBanco.setEmail(cliente.getEmail());
		}

		if (cliente.getCpf() != null) {
			clienteNoBanco.setCpf(cliente.getCpf());

		}

		if (cliente.getDataNascimento() != null) {
			clienteNoBanco.setDataNascimento(cliente.getDataNascimento());

		}
		return clienteRepository.save(clienteNoBanco);

	}

	public void deletar(Integer id) throws ClienteNotFoundException {
		Cliente cliente = listarPorId(id);
		clienteRepository.delete(cliente);

	}
}
