package biz.fabiotecnico1.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.fabiotecnico1.cursomc.domain.Cliente;
import biz.fabiotecnico1.cursomc.repositories.ClienteRepository;
import biz.fabiotecnico1.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired						
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {				
		
		Optional<Cliente> obj = repo.findById(id);	
		
		return obj.orElseThrow(()-> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: " + id + ", Tipo: "  
				+ Cliente.class.getName()));
	}
}