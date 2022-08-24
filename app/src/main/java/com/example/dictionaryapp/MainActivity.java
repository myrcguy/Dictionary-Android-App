package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button AddWord;
    Button RemoveWord;
    Button Find;
    String Word;
    String wordDefinition;

    EditText typeWord;
    TextView outputDefinitionText;
    TextView FrequentOutput1;
    TextView FrequentOutput2;
    TextView FrequentOutput3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrequentOutput1 = (TextView) findViewById(R.id.FrequentOutput1);
        FrequentOutput2 = (TextView) findViewById(R.id.FrequentOutput2);
        FrequentOutput3 = (TextView) findViewById(R.id.FrequentOutput3);
        AddWord = (Button) findViewById(R.id.ButtonNext);
        RemoveWord = (Button) findViewById(R.id.ButtonRemove);
        Find = (Button) findViewById(R.id.ButtonFind);
        typeWord = (EditText) findViewById(R.id.TypeWord);

        outputDefinitionText = (TextView) findViewById(R.id.outputDefinition);
        AddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Transition to other screen
                Intent intent = new Intent(MainActivity.this, NewWordPage.class);
                startActivity(intent);
            }
        });

        RemoveWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete word from dictionary
                Word = typeWord.getText().toString();
                DictionaryMain deleteWordObject = new DictionaryMain();
                deleteWordObject.deleteWord(Word);
            }
        });
        Find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Word = typeWord.getText().toString();
                //Creating empty object to access methods
                DictionaryMain findWordReturned = new DictionaryMain();
               //Pass Word to function that user wants to search
                findWordReturned.findWord(Word);
                //Clear the entries before displaying
                outputDefinitionText.setText(" ");
                FrequentOutput1.setText(" ");
                FrequentOutput2.setText(" ");
                FrequentOutput3.setText(" ");
                //Call Sort list to sort the words similar in order of decending frequency
                findWordReturned.sortFoundWordList();
                int sizeOfArray = findWordReturned.returnSizeofFoundArray();
                if(findWordReturned.findWordFromList().returnEmptyObjectFlag() == 0) {
                    if(sizeOfArray == 1) {
                        //If 1 word Matches
                        //Printing words from words added to foundWordList array
                        outputDefinitionText.setText((findWordReturned.getObjectAtIndex(0).returnDefinition()));
                        FrequentOutput1.setText(findWordReturned.getObjectAtIndex(0).returnWord());
                        FrequentOutput2.setText(" ");
                        FrequentOutput3.setText(" ");
                    }
                    if(sizeOfArray == 2){
                        //If 2 words match
                        outputDefinitionText.setText((findWordReturned.getObjectAtIndex(0).returnDefinition()));
                        FrequentOutput1.setText(findWordReturned.getObjectAtIndex(0).returnWord());
                        FrequentOutput2.setText(findWordReturned.getObjectAtIndex(1).returnWord());
                        FrequentOutput3.setText(" ");
                    }
                    if(sizeOfArray >= 3){
                        //If 3 or words match
                        outputDefinitionText.setText((findWordReturned.getObjectAtIndex(0).returnDefinition()));
                        FrequentOutput1.setText(findWordReturned.getObjectAtIndex(0).returnWord());
                        FrequentOutput2.setText(findWordReturned.getObjectAtIndex(1).returnWord());
                        FrequentOutput3.setText(findWordReturned.getObjectAtIndex(2).returnWord());
                    }
                }
                else{
                    outputDefinitionText.setText(" ");
                }
            }
        });






    }
}
