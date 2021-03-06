package com.iesebre.dam2.amayor.todos;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Intent;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String SHARED_PREFERENCES_TODOS = "SP_TODOS";
    private static final String TODO_LIST = "todo_list" ;
    private Gson gson;
    public TodoArrayList tasks;
    private CustomListAdapter adapter;
    private String todoName;
    private boolean todoDone;
    private int todoPri;
    private boolean checkint;
    TodoItem ti_temp;
    TodoItem.Color checkedColor;
    String taskTitle;

    View positiveAction;

    boolean undoIsVisible = false;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();




//        TodoArrayList temp = gson.fromJson(todoList,arrayTodoList);
//        this.gson = new Gson();
//        Type arrayTodoList = new TypeToken<TodoArrayList>() {}.getType();
//
//        if (temp != null) {
//            tasks = temp;
//        } else {
//            //Error TODO
//        }
//
//        String initial_json = "";//TODO tasks.toJson
//        SharedPreferences.Editor editor = todos.edit();
//        editor.putString(TODO_LIST, initial_json);
//        editor.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences todos = getSharedPreferences(SHARED_PREFERENCES_TODOS, 0);
        String todoList = todos.getString(TODO_LIST, null);

         /*

        [
         {"name":"Comprar llet", "done": true, "priority": 2},
         {"name":"Comprar pa", "done": true, "priority": 1},
         {"name":"Fer exercici", "done": false, "priority": 3'}
        ]

         */


//Gson (pista)
//        if (todoList == null) {
//            Type arrayTodoList = new TypeToken<TodoArrayList>() {}.getType();
//            this.gson = new Gson();
//            TodoArrayList temp = gson.fromJson(todoList, arrayTodoList);
//
//
//            String initial_json = "";//TODO tasks.toJson
//            SharedPreferences.Editor editor = todos.edit();
//            editor.putString(TODO_LIST, initial_json);
//            editor.commit();
//        }




//        Log.d("TAG_PROVA","****************************************************");
//        Log.d("TAG_PROVA", todoList);
//        Log.d("TAG_PROVA", "****************************************************");

        //Toast.makeText(this, todoList, Toast.LENGTH_LONG).show();

        /*
        [
         {"name":"Comprar llet", "done": true, "priority": 2},
         {"name":"Comprar pa", "done": true, "priority": 1},
         {"name":"Fer exercici", "done": false, "priority": 3}
        ]

         */

        Type arrayTodoList = new TypeToken<TodoArrayList>() {}.getType();
        this.gson = new Gson();
        TodoArrayList temp = gson.fromJson(todoList,arrayTodoList);

        if (temp != null) {
            tasks = temp;
        } else {
            //Error TODO
        }

        ListView todoslv = (ListView) findViewById(R.id.todolistview);

        //We bind our arraylist of tasks to the adapter
        adapter = new CustomListAdapter(this, tasks);
        todoslv.setAdapter(adapter);

        Toolbar toolbar = (Toolbar)
                findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener()   {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//                startActivity(intent);
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//            }
//        });

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showAddTodoForm(View view){

        checkint = true;
        todoName = "";
        checkedColor = TodoItem.Color.BLUE;
        todoDone = false;
        EditText todoNameText;
        RadioGroup todoPriority;

        MaterialDialog dialog = new MaterialDialog.Builder(this).
                title("Afegir tasca").
                customView(R.layout.form_todo_add, true).
                negativeText("Cancelar").
                positiveText("Acceptar").
                negativeColor(Color.parseColor("#2196F3")).
                positiveColor(Color.parseColor("#2196F3")).
                onPositive(new MaterialDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        final TodoItem todoItem = new TodoItem();
                        todoItem.setText(todoName);
                        todoItem.setColor(checkedColor);
                        todoItem.setChecked(false);

                        tasks.add(todoItem);
                        adapter.notifyDataSetChanged();

                        //View v = findViewByIndex(tasks.indexOf(todoItem), (ListView) findViewById(R.id.listview));
                        if (!checkint) {
                            toast( "Error al definir la prioritat. Posat a 0 per defecte." );
                        }
                    }
                }).
                build();
                dialog.show();


        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);

        todoNameText = (EditText) dialog.getCustomView().findViewById(R.id.todo_title);

        todoNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                todoName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        todoPriority = (RadioGroup) dialog.getCustomView().findViewById(R.id.todo_priority);
        todoPriority.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup taskPriority, int checkedId) {

                RadioButton checkedRadioButton = (RadioButton) taskPriority.findViewById(checkedId);

                if (checkedRadioButton.isChecked()) {

                    if (checkedId == R.id.prioritat_alta) {
                        checkedColor = TodoItem.Color.RED;
                    } else if (checkedId == R.id.prioritat_mitja) {
                        checkedColor = TodoItem.Color.BLUE;
                    } else if (checkedId == R.id.prioritat_baixa) {
                        checkedColor = TodoItem.Color.GREEN;
                    } else {
                        checkedColor = null;
                    }

                    positiveAction.setEnabled(taskTitle.trim().length() > 0 && checkedColor != null);
                }
            }

//        todoPriText = (EditText) dialog.getCustomView().findViewById(R.id.todo_priority);
//
//        todoPriText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence p, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence p, int start, int before, int count) {
//                try {
//                    todoPri = Integer.parseInt(p.toString());
//                    checkint = true;
//                } catch (NumberFormatException nfe) {
//                    toast( "Si us plau, introdueix un número enter." );
//                    checkint = false;
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable p) {
//
//            }
        });
        //todoDoneText = (boolean) dialog.getCustomView().findViewById(R.id.todo_title);
    }

    public void toast(String msg){
        Context context = getApplicationContext();
        CharSequence text = msg;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}