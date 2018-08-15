/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import listapersonalizada.AdapterListView;
import listapersonalizada.ItemListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import br.com.partiubike.favoritaslaranjinhario.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

/**
 *
 * @author furnas
 */
public class Estacao implements Parcelable{

	private String Nome;
	private String IdEstacao;
	private String StatusOnline;
	private String StatusOperacao;
	private String BikesDisponiveis;
	private String numBicicletas;
	private String Endereco;
	private String EstacaoDeEvento;

	
public Estacao(Parcel in){
	Nome=in.readString();
	IdEstacao=in.readString();
	StatusOnline=in.readString();
	StatusOperacao=in.readString();
	BikesDisponiveis=in.readString();
	numBicicletas=in.readString();
	Endereco=in.readString();
	EstacaoDeEvento=in.readString();
}
	

@Override
public int describeContents() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public void writeToParcel(Parcel dest, int flags) {
	// TODO Auto-generated method stub
	
	dest.writeString(Nome);
	dest.writeString(IdEstacao);
	dest.writeString(StatusOnline);
	dest.writeString(StatusOperacao);
	dest.writeString(BikesDisponiveis);
	dest.writeString(numBicicletas);
	dest.writeString(Endereco);
	dest.writeString(EstacaoDeEvento);
	
	
}

public static final Parcelable.Creator<Estacao> CREATOR = new Parcelable.Creator<Estacao>()
{
    public Estacao createFromParcel(Parcel in)
    {
        return new Estacao(in);
    }
    public Estacao[] newArray(int size)
    {
        return new Estacao[size];
    }
};



	public Estacao() {
	// TODO Auto-generated constructor stub
}


	public String getNome() {
		return Nome;
	}

	public void setNome(String Nome) {
		this.Nome = Nome;
	}

	public String getIdEstacao() {
		return IdEstacao;
	}

	public void setIdEstacao(String IdEstacao) {
		this.IdEstacao = IdEstacao;
	}

	public String getStatusOnline() {
		return StatusOnline;
	}

	public void setStatusOnline(String StatusOnline) {
		this.StatusOnline = StatusOnline;
	}

	public String getStatusOperacao() {
		return StatusOperacao;
	}

	public void setStatusOperacao(String StatusOperacao) {
		this.StatusOperacao = StatusOperacao;
	}

	public String getBikesDisponiveis() {
		return BikesDisponiveis;
	}

	public void setBikesDisponiveis(String BikesDisponiveis) {
		this.BikesDisponiveis = BikesDisponiveis;
	}

	public String getNumBicicletas() {
		return numBicicletas;
	}

	public void setNumBicicletas(String numBicicletas) {
		this.numBicicletas = numBicicletas;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String Endereco) {
		this.Endereco = Endereco;
	}

	public String getEstacaoDeEvento() {
		return EstacaoDeEvento;
	}

	public void setEstacaoDeEvento(String EstacaoDeEvento) {
		this.EstacaoDeEvento = EstacaoDeEvento;
	}

	public String RemoveEspecial(String x) {
		return x.replace("\"", "").replace(",", "").replace(")", "")
				.replace(";", "").trim();

	}

	// Metodo para Pegar as estacoes gravadas pelo usuario
	public ArrayList<String> preferencias(Context context, String perfil) {

		ArrayList<String> numeroEstacao = new ArrayList<String>();
		SharedPreferences settings = context.getSharedPreferences("idEstacoes",
				Context.MODE_WORLD_READABLE);
		String id = settings.getString(perfil, "");
		String[] partes = id.split("-");

		for (String itemId : partes) {
			numeroEstacao.add(itemId);
		}

		return numeroEstacao;
	}

	public List<Estacao> popula(Context context) {
		List<Estacao> todasEstacoes = new ArrayList<Estacao>();
		String url = "http://ww2.mobilicidade.com.br/sambarjpt/mapaestacao.asp";

		String s = "";
		String x = "";

		try {
			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setContentCharset(params, "ISO-8859-1");

			HttpClient client = new DefaultHttpClient();


			URI website = new URI(url);
			HttpGet request = new HttpGet();

			request.setURI(website);

			HttpResponse response = client.execute(request);
			response.getStatusLine().getStatusCode();

			// Leitura da web
			BufferedReader d = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent(), "ISO-8859-1"));

