package dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import modelo.Problemas;
import modelodedados.ModeloDados;


public class ProblemasDao {
	datasource.DataSource ds;
	ContentValues values;

	public ProblemasDao(Context context) {
		ds = new datasource.DataSource(context);

	}

	public boolean adicionar(Problemas obj) {
		boolean retorno = false;
		values = new ContentValues();
		values.put(ModeloDados.getData(), obj.getData());
		values.put(ModeloDados.getNumeroEstacao(), obj.getNumero_estacao());
		values.put(ModeloDados.getNumeroBike(), obj.getNumero_bike());
		values.put(ModeloDados.getDescricao(), obj.getDescricao());

		try {

			ds.persist(values, ModeloDados.getTabelaCadastro());

			retorno = true;

		} catch (Exception e) {

		}
		return retorno;
	}

	public List<Problemas> listarTodos() {

		Cursor cursor = ds.find(ModeloDados.getTabelaCadastro(), null, null,
				null, null, null, null, null);
		List<Problemas> lista = new ArrayList<Problemas>();

		if (cursor.getCount()>0){
			cursor.moveToFirst();
			
			for(int i=0;i<cursor.getCount();i++){
				Problemas obj =new Problemas();
				obj.setId(cursor.getInt(cursor.getColumnIndex(ModeloDados.getId())));
				obj.setData(cursor.getString(cursor.getColumnIndex(ModeloDados.getData())));
				obj.setNumero_estacao(cursor.getString(cursor.getColumnIndex(ModeloDados.getNumeroEstacao())));
				obj.setNumero_bike(cursor.getString(cursor.getColumnIndex(ModeloDados.getNumeroBike())));
				obj.setDescricao(cursor.getString(cursor.getColumnIndex(ModeloDados.getDescricao())));

				
				lista.add(obj);
				
				cursor.moveToNext();
			}
			
		}
		return lista;
	}
}
