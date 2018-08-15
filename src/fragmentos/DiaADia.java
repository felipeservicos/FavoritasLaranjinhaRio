package fragmentos;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

import listapersonalizada.AdapterListView;
import listapersonalizada.ItemListView;
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

import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.sax.RootElement;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import br.com.partiubike.favoritaslaranjinhario.FavoritasPrincipal;
import listapersonalizada.*;

// Fragmento responsável pelo perfil "dia a dia" 

public class DiaADia extends Fragment {

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
				R.id.listtodosproblemas);

		// /// Primeiro uso do app?
		String p1, p2;
		SharedPreferences settings = getActivity().getSharedPreferences(
				"idEstacoes", 0);
		p1 = settings.getString("id", "");
		p2 = settings.getString("idFDS", "");

		if (p1.equals("") & p2.equals("")){
			Toast.makeText(getActivity(), "Primeiro uso? Vá em configurações e adicione suas estações favoritas.",
					Toast.LENGTH_LONG).show();

		}
		////////////
		
		ArrayList<listapersonalizada.ItemListView> itens;

		extras = getActivity().getIntent().getExtras();
		itens = extras.getParcelableArrayList("vetor_diaadia");

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
		View rootView = inflater.inflate(R.layout.activity_dia_a_dia,
				container, false);

		return rootView;
	}

}
