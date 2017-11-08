package com.firstapp.joel.myquizz.java;

// This class contains a list of questions

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    // declare list of Question objects
    List <Question> list = new ArrayList<>();
    DataBaseHelper DataBaseHelper;

    // method returns number of questions in list
    public int getLength(){
        return list.size();
    }

    // method returns question from list based on list index
    public String getQuestion(int a) {
        return list.get(a).getQuestion();
    }

    // method return a single multiple choice item for question based on list index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4
    // as an argument
    public String getChoice(int index, int num) {
        return list.get(index).getChoice(num-1);
    }

    //  method returns correct answer for the question based on list index
    public String getCorrectAnswer(int a) {
        return list.get(a).getAnswer();
    }



    public void initQuestions(Context context) {
        DataBaseHelper = new DataBaseHelper(context);
        list = DataBaseHelper.getAllQuestionsList();//get questions/choices/answers from database

        if (list.isEmpty()) {//if list is empty, populate database with default questions/choices/answers
            DataBaseHelper.addInitialQuestion(new Question("Which of these packages contain all the collection classes?",
                    new String[]{"java.lang", "java.util", "java.net", "java.awt"}, "java.util"));
            DataBaseHelper.addInitialQuestion(new Question("Which of these classes is not part of Java’s collection framework?",
                    new String[]{"List", "Set", "SortedMap", "SortedList"}, "SortedList"));
            DataBaseHelper.addInitialQuestion(new Question("Which of these methods deletes all the elements from invoking collection?",
                    new String[]{"Clear()", "Remove()", "Delete()", "DeleteAll()"}, "Clear()"));
            DataBaseHelper.addInitialQuestion(new Question("Which of these interface handle sequences?",
                    new String[]{"Set", "List", "Comparator", "Collection"}, "List"));

            list = DataBaseHelper.getAllQuestionsList();//get list from database again

        }
    }

}
