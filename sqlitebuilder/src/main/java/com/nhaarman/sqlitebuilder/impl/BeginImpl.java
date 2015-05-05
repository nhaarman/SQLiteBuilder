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

import com.nhaarman.sqlitebuilder.Begin;
import com.nhaarman.sqlitebuilder.Deferred;
import com.nhaarman.sqlitebuilder.Exclusive;
import com.nhaarman.sqlitebuilder.Immediate;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import com.nhaarman.sqlitebuilder.Transaction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class BeginImpl extends BaseSqlPart implements Begin {

  @NotNull
  @Override
  public Transaction transaction() {
    return new TransactionImpl(this);
  }

  @NotNull
  @Override
  public Deferred deferred() {
    return new DeferredImpl(this);
  }

  @NotNull
  @Override
  public Immediate immediate() {
    return new ImmediateImpl(this);
  }

  @NotNull
  @Override
  public Exclusive exclusive() {
    return new ExclusiveImpl(this);
  }

  @Override
  public void prependTo(@NotNull final RawSqlBuilder builder) {
    builder.prepend("BEGIN");
  }

  @Nullable
  @Override
  public SqlPart previous() {
    return null;
  }
}
