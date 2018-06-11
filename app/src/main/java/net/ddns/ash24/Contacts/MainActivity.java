package net.ddns.ash24.Contacts;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Dbhandler myDb;
    EditText editName, editSName, editEmail, editMobile;
    Button btnAddContact,btnViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new Dbhandler(this);
        editName = (EditText)findViewById(R.id.editText_name);
        editSName = (EditText)findViewById(R.id.editText_sname);
        editEmail = (EditText)findViewById(R.id.editText_email);
        editMobile = (EditText)findViewById(R.id.editText_mobile);
        btnAddContact = (Button) findViewById(R.id.button_add);
        btnViewAll = (Button) findViewById(R.id.button_viewAll);
        AddData();
        ViewAll();
    }
    public void AddData(){
        btnAddContact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               boolean isInserted= myDb.insertData(editName.getText().toString(),editSName.getText().toString(),editEmail.getText().toString(),editMobile.getText().toString());
               if(isInserted)
                   Toast.makeText(MainActivity.this, "Contact Saved", Toast.LENGTH_LONG).show();
               else
                   Toast.makeText(MainActivity.this, "Failed to Save", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ViewAll(){
        btnViewAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Cursor res = myDb.displayContacts();
                if(res.getCount()==0){
                    showMessage("Error", "No Contacts");
                    return;
                }
                StringBuffer buffer =new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID:"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Last Name:"+res.getString(2)+"\n");
                    buffer.append( "Mobile:"+res.getString(4)+"\n");
                    buffer.append("Email:"+res.getString(3)+"\n\n");
                }
                showMessage("Contact", buffer.toString());
            }
        });
    }

    public void showMessage(String Title, String Contact){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Contact);
        builder.show();
    }

}
