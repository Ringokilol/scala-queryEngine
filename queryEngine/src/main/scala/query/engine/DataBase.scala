package query

import io.getquill.{Literal, PostgresJdbcContext, PostgresAsyncContext}

package object DataBase {
  lazy val ctx = new PostgresJdbcContext(Literal, "ctx")
}