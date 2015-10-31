package app.db;

/**
 * Created by Administrator on 2015/10/31.
 */
public class DBConversion {
    private static DBConversion dbConversion;

    private DBConversion() {
    }

    public static DBConversion getInstance() {
        if (dbConversion == null) {
            synchronized (DBConversion.class) {
                if (dbConversion == null)
                    dbConversion = new DBConversion();
            }
        }
        return dbConversion;
    }
}
