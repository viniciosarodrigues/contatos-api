package br.com.lampiao.contatosapi.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Table
@Entity
@Data
public class Contato {

	@Id
	private Long id;
	private String nome;
	private String telefone;
}
