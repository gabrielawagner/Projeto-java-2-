package org.serratec.residencia.projetofinal.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.serratec.residencia.projetofinal.exception.CategoriaNotFoundException;
import org.serratec.residencia.projetofinal.exception.ParametroObrigatorioException;
import org.serratec.residencia.projetofinal.form.FormCategoria;
import org.serratec.residencia.projetofinal.model.Categoria;
import org.serratec.residencia.projetofinal.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public Categoria inserir(FormCategoria formCategoria) {

		return categoriaRepository.save(formCategoria.toCategoria(null));
	}

	public List<Categoria> listarTodos() {
		List<Categoria> categorias = categoriaRepository.findAll();
		Collections.sort(categorias);
		return categorias;
	}

	public Categoria listarPorId(Integer id) throws CategoriaNotFoundException {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		
		if(categoria.isPresent()) {
			return categoria.get();
		}
		
		throw new CategoriaNotFoundException("Categoria com o id: " + id + " não encontrada.");
	}

	public void deletar(Integer id) throws CategoriaNotFoundException {
		Categoria categoriaDeletada = listarPorId(id);
		categoriaRepository.delete(categoriaDeletada);
	}

	public Categoria substituir(Integer id, FormCategoria formCategoria)
			throws ParametroObrigatorioException, CategoriaNotFoundException {
		if (formCategoria == null)
			throw new ParametroObrigatorioException("Categoria 'categoria' é obrigatória");

		Categoria categoriaNoBanco = formCategoria.toCategoria(id);

		if (formCategoria.getNome() != null) {
			categoriaNoBanco.setNome(formCategoria.getNome());
		}

		if (formCategoria.getDescricao() != null) {
			categoriaNoBanco.setDescricao(formCategoria.getDescricao());
		}

		return categoriaRepository.save(categoriaNoBanco);
	}

}
