package com.example.speed_typing;

import android.os.Bundle;

public class FragmentActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Définissez votre vue, rien de plus. Tout sera pris en charge par le fragment qui affiche les données
		setContentView(R.layout.activity_fragment);
		//Retrouver votre fragment en utilisant son identifiant (si besoin)
		//FragmentDetails fragmentDetails = findViewById(R.id.fragmentDetails);
	}



}
