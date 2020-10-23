package biz.fabiotecnico1.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.fabiotecnico1.cursomc.domain.Categoria;
import biz.fabiotecnico1.cursomc.repositories.CategoriaRepository;
import biz.fabiotecnico1.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired						
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {					
		
		Optional<Categoria> obj = repo.findById(id);	
		
		return obj.orElseThrow(()-> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: " + id + ", Tipo: "  
				+ Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	} 
	
}
