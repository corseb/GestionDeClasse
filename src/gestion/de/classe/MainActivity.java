package gestion.de.classe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	// array_names contains the pupil names
	String[] array_CE1 = getResources().getStringArray(R.array.liste_eleves_CE1);
	String[] array_CM1 = getResources().getStringArray(R.array.liste_eleves_CM1);
	static String[] array_names;
	// array_names contains the pupil names that are present and have not been asked a question yet (="remaining pupils" list)
	ArrayList<String> presentNameList;
	ArrayList<String> nameList;
	// tVpupilName is the TextView in which the pupil name is displayed
	TextView tVpupilName;
	TextView tVdebugInfo;
	Spinner spinClasse;
	ArrayAdapter<CharSequence> adapClasse;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fill_arrays_and_textViews(1);
		fill_spinner();
		//spinClasse.setOnItemSelectedListener(this);
		addListenerOnSpinnerItemSelection();
	}

	public void addListenerOnSpinnerItemSelection() {
		spinClasse.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView parent, View view, int pos, long id) {
				Toast.makeText(parent.getContext(), "Classe sélectionnée : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
				fill_array_names(pos);
			}

			@Override
			public void onNothingSelected(AdapterView parent) {
				// TODO Auto-generated method stub		
			}});
	  }

	public void fill_arrays_and_textViews(int index_classe){
		fill_array_names(index_classe);
		// at the beginning (before attendance check and interrogation) array_current_names is array_names
		nameList = new ArrayList<String>(Arrays.asList(array_names));
    	// get the right TextView
    	tVpupilName = (TextView) findViewById(R.id.tV_pupil_name);
    	tVdebugInfo = (TextView) findViewById(R.id.tV_debug_info);
	}
	
	public void fill_array_names(int index_classe){
		// fill array_names with the corresponding pupil name list (0-> CE1, 1 -> CM1)
		if (index_classe==0) {
			array_names = array_CE1;}
		else {
			array_names = array_CM1;
		}
	}
	
	public void fill_spinner(){
		spinClasse= (Spinner) findViewById(R.id.spinnerClasse);
    	// Create an ArrayAdapter using the string array and a default spinner layout
    	adapClasse = ArrayAdapter.createFromResource(this, 
    			R.array.liste_classes, android.R.layout.simple_spinner_item);
    	// Specify the layout to use when the list of choices appears
    	adapClasse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	// Apply the adapter to the spinner
    	spinClasse.setAdapter(adapClasse);		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
//        getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	public void refill_nameList(){
		for (int i = 0; i < array_names.length; i++) {
			nameList.add(array_names[i]);
		}
	}
	
	public void selectPupil(View view) {
		int nbPupils;
		// if nameList is empty, then refill it !
		if (nameList.isEmpty()) {refill_nameList();}	
		nbPupils = nameList.size();
		// get a random index in the list
		int nPupil = new Random().nextInt(nbPupils);
		// and get a name at this index in the list
		String randomName = (String) nameList.get(nPupil);
		tVpupilName.setText(randomName);
		nameList.remove(nPupil);		
		// set the correct text
		tVdebugInfo.setText("Élèves restants = "+nameList.size()+" - élèves interrogés = "+(array_names.length-nameList.size()));
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
		// parent.getItemAtPosition(pos);
		if (pos==0){
			array_names = this.getResources().getStringArray(R.array.liste_eleves_CE1);
			tVdebugInfo.setText("-> CE1");
		} else {
			array_names = this.getResources().getStringArray(R.array.liste_eleves_CM1);			
			tVdebugInfo.setText("-> CM1");
		}
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
	
}
