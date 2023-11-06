import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Curso{
	private int cod;
	private String nome;
	private int quantVagas;
	private ArrayList<Candidato> listaSelecionados;
	private Queue<Candidato> filaEspera;
	private Double notadeCorte;
	
	public Curso() {
	
	}
	
	public Curso(int cod, String nome, int quantVagas) 
	{
		this.cod=cod;
		this.nome=nome;
		this.quantVagas=quantVagas;
		this.notadeCorte=0.0;

		listaSelecionados = new ArrayList<>();
		filaEspera = new LinkedList<>();
	}
	
	public void mostrar(){
		System.out.println("Cod:" +cod +"Nome:"+nome +"Vagas:"+quantVagas);
	}
	
	public boolean inserirListaSelecionados (Candidato candidato) 
	{
		if(listaSelecionados.size()<quantVagas)
		{
			//adiciona um candidato na sua opção de curso como selecionado
			listaSelecionados.add(candidato);
			
			//a nota de corte é a media do ultimo candidato selecionado
			this.notadeCorte=candidato.getMedia();
			
			return true;
		}
		else 
		{
			//se ele não for selecionado, retorna falso e adiciona na fila de espera
			filaEspera.add(candidato);
			
			return false;
		}
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
	public int getQuantVagas() {
		return quantVagas;
	}
	public void setQuantVagas(int quantVagas) {
		this.quantVagas = quantVagas;
	}
	public ArrayList<Candidato> getListaSelecionados() {
		return listaSelecionados;
	}
	public void setListaSelecionados(ArrayList<Candidato> listaSelecionados) {
		this.listaSelecionados = listaSelecionados;
	}
	public Queue<Candidato> getFilaEspera() {
		return filaEspera;
	}
	public void setFilaEspera(Queue<Candidato> filaEspera) {
		this.filaEspera = filaEspera;
	}
	public Double getNotadeCorte() {
		return notadeCorte;
	}
	public void setNotadeCorte(Double notadeCorte) {
		this.notadeCorte = notadeCorte;
	}
	@Override
	public String toString() {
		String saida = nome + " " +String.format("%.2f",notadeCorte) + "\n"+ "Selecionados \n";
		
		for( Candidato cand : listaSelecionados) 
		{
			saida += cand.getNome() + " "+ String.format("%.2f",cand.getMedia()) + "\n";
		}
		
		saida += "Fila de Espera \n";
		
		for( Candidato cand : filaEspera) 
		{
			saida += cand.getNome() + " "+String.format("%.2f", cand.getMedia()) + "\n";
		}
		
		return saida;
	}
}
