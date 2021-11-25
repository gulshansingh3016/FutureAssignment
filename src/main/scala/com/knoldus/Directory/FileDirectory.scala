package com.knoldus.Directory
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import java.io.File
import scala.language.postfixOps

object FileDirectory extends App {

  object Search {

    def fileSubdirectory(path: String): Future[List[String]] = Future {
      def fileSubdirectoryhelper(dir: File, isFileDirectory: Boolean = true): List[File] = {
        val files = dir.listFiles
        files ++
          files
            .filter(_.isDirectory)
            .filter(_ => isFileDirectory)
            .flatMap(fileSubdirectoryhelper(_, isFileDirectory))
      }.toList

      val list: Seq[File] = fileSubdirectoryhelper(new File(path))
      list.asInstanceOf[List[String]]
    }
  }
}
