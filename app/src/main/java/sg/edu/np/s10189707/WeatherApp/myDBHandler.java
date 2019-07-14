package sg.edu.np.s10189707.WeatherApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDBHandler extends SQLiteOpenHelper {

    private static final String TAG = "MYDBHandler";

    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Account.db";
    public static final String ACCOUNTS = "Accounts";
    public static final String COLUMN_USRENAME = "UserName";
    public static final String COLUMN_PASSWORD = "Password";

    public myDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,DATABASE_NAME,factory,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE "+ ACCOUNTS + "(" + COLUMN_USRENAME + " TEXT, "+ COLUMN_PASSWORD
                + " TEXT "+ ")";
        db.execSQL(CREATE_ACCOUNTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);
        onCreate(db);
    }

    public void addUser(UserData userData){
        ContentValues value = new ContentValues();
        value.put(COLUMN_USRENAME,userData.getMyUserName());
        value.put(COLUMN_PASSWORD,userData.getMyPassword());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(ACCOUNTS,null,value);
        db.close();

    }

    public UserData findUser(String username){
        String query = "SELECT * FROM "+ACCOUNTS + " WHERE "+ COLUMN_USRENAME + " =\"" + username +"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        UserData queryData = new UserData();

        if(cursor.moveToFirst()){
            queryData.setMyUserName(cursor.getString(0));
            queryData.setMyPassword(cursor.getString(1));
            cursor.close();
        }
        else {
            queryData = null;
        }
        db.close();
        return  queryData;
    }

    public boolean deleteUser(String username){

        boolean result = false;
        String query = "SELECT * FROM "+ACCOUNTS + " WHERE "+ COLUMN_USRENAME + " =\"" + username +"\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        UserData delData = new UserData();

        if(cursor.moveToFirst()){
            delData.setMyUserName(cursor.getString(0));
            db.delete(ACCOUNTS,COLUMN_USRENAME + "=?", new String[]{String.valueOf(delData.getMyUserName())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;

    }
}
