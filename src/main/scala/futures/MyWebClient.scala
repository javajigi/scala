package futures

import java.util.concurrent.Executor

import com.ning.http.client.AsyncHttpClient

import scala.concurrent.{Promise, Future}

case class BadStatus(status: Int) extends RuntimeException

object MyWebClient {
  private val client = new AsyncHttpClient

  def getSync(url: String): String = {
    val response = client.prepareGet(url).execute().get
    if (response.getStatusCode < 400)
      response.getResponseBodyExcerpt(131072)
    else throw BadStatus(response.getStatusCode)
  }

  def getAsync(url: String)(implicit exec: Executor): Future[String] = {
    val f = client.prepareGet(url).execute();
    val p = Promise[String]()
    f.addListener(new Runnable {
      def run = {
        val response = f.get
        if (response.getStatusCode / 100 < 4)
          p.success(response.getResponseBodyExcerpt(131072))
        else p.failure(BadStatus(response.getStatusCode))
      }
    }, exec)
    p.future
  }

  def shutdown(): Unit = client.close()
}
