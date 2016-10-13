package com.example.pokedex;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_GUESS_TEXT = "MESSAGE";
    public final static String EXTRA_IMAGE_RESOURCE_ID = "POKEMON_IMAGE";

    Random rand = new Random();
    int lastrandomImageId;
    int randomImageId;
    int arraySize;
    EditText editText;
    ImageView imageView;
    TypedArray pokemonImages;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.view_pokemon);
        editText = (EditText) findViewById(R.id.edit_result);

        //Accessing a random image from Pokemon image array and setting as intial image when app starts up
        pokemonImages = getResources().obtainTypedArray(R.array.pokemon_imgs);
        arraySize = pokemonImages.length();

        buttonClick();
    }

    private void buttonClick() {
        Button button = (Button) findViewById(R.id.send_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pokemonGuess = editText.getText().toString();
                sendPokemon(pokemonGuess, randomImageId);
            }
        });
    }

    // sends
    public void sendPokemon(String pokemonGuess, int pokemonResourceId) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(EXTRA_GUESS_TEXT, pokemonGuess);
        intent.putExtra(EXTRA_IMAGE_RESOURCE_ID, pokemonResourceId);
        startActivity(intent);
    }


    private int getRandomResourceId() {
        return pokemonImages.getResourceId(rand.nextInt(arraySize), R.drawable.pidgey);
    }

    @Override
    protected void onResume() {
        super.onResume();

        lastrandomImageId = randomImageId;
        randomImageId = getRandomResourceId();

        while (lastrandomImageId == randomImageId) {
            Log.e(getClass().getName(), "Oh noes, they are the same!!");
            randomImageId = getRandomResourceId();
        }

        imageView.setImageResource(randomImageId);
        editText.setText("");
    }

    @Override
    protected void onDestroy() {
        pokemonImages.recycle();
        super.onDestroy();
    }
}
