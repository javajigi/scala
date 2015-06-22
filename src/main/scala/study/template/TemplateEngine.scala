package study.template

object TemplateEngine {
  private val pattern = "\\$\\{(.+?)\\}".r

  def process(template: String, params: Map[String, Any]) = {
    var replacedTemplate = template
    val iterator = pattern findAllMatchIn template
    iterator.foreach(m => {
      replacedTemplate = replacedTemplate.replace(m.group(0), params(m.group(1)) + "")
    })
    replacedTemplate
  }
}