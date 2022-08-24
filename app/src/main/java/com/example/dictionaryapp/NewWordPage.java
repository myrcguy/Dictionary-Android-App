package com.example.dictionaryapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class NewWordPage extends AppCompatActivity {

    String wordToAdd;
    String definitionToAdd;
    int frequencyToAdd;

    Button homePage;
    Button addWord;
    Button clearWord;

    EditText typeWord;
    EditText typeFrequency;
    EditText typeDefinition;
    //Three Text Entry locations for the new word page layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word_page);

        homePage = (Button) findViewById(R.id.ButtonGoHome);
        addWord = (Button) findViewById(R.id.ButtonAdd);
        clearWord = (Button) findViewById(R.id.ButtonClear);

        typeWord = (EditText) findViewById(R.id.NewWordInput);
        typeFrequency = (EditText) findViewById(R.id.FrequencyInput);
        typeDefinition = (EditText) findViewById(R.id.MeaningInput);

        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Transition bck to home page
                Intent intent = new Intent(NewWordPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        clearWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set text entries to empty
                typeWord.setText("");
                typeDefinition.setText("");
                typeFrequency.setText("");
            }
        });
        addWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve data from text entries
                wordToAdd = typeWord.getText().toString();
                definitionToAdd = typeDefinition.getText().toString();
                //If frequency is not inputed, set its value to 1
                if(typeFrequency.getText().toString().matches("")){
                    frequencyToAdd = Integer.valueOf(1);
                }
                else{
                    frequencyToAdd = Integer.valueOf(typeFrequency.getText().toString());
                }
                //Create the new object(word) and set its specific values
                DictionaryMain newWord = new DictionaryMain(wordToAdd,definitionToAdd,frequencyToAdd);
                //Add to dictionary Array
                newWord.addWordToArray(newWord);
                newWord.printArrayList();
            }
        });
    }
}