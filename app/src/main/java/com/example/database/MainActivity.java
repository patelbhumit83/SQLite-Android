package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    static EditText Name;
    static EditText Contact;
    static EditText SSN;
    static EditText Address;
    static Button submit;
    static DBhandler db;
    static MyAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.name);
        Contact = (EditText) findViewById(R.id.contact);
        SSN = (EditText) findViewById(R.id.ssn);
        Address = (EditText) findViewById(R.id.address);
        submit = (Button) findViewById(R.id.submitbtn);
        db = new DBhandler(this);

         //Inserting Contacts
//        Log.d("Insert: ", "Inserting ..");
//        Log.d("Counr", "B" + db.getContactsCount());
        db.addContact(new ContactDB("Ravi", "9100000000", "123456789", "Abcdbhjsc"));
        db.addContact(new ContactDB("Srinivas", "9199999999", "123456780", "asbhscjdbhj"));
//        db.addContact(new ContactDB("Tommy", "9522222222", "123456790","dcbhscs"));
//        db.addContact(new ContactDB("Karthik", "9533333333","1234567890", "efbhwfbabfhbf"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<ContactDB> contacts = db.getAllContacts();

        for (ContactDB cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " +
                    cn.getPhoneNumber() + "SSN: " + cn.get_ssn() + "Address: " + cn.get_address();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

//        ContactDB[] myListData = new ContactDB[] {
//                new ContactDB("Email", "234814184", "This is email", "10:25"),
//                new ContactDB("Info", "241341434","This is Info", "9:45"),
//                new ContactDB("Delete", "23526464", "This is Delete", "1:53"),
//                new ContactDB("Dialer", "5452464262","This is Dialer", "12:32")
//        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new MyAdapter(contacts, db);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        
    }


    public void AddUser(View view) {

        String nm = Name.getText().toString();
        String ct = Contact.getText().toString();
        String ssn = SSN.getText().toString();
        String add = Address.getText().toString();

        if (nm.isEmpty() || ct.isEmpty() || ssn.isEmpty() || add.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please complete all fields", Toast.LENGTH_LONG).show();
        }
        else
        {
            long id = db.addContact(new ContactDB(nm, ct, ssn, add));
            adapter.addelement(new ContactDB(nm,ct,ssn,add));
            adapter.notifyDataSetChanged();
            if (id <= 0)
            {
                Toast.makeText(getApplicationContext(), "Not added data", Toast.LENGTH_LONG).show();

            }
            else
            {
                Toast.makeText(getApplicationContext(), "Data Added successfully", Toast.LENGTH_LONG).show();
                Name.setText("");
                Contact.setText("");
                Address.setText("");
                SSN.setText("");
                Name.setHint("Name");
                Contact.setHint("Contact");
                SSN.setHint("SSN");
                Address.setHint("Address");
            }
        }

        List<ContactDB> contacts = db.getAllContacts();
        for (ContactDB cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " +
                    cn.getPhoneNumber() + "SSN: " + cn.get_ssn() + "Address: " + cn.get_address();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

    }

    public static void edituser(ContactDB editContact)
    {
        Name.setText(editContact.getName());
        Contact.setText(editContact.getPhoneNumber());
        SSN.setText(editContact.get_ssn());
        Address.setText(editContact.get_address());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editContact.setName(Name.getText().toString());
                editContact.setPhoneNumber(Contact.getText().toString());
                editContact.set_ssn(SSN.getText().toString());
                editContact.set_address(Address.getText().toString());
                db.updateContact(editContact);
                Name.setText("");
                Contact.setText("");
                Address.setText("");
                SSN.setText("");
                Name.setHint("Name");
                Contact.setHint("Contact");
                SSN.setHint("SSN");
                Address.setHint("Address");
                adapter.notifyDataSetChanged();
            }
        });
    }


}