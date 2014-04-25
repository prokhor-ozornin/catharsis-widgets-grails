package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(FacebookTagLib)
class FacebookTagLibTests
{
  void test_initialize_tag()
  {
    assert !applyTemplate('<facebook:initialize/>')

    /*def html = applyTemplate('<facebook:initialize appId="appId"/>');
    assert html.contains('<div id="fb-root"></div>')
    assert html.contains('//connect.facebook.net/en_US/all.js#xfbml=1&appId=appId')*/
  }

  void test_activity_feed_tag()
  {
    assert applyTemplate('<facebook:activity_feed/>') == '<div class="fb-activity"></div>'
    assert applyTemplate('<facebook:activity_feed domain="domain" app_id="app_id" actions="actions" width="width" height="height" color_scheme="color_scheme" header="true" link_target="link_target" max_age="1" recommendations="true" track_label="track_label"/>') == '<div class="fb-activity" data-site="domain" data-app-id="app_id" data-action="actions" data-width="width" data-height="height" data-colorscheme="color_scheme" data-header="true" data-linktarget="link_target" data-max-age="1" data-recommendations="true" data-ref="track_label"></div>'
  }

  void test_recommendations_feed_tag()
  {
    assert applyTemplate('<facebook:recommendations_feed/>') == '<div class="fb-recommendations"></div>'
    assert applyTemplate('<facebook:recommendations_feed domain="domain" app_id="app_id" actions="actions" width="width" height="height" color_scheme="color_scheme" header="true" link_target="link_target" max_age="1" track_label="track_label"/>') == '<div class="fb-recommendations" data-site="domain" data-app-id="app_id" data-action="actions" data-width="width" data-height="height" data-colorscheme="color_scheme" data-header="true" data-linktarget="link_target" data-max-age="1" data-ref="track_label"></div>'
  }

  void test_comments_tag()
  {
    assert applyTemplate('<facebook:comments/>') == '<div class="fb-comments"></div>'
    assert applyTemplate("<facebook:comments url=\"url\" posts=\"1\" width=\"width\" color_scheme=\"${FacebookColorScheme.DARK}\" mobile=\"true\" order=\"${FacebookCommentsOrder.REVERSE_TIME}\"/>") == '<div class="fb-comments" data-href="url" data-num-posts="1" data-width="width" data-colorscheme="dark" data-mobile="true" data-order-by="reverse_time"></div>'
  }

  void test_facepile_tag()
  {
    assert applyTemplate('<facebook:facepile/>') == "<div class=\"fb-facepile\" data-href=\"${request.requestURL}\"></div>"
    assert applyTemplate("<facebook:facepile url=\"url\" actions=\"actions\" photo_size=\"${FacebookFacepilePhotoSize.LARGE}\" width=\"width\" height=\"height\" max_rows=\"10\" color_scheme=\"${FacebookColorScheme.DARK}\"/>") == '<div class="fb-facepile" data-href="url" data-action="actions" data-size="large" data-width="width" data-height="height" data-max-rows="10" data-colorscheme="dark"></div>'
  }

  void test_follow_button_tag()
  {
    assert !applyTemplate('<facebook:follow_button/>')
    assert applyTemplate('<facebook:follow_button url="url"/>') == '<div class="fb-follow" data-href="url"></div>'
    assert applyTemplate("<facebook:follow_button color_scheme=\"${FacebookColorScheme.DARK}\" url=\"url\" kids_mode=\"true\" layout=\"${FacebookButtonLayout.BOX_COUNT}\" faces=\"true\" width=\"width\" height=\"height\"/>") == '<div class="fb-follow" data-layout="box_count" data-show-faces="true" data-href="url" data-colorscheme="dark" data-kid-directed-site="true" data-width="width" data-height="height"></div>'
  }

  void test_like_button_tag()
  {
    assert applyTemplate('<facebook:like_button/>') == "<div class=\"fb-like\" data-href=\"${request.requestURL}\"></div>"
    assert applyTemplate("<facebook:like_button verb=\"${FacebookLikeButtonVerb.RECOMMEND}\" color_scheme=\"${FacebookColorScheme.DARK}\" url=\"url\" kids_mode=\"true\" layout=\"${FacebookButtonLayout.BOX_COUNT}\" track_label=\"track_label\" faces=\"true\" width=\"width\"/>") == '<div class="fb-like" data-action="recommend" data-layout="box_count" data-show-faces="true" data-href="url" data-colorscheme="dark" data-kid-directed-site="true" data-ref="track_label" data-width="width"></div>'
  }

  void test_like_box_tag()
  {
    assert !applyTemplate('<facebook:like_box/>')
    assert applyTemplate('<facebook:like_box url="https://www.facebook.com/pages/Clear-Words/515749945120070"/>') == '<div class="fb-like-box" data-href="https://www.facebook.com/pages/Clear-Words/515749945120070"></div>'
    assert applyTemplate("<facebook:like_box url=\"https://www.facebook.com/pages/Clear-Words/515749945120070\" width=\"width\" height=\"height\" color_scheme=\"${FacebookColorScheme.DARK}\" wall=\"true\" header=\"true\" border=\"true\" faces=\"true\" stream=\"true\"/>") == '<div class="fb-like-box" data-href="https://www.facebook.com/pages/Clear-Words/515749945120070" data-width="width" data-height="height" data-colorscheme="dark" data-force-wall="true" data-header="true" data-show-border="true" data-show-faces="true" data-stream="true"></div>'
  }

  void test_send_button_tag()
  {
    assert applyTemplate('<facebook:send_button/>') == '<div class="fb-send"></div>'
    assert applyTemplate("<facebook:send_button url=\"url\" color_scheme=\"${FacebookColorScheme.DARK}\" kids_mode=\"true\" width=\"width\" height=\"height\"/>") == '<div class="fb-send" data-href="url" data-colorscheme="dark" data-kid-directed-site="true" data-width="width" data-height="height"></div>'
  }

  void test_post_tag()
  {
    assert !applyTemplate('<facebook:post/>')
    assert applyTemplate('<facebook:post url="url" width="width"/>') == '<div class="fb-post" data-href="url" data-width="width"></div>'
  }

  void test_video_tag()
  {
    assert !applyTemplate('<facebook:video/>')
    assert !applyTemplate('<facebook:video id="id" height="height"/>')
    assert !applyTemplate('<facebook:video id="id" width="width"/>')
    assert !applyTemplate('<facebook:video height="height" width="width"/>')

    assert applyTemplate('<facebook:video id="id" width="width" height="height"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://www.facebook.com/video/embed?video_id=id"></iframe>'
  }
}
