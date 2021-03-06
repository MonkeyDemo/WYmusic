package com.ping.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import kingtuoware.com.wymusic.model.beans.Mp3Info;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MP3_INFO".
*/
public class Mp3InfoDao extends AbstractDao<Mp3Info, Long> {

    public static final String TABLENAME = "MP3_INFO";

    /**
     * Properties of entity Mp3Info.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Album = new Property(2, String.class, "album", false, "ALBUM");
        public final static Property Artist = new Property(3, String.class, "artist", false, "ARTIST");
        public final static Property Path = new Property(4, String.class, "path", false, "PATH");
        public final static Property Duration = new Property(5, int.class, "duration", false, "DURATION");
        public final static Property Size = new Property(6, int.class, "size", false, "SIZE");
        public final static Property Label = new Property(7, String.class, "label", false, "LABEL");
    };


    public Mp3InfoDao(DaoConfig config) {
        super(config);
    }
    
    public Mp3InfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MP3_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TITLE\" TEXT," + // 1: title
                "\"ALBUM\" TEXT," + // 2: album
                "\"ARTIST\" TEXT," + // 3: artist
                "\"PATH\" TEXT," + // 4: path
                "\"DURATION\" INTEGER NOT NULL ," + // 5: duration
                "\"SIZE\" INTEGER NOT NULL ," + // 6: size
                "\"LABEL\" TEXT);"); // 7: label
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MP3_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Mp3Info entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String album = entity.getAlbum();
        if (album != null) {
            stmt.bindString(3, album);
        }
 
        String artist = entity.getArtist();
        if (artist != null) {
            stmt.bindString(4, artist);
        }
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(5, path);
        }
        stmt.bindLong(6, entity.getDuration());
        stmt.bindLong(7, entity.getSize());
 
        String label = entity.getLabel();
        if (label != null) {
            stmt.bindString(8, label);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Mp3Info entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String album = entity.getAlbum();
        if (album != null) {
            stmt.bindString(3, album);
        }
 
        String artist = entity.getArtist();
        if (artist != null) {
            stmt.bindString(4, artist);
        }
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(5, path);
        }
        stmt.bindLong(6, entity.getDuration());
        stmt.bindLong(7, entity.getSize());
 
        String label = entity.getLabel();
        if (label != null) {
            stmt.bindString(8, label);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Mp3Info readEntity(Cursor cursor, int offset) {
        Mp3Info entity = new Mp3Info( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // album
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // artist
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // path
            cursor.getInt(offset + 5), // duration
            cursor.getInt(offset + 6), // size
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // label
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Mp3Info entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAlbum(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setArtist(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPath(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDuration(cursor.getInt(offset + 5));
        entity.setSize(cursor.getInt(offset + 6));
        entity.setLabel(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Mp3Info entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Mp3Info entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
