package biz.fabiotecnico1.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// JPA - Define estratégia para geração automática das chaves primárias
	private Integer id;
	private String nome;
	private Double preco;
	
	@JsonBackReference										//Irá omitir a lista de categoria para cada produto
	@ManyToMany												//JPA - Anotação de relacionamento muitos para muitos
	@JoinTable(name = "PRODUTO_CATEGORIA",  				//Cria a tebela muitos para muitos com o Nome informado
	joinColumns = @JoinColumn(name = "produto_id"),			//Informa o campo da chave estranjeira na tabela
	inverseJoinColumns= @JoinColumn(name = "categoria_id") 	//Nome da outra chave estranjeira que refencia a categoria
	)
	
	private List<Categoria> categorias = new ArrayList<>();	//Associação do produto com a categoria
	
	public Produto() {										//Constutor sem parâmetros
	}

	// Generated Constructor using field
	public Produto(Integer id, String nome, Double preco) {	//Construtor com parâmetros
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	// Generated Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	//Generated hashCode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}