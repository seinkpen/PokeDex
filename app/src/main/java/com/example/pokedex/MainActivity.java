package com.example.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_RESULT = "com.example.pokedex.MESSAGE";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // sends
    public void sendPokemon(View view){
        Intent intent = new Intent(this,ResultActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_result);
        String pokemonGuess = editText.getText().toString();
        intent.putExtra(EXTRA_RESULT, pokemonGuess);
        startActivity(intent);
    }
}
