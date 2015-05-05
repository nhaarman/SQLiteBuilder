/*
 *  Copyright 2015 Niek Haarman
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.nhaarman.sqlitebuilder.android;

import android.database.sqlite.SQLiteDatabase;
import com.nhaarman.sqlitebuilder.FinishedQuery;
import com.nhaarman.sqlitebuilder.FinishedStatement;
import com.nhaarman.sqlitebuilder.SqlPart;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class AndroidStatementExecutorTest {

  private AndroidStatementExecutor mStatementExecutor;
  private SQLiteDatabase mDatabaseMock;

  @Before
  public void setUp() {
    mDatabaseMock = mock(SQLiteDatabase.class);
    mStatementExecutor = new AndroidStatementExecutor(mDatabaseMock);
  }

  @Test
  public void executeStatement_delegatesToDatabase() {
    /* Given */
    FinishedStatement statement = mock(FinishedStatement.class);
    when(statement.iterator()).thenReturn(new SqlPartIterator());

    /* When */
    mStatementExecutor.execute(statement);

    /* Then */
    verify(mDatabaseMock).execSQL(anyString(), any(Object[].class));
  }


  @Test
  public void executeQuery_delegatesToDatabase() {
    /* Given */
    FinishedQuery query = mock(FinishedQuery.class);
    when(query.iterator()).thenReturn(new SqlPartIterator());

    /* When */
    mStatementExecutor.executeQuery(query);

    /* Then */
    verify(mDatabaseMock).rawQuery(anyString(), any(String[].class));
  }

  private static class SqlPartIterator implements Iterator<SqlPart> {

    @Override
    public boolean hasNext() {
      return false;
    }

    @Override
    public SqlPart next() {
      throw new NoSuchElementException("");
    }

    @Override
    public void remove() {

    }
  }
}
