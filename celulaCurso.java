public class celulaCurso {
	
	public Curso elemento;
	public celulaCurso prox;

	public celulaCurso() {
	
	}
	public celulaCurso(Curso curso) 
	{
		this.elemento = curso;
		this.prox = null;
	}

}
