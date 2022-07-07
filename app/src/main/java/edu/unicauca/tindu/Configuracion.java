package edu.unicauca.tindu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Switch;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;

public class Configuracion extends AppCompatActivity {

    public Switch s1,s2,s3,s4,s5,s6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        //Config update from database
        getConfigs();
        //Config save when changes detected

    }

    @Override
    protected void onPause() {
        saveconfig();
        super.onPause();
    }

    @Override
    protected void onStop() {
        //saveconfig();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getConfigs();
    }

    private void getConfigs() {
        class GetConfigs extends AsyncTask<Void, Void, List<TinduDB>> {

            @Override
            protected List<TinduDB> doInBackground(Void... voids) {
                List<TinduDB> optList = TinduDBDatabaseAccesor.getInstance(getApplication()).TinduDBDAO().loadAllItems();
                return optList;
            }

            @Override
            protected void onPostExecute(List<TinduDB> configs) {
                super.onPostExecute(configs);
                if (configs.size() == 0) {
                    initconfig();
                }
                //configItems.clear();
                //dbconfigItems.clear();
                else {
                    s1 = findViewById(R.id.switch1);
                    s2 = findViewById(R.id.switch3);
                    s3 = findViewById(R.id.switch4);
                    s4 = findViewById(R.id.switch5);
                    s5 = findViewById(R.id.switch6);
                    s6 = findViewById(R.id.switch7);

                    s1.setChecked(configs.get(0).getConfig());
                    s2.setChecked(configs.get(1).getConfig());
                    s3.setChecked(configs.get(2).getConfig());
                    s4.setChecked(configs.get(3).getConfig());
                    s5.setChecked(configs.get(4).getConfig());
                    s6.setChecked(configs.get(5).getConfig());

                    configs.clear();
                }
            }
        }
        GetConfigs getConfigs = new GetConfigs();
        getConfigs.execute();

    }

    private void initconfig() {
        class Initconfig extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                //adding to database
                for (int i = 0; i < 6; i++) {
                    TinduDB configstart = new TinduDB(true, "Default", java.lang.System.currentTimeMillis());
                    TinduDBDatabaseAccesor.getInstance(getApplication()).TinduDBDAO().insertTinduDB(configstart);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //getTasks();
                //myEditText.setText("");
                //cancelAdd();
                getConfigs();
            }
        }

        Initconfig initconfig = new Initconfig();
        initconfig.execute();
    }

    private void saveconfig() {
        class Saveconfig extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                List<TinduDB> configsav = TinduDBDatabaseAccesor.getInstance(getApplication()).TinduDBDAO().loadAllItems();

                //DB Delete
                TinduDBDatabaseAccesor.getInstance(getApplication()).TinduDBDAO().deleteAllItems();

                TinduDB config = new TinduDB(s1.isChecked(), "Change1", java.lang.System.currentTimeMillis());
                configsav.set(0,config);

                config = new TinduDB(s2.isChecked(), "Change2", java.lang.System.currentTimeMillis());
                configsav.set(1,config);

                config = new TinduDB(s3.isChecked(), "Change3", java.lang.System.currentTimeMillis());
                configsav.set(2,config);

                config = new TinduDB(s4.isChecked(), "Change4", java.lang.System.currentTimeMillis());
                configsav.set(3,config);

                config = new TinduDB(s5.isChecked(), "Change5", java.lang.System.currentTimeMillis());
                configsav.set(4,config);

                config = new TinduDB(s6.isChecked(), "Change6", java.lang.System.currentTimeMillis());
                configsav.set(5,config);

                //DB Update
                TinduDBDatabaseAccesor.getInstance(getApplication()).TinduDBDAO().insertTinduDBs(configsav);

                configsav.clear();

                return null;
            }
        }

        Saveconfig saveconfig = new Saveconfig();
        saveconfig.execute();
    }



}