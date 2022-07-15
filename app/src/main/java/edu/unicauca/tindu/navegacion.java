package edu.unicauca.tindu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import edu.unicauca.tindu.R;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class navegacion extends AppCompatActivity implements EventoAdaptador.RecyclerItemClick {
    private RecyclerView Lista;
    private SearchView svSearch;
    private EventoAdaptador adapter;
    private List<Eventos> items;
    private FirebaseAuth mAuth;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion);

        mAuth = FirebaseAuth.getInstance();


        initViews();
        initValues();

        //Definicion de la barra de menu
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar();
        ActionBar actionBar = getSupportActionBar();

        //Titulo de la barra de menu
        actionBar.setTitle("  TindU | Menu Principal");

        // Icono alterno barra menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Vista del drawer
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();


        // Cambio icono alterno barra menu
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        // Unir el boton alterno al drawer
        mDrawer.addDrawerListener(drawerToggle);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Colocacion de vista del drawer
        setupDrawerContent(nvDrawer);

    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Menu del drawer
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_act:
                fragmentClass = null;
                break;
            case R.id.nav_chat:
                fragmentClass = Chat.class;
                break;
            case R.id.nav_perfil:
                fragmentClass = Perfil.class;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + menuItem.getItemId());
        }

        try {
            if(fragmentClass != null){
                fragment = (Fragment) fragmentClass.newInstance();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
            else{
                getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.flContent)).commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        menuItem.setChecked(true);
        // Marco la seleccion del menu
        setTitle(menuItem.getTitle());
        // Cierro el drawer
        mDrawer.closeDrawers();
    }

    //Inflate del menu en la barra de aplicacion
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Acciones del menu en la barra de aplicacion
    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {

        switch (item.getItemId()){
            case R.id.action_config:
                Intent i2 = new Intent(getApplicationContext(), Configuracion.class);
                startActivity(i2);
                break;
            case R.id.action_creditos:
                Intent i3 = new Intent(getApplicationContext(), Creditos.class);
                startActivity(i3);
                break;
            case R.id.action_cerrar:
                mAuth.signOut();
                Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i1);
                finish();
                break;
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews(){
        Lista = findViewById(R.id.Lista);
        //svSearch = findViewById(R.id.svSearch);
    }

    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //Si quiero en representarlo en celdas
        //GridLayoutManager manager1 = new GridLayoutManager(this);
        Lista.setLayoutManager(manager);

        items = getItems();
        adapter = new EventoAdaptador(items, this);
        Lista.setAdapter(adapter);
    }

    /*private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }*/

    private List<Eventos> getItems() {
        List<Eventos> itemLists = new ArrayList<>();
        itemLists.add(new Eventos("Futsal CDU", "Canchas disponibles los viernes, llega con tu equipo.", R.drawable.microfutbol_unicauca));
        itemLists.add(new Eventos("Piscina CDU", "Ven y echate un chapuson.", R.drawable.natacion));
        itemLists.add(new Eventos("Foros", "Unete a las conferencias de becas en el exterior.", R.drawable.reunion));
        itemLists.add(new Eventos("Rumba", "Ven y comparte con tu parche en esta extraordinaria celebracion de cierre de semestre.", R.drawable.rumbas));
        itemLists.add(new Eventos("Running", "Ven y disfruta los mejores paisajes mientras corres. Hora de salida sabado 6 AM.", R.drawable.runing));
        itemLists.add(new Eventos("Ciclomontañismo", "Ven y demuestra que tan bueno eres para el pedal. Hora de salida sabados. 6:30 AM.", R.drawable.montar_en_bicicleta));
        itemLists.add(new Eventos("Aprende a nadar", "Clases de natacion, todos los dias 6:PM.", R.drawable.piscina_en_cdu));
        itemLists.add(new Eventos("Sustentaciones", "Quieres aprender como se sustenta un trabajo de grado?, ven y aprende. Viernes 7:25 AM.", R.drawable.sustentacion_de_trabajos));

        return itemLists;
    }

    @Override
    public void itemClick(Eventos item) {
        //Llamamos al intent
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra("itemDetail", item);
        startActivity(intent);
    }

        //BOTONES DE PRUEBA
    /*public void CerrarSesion(View view) {
        mAuth.signOut();
        Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i1);
        finish();
    }

    public void Configuracion(View view) {
        Intent i2 = new Intent(getApplicationContext(), Configuracion.class);
        startActivity(i2);
        //finish();
    }
    public void Creditos(View view) {
        Intent i3 = new Intent(getApplicationContext(), Creditos.class);
        startActivity(i3);
        //finish();
    }*/
}