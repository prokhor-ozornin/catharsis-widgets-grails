package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(FacebookTagLib)
class FacebookTagLibSpec extends Specification
{
  void activity_feed()
  {
    when :
      String template = applyTemplate('<facebook:activity_feed/>')
    then :
      template == '<div class="fb-activity"></div>'

    when :
      template = applyTemplate('<facebook:activity_feed domain="domain" app_id="app_id" actions="actions" width="width" height="height" color_scheme="color_scheme" header="true" link_target="link_target" max_age="1" recommendations="true" track_label="track_label"/>')
    then :
      template == '<div class="fb-activity" data-site="domain" data-app-id="app_id" data-action="actions" data-width="width" data-height="height" data-colorscheme="color_scheme" data-header="true" data-linktarget="link_target" data-max-age="1" data-recommendations="true" data-ref="track_label"></div>'
  }

  void comments()
  {
    when :
      String template = applyTemplate('<facebook:comments/>')
    then :
      template == '<div class="fb-comments"></div>'

    when :
      template = applyTemplate("<facebook:comments url=\"url\" posts=\"1\" width=\"width\" color_scheme=\"${FacebookColorScheme.DARK}\" mobile=\"true\" order=\"${FacebookCommentsOrder.REVERSE_TIME}\"/>")
    then :
      template == '<div class="fb-comments" data-href="url" data-num-posts="1" data-width="width" data-colorscheme="dark" data-mobile="true" data-order-by="reverse_time"></div>'
  }

  void facepile()
  {
    when :
      String template = applyTemplate('<facebook:facepile/>')
    then :
      template == "<div class=\"fb-facepile\"></div>"

    when :
      template = applyTemplate("<facebook:facepile url=\"url\" actions=\"actions\" photo_size=\"${FacebookFacepilePhotoSize.LARGE}\" width=\"width\" height=\"height\" max_rows=\"10\" color_scheme=\"${FacebookColorScheme.DARK}\"/>")
    then :
      template == '<div class="fb-facepile" data-href="url" data-action="actions" data-size="large" data-width="width" data-height="height" data-max-rows="10" data-colorscheme="dark"></div>'
  }

  void follow_button()
  {
    when :
      String template = applyTemplate('<facebook:follow_button/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:follow_button url="url"/>')
    then :
      template == '<div class="fb-follow" data-href="url"></div>'

    when :
      template = applyTemplate("<facebook:follow_button color_scheme=\"${FacebookColorScheme.DARK}\" url=\"url\" kids_mode=\"true\" layout=\"${FacebookButtonLayout.BOX_COUNT}\" faces=\"true\" width=\"width\" height=\"height\"/>")
    then :
      template == '<div class="fb-follow" data-layout="box_count" data-show-faces="true" data-href="url" data-colorscheme="dark" data-kid-directed-site="true" data-width="width" data-height="height"></div>'
  }

  void initialize()
  {
    when :
      String template = applyTemplate('<facebook:initialize/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:initialize app_id=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:initialize app_id=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:initialize app_id="app_id"/>')
    then :
      template.contains('<div id="fb-root"></div>')
      template.contains('//connect.facebook.net/en_US/all.js#xfbml=1&appId=app_id')
  }

  void like_box()
  {
    when :
      String template = applyTemplate('<facebook:like_box/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:like_box url="https://www.facebook.com/pages/Clear-Words/515749945120070"/>')
    then :
      template == '<div class="fb-like-box" data-href="https://www.facebook.com/pages/Clear-Words/515749945120070"></div>'

    when :
      template = applyTemplate("<facebook:like_box url=\"https://www.facebook.com/pages/Clear-Words/515749945120070\" width=\"width\" height=\"height\" color_scheme=\"${FacebookColorScheme.DARK}\" wall=\"true\" header=\"true\" border=\"true\" faces=\"true\" stream=\"true\"/>")
    then :
      template == '<div class="fb-like-box" data-href="https://www.facebook.com/pages/Clear-Words/515749945120070" data-width="width" data-height="height" data-colorscheme="dark" data-force-wall="true" data-header="true" data-show-border="true" data-show-faces="true" data-stream="true"></div>'
  }

  void like_button()
  {
    when :
      String template = applyTemplate('<facebook:like_button/>')
    then :
      template == "<div class=\"fb-like\"></div>"

    when :
      template = applyTemplate("<facebook:like_button verb=\"${FacebookLikeButtonVerb.RECOMMEND}\" color_scheme=\"${FacebookColorScheme.DARK}\" url=\"url\" kids_mode=\"true\" layout=\"${FacebookButtonLayout.BOX_COUNT}\" track_label=\"track_label\" faces=\"true\" width=\"width\"/>")
    then :
      template == '<div class="fb-like" data-action="recommend" data-layout="box_count" data-show-faces="true" data-href="url" data-colorscheme="dark" data-kid-directed-site="true" data-ref="track_label" data-width="width"></div>'
  }

  void post()
  {
    when :
      String template = applyTemplate('<facebook:post/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:post url=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:post url=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:post url="url" width="width"/>')
    then :
      template == '<div class="fb-post" data-href="url" data-width="width"></div>'
  }

  void recommendations_feed()
  {
    when :
      String template = applyTemplate('<facebook:recommendations_feed/>')
    then :
      template == '<div class="fb-recommendations"></div>'

    when :
      template = applyTemplate('<facebook:recommendations_feed domain="domain" app_id="app_id" actions="actions" width="width" height="height" color_scheme="color_scheme" header="true" link_target="link_target" max_age="1" track_label="track_label"/>')
    then :
      template == '<div class="fb-recommendations" data-site="domain" data-app-id="app_id" data-action="actions" data-width="width" data-height="height" data-colorscheme="color_scheme" data-header="true" data-linktarget="link_target" data-max-age="1" data-ref="track_label"></div>'
  }

  void send_button()
  {
    when :
      String template = applyTemplate('<facebook:send_button/>')
    then :
      template == '<div class="fb-send"></div>'

    when :
      template = applyTemplate("<facebook:send_button url=\"url\" color_scheme=\"${FacebookColorScheme.DARK}\" kids_mode=\"true\" width=\"width\" height=\"height\"/>")
    then :
      template == '<div class="fb-send" data-href="url" data-colorscheme="dark" data-kid-directed-site="true" data-width="width" data-height="height"></div>'
  }

  void video()
  {
    when :
      String template = applyTemplate('<facebook:video/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:video id="id" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:video id="id" width="width"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:video height="height" width="width"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:video id="" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:video id=" " width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:video id="id" width="" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:video id="id" width=" " height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:video id="id" width="width" height=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:video id="id" width="width" height=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<facebook:video id="id" width="width" height="height"/>')
    then :
      template == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://www.facebook.com/video/embed?video_id=id"></iframe>'
  }
}