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
import android.widget.TextView;

public class MainActivity extends Activity {
    MySQLiteAdapter helper;
    TextView txtName, txtAddr;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new MySQLiteAdapter(this);
        txtName = (TextView)findViewById(R.id.editName);
        txtAddr = (TextView)findViewById(R.id.editAddr);
    }

    public void doWrite(View view) {
        String str_ime, str_adresa;
        try {
            str_ime = String.valueOf(txtName.getText());
        } catch (Exception e) {
            str_ime = " ";
        }
        try {
            str_adresa = String.valueOf(txtAddr.getText());
        } catch (Exception e) {
            str_adresa = " ";
        }

        long id = helper.insertData(str_ime,str_adresa);

        if(id<0)
        {
            Message.message(this,"Can't write data");
        }
        else
        {
            Message.message(this,"Successfully writen data ");
            txtName.setText("");
            txtAddr.setText("");

        }
    }

    public void showAllData(View view) {
        String allData = helper.readAllData();
        Message.message(this,allData);
    }
}
