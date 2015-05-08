package pearls.essay1

import java.io.File

object MemoryAnalzer {
  def main(args: Array[String]): Unit = {
    /* Total number of processors or cores available to the JVM */
    println("Available processors (cores): " + Runtime.getRuntime().availableProcessors());

    /* Total amount of free memory available to the JVM */
    println("Free memory (bytes): " + Runtime.getRuntime().freeMemory());

    /* This will return Long.MAX_VALUE if there is no preset limit */
    var maxMemory = Runtime.getRuntime().maxMemory();
    /* Maximum amount of memory the JVM will attempt to use */
    if (maxMemory == Long.MaxValue) maxMemory = 0 
    println("Maximum memory (bytes): " + maxMemory);

    /* Total memory currently available to the JVM */
    println("Total memory available to JVM (bytes): " + Runtime.getRuntime().totalMemory());

    /* Get a list of all filesystem roots on this system */
    val roots = File.listRoots();
    roots.foreach(
      f => {
        println("File system root: " + f.getAbsolutePath());
        println("Total space (bytes): " + f.getTotalSpace());
        println("Free space (bytes): " + f.getFreeSpace());
        println("Usable space (bytes): " + f.getUsableSpace());       
      })
  }
}