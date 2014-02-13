package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(FacebookTagLib)
class FacebookTagLibTests
{
  void testInitializeTag()
  {
    assert !applyTemplate('<facebook:initialize/>')

    /*def html = applyTemplate('<facebook:initialize appId="appId"/>');
    assert html.contains('<div id="fb-root"></div>')
    assert html.contains('//connect.facebook.net/en_US/all.js#xfbml=1&appId=appId')*/
  }

  void testActivityFeedTag()
  {
    assert applyTemplate('<facebook:activityFeed/>') == '<div class="fb-activity"></div>'
    assert applyTemplate('<facebook:activityFeed domain="domain" appId="appId" actions="actions" width="width" height="height" colorScheme="colorScheme" header="true" linkTarget="linkTarget" maxAge="1" recommendations="true" trackLabel="trackLabel"/>') == '<div class="fb-activity" data-site="domain" data-app-id="appId" data-action="actions" data-width="width" data-height="height" data-colorscheme="colorScheme" data-header="true" data-linktarget="linkTarget" data-max-age="1" data-recommendations="true" data-ref="trackLabel"></div>'
  }

  void testRecommendationsFeedTag()
  {
    assert applyTemplate('<facebook:recommendationsFeed/>') == '<div class="fb-recommendations"></div>'
    assert applyTemplate('<facebook:recommendationsFeed domain="domain" appId="appId" actions="actions" width="width" height="height" colorScheme="colorScheme" header="true" linkTarget="linkTarget" maxAge="1" trackLabel="trackLabel"/>') == '<div class="fb-recommendations" data-site="domain" data-app-id="appId" data-action="actions" data-width="width" data-height="height" data-colorscheme="colorScheme" data-header="true" data-linktarget="linkTarget" data-max-age="1" data-ref="trackLabel"></div>'
  }

  void testCommentsTag()
  {
    assert applyTemplate('<facebook:comments/>') == '<div class="fb-comments"></div>'
    assert applyTemplate("<facebook:comments url=\"url\" posts=\"1\" width=\"width\" colorScheme=\"${FacebookColorScheme.DARK}\" mobile=\"true\" order=\"${FacebookCommentsOrder.REVERSE_TIME}\"/>") == '<div class="fb-comments" data-href="url" data-num-posts="1" data-width="width" data-colorscheme="dark" data-mobile="true" data-order-by="reverse_time"></div>'
  }

  void testFacepileTag()
  {
    assert applyTemplate('<facebook:facepile/>') == "<div class=\"fb-facepile\" data-href=\"${request.requestURL}\"></div>"
    assert applyTemplate("<facebook:facepile url=\"url\" actions=\"actions\" size=\"${FacebookFacepileSize.LARGE}\" width=\"width\" height=\"height\" maxRows=\"10\" colorScheme=\"${FacebookColorScheme.DARK}\"/>") == '<div class="fb-facepile" data-href="url" data-action="actions" data-size="large" data-width="width" data-height="height" data-max-rows="10" data-colorscheme="dark"></div>'
  }

  void testFollowTag()
  {
    assert !applyTemplate('<facebook:follow/>')
    assert applyTemplate('<facebook:follow url="url"/>') == '<div class="fb-follow" data-href="url"></div>'
    assert applyTemplate("<facebook:follow colorScheme=\"${FacebookColorScheme.DARK}\" url=\"url\" forKids=\"true\" layout=\"${FacebookButtonLayout.BOX_COUNT}\" showFaces=\"true\" width=\"width\" height=\"height\"/>") == '<div class="fb-follow" data-layout="box_count" data-show-faces="true" data-href="url" data-colorscheme="dark" data-kid-directed-site="true" data-width="width" data-height="height"></div>'
  }

  void testLikeTag()
  {
    assert applyTemplate('<facebook:like/>') == "<div class=\"fb-like\" data-href=\"${request.requestURL}\"></div>"
    assert applyTemplate("<facebook:like verb=\"${FacebookLikeButtonVerb.RECOMMEND}\" colorScheme=\"${FacebookColorScheme.DARK}\" url=\"url\" forKids=\"true\" layout=\"${FacebookButtonLayout.BOX_COUNT}\" trackLabel=\"trackLabel\" showFaces=\"true\" width=\"width\"/>") == '<div class="fb-like" data-action="recommend" data-layout="box_count" data-show-faces="true" data-href="url" data-colorscheme="dark" data-kid-directed-site="true" data-ref="trackLabel" data-width="width"></div>'
  }

  void testLikeBoxTag()
  {
    assert !applyTemplate('<facebook:likebox/>')
    assert applyTemplate('<facebook:likebox url="https://www.facebook.com/pages/Clear-Words/515749945120070"/>') == '<div class="fb-like-box" data-href="https://www.facebook.com/pages/Clear-Words/515749945120070"></div>'
    assert applyTemplate("<facebook:likebox url=\"https://www.facebook.com/pages/Clear-Words/515749945120070\" width=\"width\" height=\"height\" colorScheme=\"${FacebookColorScheme.DARK}\" forceWall=\"true\" header=\"true\" showBorder=\"true\" showFaces=\"true\" stream=\"true\"/>") == '<div class="fb-like-box" data-href="https://www.facebook.com/pages/Clear-Words/515749945120070" data-width="width" data-height="height" data-colorscheme="dark" data-force-wall="true" data-header="true" data-show-border="true" data-show-faces="true" data-stream="true"></div>'
  }

  void testSendTag()
  {
    assert applyTemplate('<facebook:send/>') == '<div class="fb-send"></div>'
    assert applyTemplate("<facebook:send url=\"url\" colorScheme=\"${FacebookColorScheme.DARK}\" forKids=\"true\" trackLabel=\"trackLabel\" width=\"width\" height=\"height\"/>") == '<div class="fb-send" data-href="url" data-colorscheme="dark" data-kid-directed-site="true" data-ref="trackLabel" data-width="width" data-height="height"></div>'
  }

  void testPostTag()
  {
    assert !applyTemplate('<facebook:post/>')
    assert applyTemplate('<facebook:post url="url" width="width"/>') == '<div class="fb-post" data-href="url" data-width="width"></div>'
  }

  void testVideoTag()
  {
    assert !applyTemplate('<facebook:video/>')
    assert !applyTemplate('<facebook:video id="id" height="height"/>')
    assert !applyTemplate('<facebook:video id="id" width="width"/>')
    assert !applyTemplate('<facebook:video height="height" width="width"/>')

    assert applyTemplate('<facebook:video video="video" width="width" height="height"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://www.facebook.com/video/embed?video_id=video"></iframe>'
  }

  void testVideoLinkTag()
  {
    assert !applyTemplate('<facebook:videoLink/>')
    assert applyTemplate('<facebook:videoLink video="video"/>') == '<a href="https://www.facebook.com/photo.php?v=video"></a>'
  }

  void testVideoUrlTag()
  {
    assert !applyTemplate('<facebook:videoUrl/>')
    assert applyTemplate('<facebook:videoUrl video="video"/>') == 'https://www.facebook.com/photo.php?v=video'
  }
}
