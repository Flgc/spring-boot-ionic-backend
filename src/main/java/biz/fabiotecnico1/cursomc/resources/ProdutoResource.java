package biz.fabiotecnico1.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import biz.fabiotecnico1.cursomc.domain.Produto;
import biz.fabiotecnico1.cursomc.dto.ProdutoDTO;
import biz.fabiotecnico1.cursomc.resources.utils.URL;
import biz.fabiotecnico1.cursomc.services.ProdutoService;

@RestController						
@RequestMapping(value="/produtos")			
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;			
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)  
	public ResponseEntity<Produto> find(@PathVariable Integer id) {	
		Produto obj = service.find(id);							
		return ResponseEntity.ok().body(obj);		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="0") String nome,
			@RequestParam(value="categorias", defaultValue="0") String categorias,
			@RequestParam(value="Page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nomeDecode = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeInList(categorias);
		Page<Produto> list = service.search(nomeDecode,ids , page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
		
}