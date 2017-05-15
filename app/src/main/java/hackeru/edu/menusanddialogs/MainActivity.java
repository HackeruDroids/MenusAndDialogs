package hackeru.edu.menusanddialogs;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etQuery;
    private FloatingActionButton fab;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        etQuery = (EditText) findViewById(R.id.etQuery);

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //1)instantiate AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //2) work with the builder -> setTitle, setIcon, set***Button
        builder.setTitle("No Internet Connection").
                setMessage("This app requires an internet connection.").
                setCancelable(false).
                setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK);
                        //Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        //Intent intent = new Intent(Settings.ACTION_WIFI_IP_SETTINGS);
                        startActivity(intent);
                    }
                }).
                setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if (which == DialogInterface.BUTTON_POSITIVE){}
                        System.exit(0);
                    }
                }).show();

        //3) show
    }

    public void pickAColor(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] items = new String[]{"Red", "Green", "Blue"};
        builder.setTitle("Pick A Color").
                setIcon(R.mipmap.ic_launcher).
                setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

    }

    //create the menu for the toolbar.
    //decide which XML goes inside the menu.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    //this is the menu callback.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_refresh:
                etQuery.setText("");
                return true;

            case R.id.action_search:
                String s = etQuery.getText().toString();
                Snackbar.make(etQuery, s, BaseTransientBottomBar.LENGTH_LONG).show();
                return true;

            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
