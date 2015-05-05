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

package com.nhaarman.sqlitebuilder.impl;

import com.nhaarman.sqlitebuilder.FinishedQuery;
import com.nhaarman.sqlitebuilder.SqlPart;
import com.nhaarman.sqlitebuilder.StatementExecutor;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;

abstract class BaseFinishedQuery extends BaseSqlPart implements FinishedQuery {

  @NotNull
  @Override
  public final Iterator<SqlPart> iterator() {
    return new SqlPartIterator(this);
  }

  @Override
  public <T> T executeOn(@NotNull final StatementExecutor<T> statementExecutor) {
    return statementExecutor.executeQuery(this);
  }
}
