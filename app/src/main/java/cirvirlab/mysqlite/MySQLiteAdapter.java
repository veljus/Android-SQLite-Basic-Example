package cirvirlab.mysqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteAdapter  {

    MySQLiteHelper helper;
    public MySQLiteAdapter(Context context){
        helper = new MySQLiteHelper(context);
    }
    public long insertData(String name, String address){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteHelper.FIRST_ROW,name);
        contentValues.put(MySQLiteHelper.SECOND_ROW,address);
        long id = db.insert(MySQLiteHelper.TABLE_NAME,null,contentValues);
        db.close();
        return id;
    }
     static class MySQLiteHelper extends SQLiteOpenHelper {
          private static final String DB_NAME = "exampledb";
          private static final int DB_VERSION = 4;
          private static final String TABLE_NAME = "MOJATABELA";
          private static final String UID="_id";
          private static final String FIRST_ROW ="Imekorisnika";
          private static final String SECOND_ROW = "Adresakorisnika";
          private static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIRST_ROW + " VARCHAR(150), " + SECOND_ROW + " VARCHAR(150)   ); ";
          private static final String DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;

          private Context context;


          MySQLiteHelper(Context context){
              super(context,DB_NAME,null,DB_VERSION );
              this.context = context;
              Message.message(context,"Constructor called");
          }

          public void onCreate(SQLiteDatabase db) {
              try
              {
                  db.execSQL(CREATE_TABLE);
                  Message.message(context,"onCreate called");
              } catch (SQLException e)
              {
                  Message.message(context," " + e);
              }

          }
          public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
              try
              {
                  db.execSQL(DROP_TABLE);
                  onCreate(db);
                  Message.message(context,"onUpgrade called");
              } catch (SQLException e)
              {
                  e.printStackTrace();
              }
          }
      }
}
