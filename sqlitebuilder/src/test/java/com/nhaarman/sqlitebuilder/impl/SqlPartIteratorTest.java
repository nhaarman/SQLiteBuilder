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
 *   limitations under the License.
 */

package com.nhaarman.sqlitebuilder.impl;

import com.nhaarman.sqlitebuilder.SqlPart;
import com.nhaarman.sqlitebuilder.impl.SqlPartIterator;
import java.util.NoSuchElementException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class SqlPartIteratorTest {

  @Test
  public void immediateHasNext_returnsTrue() {
    /* Given */
    SqlPartIterator iterator = new SqlPartIterator(mock(SqlPart.class));

    /* When */
    boolean result = iterator.hasNext();

    /* Then */
    assertThat(result, is(true));
  }

  @Test
  public void immediateNext_returnsFirstItem() {
     /* Given */
    SqlPart sqlPart = mock(SqlPart.class);
    SqlPartIterator iterator = new SqlPartIterator(sqlPart);

    /* When */
    SqlPart result = iterator.next();

    /* Then */
    assertThat(result, is(sqlPart));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void remove_isUnsupported() {
   /* Given */
    SqlPartIterator iterator = new SqlPartIterator(mock(SqlPart.class));

    /* When */
    iterator.remove();
  }

  @Test(expected = NoSuchElementException.class)
  public void nextWhenThereIsNoNext_throwsException() {
    /* Given */
    SqlPartIterator iterator = new SqlPartIterator(mock(SqlPart.class));
    iterator.next();

    /* When */
    iterator.next();
  }

  @Test
  public void nextWhenThereIsANext_returnsProperItem() {
    /* Given */
    SqlPart first = mock(SqlPart.class);
    SqlPart second = mock(SqlPart.class);
    when(first.previous()).thenReturn(second);

    SqlPartIterator iterator = new SqlPartIterator(first);
    iterator.next();

    /* When */
    SqlPart result = iterator.next();

    /* Then */
    assertThat(result, is(second));
  }
}