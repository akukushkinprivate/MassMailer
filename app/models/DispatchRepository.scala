package models

import javax.inject.{Inject, Singleton}

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.MySQLProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class DispatchRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  private val dbConfig = dbConfigProvider.get[MySQLProfile]

  import dbConfig._
  import profile.api._

  private class DispatchStatusTable(tag: Tag) extends Table[DispatchStatus](tag, "dispatch_statuses") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def status = column[String]("status")

    def * = ???
  }

  private val dispatchStatuses = TableQuery[DispatchStatusTable]

  def insertOrUpdate(dispatchStatus: DispatchStatus): Future[Option[DispatchStatus]] = db.run {
    (dispatchStatuses returning dispatchStatuses).insertOrUpdate(dispatchStatus)
  }
}
