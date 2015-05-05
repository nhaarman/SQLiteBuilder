SQLiteBuilder for Java 
=======================
[![Build Status](https://travis-ci.org/nhaarman/SQLiteBuilder.svg?branch=master)](https://travis-ci.org/nhaarman/SQLiteBuilder)
[![Download](https://api.bintray.com/packages/nhaarman/maven/SQLiteBuilder/images/download.svg) ](https://bintray.com/nhaarman/maven/SQLiteBuilder/_latestVersion)
[![Coverage Status](https://coveralls.io/repos/nhaarman/SQLiteBuilder/badge.svg?branch=master)](https://coveralls.io/r/nhaarman/SQLiteBuilder?branch=master)

A SQLite statement builder for Java.

Example:

```java
select("a", "b")
    .from("my_table")
    .where("a=? AND b=?", 1, 2)
    .groupBy("a")
    .orderBy("a", "b")
    .limit(5)
    .executeOn(myStatementExecutor);
``` 

Output:

```
SELECT a,b FROM my_table WHERE a=? AND b=? GROUP BY a ORDER BY a,b LIMIT 5
```

Setup
=====

Add one of the following to your `build.gradle`, replacing `x.x.x` with the latest version:

```groovy
compile 'com.nhaarman:sqlitebuilder:x.x.x'
compile 'com.nhaarman:sqlitebuilder-android:x.x.x'
```

License
=======

    Copyright 2015 Niek Haarman

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.