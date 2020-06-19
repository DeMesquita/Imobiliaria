package imob.Pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Aluguel{
	private int cod;
	private int cod_cli;
	private int cod_imov;
	private Date dataPag;
	private Date dataVenc;
	private Double valor;
	
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	public Aluguel(int cod, int cod_cli, int cod_imov, Date dataPag, Date dataVenc, Double valor) {
		this.cod = cod;
		this.cod_cli = cod_cli;
		this.cod_imov = cod_imov;
		this.dataPag = dataPag;
		this.dataVenc = dataVenc;
		this.valor = valor;
	}
	public Aluguel( int cod_cli, int cod_imov, Date dataPag, Date dataVenc, Double valor) {
		this.cod_cli = cod_cli;
		this.cod_imov = cod_imov;
		this.dataPag = dataPag;
		this.dataVenc = dataVenc;
		this.valor = valor;
	}

	public int getCod() {
		return cod;
	}

	public int getCod_cli() {
		return cod_cli;
	}

	public void setCod_cli(int cod_cli) {
		this.cod_cli = cod_cli;
	}

	public int getCod_imov() {
		return cod_imov;
	}

	public void setCod_imov(int cod_imov) {
		this.cod_imov = cod_imov;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataPag() {
		return dataPag;
	}
	
	public String getDataPagFormatada() {
		if(dataPag!=null) return formato.format(dataPag);
		return "00/00/0000";
	}
		
	public void setDataPag(Date dataPag) {
		this.dataPag = dataPag;
	}

	public Date getDataVenc() {
		return dataVenc;
	}
	
	public String getDataVenFormatada() {
		return formato.format(dataVenc);
	}

	public void setDataVenc(Date dataPagRea) {
		this.dataVenc = dataPagRea;
	}
	
	public boolean match(String pattern) {
		return toString().toUpperCase().contains(pattern.toUpperCase());
	}

	@Override
	public String toString() {
		return "Aluguel [cod=" + cod + ", cod_cli=" + cod_cli + ", cod_imov=" + cod_imov + ", dataPag=" + dataPag
				+ ", dataVenc=" + dataVenc + ", valor=" + valor + "]";
	}
}
