package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(IntenseDebateTagLib)
class IntenseDebateTagLibTests
{
  void test_comments_tag()
  {
    assert !applyTemplate("<intensedebate:comments/>")

    def html = applyTemplate('<intensedebate:comments account="account"/>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('var idcomments_acct = "account";')
    assert html.contains('http://www.intensedebate.com/js/genericCommentWrapperV2.js')
    assert html.contains('var idcomments_post_id = ""')
    assert html.contains('var idcomments_post_url = ""')
    assert html.contains('var idcomments_post_title = ""')

    html = applyTemplate('<intensedebate:comments account="account" post_id="post_id" post_url="post_url" post_title="post_title"/>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('var idcomments_acct = "account";')
    assert html.contains('http://www.intensedebate.com/js/genericCommentWrapperV2.js')
    assert html.contains('var idcomments_post_id = "post_id"')
    assert html.contains('var idcomments_post_url = "post_url"')
    assert html.contains('var idcomments_post_title = "post_title"')
  }

  void test_link_tag()
  {
    assert !applyTemplate("<intensedebate:link/>")

    def html = applyTemplate('<intensedebate:link account="account"/>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('var idcomments_acct = "account";')
    assert html.contains('http://www.intensedebate.com/js/genericLinkWrapperV2.js')
    assert html.contains('var idcomments_post_id = ""')
    assert html.contains('var idcomments_post_url = ""')
    assert html.contains('var idcomments_post_title = ""')

    html = applyTemplate('<intensedebate:link account="account" post_id="post_id" post_url="post_url" post_title="post_title"/>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('var idcomments_acct = "account";')
    assert html.contains('http://www.intensedebate.com/js/genericLinkWrapperV2.js')
    assert html.contains('var idcomments_post_id = "post_id"')
    assert html.contains('var idcomments_post_url = "post_url"')
    assert html.contains('var idcomments_post_title = "post_title"')
  }
}
