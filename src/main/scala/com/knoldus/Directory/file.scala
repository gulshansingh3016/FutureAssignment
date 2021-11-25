package com.knoldus.Directory

object file extends App {
  import java.io.IOException
  import java.nio.file.Files
  import java.nio.file.Path
  import java.nio.file.Paths
  import java.util
  import java.util.stream.Collectors
  import java.util.stream.Stream


  object FilesWalkExample1 {
    @throws[IOException]
    def main(args: Array[String]): Unit = {
      val path = Paths.get("./src")
      val paths = listFiles(path)
      paths.forEach((x: Path) => System.out.println(x))
    }

    // list all files from this path
    @throws[IOException]
    def listFiles(path: Path): util.List[Path] = {
      var result = null
      try {
        val walk = Files.walk(path)
        try result = walk.filter(Files.isRegularFile).collect(Collectors.toList)
        finally if (walk != null) walk.close()
      }
      result
    }
  }

}
