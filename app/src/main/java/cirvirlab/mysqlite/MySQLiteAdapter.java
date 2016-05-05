package cirvirlab.mysqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteAdapter  {

    MySQLiteHelper helper;
    public MySQLiteAdapter(Context context){
        helper = new MySQLiteHelper(context);
    }
    public String readAllData(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String column[] = {MySQLiteHelper.UID,MySQLiteHelper.FIRST_ROW,MySQLiteHelper.SECOND_ROW};
        Cursor cursor = db.query(MySQLiteHelper.TABLE_NAME,column,null,null,null,null,null);
        StringBuffer stringBuffer = new StringBuffer();

        while (cursor.moveToNext())
        {
            int index_id = cursor.getColumnIndex(MySQLiteHelper.UID);
            int index_first_row = cursor.getColumnIndex(MySQLiteHelper.FIRST_ROW);
            int index_second_row = cursor.getColumnIndex(MySQLiteHelper.SECOND_ROW);
            String name = cursor.getString(index_first_row);
            String addr = cursor.getString(index_second_row);
            int cursor_id = cursor.getInt(index_id);
            stringBuffer.append(cursor_id + " " + name + " " + addr + " " + "\n");
        }
        return stringBuffer.toString();
    }
    public int updateRow(String oldName, String newName) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteHelper.FIRST_ROW, newName);
        String[] whereArgs = {oldName};
        int count_updated = db.update(MySQLiteHelper.TABLE_NAME,contentValues,MySQLiteHelper.FIRST_ROW + " = ? " , whereArgs);
        return count_updated;

    }
    public int deleteallRows(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] whereArgs = {"0"};
        int count_deleted =  db.delete(MySQLiteHelper.TABLE_NAME,MySQLiteHelper.UID + " > ?", whereArgs);
        return count_deleted;
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
