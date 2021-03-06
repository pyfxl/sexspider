package com.android.sexspider4.list.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.android.sexspider4.BaseAccess;
import com.android.sexspider4.list.bean.ListBean;
import com.android.sexspider4.site.bean.SiteBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by feng on 2017/5/5.
 */

public class ListAccess extends BaseAccess {
    //构造方法
    public ListAccess(Context context) {
        super(context);
    }

    //查询所有已下载列表
    public List<ListBean> queryAllDown() {
        String sql = "SELECT A.ListID, A.ListTitle, A.ListLink, A.SiteID, A.IsDown, A.IsDowning, A.IsShow, A.IsRead, A.IsNew, B.ImageDiv, B.ImageFilter, B.PageEncode, B.Domain, B.MainDiv, B.ThumbDiv, B.PageDiv, B.PageFilter, B.PageLevel, B.SiteRank, B.SiteFilter, B.SiteReplace, B.DocType, A.ListPicture, A.IsFavorite, A.ListNum "
                + "FROM " + TABLE_LIST + " A INNER JOIN " + TABLE_SITE + " B ON A.SiteID = B.SiteID WHERE A.IsShow = 1 AND A.IsDown <> 0 AND A.IsDown <> 3 ORDER BY A.ListTitle ASC, A.IsRead ASC";

        return queryByString(sql, null);
    }

    //查询所有列表
    public List<ListBean> queryAll() {
        String sql = "SELECT A.ListID, A.ListTitle, A.ListLink, A.SiteID, A.IsDown, A.IsDowning, A.IsShow, A.IsRead, A.IsNew, B.ImageDiv, B.ImageFilter, B.PageEncode, B.Domain, B.MainDiv, B.ThumbDiv, B.PageDiv, B.PageFilter, B.PageLevel, B.SiteRank, B.SiteFilter, B.SiteReplace, B.DocType, A.ListPicture, A.IsFavorite, A.ListNum "
                + "FROM " + TABLE_LIST + " A INNER JOIN " + TABLE_SITE + " B ON A.SiteID = B.SiteID WHERE B.IsUpdated <> 2 AND A.IsShow = 1 ORDER BY A.ListTitle ASC, A.IsRead ASC";

        return queryByString(sql, null);
    }

    //查询所有喜爱列表
    public List<ListBean> queryAllFavorite() {
        String sql = "SELECT A.ListID, A.ListTitle, A.ListLink, A.SiteID, A.IsDown, A.IsDowning, A.IsShow, A.IsRead, A.IsNew, B.ImageDiv, B.ImageFilter, B.PageEncode, B.Domain, B.MainDiv, B.ThumbDiv, B.PageDiv, B.PageFilter, B.PageLevel, B.SiteRank, B.SiteFilter, B.SiteReplace, B.DocType, A.ListPicture, A.IsFavorite, A.ListNum "
                + "FROM " + TABLE_LIST + " A INNER JOIN " + TABLE_SITE + " B ON A.SiteID = B.SiteID WHERE A.IsFavorite = 1 AND A.IsShow = 1 ORDER BY A.ListTitle ASC, A.IsRead ASC";

        return queryByString(sql, null);
    }

    //查询所有推荐列表
    public List<ListBean> queryAllRecommend() {
        String sql = "SELECT A.ListID, A.ListTitle, A.ListLink, A.SiteID, A.IsDown, A.IsDowning, A.IsShow, A.IsRead, A.IsNew, B.ImageDiv, B.ImageFilter, B.PageEncode, B.Domain, B.MainDiv, B.ThumbDiv, B.PageDiv, B.PageFilter, B.PageLevel, B.SiteRank, B.SiteFilter, B.SiteReplace, B.DocType, A.ListPicture, A.IsFavorite, A.ListNum "
                + "FROM " + TABLE_LIST + " A INNER JOIN " + TABLE_SITE + " B ON A.SiteID = B.SiteID WHERE B.IsUpdated <> 2 AND A.IsShow = 1 AND A.ListRank > 0 ORDER BY A.ListRank DESC, A.ListTitle ASC, A.IsRead ASC LIMIT 500 OFFSET 0";

        return queryByString(sql, null);
    }

