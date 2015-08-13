package linkchecker

import java.util.concurrent.Executor

import com.ning.http.client.AsyncHttpClient
import org.jsoup.Jsoup

import scala.concurrent.{Future, Promise}
import scala.collection.JavaConverters._

object WebClient {
  val client = new AsyncHttpClient

  def get(url: String)(implicit exec: Executor): Future[String] = {
    val f = client.prepareGet(url).execute()
    val p = Promise[String]
    f.addListener(new Runnable {
      override def run(): Unit = {
        val response = f.get
        if (response.getStatusCode < 400)
          p.success(response.getResponseBodyExcerpt(131072))
        else
          p.failure(new IllegalAccessException(s"response : ${response.getStatusCode}"))
      }
    }, exec)
    p.future
  }

  def findLinks(url: String): Iterator[String] = {
    val document = Jsoup.connect(url).get()
    val links = document.select("a[href]")
    for {
      link <- links.iterator().asScala
    } yield link.absUrl("href")
  }
}

