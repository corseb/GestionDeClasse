package gestion.de.classe;

import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gestion.de.classe.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	// array_names contains the pupil names
	String[] array_names;
	// array_names contains the pupil names that are present and have not been asked a question yet (="remaining pupils" list)
	ArrayList<String> nameList;
	// tVpupilName is the TextView in which the pupil name is displayed
	TextView tVpupilName;
	TextView tVdebugInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
    	// fill array_names with the default pupil name list
    	array_names = this.getResources().getStringArray(R.array.liste_eleves_CM1);
    	// at the beginning (before attendance check and interrogation) array_current_names is array_names
		nameList = new ArrayList<String>(Arrays.asList(array_names));
    	// get the right TextView
    	tVpupilName = (TextView) findViewById(R.id.tV_pupil_name);
    	tVdebugInfo = (TextView) findViewById(R.id.tV_debug_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void selectPupil(View view) {
		int nbPupils;
		if (nameList.isEmpty()) {
			// if nameList is empty, then refill it !
			for (int i = 0; i < array_names.length; i++) {
				nameList.add(array_names[i]);
			}
		} 
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
