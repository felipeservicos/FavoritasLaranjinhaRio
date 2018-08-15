package br.com.partiubike.favoritaslaranjinhario;

import modelo.Problemas;

import br.com.partiubike.favoritaslaranjinhario.R;
import br.com.partiubike.favoritaslaranjinhario.R.id;
import br.com.partiubike.favoritaslaranjinhario.R.layout;
import br.com.partiubike.favoritaslaranjinhario.R.menu;
import br.com.partiubike.favoritaslaranjinhario.R.string;

import dao.ProblemasDao;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Problema extends Activity implements OnClickListener {

	EditText editdata;
	EditText editnumestacao;
	EditText editnumbikes;
	EditText editnumdescricao;
	String id_instancia;
	Bundle extras;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_problema);
		
		editdata=(EditText) findViewById(R.id.txtdata);
		editnumestacao=(EditText) findViewById(R.id.txtEstacoes);
		editnumbikes=(EditText) findViewById(R.id.txtnumbike);
		editnumdescricao=(EditText) findViewById(R.id.txtdescricao);
		Button btnsalvar=(Button) findViewById(R.id.btsalvar);
		Button btncancelar=(Button) findViewById(R.id.btcancelar);
		
		btnsalvar.setOnClickListener(this);
		btncancelar.setOnClickListener(this);
		
		extras = getIntent().getExtras();
		
		if(extras != null) {
	        editdata.setText(extras.getString("data"));
	        editnumbikes.setText(extras.getString("numero_bike"));
	        editnumdescricao.setText(extras.getString("descricao"));
	        id_instancia=extras.getString("id");
	    }
		
		
				}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.problema, menu);
		return true;
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btsalvar:
			
			Problemas cadastro =new Problemas();
			
			if (!id_instancia.isEmpty() || !id_instancia.equals("") || !id_instancia.equals(null) ){
			cadastro.setId(Integer.parseInt(id_instancia));	
			}
			
			cadastro.setData(editdata.getText().toString());
	//		cadastro.setNumero_estacao(editnumestacao.getText().toString());
			cadastro.setNumero_bike(editnumbikes.getText().toString());
			cadastro.setDescricao(editnumdescricao.getText().toString());
			
			ProblemasDao dao =new ProblemasDao(getApplicationContext());
			
			if (dao.adicionar(cadastro)){
				Toast toast = Toast.makeText(getBaseContext(), "Adicionado!", Toast.LENGTH_LONG);
				toast.show();
				finish();
			}else {
				Toast toast = Toast.makeText(getBaseContext(), "Erro ao Gravar", Toast.LENGTH_LONG);
				toast.show();
			}

			
			break;

		case R.id.btcancelar:
			finish();
		}
	}
	
	
}
