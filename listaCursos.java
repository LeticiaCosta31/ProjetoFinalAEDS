public class listaCursos {
	
    private celulaCurso primeiro,ultimo;
	
	public listaCursos() {
			primeiro = new celulaCurso();
			ultimo=primeiro;
    }

	//inserir um objeto curso, com: cod, nomeCurso e qntVagas
    public void inserirFim (Curso x) 
	{
		ultimo.prox = new celulaCurso(x);
		ultimo=ultimo.prox;
	}
	
    public Curso pesquisar (int codigo) 
	{
		celulaCurso i;
		for (i=primeiro.prox;i!=null;i=i.prox) 
		{
			if (i.elemento.getCod()==codigo) 
			{
				return i.elemento;
			}
		}
		return null;
	}
    
    public String escreverSaida() 
		{
    		String saida = "";
    		Curso c;
			for (celulaCurso i=primeiro.prox;i != null;i=i.prox) 
			{
				c = i.elemento;
				saida += c.toString();
				saida += "\n";
			}
			
			return saida;
		}
}