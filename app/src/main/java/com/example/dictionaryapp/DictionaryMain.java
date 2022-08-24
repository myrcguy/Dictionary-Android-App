package com.example.dictionaryapp;

import android.widget.TextView;
import java.text.DecimalFormat;
import java.lang.Math;
import java.util.ArrayList;

public class DictionaryMain {

    private String Word;
    private String Definition;
    private int Frequency;
    private int emptyObjectFlag; //Flag set to 1, if it is an emptyObject
    //wordList holds the objects as a "Dictionary"
    private static ArrayList<DictionaryMain> wordList = new ArrayList<DictionaryMain>(1);
    //foundWordList holds the data of words matching the word to be searched
    private static ArrayList<DictionaryMain> foundWordList = new ArrayList<DictionaryMain>(1);


    public DictionaryMain(String typedWord, String typedDefinition, int typedFrequency){
        //When Adding new word, call Dictionary Constructor
        //And create the word as an object
        //Then add object to Dynamic Array
        Word = typedWord;
        Definition = typedDefinition;
        Frequency = typedFrequency;
        this.emptyObjectFlag = 0;
    }

    public DictionaryMain(){
        //Empty Constructor for objects not to be added to dictionary
    }

    //Constructor specifically to specify it is an empty object
    public DictionaryMain(String Text){
        this.emptyObjectFlag = 1;
    }

    public String returnWord(DictionaryMain Object){
        return(Object.Word);

    }
    public String returnWord(){
        return(this.Word);
    }

    public int returnEmptyObjectFlag(){
        return(this.emptyObjectFlag);
    }

    public String returnDefinition(){
        return(this.Definition);
    }

    public int returnFrequency(){
        return(this.Frequency);
    }

    //Overwrite toString for debugging
    public String toString(){//overriding the toString() method
        return (Word + " " + Definition + " " + Frequency);
    }
    //Printing the "Dictionary" arraylist for debugging
    public void printArrayList() {
        DictionaryMain testObject;
        for (int i = 0; i < wordList.size(); i++)
            System.out.println(wordList.get(i));
    }

    public void addWordToArray(DictionaryMain Object) {
        wordList.add(Object);
        System.out.println(wordList.size());
    }

    public void findWord(String Word){
        foundWordList.clear();
        for(int i = 0; i < wordList.size(); i++){
            if(Word.length() > returnWord(wordList.get(i)).length()){ //If Word pass > word checked will error
                continue;
            }
            //use .substring to slice the dictionary word to compare to searched word
            String slicedString = returnWord(wordList.get(i)).substring(0,Word.length());
            if(Word.equals(slicedString)){
                foundWordList.add(0,wordList.get(i));
            }
        }
    }
    public void deleteWord(String Word){
        foundWordList.clear();
        for(int i = 0; i < wordList.size(); i++){
            if(Word.equals(wordList.get(i).returnWord())){
                wordList.remove(i);
                }
            }
    }

    public DictionaryMain findWordFromList(){
        System.out.println(foundWordList);
        if(foundWordList.size() > 0) {
            return (foundWordList.get(0));
        }
        else{
            DictionaryMain emptyObject = new DictionaryMain("Empty");
            return(emptyObject);
        }
    }
    //Sort the matched words in order of decending frequency to display most common words
    public void sortFoundWordList(){
        for(int i = 0; i < foundWordList.size(); i++){
            for(int j = i + 1; j < (foundWordList.size() - i); j++){
                if(foundWordList.get(j-1).returnFrequency() < foundWordList.get(j).returnFrequency()){
                    DictionaryMain tempObject = foundWordList.get(j-1);
                    foundWordList.set(j-1,foundWordList.get(j));
                    foundWordList.set(j,tempObject);
                }
            }
        }
    }
    public DictionaryMain getObjectAtIndex(int index){
        return(foundWordList.get(index));
    }
    public int returnSizeofFoundArray(){
        return(foundWordList.size());
    }
}
