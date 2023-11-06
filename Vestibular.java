
import java.io.*;
public class Vestibular {

	private listaCursos listaCursos;
	private Candidato[] candidatos;
	private int quantCursos;
	private int quantCandidatos;

	public Vestibular() {
		listaCursos = new listaCursos();
		candidatos=new Candidato[0];
	}

	public void ordenarCandidatos() {
		
		mergesort(this.candidatos, 0, this.candidatos.length - 1);
	}
	
	public static void mergesort(Candidato[] array, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergesort(array, esq, meio);
            mergesort(array, meio + 1, dir);
            intercalar(array, esq, meio, dir);
        }
    }

    public static void intercalar(Candidato[] array, int esq, int meio, int dir) {
        int tamEsq = meio - esq + 1;
        int tamDir = dir - meio;

        Candidato[] arrayEsq = new Candidato[tamEsq + 1];
        Candidato[] arrayDir = new Candidato[tamDir + 1];

        Candidato sentinela = new Candidato("Sentinela", -Double.MAX_VALUE, -Double.MAX_VALUE, -Double.MAX_VALUE, -1, -2);
        arrayEsq[tamEsq] = sentinela;
        arrayDir[tamDir] = sentinela;

        int posEsq, posDir, pos;

        for (posEsq = 0; posEsq < tamEsq; posEsq++) {
            arrayEsq[posEsq] = array[esq + posEsq];
        }

        for (posDir = 0; posDir < tamDir; posDir++) {
            arrayDir[posDir] = array[(meio + 1) + posDir];
        }

        for (posEsq = 0, posDir = 0, pos = esq; pos <= dir; pos++) {
            Candidato esquerdo = arrayEsq[posEsq];
            Candidato direita = arrayDir[posDir];

            if (esquerdo.getMedia() > direita.getMedia()) 
            {
                array[pos] = arrayEsq[posEsq++];
            } 
            else if (esquerdo.getMedia() == direita.getMedia()) 
            {
                if (direita.getNotaRed() > esquerdo.getNotaRed()) 
                
                {
                    array[pos] = arrayDir[posDir++];
                } else {
                    array[pos] = arrayEsq[posEsq++];
                }
            }
            else 
            {
                array[pos] = arrayDir[posDir++];
            }
        }
    }
    
	public void lerEntrada(String nomeArq) {
		try {
	
			// Para leitura e criação do arq
			BufferedReader br = new BufferedReader(new FileReader(nomeArq));

			// Variavel que armazena o conteúdo da primeira linha do arq
			String teste = br.readLine();

			// criação do vetor
			String qnt[] = new String[2];
			// armazenamento em cada posição do vetor até encontrar o ;
			qnt = teste.split(";");

			// variáveis que contém a quantidade de cursos e quantidade de candidatos que o arq possui
			 quantCursos = Integer.parseInt(qnt[0]);
			 quantCandidatos = Integer.parseInt(qnt[1]);
			 
			 if (quantCandidatos>1000) 
			 {
				 System.out.println("Número de candidatos acima do limite");
			 }
			 else 
			 {
				 Candidato candidatos[]= new Candidato[quantCandidatos];
				 
				// Criação do vetor que seja utilizado para armazenar cada linha do arq
					String vet[] = new String[3];

					// estrura de repeticao que irá percorrer e armazenar as informações sobre o  curso, seu cod e sua quantidade de vagas
					for (int i = 0; i < quantCursos; i++) {

						String linha = br.readLine();
						vet = linha.split(";");

						int codCurso = Integer.parseInt(vet[0]);
						String nomeCurso = vet[1];
						int qtdVagas = Integer.parseInt(vet[2]);
					

						//inserir na lista 
						listaCursos.inserirFim(new Curso(codCurso, nomeCurso, qtdVagas));
			
					}

					// Criação do vetor que seja utilizado para armazenar cada linha do arq
					String vet2[] = new String[6];

					// estrura de repeticao que irá percorrer e armazenar as informações sobre nome, notaRed, notaMat,notaLing,op1 e op2
					for (int i = 0; i < quantCandidatos; i++) {

						String linha2 = br.readLine();
						vet2 = linha2.split(";");

						String nomeCandidato1 = vet2[0];
						double notaRed = Double.parseDouble(vet2[1]);
						double notaMat = Double.parseDouble(vet2[2]);
						double notaLing = Double.parseDouble(vet2[3]);
						int codCursoOp1 = Integer.parseInt(vet2[4]);
						int codCursoOp2 = Integer.parseInt(vet2[5]);

						candidatos[i]= new Candidato(nomeCandidato1, notaRed, notaMat, notaLing, codCursoOp1,codCursoOp2);

						System.out.println("Antes da ordenação");
						System.out.println(candidatos[i]);
						
					}

					//vetor de candidatos recebendo os candidatos inseridos
					this.candidatos = candidatos;

					//chamar o metodo de ordenação
					ordenarCandidatos();

					
					 System.out.println("\n");
					 System.out.println("Após a ordenação");
					 for (int i=0;i<quantCandidatos;i++) 
					 {
						 candidatos[i].mostrar();

					 }
			 }

			br.close();
		}
		catch 
		(

		IOException iex) {
			System.out.println(iex.getMessage());
		}

	}

	public void escreverSaida(String nomeArq) 
	{
		try 
		{	
			FileWriter saida = new FileWriter(nomeArq);
		
			saida.write(listaCursos.escreverSaida());
			
			
			saida.close();

		} 
		
		catch (IOException iex) 
		{
			System.out.println(iex.getMessage());
		}
	}

	
	public void calcularClassificacao()
	{

		//for each que percorre o vetor de candidatos ordenados após o merge
	
		for(Candidato candidato : candidatos) 
		{
			//armazena qual a primeira opção de curso do candidato
			Curso op1 = listaCursos.pesquisar(candidato.getOp1());
			
			// faz a tentativa de inserir o candidato
			boolean selecionado = op1.inserirListaSelecionados(candidato);

			//se ele não for selecionado na primeira opção, tentativa de inserir na segunda opção
			if(!selecionado) 
			{
				Curso op2 = listaCursos.pesquisar(candidato.getOp2());
				
				//vai para lista de selecionados ou lista de espera
				op2.inserirListaSelecionados(candidato);
			}
		}
	}

}