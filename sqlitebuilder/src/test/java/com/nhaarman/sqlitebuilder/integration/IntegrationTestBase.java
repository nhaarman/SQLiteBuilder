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

package com.nhaarman.sqlitebuilder.integration;

import org.junit.Before;

import static org.mockito.AdditionalMatchers.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class IntegrationTestBase {

  private TestStatementExecutor mStatementExecutor;

  private DummyDatabase mDatabaseMock;

  @Before
  public void setUp() {
    mDatabaseMock = mock(DummyDatabase.class);
    mStatementExecutor = new TestStatementExecutor(mDatabaseMock);
  }

  void verifySelectStatementExecuted(final String sql, final Object... arguments) {
    String[] strings = new String[arguments.length];
    for (int i = 0; i < arguments.length; i++) {
      Object argument = arguments[i];
      strings[i] = String.valueOf(argument);
    }

    verify(mDatabaseMock).executeSelectStatement(eq(sql), aryEq(strings));
  }

  void verifyStatementExecuted(final String sql, final Object... arguments) {
    verify(mDatabaseMock).executeStatement(eq(sql), aryEq(arguments));
  }

  TestStatementExecutor getStatementExecutor() {
    return mStatementExecutor;
  }
}
