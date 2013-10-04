package gestion.de.classe;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MyOnItemSelectedListener implements OnItemSelectedListener {

	@Override
	public void onItemSelected(AdapterView parent, View view, int pos, long id) {		
		Toast.makeText(parent.getContext(), "Classe sélectionnée : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
		//MainActivity.fill_array_names(pos);
	}

	@Override
	public void onNothingSelected(AdapterView parent) {

	}
}