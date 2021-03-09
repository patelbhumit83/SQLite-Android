package com.example.database;

public class ContactDB {
    int _id;
    String _name;
    String _phone_number;
    String _ssn;
    String _address;

    public ContactDB(){   }
    public ContactDB(int id, String name, String _phone_number, String _ssn, String _address){
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
        this._ssn = _ssn;
        this._address = _address;
    }

    public ContactDB(String name, String _phone_number, String _ssn, String _address){
        this._name = name;
        this._phone_number = _phone_number;
        this._ssn = _ssn;
        this._address = _address;
    }
    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

    public String getPhoneNumber(){
        return this._phone_number;
    }

    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_ssn() {
        return _ssn;
    }

    public void set_ssn(String _ssn) {
        this._ssn = _ssn;
    }
}