/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    public void showusersList(){
        Intent intent = new Intent(getApplicationContext(), AfterLogin.class);

        startActivity(intent);


    }


    public void logIn(View view){

        final EditText password = (EditText) findViewById(R.id.editTextPass);
        final EditText username =(EditText) findViewById(R.id.editTextUser);

        ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user!=null){
                    showmessage("Log In successful");
                   showusersList();

                }

                else {

                    showmessage("Log In Not successful due to  "+e.toString());
                }
            }
        });





    }

    public void showmessage(String s){

        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
    public void signUp (View view){
         final EditText editText = (EditText) findViewById(R.id.editTextPass);
         final EditText editText1 =(EditText) findViewById(R.id.editTextUser);

        if(editText.getText().toString().matches("") || editText1.getText().toString().matches("")){
            Toast.makeText(this,"Cannot be left blank to sign up",Toast.LENGTH_SHORT).show();

        }

        else {
            final ParseUser user = new ParseUser();



user.setUsername(editText1.getText().toString());
                            user.setPassword(editText.getText().toString());


                            user.signUpInBackground(new SignUpCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if(e==null){

                                 showmessage("User Signed Up successfully");
                                  showusersList();


                                    }

                                    else{

                                  showmessage("User couldnt be signed up due to error  "+ e.toString());
                                    }
                                }

                            });




                }



        }




  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);














      ;
      /*
ParseUser.logOut();



      if(ParseUser.getCurrentUser()!=null){

          Log.i("currentUser"," User Logged in  "+ParseUser.getCurrentUser().getUsername());

      }

      else{
          Log.i("currentUser","User not logged in");


      }
*/
      /*
      ParseUser.logInInBackground("robpercival", "asdf", new LogInCallback() {
          @Override
          public void done(ParseUser user, ParseException e) {
              if(user!=null){
                  Log.i("Login","Successfull");
              }
              else {
                  Log.i("Login","Failed:  "+e.toString());
              }
          }
      });

      */

/*
      ParseUser user = new ParseUser();

      user.setUsername("bqureshi341");

      user.setPassword("chelseafc");

      user.signUpInBackground(new SignUpCallback() {
          @Override
          public void done(ParseException e) {
              if (e==null){

                  Log.i("Sign Up","Successfull");

              }

              else{

                  Log.i("Sign Up", "Failed");

              }
          }
      });


      */





      /*
ParseObject score = new ParseObject("Score");

    score.put("username", "rob");

    score.put("score", 86);
    score.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {

        if(e==null){

          Log.i("SaveInBackground","Successful");





        }

        else{
          Log.i("SaveInBackground","Failed. Error: "+e.toString());


        }
      }
    });
*/
/*
      ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");


      query.getInBackground("CMwDfAQGuW", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject object, ParseException e) {
              if(e==null && object!=null){

                  object.put("score",200);

                  object.saveInBackground();

                  Log.i("ObjectValue", object.getString("username"));
                  Log.i("ObjectValue",Integer.toString(object.getInt("score")));




              }
          }
      });
*/
/*
ParseObject tweet= new ParseObject("tweete");

      tweet.put("Username","Bilal_Qureshi123");

      tweet.put("Tweets"," Hello I am setting up my twitter account");

      tweet.saveInBackground(new SaveCallback() {
          @Override
          public void done(ParseException e) {
              if(e==null){

                  Log.i("SaveInbackground","Successful");
              }

              else{
                  Log.i("SaveInbackground","Failed  "+ e.toString());
              }
          }
      });
    */

/*

ParseQuery<ParseObject> query= ParseQuery.getQuery("Score");

query.whereGreaterThan("score",200);





      query.findInBackground(new FindCallback<ParseObject>() {
          @Override
          public void done(List<ParseObject> objects, ParseException e) {

              if(e==null){

                  Log.i("findInBackground","Retrieved  "+objects.size()+"objects");
                  if(objects.size()>0){
                      for(ParseObject object: objects){
                      object.put("score",object.getInt("score")+50);
                          object.saveInBackground();
                          Log.i("findInBackground",Integer.toString(object.getInt("score")));


                      }

                  }
              }

          }
      });*/



    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==1 && resultCode==RESULT_OK && data!=null){

            Uri selectedImage=data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);


                Log.i("Photo","Recieved");


                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                byte[] bytesArray = stream.toByteArray();

                ParseFile file = new ParseFile("image.png", bytesArray);

                ParseObject object = new ParseObject("Image");

                object.put("image", file);

                object.put("username", ParseUser.getCurrentUser().getUsername());

                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){

                            showmessage("Image Shared");
                        }

                        else{

                            showmessage("Image cannot be sure try latter");
                        }
                    }
                })









            }

            catch(IOException e){

                e.printStackTrace();

            }




        }

    }*/
}