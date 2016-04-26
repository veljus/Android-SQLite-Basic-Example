package cirvirlab.mysqlite;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
    MySQLiteAdapter helper;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doWrite(View view) {
        helper = new MySQLiteAdapter(this);
        helper.insertData("Velibor");
       // Message.message(this,"Zdravo");
    }
}
