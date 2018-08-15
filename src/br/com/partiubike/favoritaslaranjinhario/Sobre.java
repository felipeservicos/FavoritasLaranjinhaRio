package br.com.partiubike.favoritaslaranjinhario;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Sobre extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sobre);
		getWindow().getDecorView().setBackgroundColor(0xFFFF9900);
		

		
		
	}
	
	
	protected void onStop() {

		super.onStop();
		
	//finish();
	
	}
	

}
