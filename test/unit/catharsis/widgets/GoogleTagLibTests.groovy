package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(GoogleTagLib)
class GoogleTagLibTests
{
  void testAnalyticsTag()
  {
    assert !applyTemplate('<google:analytics/>')
    assert !applyTemplate('<google:analytics account="account"/>')
    assert !applyTemplate('<google:analytics domain="domain"/>')

    def html = applyTemplate('<google:analytics account="account" domain="domain"/>')
    assert html.contains('ga("create", "account", "domain");')
  }

  void testPlusoneTag()
  {
    assert applyTemplate('<google:plusone/>') == '<g:plusone></g:plusone>'
    assert applyTemplate("<google:plusone url=\"url\" size=\"${GooglePlusOneButtonSize.SMALL}\" annotation=\"${GooglePlusOneButtonAnnotation.NONE}\" width=\"width\" align=\"${GooglePlusOneButtonAlign.LEFT}\" callback=\"callback\" recommendations=\"true\"/>") == '<g:plusone href="url" size="small" annotation="none" width="width" align="left" data-callback="callback" data-recommendations="true"></g:plusone>'
  }
}
