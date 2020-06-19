package imob.Pojo;

public class Cliente {
	private int cod;
	private String nome;
	private String rg;
	private String cpf;
	private String telefone;
	
	public Cliente(int cod, String nome, String rg, String cpf, String telefone) {
		this.cod = cod;
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;	
		this.telefone = telefone;
	}

	public Cliente(String nome, String rg, String cpf, String telefone) {
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean match(String pattern) {
		return toString().toUpperCase().contains(pattern.toUpperCase());
	}

	@Override
	public String toString() {
		return "Cliente [cod=" + cod + ", nome=" + nome + ", rg=" + rg + ", cpf=" + cpf + ", telefone=" + telefone+ "]";
	}

}
