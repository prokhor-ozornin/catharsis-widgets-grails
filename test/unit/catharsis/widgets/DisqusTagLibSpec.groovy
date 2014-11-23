package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(DisqusTagLib)
class DisqusTagLibSpec extends Specification
{
  void comments()
  {
    when :
      String template = applyTemplate('<disqus:comments/>')
    then :
      template == ''

    when :
      template = applyTemplate('<disqus:comments account=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<disqus:comments account=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<disqus:comments account="account"/>')
    then :
      template.contains('<div id="disqus_thread"></div>')
      template.contains('var disqus_shortname = "account"')
  }
}