    //查询已下载推荐列表
    public List<ListBean> queryAllRecommendDown() {
        String sql = "SELECT A.ListID, A.ListTitle, A.ListLink, A.SiteID, A.IsDown, A.IsDowning, A.IsShow, A.IsRead, A.IsNew, B.ImageDiv, B.ImageFilter, B.PageEncode, B.Domain, B.MainDiv, B.ThumbDiv, B.PageDiv, B.PageFilter, B.PageLevel, B.SiteRank, B.SiteFilter, B.SiteReplace, B.DocType, A.ListPicture, A.IsFavorite, A.ListNum "
                + "FROM " + TABLE_LIST + " A INNER JOIN " + TABLE_SITE + " B ON A.SiteID = B.SiteID WHERE B.IsUpdated <> 2 AND A.IsShow = 1 AND A.IsDown <> 0 AND A.IsDown <> 3 AND A.ListRank > 0 ORDER BY A.ListRank DESC, A.ListTitle ASC, A.IsRead ASC LIMIT 500 OFFSET 0";

        return queryByString(sql, null);
    }

    //查询所有未读列表
    public List<ListBean> queryAllNotRead() {
        String sql = "SELECT A.ListID, A.ListTitle, A.ListLink, A.SiteID, A.IsDown, A.IsDowning, A.IsShow, A.IsRead, A.IsNew, B.ImageDiv, B.ImageFilter, B.PageEncode, B.Domain, B.MainDiv, B.ThumbDiv, B.PageDiv, B.PageFilter, B.PageLevel, B.SiteRank, B.SiteFilter, B.SiteReplace, B.DocType, A.ListPicture, A.IsFavorite, A.ListNum "
                + "FROM " + TABLE_LIST + " A INNER JOIN " + TABLE_SITE + " B ON A.SiteID = B.SiteID WHERE A.IsShow = 1 AND ((B.IsUpdated <> 2 AND A.IsDown <> 0) OR (B.IsUpdated = 2 AND A.IsDown<>0 AND A.IsDown <> 3)) AND A.IsRead = 0 ORDER BY A.ListTitle ASC, A.IsRead ASC";

        return queryByString(sql, null);
    }

    //根据siteId查询所有列表
    public List<ListBean> queryAllById(int siteId) {
        String sql = "SELECT A.ListID, A.ListTitle, A.ListLink, A.SiteID, A.IsDown, A.IsDowning, A.IsShow, A.IsRead, A.IsNew, B.ImageDiv, B.ImageFilter, B.PageEncode, B.Domain, B.MainDiv, B.ThumbDiv, B.PageDiv, B.PageFilter, B.PageLevel, B.SiteRank, B.SiteFilter, B.SiteReplace, B.DocType, A.ListPicture, A.IsFavorite, A.ListNum "
                + "FROM " + TABLE_LIST + " A INNER JOIN " + TABLE_SITE + " B ON A.SiteID = B.SiteID WHERE A.SiteID = ? ORDER BY A.IsNew DESC, A.ListNum ASC, A.ListID DESC";

        return queryByString(sql, new String[] { String.valueOf(siteId) });
    }

    //根据siteId查询所有下载列表
    public List<ListBean> queryAllDownById(int siteId) {
        String sql = "SELECT A.ListID, A.ListTitle, A.ListLink, A.SiteID, A.IsDown, A.IsDowning, A.IsShow, A.IsRead, A.IsNew, B.ImageDiv, B.ImageFilter, B.PageEncode, B.Domain, B.MainDiv, B.ThumbDiv, B.PageDiv, B.PageFilter, B.PageLevel, B.SiteRank, B.SiteFilter, B.SiteReplace, B.DocType, A.ListPicture, A.IsFavorite, A.ListNotes, A.ListNum "
                + "FROM " + TABLE_LIST + " A INNER JOIN " + TABLE_SITE + " B ON A.SiteID = B.SiteID WHERE A.SiteID = ? AND A.IsDown <> 0 ORDER BY A.ListNum ASC, A.ListID DESC";

        return queryByString(sql, new String[] { String.valueOf(siteId) });
    }