			// loop que le a pagina e grava no vetor todasEstacoes

			do {

				if (s != null) {

					if (s.contains("// Criando")) {
						Estacao est = new Estacao();
						s = d.readLine();
						s = d.readLine();
						s = d.readLine();
						s = d.readLine();

						est.setNome(RemoveEspecial(s));
						s = d.readLine();
						est.setIdEstacao(RemoveEspecial(s));
						s = d.readLine();
						est.setStatusOnline(RemoveEspecial(s));
						s = d.readLine();
						est.setStatusOperacao(RemoveEspecial(s));
						s = d.readLine();
						est.setBikesDisponiveis(RemoveEspecial(s));
						s = d.readLine();
						est.setNumBicicletas(RemoveEspecial(s));
						s = d.readLine();
						est.setEndereco(RemoveEspecial(s));
						s = d.readLine();
						est.setEstacaoDeEvento(RemoveEspecial(s));

						todasEstacoes.add(est);

					} else {
					}

				}
			} while ((s = d.readLine()) != null);

			d.close();
		} catch (MalformedURLException mue) {
			System.err.println("Problema - URL errada");
			Toast.makeText(context, "Problema - URL errada", Toast.LENGTH_SHORT);
			System.exit(2);
		} catch (IOException ioe) {
			System.err.println("Problema- Problema de IO. BIKE RIO FORA?");
			Toast.makeText(context, "Problema- Problema de IO. BIKE RIO FORA?",
					Toast.LENGTH_SHORT);
			System.out.println(ioe.getCause());
			System.out.println(ioe);
			System.exit(3);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {


		}

		return todasEstacoes;
	}

	public ArrayList<ItemListView> getFavoritas(Context context, String perfil,
			List<Estacao> todasEstacoes) {

		ArrayList<String> numeroEstacao = new ArrayList<String>();
		List<Estacao> favoritas = new ArrayList<Estacao>();

		// /Para montar array com imagem
		ArrayList<listapersonalizada.ItemListView> itens = new ArrayList<listapersonalizada.ItemListView>();

		String x = "";

		numeroEstacao = preferencias(context, perfil);

		/*
		 * Loop que lê o vetor com todas as estações e seleciona as estacoes
		 * favoritas baseadas nas preferências do usuário
		 */

		for (String item : numeroEstacao) {

			if (todasEstacoes.isEmpty()) {
				break;
			}

			for (int i = 0; i < todasEstacoes.size(); i++) {

				if (todasEstacoes.get(i).getIdEstacao().matches(item)) {

					favoritas.add(todasEstacoes.get(i));
				}
			}

		}

		int numero = favoritas.size();
		String[] bikes = new String[numero];

		int recurso;

		// Verifica disponibilidade das estações e armazena os bullets associados a elas

		for (int j = 0; j < favoritas.size(); j++) {
			x = "";
			if (favoritas.get(j).getStatusOnline().equals("A")
					&& favoritas.get(j).getStatusOperacao().equals("EO")) {
				x = "ESTAÇÃO DISPONÍVEL";
				recurso = R.drawable.greenball;
			} else {
				x = "INDISPONÍVEL!!! :(";
				recurso = R.drawable.redballsmall;

			}

			bikes[j] = "Estação: "
					+ favoritas.get(j).getIdEstacao()
					+ " - "
					+ x
					+ "\n"
					+ "Nome: "
					+ favoritas.get(j).getNome()
					+ "\n"
					+ "Bicicletas Disponíveis: "
					+ favoritas.get(j).getBikesDisponiveis()
					+ "\n"
					+ "Vagas Livres: "
					+ String.valueOf(Integer.parseInt(favoritas.get(j)
							.getNumBicicletas())
							- Integer.parseInt(favoritas.get(j)
									.getBikesDisponiveis()));

			
			// Compondo o vetor com imagem
			
			listapersonalizada.ItemListView vetorcomposto = (listapersonalizada.ItemListView) new listapersonalizada.ItemListView(
					bikes[j], recurso);
			itens.add(vetorcomposto);
			// ///
		}

		

		return itens;

	}



}
