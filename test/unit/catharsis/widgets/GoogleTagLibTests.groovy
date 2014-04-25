package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(GoogleTagLib)
class GoogleTagLibTests
{
  void test_analytics_tag()
  {
    assert !applyTemplate('<google:analytics/>')
    assert !applyTemplate('<google:analytics account="account"/>')
    assert !applyTemplate('<google:analytics domain="domain"/>')

    def html = applyTemplate('<google:analytics account="account" domain="domain"/>')
    assert html.contains('ga("create", "account", "domain");')
  }

  /*void test_map_tag()
  {

  }*/

  void test_plus_one_button_tag()
  {
    assert applyTemplate('<google:plus_one_button/>') == '<g:plusone></g:plusone>'
    assert applyTemplate("<google:plus_one_button url=\"url\" size=\"${GooglePlusOneButtonSize.SMALL}\" annotation=\"${GooglePlusOneButtonAnnotation.NONE}\" width=\"width\" align=\"${GooglePlusOneButtonAlign.LEFT}\" callback=\"callback\" recommendations=\"true\"/>") == '<g:plusone href="url" size="small" annotation="none" width="width" align="left" data-callback="callback" data-recommendations="true"></g:plusone>'
  }
}
