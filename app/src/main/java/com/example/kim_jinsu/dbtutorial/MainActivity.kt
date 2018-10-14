//https://www.tutorialkart.com/kotlin-android/android-sqlite-example-application/

package com.example.kim_jinsu.dbtutorial

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var usersDBHelper: UsersDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersDBHelper = UsersDBHelper(this)
    }

    fun addUser(v:View){
        var userid = this.edittext_userid.text.toString()
        var name = this.edittext_name.text.toString()
        var age = this.edittext_age.text.toString().toInt()
        var result = usersDBHelper.insertUser(UserModel(userid = userid,name = name,age = age))
        //clear all edittext s
        this.edittext_age.setText("")
        this.edittext_name.setText("")
        this.edittext_userid.setText("")
        this.textview_result.text = "Added user : "+result
        this.ll_entries.removeAllViews()
    }

    fun readUser(v:View){
        var userid = this.edittext_userid.text.toString()
        var user = usersDBHelper.readUser(userid)
        this.ll_entries.removeAllViews()
        user.forEach {
            var tv_user = TextView(this)
            tv_user.textSize = 30F
            tv_user.text = it.userid.toString() + " / " + it.name.toString() + " / " + it.age.toString()
            this.ll_entries.addView(tv_user)
        }
        this.textview_result.text = "Fetched " + user.size + " users"
    }

    fun deleteUser(v:View){
        var userid = this.edittext_userid.text.toString()
        val result = usersDBHelper.deleteUser(userid)
        this.textview_result.text = "Deleted user : "+result
        this.ll_entries.removeAllViews()
    }

    fun showAllUsers(v:View){
        var users = usersDBHelper.readAllUsers()
        this.ll_entries.removeAllViews()
        users.forEach {
            var tv_user = TextView(this)
            tv_user.textSize = 30F
            tv_user.text = it.userid.toString() + " / " + it.name.toString() + " / " + it.age.toString()
            this.ll_entries.addView(tv_user)
        }
        this.textview_result.text = "Fetched " + users.size + " users"
    }

    fun plusOne(v:View){
        var userid = this.edittext_userid.text.toString()
        val result = usersDBHelper.addAge(userid)
        this.textview_result.text = "Added : " + result
        this.ll_entries.removeAllViews()
    }
}