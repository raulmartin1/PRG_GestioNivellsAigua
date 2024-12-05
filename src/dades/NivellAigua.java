package dades;

public class NivellAigua {
	private Data dataMesura;
	private String nomEmbassament;
	private String poblacio;
	private String provincia;
	private double nivell;
	private double percentatgeVolum;
	private double volum;
	
	public NivellAigua(Data dataMesura, String nomEmbassament, String poblacio, String provincia, double nivell, double percent, double volum) {
		this.dataMesura=dataMesura.copia();
		this.nomEmbassament=nomEmbassament;
		this.poblacio=poblacio;
		this.provincia=provincia;
		this.nivell=nivell;
		this.percentatgeVolum=percent;
		this.volum=volum;
	}

	public boolean esTrobaEnAquestaPoblacio(String poblacio) {
		return (poblacio.equalsIgnoreCase(this.poblacio));
	}
	
	public boolean esTrobaEnAquestaProvincia(String provincia) {
		return (provincia.equalsIgnoreCase(this.provincia));
	}

	public boolean esTrobaEnAquestPeriode(Data d1, Data d2) {
		return (d1.esDataInferiorOigual(dataMesura) && dataMesura.esDataInferiorOigual(d2));
	}
	
	public boolean esAquestEmbassament(String nomEmbassament) {
        return this.nomEmbassament.equalsIgnoreCase(nomEmbassament);
    }

	public String getNomEmbassament() {
		return nomEmbassament;
	}

	public double getPercentVolum() {
		return percentatgeVolum;
	}

	 public double getVolum() {
        return volum;
    }

	@Override
	public String toString() {
		String aux="NivellAigua =>";
		aux=aux+"\n\tData mesura= " + dataMesura + ", nom embassament= "+nomEmbassament;
		aux=aux+"\n\tpoblacio= " + poblacio + ", provincia= "+provincia;
		aux=aux+"\n\tnivell= " + nivell + ", percentatgeVolum= "+percentatgeVolum+ ", volum= "+volum;
		return aux;
	}

	public NivellAigua copia() {
		NivellAigua mesura=new NivellAigua(dataMesura, nomEmbassament, poblacio, provincia, nivell, percentatgeVolum, volum);
		return mesura;
	}
}
