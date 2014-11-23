package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(PinterestTagLib)
class PinterestTagLibSpec extends Specification
{
  void board()
  {
    when :
      String template = applyTemplate('<pinterest:board/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:board account="account"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:board id="id"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:board account="" id="id"/>')
    then :
     template == ''

    when :
      template = applyTemplate('<pinterest:board account=" " id="id"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:board account="account" id=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:board account="account" id=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:board account="account" id="id"/>')
    then :
      template == '<a data-pin-do="embedBoard" href="http://www.pinterest.com/account/id"></a>'

    when :
      template = applyTemplate('<pinterest:board account="account" id="id" width="width" height="height" image="image"/>')
    then :
      template == '<a data-pin-board-width="width" data-pin-do="embedBoard" data-pin-scale-height="height" data-pin-scale-width="image" href="http://www.pinterest.com/account/id"></a>'
  }

  void follow_button()
  {
    when :
      String template = applyTemplate('<pinterest:follow_button/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:follow_button account=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:follow_button account=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:follow_button account="account"/>')
    then :
      template == '<a data-pin-do="buttonFollow" href="http://www.pinterest.com/account">Follow</a>'

    when :
      template = applyTemplate('<pinterest:follow_button account="account" label="label"/>')
    then :
      template == '<a data-pin-do="buttonFollow" href="http://www.pinterest.com/account">label</a>'
  }

  void pin()
  {
    when :
      String template = applyTemplate('<pinterest:pin/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:pin id=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:pin id=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:pin id="id"/>')
    then :
      template == '<a data-pin-do="embedPin" href="http://www.pinterest.com/pin/id"></a>'
  }

  void pin_it_button()
  {
    when :
      String template = applyTemplate('<pinterest:pin_it_button/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:pin_it_button url="url" image="image"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:pin_it_button url="url" description="description"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:pin_it_button image="image" description="description"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:pin_it_button url="" image="image" description="description"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:pin_it_button url=" " image="image" description="description"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:pin_it_button url="url" image="" description="description"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:pin_it_button url="url" image=" " description="description"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:pin_it_button url="url" image="image" description=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:pin_it_button url="url" image="image" description=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:pin_it_button url="url" image="image" description="description"/>')
    then :
      template == '<a data-pin-color="gray" data-pin-config="none" data-pin-do="buttonPin" data-pin-height="20" data-pin-lang="en" data-pin-shape="rect" href="http://www.pinterest.com/pin/create/button?url=url&media=image&description=description"><img src="http://assets.pinterest.com/images/pidgets/pinit_fg_en_rect_gray_20.png"/></a>'

    when :
      template = applyTemplate("<pinterest:pin_it_button url=\"url\" image=\"image\" description=\"description\" color=\"color\" counter=\"${PinterestPinItButtonPinCountPosition.ABOVE}\" language=\"language\" size=\"${PinterestPinItButtonSize.LARGE}\"/>")
    then :
      template == '<a data-pin-color="color" data-pin-config="above" data-pin-do="buttonPin" data-pin-height="28" data-pin-lang="language" data-pin-shape="rect" href="http://www.pinterest.com/pin/create/button?url=url&media=image&description=description"><img src="http://assets.pinterest.com/images/pidgets/pinit_fg_language_rect_color_28.png"/></a>'
  }

  void profile()
  {
    when :
      String template = applyTemplate('<pinterest:profile/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:profile account=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:profile account=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<pinterest:profile account="account"/>')
    then :
      template == '<a data-pin-do="embedUser" href="http://www.pinterest.com/account"></a>'

    when :
      template = applyTemplate('<pinterest:profile account="account" width="width" height="height" image="image"/>')
    then :
      template == '<a data-pin-board-width="width" data-pin-do="embedUser" data-pin-scale-height="height" data-pin-scale-width="image" href="http://www.pinterest.com/account"></a>'
  }
}