package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(SurfingbirdTagLib)
class SurfingbirdTagLibTests
{
  void test_surf_tag()
  {
    assert applyTemplate('<surfingbird:surf_button/>') == "<a target=\"_blank\" class=\"surfinbird__like_button\" href=\"http://surfingbird.ru/share\" data-surf-config=\"{${'"layout":"common-nocount"'.encodeAsHTML()}}\">Surf</a>"
    assert applyTemplate("<surfingbird:surf_button color=\"blue\" counter=\"true\" label=\"Share\" url=\"url\" layout=\"${SurfingbirdSurfButtonLayout.COMMON}\" width=\"width\" height=\"height\"/>") == "<a target=\"_blank\" class=\"surfinbird__like_button\" href=\"http://surfingbird.ru/share\" data-surf-config=\"{${'"layout":"common-blue","url":"url","width":"width","height":"height"'.encodeAsHTML()}}\">Share</a>"
  }
}
