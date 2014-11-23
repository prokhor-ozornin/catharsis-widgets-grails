package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(IntenseDebateTagLib)
class IntenseDebateTagLibSpec extends Specification
{
  void comments()
  {
    when :
      String template = applyTemplate("<intensedebate:comments/>")
    then :
      template == ''

    when :
      template = applyTemplate('<intensedebate:comments account=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<intensedebate:comments account=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<intensedebate:comments account="account"/>')
    then :
      template.contains('<script type="text/javascript">')
      template.contains('var idcomments_acct = "account";')
      template.contains('http://www.intensedebate.com/js/genericCommentWrapperV2.js')
      template.contains('var idcomments_post_id = ""')
      template.contains('var idcomments_post_url = ""')
      template.contains('var idcomments_post_title = ""')

    when :
      template = applyTemplate('<intensedebate:comments account="account" post_id="post_id" post_url="post_url" post_title="post_title"/>')
    then :
      template.contains('<script type="text/javascript">')
      template.contains('var idcomments_acct = "account";')
      template.contains('http://www.intensedebate.com/js/genericCommentWrapperV2.js')
      template.contains('var idcomments_post_id = "post_id"')
      template.contains('var idcomments_post_url = "post_url"')
      template.contains('var idcomments_post_title = "post_title"')
  }

  void link()
  {
    when :
      String template = applyTemplate("<intensedebate:link/>")
    then :
      template == ''

    when :
      template = applyTemplate('<intensedebate:link account=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<intensedebate:link account=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<intensedebate:link account="account"/>')
    then :
      template.contains('<script type="text/javascript">')
      template.contains('var idcomments_acct = "account";')
      template.contains('http://www.intensedebate.com/js/genericLinkWrapperV2.js')
      template.contains('var idcomments_post_id = ""')
      template.contains('var idcomments_post_url = ""')
      template.contains('var idcomments_post_title = ""')

    when :
      template = applyTemplate('<intensedebate:link account="account" post_id="post_id" post_url="post_url" post_title="post_title"/>')
    then :
      template.contains('<script type="text/javascript">')
      template.contains('var idcomments_acct = "account";')
      template.contains('http://www.intensedebate.com/js/genericLinkWrapperV2.js')
      template.contains('var idcomments_post_id = "post_id"')
      template.contains('var idcomments_post_url = "post_url"')
      template.contains('var idcomments_post_title = "post_title"')
  }
}