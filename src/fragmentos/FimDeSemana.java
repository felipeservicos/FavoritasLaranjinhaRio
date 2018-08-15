package fragmentos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import listapersonalizada.AdapterListView;
import modelo.Estacao;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import br.com.partiubike.favoritaslaranjinhario.R;
import br.com.partiubike.favoritaslaranjinhario.R.id;
import br.com.partiubike.favoritaslaranjinhario.R.layout;
import br.com.partiubike.favoritaslaranjinhario.R.menu;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

//Fragmento responsável pelo perfil "FDS" 

public class FimDeSemana extends Fragment {

	Bundle extras;

	// / Utilizado para consertar o "nullpointer exception" quando uma activity
	// eh chamada a partir de um fragment
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		setUserVisibleHint(true);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		final ListView listaEstacoes = (ListView) getView().findViewById(
				R.id.listtodosproblemasFDS);

		ArrayList<listapersonalizada.ItemListView> itens;

		
		extras = getActivity().getIntent().getExtras();
		itens = extras.getParcelableArrayList("vetor_fds");

		// Lista Composta
		// Cria o adapter

		AdapterListView adapterListView;
		adapterListView = new AdapterListView(getActivity(), itens);

		// Define o Adapter

		listaEstacoes.setAdapter(adapterListView);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_fim_de_semana,
				container, false);
		return rootView;
	}

}
