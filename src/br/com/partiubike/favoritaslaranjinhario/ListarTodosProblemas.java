package br.com.partiubike.favoritaslaranjinhario;

import java.util.ArrayList;
import java.util.List;

import modelo.Problemas;
import modelodedados.ModeloDados;

import br.com.partiubike.favoritaslaranjinhario.R;

import dao.ProblemasDao;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListarTodosProblemas extends Activity implements OnItemClickListener{
	
	ProblemasDao dao;
	ListView mainListView;
	ArrayAdapter<String> listAdapter;
	List<Problemas> registros;
	ArrayList<String> dadosListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listar_todos_problemas);
		
		dao= new ProblemasDao(getApplicationContext());
		dadosListView=new ArrayList<String>();
		
		registros=new ArrayList<Problemas>();
		registros=dao.listarTodos();
		
		for(int i=0;i<registros.size();i++){
			
			dadosListView.add(registros.get(i).getId() + ": Data: "
					+ registros.get(i).getData().toString() +"\n" +
					
					"Bike nº: "+ registros.get(i).getNumero_bike().toString()+"\n" +
					"Descrição: "+registros.get(i).getDescricao().toString());
		///	"Estação: "+registros.get(i).getNumero_estacao().toString()+
			
		}
		
		mainListView =(ListView) findViewById(R.id.listtodosproblemas);
		listAdapter =new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1,dadosListView);
		mainListView.setAdapter(listAdapter);
		mainListView.setOnItemClickListener(this);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listar_todos_problemas, menu);
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
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

						
		String mensagem = "Item Selecionaod: " + registros.get(position).getId().toString();
		Toast.makeText(getApplicationContext(), mensagem,Toast.LENGTH_SHORT).show();
		
		Intent intent = new Intent(this,Problema.class); 
		intent.putExtra("id",  registros.get(position).getId().toString());
		intent.putExtra("data", registros.get(position).getData().toString());
		intent.putExtra("numero_bike",registros.get(position).getNumero_bike().toString());
		intent.putExtra("descricao",registros.get(position).getDescricao().toString());
		startActivity(intent); 
		finish();
		
	}
}
