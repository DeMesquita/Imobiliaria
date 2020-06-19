package imob.Pojo;

public abstract class Imovel {
	private int cod;
	private int cod_prop;
	private int cod_imob;
	private boolean cozinha;
	private int quarto;
	private boolean areaServ;
	private boolean areaLaz;
	private int vagaGaragem;
	private int banheiros;
	private int sala;
	private float area;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String pais;
	private boolean status;
	
	public Imovel(int cod_prop, int cod_imob, boolean cozinha, int quarto, boolean areaServ, boolean areaLaz,
			int vagaGaragem, int banheiros, int sala, float area, String rua, String numero, String bairro,
			String cidade, String estado, String cep, String pais, boolean status) {
		super();
		this.cod_prop = cod_prop;
		this.cod_imob = cod_imob;
		this.cozinha = cozinha;
		this.quarto = quarto;
		this.areaServ = areaServ;
		this.areaLaz = areaLaz;
		this.vagaGaragem = vagaGaragem;
		this.banheiros = banheiros;
		this.sala = sala;
		this.area = area;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.pais = pais;
		this.status = status;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public int getCod_prop() {
		return cod_prop;
	}

	public void setCod_prop(int cod_prop) {
		this.cod_prop = cod_prop;
	}

	public int getCod_imob() {
		return cod_imob;
	}

	public void setCod_imob(int cod_imob) {
		this.cod_imob = cod_imob;
	}

	public boolean isCozinha() {
		return cozinha;
	}

	public void setCozinha(boolean cozinha) {
		this.cozinha = cozinha;
	}

	public int getQuarto() {
		return quarto;
	}

	public void setQuarto(int quarto) {
		this.quarto = quarto;
	}

	public boolean isAreaServ() {
		return areaServ;
	}

	public void setAreaServ(boolean areaServ) {
		this.areaServ = areaServ;
	}

	public boolean isAreaLaz() {
		return areaLaz;
	}

	public void setAreaLaz(boolean areaLaz) {
		this.areaLaz = areaLaz;
	}

	public int getVagaGaragem() {
		return vagaGaragem;
	}

	public void setVagaGaragem(int vagaGaragem) {
		this.vagaGaragem = vagaGaragem;
	}

	public int getBanheiros() {
		return banheiros;
	}

	public void setBanheiros(int banheiros) {
		this.banheiros = banheiros;
	}

	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Imovel [cod=" + cod + ", cod_prop=" + cod_prop + ", cod_imob=" + cod_imob + ", cozinha=" + cozinha
				+ ", quarto=" + quarto + ", areaServ=" + areaServ + ", areaLaz=" + areaLaz + ", vagaGaragem="
				+ vagaGaragem + ", banheiros=" + banheiros + ", sala=" + sala + ", area=" + area + ", rua=" + rua
				+ ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", cep="
				+ cep + ", pais=" + pais + ", status=" + status + "]";
	}
	
}