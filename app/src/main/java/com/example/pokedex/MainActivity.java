package com.example.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.pokedex.Network.model.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_GUESS_TEXT = "MESSAGE";
    public final static String EXTRA_POKEMON_NAME = "POKEMON_NAME";
    public final static String EXTRA_POKEMON_IMAGE_URL = "POKEMON_IMAGE_URL";


    private PokemonRepository pokemonRepository;
    private EditText userPokemonGuessEditText;
    private Pokemon pokemon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokemonRepository = new PokemonRepository(this);
        //Find Views
        userPokemonGuessEditText = (EditText) findViewById(R.id.edit_result);

        //Calling method when button clicks
        createSubmitButton();
    }

    private void createSubmitButton() {
        Button button = (Button) findViewById(R.id.send_button);
        //onClickListener tells app to look out for view being clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usersPokemonGuess = userPokemonGuessEditText.getText().toString();
                //Calls method submitPokemon with the user's text and ID of the current image
                submitPokemon(usersPokemonGuess, pokemon.getPokemonName(), pokemon.getIconUrl());
            }
        });
    }

    // Sends user's text and ID of the current image
    public void submitPokemon(String usersPokemonGuess, String pokemonName, String pokemonImageUrl) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(EXTRA_GUESS_TEXT, usersPokemonGuess);
        intent.putExtra(EXTRA_POKEMON_NAME, pokemonName);
        intent.putExtra(EXTRA_POKEMON_IMAGE_URL, pokemonImageUrl);
        //intent.putExtra(EXTRA_IMAGE_RESOURCE_URL, randomImageUrl);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        pokemon = pokemonRepository.getRandomPokemon();

        ImageView mysteryPokemonImageView = (ImageView) findViewById(R.id.view_pokemon);
        Picasso.with(this).load(pokemon.getIconUrl()).fit().centerInside().into(mysteryPokemonImageView);
        userPokemonGuessEditText.setText("");
    }

}
