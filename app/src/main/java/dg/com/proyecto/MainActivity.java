package dg.com.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import dg.com.proyecto.Recycler.DataAdapter;

public class MainActivity extends AppCompatActivity {

    private String[] names = {
            "Tomato",
            "Zucchini",
            "Eggplant",
            "Strawberry",
            "Pumpkin",
            "Bell Pepper"
    };

    private int[] images = {
            R.drawable.tomato,
            R.drawable.zucchini,
            R.drawable.eggplant,
            R.drawable.strawberry,
            R.drawable.pumpkin,
            R.drawable.bell_pepper
    };

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DataAdapter(names, images);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, PreferencesActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

}