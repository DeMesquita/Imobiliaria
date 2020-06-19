package imob.Pojo;

public class Proprietario {
	private int cod;
	private String nome;
	private String rg;
	private String cpf;

	
	public Proprietario(int cod, String nome, String rg, String cpf) {
		super();
		this.cod = cod;
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
	
	}

	public Proprietario(String nome, String rg, String cpf) {
		super();
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		
	}
	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
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

	

	@Override
	public String toString() {
		return "Proprietario [cod=" + cod + ", nome=" + nome + ", rg=" + rg + ", cpf=" + cpf + "]";
	}
	


}
