package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(PinterestTagLib)
class PinterestTagLibTests
{
  void test_board_tag()
  {
    assert !applyTemplate('<pinterest:board/>')
    assert !applyTemplate('<pinterest:board account="account"/>')
    assert !applyTemplate('<pinterest:board id="id"/>')
    assert applyTemplate('<pinterest:board account="account" id="id"/>') == '<a data-pin-do="embedBoard" href="http://www.pinterest.com/account/id"></a>'
    assert applyTemplate('<pinterest:board account="account" id="id" width="width" height="height" image="image"/>') == '<a data-pin-board-width="width" data-pin-do="embedBoard" data-pin-scale-height="height" data-pin-scale-width="image" href="http://www.pinterest.com/account/id"></a>'
  }

  void test_follow_button_tag()
  {
    assert !applyTemplate('<pinterest:follow_button/>')
    assert applyTemplate('<pinterest:follow_button account="account"/>') == '<a data-pin-do="buttonFollow" href="http://www.pinterest.com/account">Follow</a>'
    assert applyTemplate('<pinterest:follow_button account="account" label="label"/>') == '<a data-pin-do="buttonFollow" href="http://www.pinterest.com/account">label</a>'
  }

  void test_pin_tag()
  {
    assert !applyTemplate('<pinterest:pin/>')
    assert applyTemplate('<pinterest:pin id="id"/>') == '<a data-pin-do="embedPin" href="http://www.pinterest.com/pin/id"></a>'
  }

  void test_pin_it_button_tag()
  {
    assert !applyTemplate('<pinterest:pin_it_button/>')
    assert !applyTemplate('<pinterest:pin_it_button url="url" image="image"/>')
    assert !applyTemplate('<pinterest:pin_it_button url="url" description="description"/>')
    assert !applyTemplate('<pinterest:pin_it_button image="image" description="description"/>')
    assert applyTemplate('<pinterest:pin_it_button url="url" image="image" description="description"/>') == '<a data-pin-color="gray" data-pin-config="none" data-pin-do="buttonPin" data-pin-height="20" data-pin-lang="en" data-pin-shape="rect" href="http://www.pinterest.com/pin/create/button/?url=url&media=image&description=description"><img src="http://assets.pinterest.com/images/pidgets/pinit_fg_en_rect_gray_20.png"/></a>'
    assert applyTemplate("<pinterest:pin_it_button url=\"url\" image=\"image\" description=\"description\" color=\"color\" counter=\"${PinterestPinItButtonPinCountPosition.ABOVE}\" language=\"language\" size=\"${PinterestPinItButtonSize.LARGE}\"/>") == '<a data-pin-color="color" data-pin-config="above" data-pin-do="buttonPin" data-pin-height="28" data-pin-lang="language" data-pin-shape="rect" href="http://www.pinterest.com/pin/create/button/?url=url&media=image&description=description"><img src="http://assets.pinterest.com/images/pidgets/pinit_fg_language_rect_color_28.png"/></a>'
  }

  void test_profile_tag()
  {
    assert !applyTemplate('<pinterest:profile/>')
    assert applyTemplate('<pinterest:profile account="account"/>') == '<a data-pin-do="embedUser" href="http://www.pinterest.com/account"></a>'
    assert applyTemplate('<pinterest:profile account="account" width="width" height="height" image="image"/>') == '<a data-pin-board-width="width" data-pin-do="embedUser" data-pin-scale-height="height" data-pin-scale-width="image" href="http://www.pinterest.com/account"></a>'
  }
}