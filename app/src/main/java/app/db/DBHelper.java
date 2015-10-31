package app.db;

import android.content.ContentValues;

import org.litepal.crud.DataSupport;

import java.util.List;

import app.entity.UserInfo;

/**
 * Created by Administrator on 2015/10/31.
 */
public class DBHelper {

    private static DBHelper dbHelper;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            synchronized (DBHelper.class) {
                if (dbHelper == null) {
                    dbHelper = new DBHelper();
                }
            }
        }
        return dbHelper;
    }
    /**
     *
     * @Author 小小吴
     * @date 2015-10-31 9:47:43
     * @TODO(功能) 根据条件查找记录
     * @mark(备注)
     * @param clazz
     * @param conditions
     * @return
     */

    public UserInfo getUserById(String id) {
        return find(UserInfo.class, "id=?", id);
    }

    public <T> T update(Class<T> modelClass, ContentValues values,
                        String... conditions) {
        DataSupport.updateAll(modelClass, values, conditions);
        return find(modelClass, conditions);

    }

    public <T> T find(Class<T> clazz, String... conditions) {
        List<T> list = DataSupport.where(conditions).find(clazz);
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }
}
