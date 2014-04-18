package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(VkontakteTagLib)
class VkontakteTagLibTests
{
  void test_initialize_tag()
  {
    assert !applyTemplate("<vkontakte:initialize/>")

    /*def html = applyTemplate('<vkontakte:initialize apiId="id"/>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.init({apiId:id, onlyWidgets:true})')*/
  }

  void test_comments_tag()
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

  void test_community_tag()
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

  void test_like_tag()
  {
    def html = applyTemplate('<vkontakte:like/>')
    assert html.contains('<div id="vk_like"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Like("vk_like", {});')

    html = applyTemplate("<vkontakte:like layout=\"${VkontakteLikeButtonLayout.BUTTON}\" width=\"width\" page_title=\"page_title\" page_description=\"page_description\" page_url=\"page_url\" page_image=\"page_image\" text=\"text\" height=\"height\" verb=\"1\"/>")
    assert html.contains('<div id="vk_like"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Like("vk_like", {"type":"button","pageTitle":"page_title","pageDescription":"page_description","pageUrl":"page_url","pageImage":"page_image","text":"text","height":"height","verb":1});')
  }

  void test_subscribe_tag()
  {
    assert !applyTemplate("<vkontakte:subscribe/>")

    def html = applyTemplate('<vkontakte:subscribe account="account"/>')
    assert html.contains('<div id="vk_subscribe"></div>')
    assert html.contains('VK.Widgets.Subscribe("vk_subscribe", {"mode":"1"}, "account");')

    html = applyTemplate("<vkontakte:subscribe account=\"account\" layout=\"${VkontakteSubscribeButtonLayout.SECOND}\" only_button=\"true\"/>")
    assert html.contains('<div id="vk_subscribe"></div>')
    assert html.contains('VK.Widgets.Subscribe("vk_subscribe", {"mode":"2","soft":1}, "account");')
  }

  void test_video_tag()
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
}
