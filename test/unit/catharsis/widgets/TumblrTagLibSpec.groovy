package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(TumblrTagLib)
class TumblrTagLibSpec extends Specification
{
  void follow()
  {
    when :
      String template = applyTemplate('<tumblr:follow_button/>')
    then :
      template == ''

    when :
      template = applyTemplate('<tumblr:follow_button account=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<tumblr:follow_button account=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<tumblr:follow_button account="account"/>')
    then :
      template == '<iframe class="btn" border="0" allowtransparency="true" frameborder="0" height="25" width="189" scrolling="no" src="http://platform.tumblr.com/v1/follow_button.html?button_type=1&tumblelog=account&color_scheme=light"></iframe>'

    when :
      template = applyTemplate("<tumblr:follow_button account=\"account\" type=\"${TumblrFollowButtonType.SECOND}\" color_scheme=\"${TumblrFollowButtonColorScheme.DARK}\"/>")
    then :
      template == '<iframe class="btn" border="0" allowtransparency="true" frameborder="0" height="25" width="113" scrolling="no" src="http://platform.tumblr.com/v1/follow_button.html?button_type=2&tumblelog=account&color_scheme=dark"></iframe>'
  }

  void share()
  {
    when :
      String template = applyTemplate('<tumblr:share_button/>')
    then :
      template == '<a href="http://www.tumblr.com/share" title="Share on Tumblr" style="display:inline-block; text-indent:-9999px; overflow:hidden; width:80px; height:20px; background:url(\'http://platform.tumblr.com/v1/share_1.png\') top left no-repeat transparent;">Share on Tumblr</a>'

    when :
      template = applyTemplate("<tumblr:share_button type=\"${TumblrShareButtonType.SECOND}\" color_scheme=\"${TumblrShareButtonColorScheme.GRAY}\"/>")
    then :
      template == '<a href="http://www.tumblr.com/share" title="Share on Tumblr" style="display:inline-block; text-indent:-9999px; overflow:hidden; width:70px; height:20px; background:url(\'http://platform.tumblr.com/v1/share_2T.png\') top left no-repeat transparent;">Share on Tumblr</a>'
  }
}