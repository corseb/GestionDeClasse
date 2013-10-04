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
	String[] array_classes;
	String[] array_CE1;
	String[] array_CM1;
	String[] array_names;
	// array_names contains the pupil names that are present and have not been asked a question yet (="remaining pupils" list)
	ArrayList<String> presentNameList;
	// at the beginning (before attendance check and interrogation) array_current_names is array_names
	ArrayList<String> nameList;
	// tVpupilName is the TextView in which the pupil name is displayed
	TextView tVpupilName;
	TextView tVdebugInfo;
	Spinner spinClasse;
   	// Create an ArrayAdapter using the string array and a default spinner layout
	ArrayAdapter<CharSequence> adapClasse;
	int nbPupils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init_Vars(0);
		fill_spinner();
		addListenerOnSpinnerItemSelection();
	}

	private void init_Vars(int index_Classe){
		array_classes= this.getResources().getStringArray(R.array.liste_classes);
		array_CE1= this.getResources().getStringArray(R.array.liste_eleves_CE1);
		array_CM1= this.getResources().getStringArray(R.array.liste_eleves_CM1);
		tVpupilName = (TextView) findViewById(R.id.tV_pupil_name);
		tVdebugInfo = (TextView) findViewById(R.id.tV_debug_info);
		fill_array_names(index_Classe);
		nameList = new ArrayList<String>(Arrays.asList(array_names));
		spinClasse = (Spinner) findViewById(R.id.spinnerClasse);
		adapClasse = ArrayAdapter.createFromResource(this, 
				R.array.liste_classes, android.R.layout.simple_spinner_item);
	}
	
	public void fill_array_names(int index_classe){
		// fill array_names with the corresponding pupil name list (0-> CE1, 1 -> CM1)
		if (index_classe==0) {
			array_names = array_CE1;}
		else {
			array_names = array_CM1;
		}
	}

	public void addListenerOnSpinnerItemSelection() {
		spinClasse.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView parent, View view, int pos, long id) {
				Toast.makeText(parent.getContext(), "Classe sélectionnée : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
				empty_nameList();
				fill_array_names(pos);
				tVpupilName.setText(array_classes[pos]);
			
			}

			@Override
			public void onNothingSelected(AdapterView parent) {
			}});
	  }
	
	public void refill_nameList(){
		for (int i = 0; i < array_names.length; i++) {
			nameList.add(array_names[i]);
		}
	}
		
	public void empty_nameList(){
//		StringBuilder debugTxt = new StringBuilder();
//		debugTxt.append("EmptyList - avant : ");
//		debugTxt.append(nameList.size());
//		debugTxt.append(" après : ");
		for (; nameList.size()>0;) {
			nameList.remove(0);
		}
//		debugTxt.append(nameList.size());
//		tVdebugInfo.setText(debugTxt);
	}
		
	public void fill_spinner(){
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
	
	public void selectPupil(View view) {
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
	
}
