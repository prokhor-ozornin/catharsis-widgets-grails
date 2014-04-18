package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(DisqusTagLib)
class DisqusTagLibTests
{
  void test_comments_tag()
  {
    assert !applyTemplate('<disqus:comments/>')
    def html = applyTemplate('<disqus:comments account="account"/>')
    assert html.contains('<div id="disqus_thread"></div>')
    assert html.contains('var disqus_shortname = "account"')
  }
}
