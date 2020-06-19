package imob.Pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Imobiliaria {
	private Map<String, Imovel> imoveis;
	private Map<Integer, Cliente> clientes;
	private Map<Integer, Aluguel> alugueis;
	private Map<String, Cliente> devedores;
	private Map<Integer, Proprietario> proprietarios;

//	/**
//	 * CONSTRUTOR
//	 * */
//	public Imobiliaria() {
//		this.imoveis = new HashMap<String, Imovel>();
//		this.clientes = new HashMap<Integer, Cliente>();
//		this.alugueis = new HashMap<Integer, Aluguel>();
//		this.devedores = new HashMap<String, Cliente>();
//		this.proprietarios = new HashMap<Integer, Proprietario>();
//	}
//
//	/**
//	 * GETTERS E SETTERS
//	 * */
//	public Map<String, Imovel> getImoveis() {
//		return imoveis;
//	}
//
//	public void setImoveis(Map<String, Imovel> imoveis) {
//		this.imoveis = imoveis;
//	}
//
//	public Map<Integer, Cliente> getClientes() {
//		return clientes;
//	}
//
//	public void setClientes(Map<Integer, Cliente> clientes) {
//		this.clientes = clientes;
//	}
//
//	public Map<Integer, Aluguel> getAlugueis() {
//		return alugueis;
//	}
//
//	public void setAlugueis(Map<Integer, Aluguel> alugueis) {
//		this.alugueis = alugueis;
//	}
//
//	public Map<String, Cliente> getDevedores() {
//		return devedores;
//	}
//
//	public void setDevedores(Map<String, Cliente> devedores) {
//		this.devedores = devedores;
//	}
//
//	public Map<Integer, Proprietario> getProprietarios() {
//		return proprietarios;
//	}
//
//	public void setProprietarios(Map<Integer, Proprietario> proprietarios) {
//		this.proprietarios = proprietarios;
//	}
//
//	
//
//	/**
//	 * ADICIONA NOVO IM�VEL
//	 * */
//	public boolean addImovel(Imovel imovel) throws Exception {
//		if (imoveis.containsValue(imovel)) {
//			throw new Exception("Imovel " + imovel.getNumero() + "j� cadastrado.");
//		} else {
//			imoveis.put(imovel.getNumero(), imovel);
//			return true;
//		}
//	}
//
//	/**
//	 * REMOVER IM�VEL PELO N�MERO
//	 * */
//	public boolean rvImovel(String numeroImovel) throws Exception {
//		if (imoveis.containsValue(buscarImovel(numeroImovel))) {
//			imoveis.remove(numeroImovel);
//			return true;
//		} else {
//			throw new Exception("Imovel " + numeroImovel + " n�o existe.");
//		}
//	}
//
//	/**
//	 * BUSCAR IM�VEL PELO N�MERO
//	 * */
//	public Imovel buscarImovel(String numeroImovel) throws Exception {
//		if (imoveis.containsKey(numeroImovel)) {
//			return imoveis.get(numeroImovel);
//		} else {
//			throw new Exception("Imovel com n�mero " + numeroImovel + " n�o existe.");
//		}
//	}
//	
//	/**
//	 * BUSCA IM�VEL PELO CEP
//	 * */
//	public List<Imovel> buscarImovelCep(String cep) throws Exception{
//		List<Imovel> patterns = new ArrayList<Imovel>();
//		
//		for (Imovel imovel : this.imoveis.values()) {
//			if (imovel.match(cep)) {
//				patterns.add(imovel);
//			}
//		}
//		
//		ordenaImoveis(patterns);
//		
//		if(patterns.size()!=0) {
//			return patterns;
//		}
//		throw new Exception("Im�vel com cep " + cep + " n�o existe.");
//	}
//	
//
//	/**
//	 * ADICIONA NOVO CLIENTE
//	 * */
//	public boolean addCli(Cliente cli) throws Exception {
//		for (Cliente c : clientes.values()) {
//			if (c.equals(cli.getNome(), cli.getCpf())) {
//				throw new Exception("Cliente portador do cpf "+cli.getCpf()+" j� est� cadastrado.");
//			}
//		}
//		clientes.put(cli.getCod(), cli);
//		return true;
//	}
//
//	/**
//	 * REMOVE CLIENTE PELO NOME E CPF
//	 * */
//	public boolean rvCliente(String nome, String cpf) throws Exception {
//		Cliente c = buscarCli(nome, cpf);
//		if (c!=null) {
//			clientes.remove(c.getCod());
//			return true;
//		} else {
//			throw new Exception("Cliente " + nome + " n�o existe.");
//		}
//	}
//
//	/**
//	 * BUSCA CLIENTE PELO CPF E NOME
//	 * */
//	public Cliente buscarCli(String nome, String cpf) throws Exception {
//		for (Cliente c : clientes.values()) {
//			if (c.equals(nome, cpf)) {
//				return c;
//			}
//		} 
//		throw new Exception("Cliente " + nome + " n�o existe.");
//	}
//	
//	/**
//	 * BUSCA CLIENTE PELO CPF
//	 * */
//	public Cliente buscarCliCpf(String cpf) throws Exception{	
//		for (Cliente c : this.clientes.values()) {
//			if (c.getCpf().equals(cpf)) {
//				return c;
//			}
//		}		
//		throw new Exception("Cliente portador do cpf " + cpf + " n�o existe.");
//	}
//	
//	/**
//	 * BUSCA CLIENTE PELO NOME
//	 * */
//	public List<Cliente> buscarCliNome(String nome) throws Exception{
//		List<Cliente> patterns = new ArrayList<Cliente>();
//		for (Cliente cliente : this.clientes.values()) {
//			if (cliente.match(nome)) {
//				patterns.add(cliente);
//			}
//		}		
//		ordenaClientes(patterns);		
//		if(patterns.size()!=0) {
//			return patterns;
//		}
//		throw new Exception("Cliente " + nome + " n�o existe.");
//	}
//
//	/**
//	 * ADICIONA NOVO ALUGUEL
//	 * */
//	public boolean addAluguel(Aluguel aluguel) throws Exception {
//		if (buscarAluguel(aluguel.getImovel().getNumero(), aluguel.getCliente().getCpf())!=null) {
//			throw new Exception("Ciente " + aluguel.getCliente().getNome() + "j� vinculado ao im�vel" + aluguel.getImovel().getNumero() +" localizado no cep: "+aluguel.getImovel().getCep());
//		} else {
//			alugueis.put(aluguel.getCod(), aluguel);
//			aluguel.getImovel().setStatus(true);
//			return true;
//		}
//	}
//
//	/**
//	 * REMOVE ALUGUEL PELO CPF DO CLIENTE E PELO N�MERO DO IM�VEL
//	 * */
//	public boolean rvAluguel(String cpfCli, String numImovel) throws Exception {
//		Aluguel auxAluguel =  buscarAluguel(numImovel, cpfCli);
//		if (!imoveis.containsValue(buscarImovel(numImovel))) {
//			throw new Exception("Imovel com n�mero " + numImovel + " n�o existe. ");
//		} else if (!clientes.containsValue(buscarCliCpf(cpfCli))) {
//			throw new Exception("Cliente portador do cpf " + cpfCli + " n�o existe.");
//		} else if (alugueis.containsValue(auxAluguel)) {
//			alugueis.remove(auxAluguel.getCod());
//			auxAluguel.getImovel().setStatus(false);
//			return true;
//		} else {
//			throw new Exception("Cliente " + auxAluguel.getCliente().getNome() + "n�o vinculado ao im�vel" + numImovel + " localizado no cep: "+auxAluguel.getImovel().getCep());
//		}
//	}
//	
//	/**
//	 * BUSCA ALUGUEL PELO N�MERO DO IM�VEL E CPF DO CLIENTE
//	 * */
//	public Aluguel buscarAluguel(String num, String cpf) { // se aluguel j� possui cliente vnculado ao imovel
//		for (Aluguel alu : alugueis.values()) {
//			if (alu.equals(num, cpf)) {
//				return alu;
//			}
//		}
//		return null;
//	}
//	
//	/**
//	 * BUSCA ALUGUEL PELO N�MERO DO IM�VEL 
//	 * */
//	public List<Aluguel> buscarAluguelNumero(String num) throws Exception{
//		List<Aluguel> patterns = new ArrayList<>();
//		for (Aluguel alu : alugueis.values()) {
//			if (alu.match(num)) {
//				patterns.add(alu);
//			}
//		}
//		if(patterns.size()!=0) {
//			return patterns;
//		}
//		throw new Exception("Aluguel com im�vel de n�mero "+num+" n�o encontrado!");
//	}
//	
//	/**
//	 * BUSCA ALUGUEL PELO CPF DO CLIENTE 
//	 * */
//	public List<Aluguel> buscarAluguelCpfCliente(String cpf) throws Exception{
//		List<Aluguel> patterns = new ArrayList<>();
//		for (Aluguel alu : alugueis.values()) {
//			if (alu.getCliente().getCpf().equals(cpf)) {
//				patterns.add(alu);
//			}
//		}
//		if(patterns.size()!=0) {
//			return patterns;
//		}
//		throw new Exception("Aluguel com cliente portador do cpf "+cpf+" n�o encontrado!");
//	}
//	
//	/**
//	 * BUSCA ALUGUEL DE DEVEDORES
//	 * */
//	public List<Aluguel> buscarAluguelDevedores(){
//		List<Aluguel> patterns = new ArrayList<>();
//		for (Aluguel alu : alugueis.values()) {
//			if(this.devedores.containsKey(alu.getCliente().getCpf())) {
//				patterns.add(alu);
//			}
//		}
//		return patterns;
//	}
//	/**
//	 * BUSCA ALUGUEL DE QUITADOS
//	 * */
//	public List<Aluguel> buscarAluguelQuitados(){
//		List<Aluguel> patterns = new ArrayList<>();
//		for (Aluguel alu : alugueis.values()) {
//			if(!this.devedores.containsKey(alu.getCliente().getCpf())) {
//				patterns.add(alu);
//			}
//		}
//		return patterns;
//	}
//	
//	
//	/**
//	 * BUSCA ALUGUEL DEVEDORES PELO CPF DO CLIENTE 
//	 * */
//	public List<Aluguel> buscarAluguelDevedoresCpfCliente(String cpf) throws Exception{
//		List<Aluguel> patterns = new ArrayList<>();
//		for (Aluguel alu : buscarAluguelDevedores()) {
//			if(alu.getCliente().getCpf().equals(cpf)) {
//				patterns.add(alu);
//			}
//		}
//		if(patterns.size()!=0) {
//			return patterns;
//		}
//		throw new Exception("Aluguel com cliente portador do cpf "+cpf+" n�o encontrado!");
//	}
//	
//	/**
//	 * BUSCA ALUGUEL DEVEDORES PELO N�MERO DO IM�VEL 
//	 * */
//	public List<Aluguel> buscarAluguelDevedoresNumero(String num) throws Exception{
//		List<Aluguel> patterns = new ArrayList<>();
//		for (Aluguel alu : buscarAluguelDevedores()) {
//			if (alu.match(num)) {
//				patterns.add(alu);
//			}
//		}
//		if(patterns.size()!=0) {
//			return patterns;
//		}
//		throw new Exception("Aluguel com im�vel de n�mero "+num+" n�o encontrado!");
//	}
//	
//	/**
//	 * PAGAR  ALUGUEL ATRASADO AP�S PAGAMENTO DO MESMO
//	 * */
//	public boolean realizarPag(String cpfCli, String numImovel) throws Exception {
//		Aluguel auxAluguel =  buscarAluguel(numImovel, cpfCli);
//		if (!imoveis.containsValue(buscarImovel(numImovel))) {
//			throw new Exception("Imovel com n�mero " + numImovel + " n�o existe. ");
//		} else if (!clientes.containsValue(buscarCliCpf(cpfCli))) {
//			throw new Exception("Cliente portador do cpf " + cpfCli + " n�o existe.");
//		} else if (alugueis.containsValue(auxAluguel)) {
//			if(((auxAluguel.getMultas().containsKey(auxAluguel.getDataPagFormatada())) 
//							&& (!auxAluguel.getPagamentos().containsKey(auxAluguel.getDataPagFormatada()))) || 
//							((!auxAluguel.getMultas().containsKey(auxAluguel.getDataPagFormatada())) 
//									&& (!auxAluguel.getPagamentos().containsKey(auxAluguel.getDataPagFormatada())))) {
//				auxAluguel.setDataPag(new Date());
//				System.out.println("-----"+auxAluguel.getDataPagFormatada()+"-----");
//				devedores.remove(cpfCli);
//				auxAluguel.pagar();
//				return true;
//			}else {
//				throw new Exception("�ltimo pagamento de aluguel foi efetuado no dia " + auxAluguel.getDataPagFormatada() + " referente ao im�vel " + numImovel + " localizado no cep: "+auxAluguel.getImovel().getCep());
//			}
//		} else {
//			throw new Exception("Cliente " + auxAluguel.getCliente().getNome() + "n�o vinculado ao im�vel" + numImovel + " localizado no cep: "+auxAluguel.getImovel().getCep());
//		}
//	}
//	/**
//	 * ADICIONA NOVO PROPRIET�RIO
//	 * */
//	public boolean addProp(Proprietario prop) throws Exception {
//		for (Proprietario p : proprietarios.values()) {
//			if (p.equals(prop.getNome(), prop.getCpf())) {
//				throw new Exception("Proprietario portador do cpf "+prop.getCpf()+" j� est� cadastrado.");
//			}
//		}
//		proprietarios.put(prop.getCod(), prop);
//		return true;
//	}
//
//	/**
//	 * REMOVE PROPRIET�RIO PELO CPF
//	 * */
//	public boolean rvProp(String cpf) throws Exception {
//		Proprietario p = buscarProp(cpf);
//		if (p!=null) {
//			clientes.remove(p.getCod());
//			return true;
//		} else {
//			throw new Exception("Propriet�rio portador do cpf " + cpf + " n�o existe.");
//		}
//	}
//	
//	/**
//	 * BUSCA PROPRIET�RIO PELO CPF
//	 * */
//	public Proprietario buscarProp(String cpf) throws Exception {
//		for (Proprietario p : proprietarios.values()) {
//			if (p.getCpf().equals(cpf)) {
//				return p;
//			}
//		} 
//		throw new Exception("Propriet�rio portador do cpf " + cpf + " n�o existe.");
//	}
//	
//	/**
//	 * ORDENA��O POR NOME EM ORDEM ALFAB�TICA DOS CLIENTES
//	 * */
//	public void ordenaClientes(List<Cliente> paterns) {
//		Collections.sort(paterns, new Comparator<Cliente>() {
//			public int compare(Cliente o1, Cliente o2) {
//				return o1.getNome().compareTo(o2.getNome());
//			}
//		});
//	}
//	
//	/**
//	 * ORDENA��O POR CEP EM ORDEM CRESCENTE DOS IM�VEIS
//	 * */
//	public void ordenaImoveis(List<Imovel> paterns) {
//		Collections.sort(paterns, new Comparator<Imovel>() {
//			public int compare(Imovel o1, Imovel o2) {
//				return o1.getCep().compareTo(o2.getCep());
//			}
//		});
//	}

}