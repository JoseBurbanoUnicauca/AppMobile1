package edu.unicauca.tindu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Switch;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;

public class Configuracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        Switch s1 = (Switch) findViewById(R.id.switch1);
        Switch s2 = (Switch) findViewById(R.id.switch3);
        Switch s3 = (Switch) findViewById(R.id.switch4);
        Switch s4 = (Switch) findViewById(R.id.switch5);
        Switch s5 = (Switch) findViewById(R.id.switch6);
        Switch s6 = (Switch) findViewById(R.id.switch7);
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
                    Switch s1 = (Switch) findViewById(R.id.switch1);
                    s1.setChecked(configs.get(0).getConfig());
                    Switch s2 = (Switch) findViewById(R.id.switch3);
                    s2.setChecked(configs.get(1).getConfig());
                    Switch s3 = (Switch) findViewById(R.id.switch4);
                    s3.setChecked(configs.get(2).getConfig());
                    Switch s4 = (Switch) findViewById(R.id.switch5);
                    s4.setChecked(configs.get(3).getConfig());
                    Switch s5 = (Switch) findViewById(R.id.switch6);
                    s5.setChecked(configs.get(4).getConfig());
                    Switch s6 = (Switch) findViewById(R.id.switch7);
                    s6.setChecked(configs.get(5).getConfig());
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

    public void saveconfig() {
        class Saveconfig extends AsyncTask<Void, Void, List<TinduDB>> {

            @Override
            protected List<TinduDB> doInBackground(Void... voids) {
                List<TinduDB> optList = TinduDBDatabaseAccesor.getInstance(getApplication()).TinduDBDAO().loadAllItems();
                return optList;
            }

            @Override
            protected void onPostExecute(List<TinduDB> configs) {
                super.onPostExecute(configs);
                    Switch s1 = (Switch) findViewById(R.id.switch1);
                    TinduDB config1 = new TinduDB(s1.isChecked(), "Change", java.lang.System.currentTimeMillis());
                    configs.set(0,config1);
                    Switch s2 = (Switch) findViewById(R.id.switch3);
                    TinduDB config2 = new TinduDB(s2.isChecked(), "Change", java.lang.System.currentTimeMillis());
                    configs.set(1,config2);
                    Switch s3 = (Switch) findViewById(R.id.switch4);
                    TinduDB config3 = new TinduDB(s3.isChecked(), "Change", java.lang.System.currentTimeMillis());
                    configs.set(2,config3);
                    Switch s4 = (Switch) findViewById(R.id.switch5);
                    TinduDB config4 = new TinduDB(s4.isChecked(), "Change", java.lang.System.currentTimeMillis());
                    configs.set(3,config4);
                    Switch s5 = (Switch) findViewById(R.id.switch6);
                    TinduDB config5 = new TinduDB(s5.isChecked(), "Change", java.lang.System.currentTimeMillis());
                    configs.set(4,config5);
                    Switch s6 = (Switch) findViewById(R.id.switch7);
                    TinduDB config6 = new TinduDB(s6.isChecked(), "Change", java.lang.System.currentTimeMillis());
                    configs.set(5,config6);

                    TinduDBDatabaseAccesor.getInstance(getApplication()).TinduDBDAO().updateTinduDBs(configs);
            }
        }

        Saveconfig saveconfig = new Saveconfig();
        saveconfig.execute();
    }



}