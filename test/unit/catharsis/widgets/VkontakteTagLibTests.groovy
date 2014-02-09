package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(VkontakteTagLib)
class VkontakteTagLibTests
{
  void testInitializeTag()
  {
    assert !applyTemplate("<vkontakte:initialize/>")

    /*def html = applyTemplate('<vkontakte:initialize apiId="id"/>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.init({apiId:id, onlyWidgets:true})')*/
  }

  void testCommentsTag()
  {
    def html = applyTemplate('<vkontakte:comments/>')
    assert html.contains('<div id="vk_comments"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Comments("vk_comments", {"limit":"5","attach":false});')

    html = applyTemplate("<vkontakte:comments limit=\"${VkontakteCommentsLimit.TEN}\" attach=\"${VkontakteCommentsAttach.ALL}\" width=\"width\"/>")
    assert html.contains('<div id="vk_comments"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Comments("vk_comments", {"limit":"10","attach":"*","width":"width"});')
  }

  void testCommunityTag()
  {
    assert !applyTemplate("<vkontakte:community/>")

    def html = applyTemplate('<vkontakte:community account="account"/>')
    assert html.contains('<div id="vk_groups"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Group("vk_groups", {"mode":"0"}, "account");')

    html = applyTemplate("<vkontakte:community account=\"account\" mode=\"${VkontakteCommunityMode.NEWS}\" width=\"width\" height=\"height\"/>")
    assert html.contains('<div id="vk_groups"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Group("vk_groups", {"mode":"2","wide":1,"width":"width","height":"height"}, "account");')
  }

  void testLikeTag()
  {
    def html = applyTemplate('<vkontakte:like/>')
    assert html.contains('<div id="vk_like"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Like("vk_like", {});')

    html = applyTemplate("<vkontakte:like layout=\"${VkontakteLikeButtonLayout.BUTTON}\" width=\"width\" pageTitle=\"pageTitle\" pageDescription=\"pageDescription\" pageUrl=\"pageUrl\" pageImage=\"pageImage\" text=\"text\" height=\"height\" verb=\"1\"/>")
    assert html.contains('<div id="vk_like"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Like("vk_like", {"type":"button","pageTitle":"pageTitle","pageDescription":"pageDescription","pageUrl":"pageUrl","pageImage":"pageImage","text":"text","height":"height","verb":1});')
  }

  void testSubscribeTag()
  {
    assert !applyTemplate("<vkontakte:subscribe/>")

    def html = applyTemplate('<vkontakte:subscribe account="account"/>')
    assert html.contains('<div id="vk_subscribe"></div>')
    assert html.contains('VK.Widgets.Subscribe("vk_subscribe", {"mode":"1"}, "account");')

    html = applyTemplate("<vkontakte:subscribe account=\"account\" layout=\"${VkontakteSubscribeButtonLayout.SECOND}\" onlyButton=\"true\"/>")
    assert html.contains('<div id="vk_subscribe"></div>')
    assert html.contains('VK.Widgets.Subscribe("vk_subscribe", {"mode":"2","soft":1}, "account");')
  }

  void testVideoTag()
  {
    assert !applyTemplate('<vkontakte:video/>')
    assert !applyTemplate('<vkontakte:video oid="oid" hash="hash" width="width" height="height"/>')
    assert !applyTemplate('<vkontakte:video video="video" hash="hash" width="width" height="height"/>')
    assert !applyTemplate('<vkontakte:video video="video" oid="oid" width="width" height="height"/>')
    assert !applyTemplate('<vkontakte:video video="video" oid="oid" hash="hash" height="height"/>')
    assert !applyTemplate('<vkontakte:video video="video" oid="oid" hash="hash" width="width"/>')

    assert applyTemplate('<vkontakte:video video="video" user="user" hash="hash" width="width" height="height"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://vk.com/video_ext.php?oid=user&id=video&hash=hash&hd=0"></iframe>'
    assert applyTemplate('<vkontakte:video video="video" user="user" hash="hash" width="width" height="height" hd="true"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://vk.com/video_ext.php?oid=user&id=video&hash=hash&hd=1"></iframe>'
  }

  void testVideoLinkTag()
  {
    assert !applyTemplate('<vkontakte:videoLink/>')
    assert !applyTemplate('<vkontakte:videoLink video="video"/>')
    assert !applyTemplate('<vkontakte:videoLink user="user"/>')
    assert applyTemplate('<vkontakte:videoLink video="video" user="user"/>') == '<a href="http://vk.com/videouser_video"></a>'
  }

  void testVideoUrlTag()
  {
    assert !applyTemplate('<vkontakte:videoUrl/>')
    assert !applyTemplate('<vkontakte:videoUrl video="video"/>')
    assert !applyTemplate('<vkontakte:videoUrl user="user"/>')
    assert applyTemplate('<vkontakte:videoUrl video="video" user="user"/>') == 'http://vk.com/videouser_video'
  }
}