    //根据query查询所有列表
    public List<ListBean> queryIsShowByKey(String searchKey) {
        String key = "%" + searchKey + "%";
        String sql = "SELECT A.ListID, A.ListTitle, A.ListLink, A.SiteID, A.IsDown, A.IsDowning, A.IsShow, A.IsRead, A.IsNew, B.ImageDiv, B.ImageFilter, B.PageEncode, B.Domain, B.MainDiv, B.ThumbDiv, B.PageDiv, B.PageFilter, B.PageLevel, B.SiteRank, B.SiteFilter, B.SiteReplace, B.DocType, A.ListPicture, A.IsFavorite, A.ListNum "
                + "FROM " + TABLE_LIST + " A INNER JOIN " + TABLE_SITE + " B ON A.SiteID = B.SiteID WHERE (B.IsUpdated <> 2 OR (B.IsUpdated = 2 AND A.IsDown <> 0 AND A.IsDown <> 3)) AND A.IsShow = 1 AND A.ListTitle LIKE ? ORDER BY A.ListTitle ASC, A.IsRead ASC";

        return queryByString(sql, new String[] { String.valueOf(key) });
    }

    //根据query查询所有下载列表
    public List<ListBean> queryIsDownByKey(String searchKey) {
        String key = "%" + searchKey + "%";
        String sql = "SELECT A.ListID, A.ListTitle, A.ListLink, A.SiteID, A.IsDown, A.IsDowning, A.IsShow, A.IsRead, A.IsNew, B.ImageDiv, B.ImageFilter, B.PageEncode, B.Domain, B.MainDiv, B.ThumbDiv, B.PageDiv, B.PageFilter, B.PageLevel, B.SiteRank, B.SiteFilter, B.SiteReplace, B.DocType, A.ListPicture, A.IsFavorite, A.ListNum "
                + "FROM " + TABLE_LIST + " A INNER JOIN " + TABLE_SITE + " B ON A.SiteID = B.SiteID WHERE A.IsShow = 1 AND A.IsDown <> 0 AND A.IsDown <> 3 AND A.ListTitle LIKE ? ORDER BY A.ListTitle ASC, A.IsRead ASC";

        return queryByString(sql, new String[] { String.valueOf(key) });
    }

