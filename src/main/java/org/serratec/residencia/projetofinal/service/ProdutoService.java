package org.serratec.residencia.projetofinal.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.serratec.residencia.projetofinal.dto.DtoProduto;
import org.serratec.residencia.projetofinal.exception.ErroNoCadastroException;
import org.serratec.residencia.projetofinal.exception.ParametroObrigatorioException;
import org.serratec.residencia.projetofinal.exception.ProdutoNotFoundException;
import org.serratec.residencia.projetofinal.form.FormProduto;
import org.serratec.residencia.projetofinal.model.Categoria;
import org.serratec.residencia.projetofinal.model.Funcionario;
import org.serratec.residencia.projetofinal.model.Produto;
import org.serratec.residencia.projetofinal.repository.CategoriaRepository;
import org.serratec.residencia.projetofinal.repository.FuncionarioRepository;
import org.serratec.residencia.projetofinal.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public void inserir(FormProduto form) throws ErroNoCadastroException {
		Optional<Produto> prod = produtoRepository.findByNome(form.getNome());
		
		if(!prod.isPresent()) {
			Optional<Categoria> categoria = categoriaRepository.findById(form.getIdCategoria());
			Optional<Funcionario> funcionario = funcionarioRepository.findById(form.getIdFuncionario());
			if(categoria.isPresent() && funcionario.isPresent()) {
				Produto produto = form.toProduto(categoria.get(), funcionario.get());
				produtoRepository.save(produto);
			}else {
				throw new ErroNoCadastroException("Funcionario ou categoria nao existem no banco.");
			}
		} else {
			prod.get().setQuantidadeEmEstoque(prod.get().getQuantidadeEmEstoque() + form.getQuantidadeEmEstoque());
			produtoRepository.save(prod.get());
		}
	}

	public List<Produto> listarTodos() {
		List<Produto> listaProdutos = produtoRepository.findAll();
		List<DtoProduto> listDtoProduto = new ArrayList<DtoProduto>();
		Collections.sort(listaProdutos);

		for (Produto produto : listaProdutos) {
			listDtoProduto.add(new DtoProduto(produto));
		}

		return listaProdutos;
	}

	public Produto listarPorId(Integer id) throws ProdutoNotFoundException {
		Optional<Produto> optionalProduto = produtoRepository.findById(id);

		if (optionalProduto.isPresent())
			return optionalProduto.get();

		throw new ProdutoNotFoundException("Produto com o id: " + id + " não encontrado.");
	}

	public Produto substituir(Integer id, FormProduto formProduto)
			throws ParametroObrigatorioException, ProdutoNotFoundException {
		if (formProduto == null)
			throw new ParametroObrigatorioException("Campo 'produto' é obrigatório");

		Categoria categoria = categoriaRepository.getOne(formProduto.getIdCategoria());
		Funcionario funcionario = funcionarioRepository.getOne(formProduto.getIdFuncionario());

		Produto produtoNoBanco = listarPorId(id);

		if (formProduto.getNome() != null) {
			produtoNoBanco.setNome(formProduto.getNome());
		}

		if (formProduto.getDescricao() != null) {
			produtoNoBanco.setDescricao(formProduto.getDescricao());
		}

		if (formProduto.getDataDeFabricacao() != null) {
			produtoNoBanco.setDataDeFabricacao(formProduto.getDataDeFabricacao());
		}

		if (formProduto.getQuantidadeEmEstoque() != null) {
			produtoNoBanco.setQuantidadeEmEstoque(formProduto.getQuantidadeEmEstoque());
		}

		if (formProduto.getValorUnitario() != null) {
			produtoNoBanco.setValorUnitario(formProduto.getValorUnitario());
		}
		if (categoria != null) {
			produtoNoBanco.setCategoria(categoria);
		}
		if (funcionario != null) {
			produtoNoBanco.setFuncionario(funcionario);
		}

		return produtoRepository.save(produtoNoBanco);
	}

	@Transactional
	public void deletar(Integer id) throws ProdutoNotFoundException {
		Produto produtoNoBanco = listarPorId(id);
		produtoRepository.delete(produtoNoBanco);
	}

	public Produto buscaPorNome(String nome) throws ProdutoNotFoundException {
		Optional<Produto> prod = produtoRepository.findByNome(nome);
		
		if (prod.isPresent()) {
			return prod.get();
		}
		throw new ProdutoNotFoundException("O produto " + nome + " nao esta cadastrado.");
	}

	public List<Produto> buscaPorCategoria(String categoria){
		List<Produto> produtos = produtoRepository.findByCategoriaNome(categoria);
		return produtos;
	}
}
