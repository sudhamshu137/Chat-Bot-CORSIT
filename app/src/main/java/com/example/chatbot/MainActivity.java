package com.example.chatbot;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.icu.text.CaseMap;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText msgEt;
    String msg;
    FloatingActionButton floating;
    private static final int RECOGNIZER_RESULT = 1;
    TextView textsent, title, paragraph;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floating = findViewById(R.id.floating);
        textsent = findViewById(R.id.textsent);

        image = findViewById(R.id.image);
        image.requestLayout();

        title = findViewById(R.id.title);
        paragraph = findViewById(R.id.paragraph);

        msgEt = findViewById(R.id.msg);
        msgEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!(msgEt.getText().toString().trim().isEmpty())){
                    floating.setImageResource(R.drawable.ic_baseline_send);
                }
                else{
                    floating.setImageResource(R.drawable.ic_baseline_mic);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void sendbtn(View view) {
        hideSoftKeyboard(this);
        title.setText("");
        paragraph.setText("");
        if(msgEt.getText().toString().trim().isEmpty()){
            Intent speachIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            speachIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            speachIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"speech to text");
            startActivityForResult(speachIntent,RECOGNIZER_RESULT);
        }
        else{
            msg = msgEt.getText().toString().trim();
            msgEt.getText().clear();
            textsent.setText(msg);
            textsent.setBackgroundResource(R.drawable.etbg);
            msgEt.clearFocus();
            chatbotfunction();
        }
    }

    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == RECOGNIZER_RESULT && resultCode == RESULT_OK){
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            msg = matches.get(0);
            textsent.setText(msg);
            textsent.setBackgroundResource(R.drawable.etbg);
            chatbotfunction();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        title.setText("Hello 😃 How can I help you?");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        msgEt.clearFocus();
        msgEt.clearComposingText();
        hideSoftKeyboard(this);
    }







    public void chatbotfunction(){
        msg = msg.toLowerCase();


        if(msg.equals("hii") || msg.equals("hello") || msg.equals("hey") || msg.equals("heyy") || msg.equals("hiii") || msg.equals("hi")){
            paragraph.setText("hey! How can I help you");
        }
        else if(msg.contains("corsit website") || msg.contains("your website") || msg.contains("web")){
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://www.corsit.in"));
            startActivity(i);
        }
        else if(msg.contains("android dev") || msg.contains("app dev") || msg.contains("app lead") || msg.contains("app development lead") ||
                    msg.contains("android developer lead") || msg.contains("shikhar") ){
            title.setText("Shikhar Prakash");
            paragraph.setText("Android developer lead\nfinal year EIE");
        }
        else if(msg.contains("web dev") || msg.contains("web developer") || msg.contains("web lead") || msg.contains("web development lead") ||
                msg.contains("website developer lead") || msg.contains("asra") || msg.contains("website dev") || msg.contains("website developer")){
            title.setText("Asra");
            paragraph.setText("Web developer lead\nfinal year ECE");
        }
        else if(msg.contains("about corsit") || msg.contains("about club") || msg.contains("about") || msg.contains("aim") ||
                    msg.contains("what is corsit") || msg.contains("what is tobotics club") || msg.contains("dream")){
            title.setText("CLUB OF ROBOTICS");
                String ss = "CORSIT is Robotic club of Siddaganga Institute of Technology, which has it's own lab and strives continuously " +
                        "for the technical development o students\n" +
                        "It comes under the tenure of IIT Bombay\n" +
                        "website:  www.corsit.in\n" +
                        "android app:  play.google.com/store/apps/details?id=com.corsit.robocore";
            paragraph.setText(ss);
        }
        else if(msg.contains("events of corsit") || msg.contains("events of club") || msg.contains("programmes") || msg.contains("programs") ||
                    msg.contains("events") || msg.contains("all events")){
            title.setText("Events of CORSIT");
            String ss = "4 Main events conducted by CORSIT:\n" +
                    "1. Internet o Things(IoT) Workshop" +
                    "2. Recruitments" +
                    "3. Robocor workshop" +
                    "4. Robocor";
            paragraph.setText(ss);
        }
        else if(msg.contains("iot") || msg.contains("internet of things") || msg.contains("automation") || msg.contains("iot event")){
            title.setText("IoT Workshop");
            String ss = "Internet of Things workshop:\n" +
                    "An Event organised by CORSIT to skill up the students in the filed of automations and technology \n" +
                    "Duration: 2 days\n" +
                    "Date: will be notified";
            paragraph.setText(ss);
        }
        else if(msg.contains("recruit") || msg.contains("recruitment") || msg.contains("recruitments") || msg.contains("selection") || msg.contains("interview") ||
                    msg.contains("intake") || msg.contains("part of team") || msg.contains("part of the team") || msg.contains("recruiting")){
            title.setText("Event Recruitment");
            String ss = "An event organized by CORSIT to recruit the students to the team based on the skills and talents hidden!\n" +
                    "2 rounds: Writing test and Personal Interview\n" +
                    "Finalists will be announced on official CORSIT instagram page\n" +
                    "check out: instagram.com/corsit.sit";
            paragraph.setText(ss);
        }
        else if(msg.contains("robot workshop") || msg.contains("robotic") || msg.contains("robo workshop") || msg.contains("corsit workshop") ||
                msg.contains("practice") || msg.contains("rob workshop") || msg.contains("getting hands on") || msg.contains("robocore workshop") ||
                msg.contains("workshop event") || msg.contains("workshop") || msg.contains("arduino") || msg.contains("aurdino") || msg.contains("sensor") ||
                msg.contains("sensors")){
            title.setText("Robocore workshop Event:");
            String ss = "Be ready to emerge victorious!! \nGet your hands on Arduino and sensors to prepare for Robocor- The most awaited event!" +
                    "\nGet ready for the Robocor by this free workshop event \nDates will be announced soon";
            paragraph.setText(ss);
        }
        else if(msg.contains("robocor") || msg.contains("robocore") || msg.contains("big event") || msg.contains("biggest") || msg.contains("significant") ||
                msg.contains("awaited") || msg.contains("important") || msg.contains("compete") || msg.contains("competitions") || msg.contains("arduino clash") ||
                msg.contains("race") || msg.contains("rugged rage") || msg.contains("project") || msg.contains("symposium") || msg.contains("decode") ||
                msg.contains("dcode") || msg.contains("d-code") || msg.contains("decoders") || msg.contains("dcoders") || msg.contains("cross roads") ||
                msg.contains("crossroads") || msg.contains("bots") || msg.contains("robotic event") || msg.contains("robots") || msg.contains("bluetooth cars")||
                msg.contains("wireless") || msg.contains("rc") || msg.contains("remote control") || msg.contains("remote control cars") ||
                msg.contains("robosoccer")|| msg.contains("soccer") || msg.contains("foot ball") || msg.contains("ball") || msg.contains("robot football") ||
                msg.contains("robot soccer") || msg.contains("paper presentation") || msg.contains("presentation") || msg.contains("lfr") ||
                msg.contains("line follower") || msg.contains("line following robot") || msg.contains("situation")){

            title.setText("ROBOCOR 🤖");
            String ss = "The most significant event of the year: Robocor \nIt is the biggest national level robotic championship held by CORSIT" +
                    "\nDates will be notiied\nGive your best on the bot and emerge victorious. This event contains 8 competitions mentioned as follows:\n" +
                    "1. Arduino Clash         2. D-Code\n" +
                    "3. Situation 2.0         4. Cross-Roads\n" +
                    "5. Rugged rage           6. Robosoccer\n" +
                    "7. Paper Presentation    8. Project Symposium\n" +
                    "For more results, visit our website:  https://www.corsit.in";
            paragraph.setText(ss);

        }
        else if(msg.contains("where") || msg.contains("lab") || msg.contains("laboratory") || msg.contains("corsit lab")){
            paragraph.setText("CORSIT lab is in the new Electrical building of the campus");
        }
        else if(msg.contains("anushanth") || msg.contains("anushant") || msg.contains("cashier") || msg.contains("treasurer")){
            title.setText("Anushanth Bhushan");
            paragraph.setText("Treasurer\nfinal year EIE");
        }
        else if(msg.contains("sudhamshu")||msg.contains("harshitha")||msg.contains("sudhanshu")||msg.contains("sristi")||msg.contains("srishti")||
                msg.contains("aman")||msg.contains("gupta")||msg.contains("amman")||msg.contains("aditi")||msg.contains("adithi")||
                msg.contains("aditya")||msg.contains("yashwanth")||msg.contains("rishikesh")||msg.contains("hrishikesh")||msg.contains("kshitiz")||
                msg.contains("vats")||msg.contains("chitij")||msg.contains("jyothi")||msg.contains("vishal")||msg.contains("vineela")||
                msg.contains("anubhav")||msg.contains("keshav")||msg.contains("mishra")||msg.contains("abhinav")||msg.contains("pankaj")||
                msg.contains("rohit")||msg.contains("harsh")||msg.contains("yashas")||msg.contains("amit")||msg.contains("anshuman")||
                msg.contains("anand")||msg.contains("ansa")||msg.contains("anurag")||msg.contains("anuraag")||msg.contains("aquib")||
                msg.contains("bhakthi")||msg.contains("bhakti")||msg.contains("bhavani")||msg.contains("chaya")||msg.contains("chaaya")||
                msg.contains("chethan")||msg.contains("chetan")||msg.contains("hrijusha")||msg.contains("kaisar")||msg.contains("krishn")||
                msg.contains("raghav")||msg.contains("rohan")||msg.contains("sandeep")||msg.contains("shagun")||msg.contains("shashwath")||
                msg.contains("shashwat")||msg.contains("shashvath")||msg.contains("shivneeth")||msg.contains("shruthi")||msg.contains("bharadwaj")||
                msg.contains("shwetha")||msg.contains("sonakshi")||msg.contains("tanmay")||msg.contains("megharaj")||msg.contains("meghraj")||
                msg.contains("natesh")){

            paragraph.setText("For the details of team members, check out our website: https://www.corsit.in");

        }



        else if(msg.contains("manojna") || msg.contains("manojana") || msg.contains("vice captain") || msg.contains("vice chairman") ||
                    msg.contains("vice chair person")){
            title.setText("Manojna Rao");
            paragraph.setText("Vice chair person\nFinal year EEE");
        }

        else if(msg.contains("who is your captain") || msg.contains("who is your boss") || msg.contains("chairman of corsit") ||
                    msg.contains("captain of corsit") || msg.contains("leader of corsit") || msg.contains("head") || msg.contains("team lead") ||
                    msg.contains("shaksham") || msg.contains("chairman") || msg.contains("captain") || msg.contains("chair parson")){
            title.setText("Shaksham Sinha");
            paragraph.setText("Chairman\nfinal year TC");
        }
        else{
            title.setText("Redirecting your question to the team");
            paragraph.setText("You will be notified soon!");
        }

    }


}