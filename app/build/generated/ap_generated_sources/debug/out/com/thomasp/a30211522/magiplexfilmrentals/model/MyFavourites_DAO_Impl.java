package com.thomasp.a30211522.magiplexfilmrentals.model;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MyFavourites_DAO_Impl implements MyFavourites_DAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MyFavourites_Data> __insertionAdapterOfMyFavourites_Data;

  private final EntityDeletionOrUpdateAdapter<MyFavourites_Data> __deletionAdapterOfMyFavourites_Data;

  public MyFavourites_DAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMyFavourites_Data = new EntityInsertionAdapter<MyFavourites_Data>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `favouritesList` (`FilmID`,`FilmTitle`,`FilmGenre`,`FilmDescription`,`FilmUserRating`,`FilmCertificate`,`FilmThumbnailURL`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MyFavourites_Data value) {
        stmt.bindLong(1, value.getFilmID());
        if (value.getFilmTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFilmTitle());
        }
        if (value.getFilmGenre() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFilmGenre());
        }
        if (value.getFilmDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFilmDescription());
        }
        if (value.getFilmUserRating() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getFilmUserRating());
        }
        if (value.getFilmCertificate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getFilmCertificate());
        }
        if (value.getFilmThumbnailURL() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getFilmThumbnailURL());
        }
      }
    };
    this.__deletionAdapterOfMyFavourites_Data = new EntityDeletionOrUpdateAdapter<MyFavourites_Data>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `favouritesList` WHERE `FilmID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MyFavourites_Data value) {
        stmt.bindLong(1, value.getFilmID());
      }
    };
  }

  @Override
  public void addData(final MyFavourites_Data myFavouritesData) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMyFavourites_Data.insert(myFavouritesData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final MyFavourites_Data myFavouritesData) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfMyFavourites_Data.handle(myFavouritesData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<MyFavourites_Data> getMyFavouritesData() {
    final String _sql = "select * from favouritesList";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfFilmID = CursorUtil.getColumnIndexOrThrow(_cursor, "FilmID");
      final int _cursorIndexOfFilmTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "FilmTitle");
      final int _cursorIndexOfFilmGenre = CursorUtil.getColumnIndexOrThrow(_cursor, "FilmGenre");
      final int _cursorIndexOfFilmDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "FilmDescription");
      final int _cursorIndexOfFilmUserRating = CursorUtil.getColumnIndexOrThrow(_cursor, "FilmUserRating");
      final int _cursorIndexOfFilmCertificate = CursorUtil.getColumnIndexOrThrow(_cursor, "FilmCertificate");
      final int _cursorIndexOfFilmThumbnailURL = CursorUtil.getColumnIndexOrThrow(_cursor, "FilmThumbnailURL");
      final List<MyFavourites_Data> _result = new ArrayList<MyFavourites_Data>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MyFavourites_Data _item;
        _item = new MyFavourites_Data();
        final int _tmpFilmID;
        _tmpFilmID = _cursor.getInt(_cursorIndexOfFilmID);
        _item.setFilmID(_tmpFilmID);
        final String _tmpFilmTitle;
        _tmpFilmTitle = _cursor.getString(_cursorIndexOfFilmTitle);
        _item.setFilmTitle(_tmpFilmTitle);
        final String _tmpFilmGenre;
        _tmpFilmGenre = _cursor.getString(_cursorIndexOfFilmGenre);
        _item.setFilmGenre(_tmpFilmGenre);
        final String _tmpFilmDescription;
        _tmpFilmDescription = _cursor.getString(_cursorIndexOfFilmDescription);
        _item.setFilmDescription(_tmpFilmDescription);
        final String _tmpFilmUserRating;
        _tmpFilmUserRating = _cursor.getString(_cursorIndexOfFilmUserRating);
        _item.setFilmUserRating(_tmpFilmUserRating);
        final String _tmpFilmCertificate;
        _tmpFilmCertificate = _cursor.getString(_cursorIndexOfFilmCertificate);
        _item.setFilmCertificate(_tmpFilmCertificate);
        final String _tmpFilmThumbnailURL;
        _tmpFilmThumbnailURL = _cursor.getString(_cursorIndexOfFilmThumbnailURL);
        _item.setFilmThumbnailURL(_tmpFilmThumbnailURL);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int isFavourite(final int FilmID) {
    final String _sql = "SELECT EXISTS (SELECT 1 FROM favouritesList WHERE FilmID=?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, FilmID);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
