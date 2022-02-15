package ru.tikodvlp.madridistaapp.quiz;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.tikodvlp.madridistaapp.R;
import ru.tikodvlp.madridistaapp.quiz.QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "RealMadridQuiz";
    private static final int DATABASE_VERSION = 2;

    private SQLiteDatabase db;

     public QuizDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NUM + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTION_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
            onCreate(db);
    }

    private void fillQuestionsTable() {
         Question q1 = new Question("When Real Madrid was founded?", "1901", "1902",
                 "1900", "1905", 2);
         addQuestion(q1);
        Question q2 = new Question("When did King Alfonso XII add the word 'real' in the front of club name?",
                "1920", "1910", "1917", "1919", 1);
        addQuestion(q2);
        Question q3 = new Question("What does 'real' mean?", "Royal", "King's institution", "White",
                "Galacticos", 1);
        addQuestion(q3);
        Question q4 = new Question("The name of Real Madrid stadium is...", "Madrid Arena",
                "Paco Gento stadium", "Santiago Bernabeu",
                "Alfredo Di Stefano", 3);
        addQuestion(q4);
        Question q5 = new Question("Real Madrid is among the three teams to never been in Second division, " +
                "Which are the other two teams", "Barcelona, Sevilla", "Barcelona, Atletico Madrid",
                "Athletic Bilbao, Atletico Madrid", "Barcelona, Athletic Bilbao", 4);
        addQuestion(q5);
        Question q6 = new Question("The capacity of the Santiago Bernabeu stadium is...",
                "81 044", "77 987", "79 503", "80 453", 1);
        addQuestion(q6);
        Question q7 = new Question("How many National Championships Real Madrid has won?", "33",
                "31", "32", "34", 4);
        addQuestion(q7);
        Question q8 = new Question("How many Champions League Real Madrid has won?",
                "10", "11", "12", "13", 4);
        addQuestion(q8);
        Question q9 = new Question("During of all domestic games in 7th minute madridistas pay " +
                "a special tribute to a legendary player, his name is...", "Raul",
                "Juanito", "Cristiano Ronaldo", "Alfredo Di Stefano", 2);
        addQuestion(q9);
        Question q10 = new Question("Real Madrid president name is...?", "Alphonso Lopez",
                "Carlo Ancelotti", "Joan Laporta", "Florentino Perez", 4);
        addQuestion(q10);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NUM, question.getAnswerNum());

        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }
    @SuppressLint("Range")
    public ArrayList<Question> getAllQuestions() {
         ArrayList<Question> questionList = new ArrayList<>();
         db = getReadableDatabase();
         Cursor cursor = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
         if (cursor.moveToFirst()) {
             do {
                 Question question = new Question();
                 question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                 question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                 question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                 question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                 question.setOption4(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                 question.setAnswerNum(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUM)));
                 questionList.add(question);
             } while (cursor.moveToNext());
         }
         cursor.close();
         return questionList;
    }
}
