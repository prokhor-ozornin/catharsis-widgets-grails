package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(IntenseDebateTagLib)
class IntenseDebateTagLibTests
{
  void testCommentsTag()
  {
    assert !applyTemplate("<intensedebate:comments/>")

    def html = applyTemplate('<intensedebate:comments account="account"/>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('var idcomments_acct = "account";')
    assert html.contains('http://www.intensedebate.com/js/genericCommentWrapperV2.js')
    assert html.contains('var idcomments_post_id = ""')
    assert html.contains('var idcomments_post_url = ""')
    assert html.contains('var idcomments_post_title = ""')

    html = applyTemplate('<intensedebate:comments account="account" postId="postId" postUrl="postUrl" postTitle="postTitle"/>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('var idcomments_acct = "account";')
    assert html.contains('http://www.intensedebate.com/js/genericCommentWrapperV2.js')
    assert html.contains('var idcomments_post_id = "postId"')
    assert html.contains('var idcomments_post_url = "postUrl"')
    assert html.contains('var idcomments_post_title = "postTitle"')
  }

  void testLinkTag()
  {
    assert !applyTemplate("<intensedebate:link/>")

    def html = applyTemplate('<intensedebate:link account="account"/>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('var idcomments_acct = "account";')
    assert html.contains('http://www.intensedebate.com/js/genericLinkWrapperV2.js')
    assert html.contains('var idcomments_post_id = ""')
    assert html.contains('var idcomments_post_url = ""')
    assert html.contains('var idcomments_post_title = ""')

    html = applyTemplate('<intensedebate:link account="account" postId="postId" postUrl="postUrl" postTitle="postTitle"/>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('var idcomments_acct = "account";')
    assert html.contains('http://www.intensedebate.com/js/genericLinkWrapperV2.js')
    assert html.contains('var idcomments_post_id = "postId"')
    assert html.contains('var idcomments_post_url = "postUrl"')
    assert html.contains('var idcomments_post_title = "postTitle"')
  }
}
