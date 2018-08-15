package br.com.partiubike.favoritaslaranjinhario;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.com.partiubike.favoritaslaranjinhario.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigActivity extends Activity {

	EditText txt, txt_fds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config);
		getWindow().getDecorView().setBackgroundColor(0xFFFF9900);

		txt = (EditText) findViewById(R.id.txtEstacoes);
		txt_fds = (EditText) findViewById(R.id.txtEstacoesFDS);

		SharedPreferences settings = getSharedPreferences("idEstacoes", 0);
		txt.setText(settings.getString("id", "").toString());
		txt_fds.setText(settings.getString("idFDS", "").toString());
		
	}

	public void btnSalvarClick(View v) {
		
		gravaPreferencias();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.config, menu);
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	protected void onStop() {

		super.onStop();
		
	gravaPreferencias();
	
	}
	
	public void gravaPreferencias(){
		SharedPreferences settings = getSharedPreferences("idEstacoes", 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("id", txt.getText().toString());
		editor.putString("idFDS", txt_fds.getText().toString());
		
		// Confirma a gravação dos dados
		editor.commit();
		Toast.makeText(getApplicationContext(), "Estações favoritas salvas! :) Não esqueça de Tocar em menu/Atualizar! ",
				Toast.LENGTH_LONG).show();

	}
}
