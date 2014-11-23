package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(GoogleTagLib)
class GoogleTagLibSpec extends Specification
{
  void analytics()
  {
    when :
      String template = applyTemplate('<google:analytics/>')
    then :
      template == ''

    when :
      template = applyTemplate('<google:analytics account="account"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<google:analytics domain="domain"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<google:analytics account="" domain="domain"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<google:analytics account=" " domain="domain"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<google:analytics account="account" domain=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<google:analytics account="account" domain=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<google:analytics account="account" domain="domain"/>')
    then :
      template.contains('ga("create", "account", "domain");')
  }

  void plus_one_button()
  {
    when :
      String template = applyTemplate('<google:plus_one_button/>')
    then :
      template == '<g:plusone></g:plusone>'

    when :
      template = applyTemplate("<google:plus_one_button url=\"url\" size=\"${GooglePlusOneButtonSize.SMALL}\" annotation=\"${GooglePlusOneButtonAnnotation.NONE}\" width=\"width\" align=\"${GooglePlusOneButtonAlign.LEFT}\" callback=\"callback\" recommendations=\"true\"/>")
    then :
      template == '<g:plusone href="url" size="small" annotation="none" width="width" align="left" data-callback="callback" data-recommendations="true"></g:plusone>'
  }
}