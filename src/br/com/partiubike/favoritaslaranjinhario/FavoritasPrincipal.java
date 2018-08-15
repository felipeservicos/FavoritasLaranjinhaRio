package br.com.partiubike.favoritaslaranjinhario;

import java.util.Locale;

import fragmentos.*;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import android.R.color;
import android.app.Activity;


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

import modelo.Estacao;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;










//import com.example.favoritaslaranjinhario.StartPoint.PrefetchData;



import br.com.partiubike.favoritaslaranjinhario.R;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


public class FavoritasPrincipal extends Activity implements ActionBar.TabListener {


	
	
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favoritas_principal);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		getWindow().getDecorView().setBackgroundColor(0xFFFF9900);
		
		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favoritas_principal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			
			Intent i =new Intent(this, ConfigActivity.class);
			startActivity(i);

			
		}
		
		if (id == R.id.action_atualizar) {
	
			finish();
			Intent intent = new Intent(this,StartPoint.class);
			startActivity(intent);
		}
		
		
////////// OPÇÕES DE MENU OCULTAS PARA FUTUROS ACESSOS AO BANCO DE DADOS	FEV/2016	
	/*	if (id==R.id.action_problemas){
			Intent telaproblemas =new Intent(this, Problema.class);
			startActivity(telaproblemas);
		}
		
		if (id==R.id.action_listartodos){
			Intent telalistarproblemas =new Intent(this, ListarTodosProblemas.class);
			startActivity(telalistarproblemas);
		}*/
		
		if (id==R.id.action_sobre){
			
			Intent telasobre =new Intent(this, Sobre.class);
			startActivity(telasobre);
		}
		
		if (id==R.id.action_sair){
			finish();
		}
		
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			
			switch (position) {
			case 0:

				return new DiaADia();

			case 1:

				return new FimDeSemana();

			}
			
			return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return "DIA A DIA".toUpperCase(l);
			case 1:
				return "FDS".toUpperCase(l);
			
			}
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_favoritas_principal, container,
					false);
			return rootView;
		}
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	
	
/*	
	
	private class PrefetchData extends AsyncTask<Void, Void, Void> {

		ProgressDialog barProgressDialog;
		Estacao est = null;
		
		ArrayList<listapersonalizada.ItemListView> diaadia;
		ArrayList<listapersonalizada.ItemListView> fds;


		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// before making http calls

			getWindow().getDecorView().setBackgroundColor(0xFFFF9900);

			barProgressDialog = new ProgressDialog(FavoritasPrincipal.this);

			barProgressDialog.setTitle("Carregando ...");

			barProgressDialog
					.setMessage("Obtendo informações sobre as estações ...");

			barProgressDialog.setIndeterminate(true);

			barProgressDialog.setProgressStyle(barProgressDialog.STYLE_SPINNER);

			barProgressDialog.setCancelable(false);

			barProgressDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			
			 * Will make http call here This call will download required data
			 * before launching the app example: 1. Downloading and storing in
			 * SQLite 2. Downloading images 3. Fetching and parsing the xml /
			 * json 4. Sending device information to server 5. etc.,
			 

			Estacao est = new Estacao();
			
			diaadia=est.getFavoritas(getApplicationContext(), "id",est.popula(getApplicationContext()));
			fds=est.getFavoritas(getApplicationContext(), "idFDS", est.popula(getApplicationContext()));


			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// After completing http call
			// will close this activity and lauch main activity

		
			
			Bundle list_bundle = new Bundle();
			list_bundle.putParcelableArrayList("vetor_diaadia", fds);



			// close this activity
			barProgressDialog.dismiss();
		}

	}
	
	*/
	
	

	
	
}
