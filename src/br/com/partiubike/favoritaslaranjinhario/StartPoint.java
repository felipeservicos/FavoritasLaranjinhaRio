package br.com.partiubike.favoritaslaranjinhario;

import java.util.ArrayList;
import java.util.List;

import br.com.partiubike.favoritaslaranjinhario.R;
import listapersonalizada.ItemListView;
import listapersonalizada.AdapterListView;
import modelo.Estacao;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;

// Activity criada para funcionar como um loader antes da tela principal do APP
// Exibindo uma caixa de dialogo e processando hhtp request em segundo plano
public class StartPoint extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_point);


		if(haveNetworkConnection()){
		
		// Chama pres e pos para processamento em background
		
		new PrefetchData().execute();

		
	}else{
		
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Sem Conexão...");
		alertDialog.setMessage("Sem internet?");
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {

finish();
			
		}
		});
		
		alertDialog.show();
		
	}
		

	}

	
	//Verificar se há conexão com a internet
	private boolean haveNetworkConnection() {
	    boolean haveConnectedWifi = false;
	    boolean haveConnectedMobile = false;

	    ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	    for (NetworkInfo ni : netInfo) {
	        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	            if (ni.isConnected())
	                haveConnectedWifi = true;
	        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	            if (ni.isConnected())
	                haveConnectedMobile = true;
	    }
	    return haveConnectedWifi || haveConnectedMobile;
	}
	
	
	/**
	 * Async Task para efetuar chamadas http
	 */
	private class PrefetchData extends AsyncTask<Void, Void, Void> {

		ProgressDialog barProgressDialog;
		Estacao est = null;

		ArrayList<listapersonalizada.ItemListView> diaadia;
		ArrayList<listapersonalizada.ItemListView> fds;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Antes das chamadas http

			getWindow().getDecorView().setBackgroundColor(0xFFFF9900);

			barProgressDialog = new ProgressDialog(StartPoint.this);

			barProgressDialog.setTitle("Carregando ...");

			barProgressDialog.setMessage("Obtendo informações sobre as estações ...");

			barProgressDialog.setIndeterminate(true);

			barProgressDialog.setProgressStyle(barProgressDialog.STYLE_SPINNER);

			barProgressDialog.setCancelable(false);

			barProgressDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {

			/*
			 * Aqui podem entrar as chamadas http, downloads, gravação no banco, envio de informações
			 * processamento de json, parsing de xml etc.. Tudo que pode ser feito em background
			 */

			Estacao est = new Estacao();

			diaadia = est.getFavoritas(getApplicationContext(), "id",est.popula(getApplicationContext()));
			fds = est.getFavoritas(getApplicationContext(), "idFDS",est.popula(getApplicationContext()));

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Após completar as chamadas http
			// A atividade StartPoint será fechada e chamará a atividade Principal Favoritas laranjinha 

			Intent carregaprincipal = new Intent(StartPoint.this,FavoritasPrincipal.class);

			carregaprincipal.putParcelableArrayListExtra("vetor_diaadia",diaadia);
			carregaprincipal.putParcelableArrayListExtra("vetor_fds", fds);
			startActivity(carregaprincipal);

			// Fecha essa atividade
			barProgressDialog.dismiss();
			finish();
			
		}
		


	}

}
