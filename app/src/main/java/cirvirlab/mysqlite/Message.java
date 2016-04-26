package cirvirlab.mysqlite;


import android.content.Context;
import android.widget.Toast;

public class Message {
    public static void message(Context context,String err_msg){
        Toast.makeText(context,err_msg,Toast.LENGTH_SHORT).show();
    }
}
