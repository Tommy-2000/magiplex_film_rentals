package com.thomasp.a30211522.magiplexfilmrentals.model;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MagiPlexFilm_DB_Impl extends MagiPlexFilm_DB {
  private volatile MyFavourites_DAO _myFavouritesDAO;

  private volatile MyPurchases_DAO _myPurchasesDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `favouritesList` (`FilmID` INTEGER NOT NULL, `FilmTitle` TEXT, `FilmGenre` TEXT, `FilmDescription` TEXT, `FilmUserRating` TEXT, `FilmCertificate` TEXT, `FilmThumbnailURL` TEXT, PRIMARY KEY(`FilmID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `purchasesList` (`FilmID` INTEGER NOT NULL, `FilmTitle` TEXT, `FilmGenre` TEXT, `FilmDescription` TEXT, `FilmUserRating` TEXT, `FilmCertificate` TEXT, `FilmThumbnailURL` TEXT, PRIMARY KEY(`FilmID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9498ded90b8e298cccfc81ec67affdfb')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `favouritesList`");
        _db.execSQL("DROP TABLE IF EXISTS `purchasesList`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsFavouritesList = new HashMap<String, TableInfo.Column>(7);
        _columnsFavouritesList.put("FilmID", new TableInfo.Column("FilmID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouritesList.put("FilmTitle", new TableInfo.Column("FilmTitle", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouritesList.put("FilmGenre", new TableInfo.Column("FilmGenre", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouritesList.put("FilmDescription", new TableInfo.Column("FilmDescription", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouritesList.put("FilmUserRating", new TableInfo.Column("FilmUserRating", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouritesList.put("FilmCertificate", new TableInfo.Column("FilmCertificate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouritesList.put("FilmThumbnailURL", new TableInfo.Column("FilmThumbnailURL", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFavouritesList = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFavouritesList = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFavouritesList = new TableInfo("favouritesList", _columnsFavouritesList, _foreignKeysFavouritesList, _indicesFavouritesList);
        final TableInfo _existingFavouritesList = TableInfo.read(_db, "favouritesList");
        if (! _infoFavouritesList.equals(_existingFavouritesList)) {
          return new RoomOpenHelper.ValidationResult(false, "favouritesList(com.thomasp.a30211522.magiplexfilmrentals.model.MyFavourites_Data).\n"
                  + " Expected:\n" + _infoFavouritesList + "\n"
                  + " Found:\n" + _existingFavouritesList);
        }
        final HashMap<String, TableInfo.Column> _columnsPurchasesList = new HashMap<String, TableInfo.Column>(7);
        _columnsPurchasesList.put("FilmID", new TableInfo.Column("FilmID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPurchasesList.put("FilmTitle", new TableInfo.Column("FilmTitle", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPurchasesList.put("FilmGenre", new TableInfo.Column("FilmGenre", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPurchasesList.put("FilmDescription", new TableInfo.Column("FilmDescription", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPurchasesList.put("FilmUserRating", new TableInfo.Column("FilmUserRating", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPurchasesList.put("FilmCertificate", new TableInfo.Column("FilmCertificate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPurchasesList.put("FilmThumbnailURL", new TableInfo.Column("FilmThumbnailURL", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPurchasesList = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPurchasesList = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPurchasesList = new TableInfo("purchasesList", _columnsPurchasesList, _foreignKeysPurchasesList, _indicesPurchasesList);
        final TableInfo _existingPurchasesList = TableInfo.read(_db, "purchasesList");
        if (! _infoPurchasesList.equals(_existingPurchasesList)) {
          return new RoomOpenHelper.ValidationResult(false, "purchasesList(com.thomasp.a30211522.magiplexfilmrentals.model.MyPurchases_Data).\n"
                  + " Expected:\n" + _infoPurchasesList + "\n"
                  + " Found:\n" + _existingPurchasesList);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "9498ded90b8e298cccfc81ec67affdfb", "8b44961e073486188c264e580981bb11");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "favouritesList","purchasesList");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `favouritesList`");
      _db.execSQL("DELETE FROM `purchasesList`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public MyFavourites_DAO myFavouritesDao() {
    if (_myFavouritesDAO != null) {
      return _myFavouritesDAO;
    } else {
      synchronized(this) {
        if(_myFavouritesDAO == null) {
          _myFavouritesDAO = new MyFavourites_DAO_Impl(this);
        }
        return _myFavouritesDAO;
      }
    }
  }

  @Override
  public MyPurchases_DAO myPurchasesDao() {
    if (_myPurchasesDAO != null) {
      return _myPurchasesDAO;
    } else {
      synchronized(this) {
        if(_myPurchasesDAO == null) {
          _myPurchasesDAO = new MyPurchases_DAO_Impl(this);
        }
        return _myPurchasesDAO;
      }
    }
  }
}
