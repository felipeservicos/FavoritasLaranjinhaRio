package datasource;

import modelodedados.ModeloDados;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataSource extends SQLiteOpenHelper {

	SQLiteDatabase db;

	public DataSource(Context context) {
		super(context, ModeloDados.getDbName(), null, 1);
		// TODO Auto-generated constructor stub
		db = this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(ModeloDados.CriarTabelaCadastro());

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		onCreate(db);

	}

	public void persist(ContentValues values, String tabela) {

		if (values.containsKey("id") && values.getAsInteger("id") != null
				&& values.getAsInteger("id") != 0) {
			Integer id = values.getAsInteger("id");
			db.update(tabela, values, "id = " + id, null);
		} else {

			db.insert(tabela, null, values);

		}

	}

	public Cursor find(String tabela, String[] columns, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy, String limit) {
		Cursor retorno = db.query(tabela, columns, selection, selectionArgs,
				groupBy, having, orderBy, limit);

		return retorno;
	}
}
