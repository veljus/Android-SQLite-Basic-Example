package cirvirlab.mysqlite;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
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
        helper = new MySQLiteAdapter(this);
    }

    public void doWrite(View view) {

       //SQLiteDatabase sqLiteDatabase = helper.

        long id = helper.insertData("Velibor");
       // mojSQLHelper = new MojSQLHelper(this);
       // SQLiteDatabase sqLiteDatabase = mojSQLHelper.getWritableDatabase();
        if(id<0)
        {
            Message.message(this,"Can't write data");
        }
        else
        {
            Message.message(this,"Successfully writen data ");
        }
       // Message.message(this,"Zdravo");
    }
}