    //根据listId查询列表
    public ListBean queryByListId(int listId) {
        String sql = "SELECT A.ListID, A.ListTitle, A.ListLink, A.SiteID, A.IsDown, A.IsDowning, A.IsShow, A.IsRead, A.IsNew, B.ImageDiv, B.ImageFilter, B.PageEncode, B.Domain, B.MainDiv, B.ThumbDiv, B.PageDiv, B.PageFilter, B.PageLevel, B.SiteRank, B.SiteFilter, B.SiteReplace, B.DocType, A.ListPicture, A.IsFavorite, A.ListNum "
                + "FROM " + TABLE_LIST + " A INNER JOIN " + TABLE_SITE + " B ON A.SiteID = B.SiteID WHERE A.ListID = ?";

        ListBean list = new ListBean();

        try {
            Cursor cursor = db.rawQuery(sql, new String[] { String.valueOf(listId) });
            while (cursor.moveToNext()) {
                list = queryByCursor(cursor);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //查询基本方法
    private List<ListBean> queryByString(String sql, String[] query) {
        List<ListBean> list = new ArrayList<>();

        try {
            Cursor cursor = db.rawQuery(sql, query);
            while (cursor.moveToNext()) {
                ListBean entity = queryByCursor(cursor);
                list.add(entity);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //查询基本方法--cursor
    private ListBean queryByCursor(Cursor cursor) {
        SiteBean site = new SiteBean();
        ListBean entity = new ListBean();
        entity.listId = cursor.getInt(cursor.getColumnIndex("ListID"));
        entity.listTitle = cursor.getString(cursor.getColumnIndex("ListTitle"));
        entity.listLink = cursor.getString(cursor.getColumnIndex("ListLink"));
        entity.isDown = cursor.getInt(cursor.getColumnIndex("IsDown"));
        entity.isDowning = cursor.getInt(cursor.getColumnIndex("IsDowning"));
        entity.isShow = cursor.getInt(cursor.getColumnIndex("IsShow"));
        entity.isRead = cursor.getInt(cursor.getColumnIndex("IsRead"));
        entity.isNew = cursor.getInt(cursor.getColumnIndex("IsNew"));
        site.siteId = cursor.getInt(cursor.getColumnIndex("SiteID"));
        site.imageDiv = cursor.getString(cursor.getColumnIndex("ImageDiv"));
        site.imageFilter = cursor.getString(cursor.getColumnIndex("ImageFilter"));
        site.pageEncode = cursor.getString(cursor.getColumnIndex("PageEncode"));
        site.domain = cursor.getString(cursor.getColumnIndex("Domain"));
        site.mainDiv = cursor.getString(cursor.getColumnIndex("MainDiv"));
        site.thumbDiv = cursor.getString(cursor.getColumnIndex("ThumbDiv"));
        site.pageDiv = cursor.getString(cursor.getColumnIndex("PageDiv"));
        site.pageFilter = cursor.getString(cursor.getColumnIndex("PageFilter"));
        site.pageLevel = cursor.getString(cursor.getColumnIndex("PageLevel"));
        site.siteRank = cursor.getString(cursor.getColumnIndex("SiteRank"));
        site.siteFilter = cursor.getString(cursor.getColumnIndex("SiteFilter"));
        site.siteReplace = cursor.getString(cursor.getColumnIndex("SiteReplace"));
        site.docType = cursor.getString(cursor.getColumnIndex("DocType"));
        entity.listPicture = cursor.getString(cursor.getColumnIndex("ListPicture"));
        entity.isFavorite = cursor.getInt(cursor.getColumnIndex("IsFavorite"));
        entity.listNum = cursor.getInt(cursor.getColumnIndex("ListNum"));
        entity.siteInfo = site;

        if(cursor.getColumnIndex("Notes") > 0) {
            entity.listNotes = cursor.getString(cursor.getColumnIndex("ListNotes"));
        }

        return entity;
    }

    //插入数据
    public long insert(ListBean list) {
        ContentValues values = new ContentValues();
        values.put("ListTitle", list.listTitle);
        values.put("ListLink", list.listLink);
        values.put("ListPicture", list.listPicture);
        values.put("SiteID", list.siteInfo.siteId);
        values.put("ListNum", list.listNum);
        values.put("IsNew", list.isNew);
        values.put("ListNotes", list.listNotes);

        try {
            return db.insert(TABLE_LIST, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    //更新数据
    public void update(ListBean list) {
        ContentValues values = new ContentValues();
        values.put("IsDown", list.isDown);
        values.put("IsDowning", list.isDowning);
        values.put("IsRead", list.isRead);
        values.put("IsShow", list.isShow);
        values.put("ListPicture", list.listPicture);
        values.put("IsFavorite", list.isFavorite);
        values.put("ListRank", list.listRank);
        values.put("IsNew", list.isNew);
        values.put("ListNum", list.listNum);

        try {
            db.update(TABLE_LIST, values, "ListID = ?", new String[] { String.valueOf(list.listId) });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //批量更新
    public void update(List<ListBean> lists) {
        try {
            Iterator<ListBean> it = lists.iterator();
            while (it.hasNext()) {
                ListBean list = it.next();
                ContentValues values = new ContentValues();
                values.put("ListRank", list.listRank);
                db.update(TABLE_LIST, values, "ListID = ?", new String[] { String.valueOf(list.listId) });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //正在下载的项取消下载
    public void cancelDown() {
        ContentValues values = new ContentValues();
        values.put("IsDowning", 0);

        try {
            db.update(TABLE_LIST, values, "IsDowning = ?", new String[] { "1" });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //更新最新下载的项
    public void updateIsNew(int siteId) {
        ContentValues values = new ContentValues();
        values.put("IsNew", 0);

        try {
            db.update(TABLE_LIST, values, "SiteID = ? AND STRFTIME('%Y-%m-%d', ListDate) <> STRFTIME('%Y-%m-%d', datetime('now', '+8 hour'))", new String[] { String.valueOf(siteId) });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除数据
    public void delete(ListBean list) {
        try {
            db.delete(TABLE_LIST, "ListID = ?", new String[] { String.valueOf(list.listId) });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //更新喜爱
    public void updateFavorite(int listId, int isFavorite) {
        ContentValues values = new ContentValues();
        values.put("IsFavorite", isFavorite);

        try {
            db.update(TABLE_LIST, values, "ListID = ?", new String[] { String.valueOf(listId) });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
