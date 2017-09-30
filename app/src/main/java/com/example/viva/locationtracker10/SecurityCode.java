package com.example.viva.locationtracker10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecurityCode extends AppCompatActivity {
    EditText code ;
    DBConnections dbConnections = new DBConnections(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_code);
        code = (EditText)findViewById(R.id.code);
        dbConnections.insertRowCode("");



    }
    public void save (View view){
        dbConnections.updateCode(code.getText().toString());
    }

    public void display(View view){
        DBConnections dbConnections =new DBConnections(this);
        String codeN = dbConnections.getCode();
        code.setText(codeN);

    }
    public  void delete(View view){
        DBConnections dbConnections =new DBConnections(this);
        dbConnections.deleteCode();
    }

    public void updete(View view){
        DBConnections dbConnections =new DBConnections(this);
        dbConnections.updateCode(code.getText().toString());
    }


}
