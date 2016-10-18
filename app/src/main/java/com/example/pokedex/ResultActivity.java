package com.example.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {

    PokemonRepository pokemonRepository;
    TextView usersPokemonResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        //Getting text and image from the MainActivity
        String usersPokemonGuess = intent.getStringExtra(MainActivity.EXTRA_GUESS_TEXT).trim();
        int pokemonResourceId = intent.getIntExtra(MainActivity.EXTRA_IMAGE_RESOURCE_ID, R.drawable.pidgey);

        //Finding the relevant views in the result screen
        usersPokemonResultTextView = (TextView) findViewById(R.id.your_pokemon);
        ImageView revealedPokemonImageView = (ImageView) findViewById(R.id.result_pokemon);

        //Setting the image views
        revealedPokemonImageView.setImageResource(pokemonResourceId);

        pokemonRepository = new PokemonRepository(this);
        HashMap<String, Integer> pokeMap = pokemonRepository.getPokeMap();
        boolean isGuessCorrect = false;

        Integer resource = pokeMap.get(usersPokemonGuess.toLowerCase());

        for (int i = 0; i < pokeMap.size(); i++) {
            if (resource != null && resource == pokemonResourceId) {
                isGuessCorrect = true;
                break;
            }
        }
        displayGuessResults(isGuessCorrect, usersPokemonGuess);
    }

    private void displayGuessResults(boolean isGuessCorrect, String usersPokemonGuess) {
        //Retrieving the "It's..." resources and putting it together with the guess
        String its = getResources().getString(R.string.its_pokemon);
        String itsPokemon = String.format(its, usersPokemonGuess);

        String noIts = getResources().getString(R.string.negative_result);
        String notPokemon = String.format(noIts, usersPokemonGuess);

        //Finding ImageView and setting text a tick/cross to corresponding answer
        ImageView resultBox = (ImageView) findViewById(R.id.tick_cross);

        if (isGuessCorrect) {
            usersPokemonResultTextView.setText(itsPokemon + " !");
            resultBox.setImageResource(R.drawable.tick);

        } else {
            usersPokemonResultTextView.setText(notPokemon + "." + "\nDo you even Pokémon?");
            resultBox.setImageResource(R.drawable.cross);
        }
    }
}
