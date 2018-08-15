package modelodedados;

public class ModeloDados {
	
	private static final String DB_NAME="db_laranjinha.sqlite";
	private static final String TABELA_CADASTRO="problemas";
	private static final String ID="id";
	private static final String DATA="data";
	private static final String NUMERO_ESTACAO="numero_estacao";
	private static final String NUMERO_BIKE="numero_bike";
	private static final String DESCRICAO="descricao";
	private static final String TIPO_TEXTO="TEXT";
	private static final String TIPO_INTEIRO="INTEGER";
	private static final String TIPO_INTEIRO_PK="INTEGER PRIMARY KEY";
	
	public static String CriarTabelaCadastro(){
		String query ="CREATE TABLE " + TABELA_CADASTRO;
		
		query+=" (";
	
		query+=ID +" "+TIPO_INTEIRO_PK + ", ";
		query+=DATA+" "+ TIPO_TEXTO + ", ";
		query+=NUMERO_ESTACAO+" "+ TIPO_INTEIRO + ", ";
		query+=NUMERO_BIKE+" "+ TIPO_INTEIRO + ", ";
		query+=DESCRICAO+" "+ TIPO_TEXTO + " ";
		
		
		
		query+=")";
		
		return query;
		
	}

	// adicionando getters e setters apenas
	
	public static String getId() {
		return ID;
	}

	public static String getData() {
		return DATA;
	}

	public static String getNumeroEstacao() {
		return NUMERO_ESTACAO;
	}

	public static String getNumeroBike() {
		return NUMERO_BIKE;
	}

	public static String getDescricao() {
		return DESCRICAO;
	}

	public static String getDbName() {
		return DB_NAME;
	}

	public static String getTabelaCadastro() {
		return TABELA_CADASTRO;
	}
	

	
	
	
	
}
