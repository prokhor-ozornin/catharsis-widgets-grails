package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(YandexTagLib)
class YandexTagLibTests
{
  void testAnalyticsTag()
  {
    assert !applyTemplate('<yandex:analytics/>')

    def html = applyTemplate('<yandex:analytics account="account"/>')
    assert html.contains("Ya.Metrika.informer({i: this, id: account, lang: '${request.locale.language}'})")
    assert html.contains('yaCounteraccount')
    assert html.contains('"webvisor":true')
    assert html.contains('"clickmap":true')
    assert html.contains('"trackLinks":true')
    assert html.contains('"accurateTrackBounce":true')
    assert html.contains('"trackHash":true')
    assert !html.contains('"ut":"noindex"')

    html = applyTemplate('<yandex:analytics account="account" webvisor="false" clickmap="false" tracklinks="false" accurate="false" trackhash="false" noindex="true"/>')
    assert html.contains("Ya.Metrika.informer({i: this, id: account, lang: '${request.locale.language}'})")
    assert html.contains('yaCounteraccount')
    assert html.contains('"webvisor":false')
    assert html.contains('"clickmap":false')
    assert html.contains('"trackLinks":false')
    assert html.contains('"accurateTrackBounce":false')
    assert html.contains('"trackHash":false')
    assert html.contains('"ut":"noindex"')
  }

  void testLikeTag()
  {
    assert applyTemplate('<yandex:like/>') == '<a name="ya-share" type="button" size="large"></a>'
    assert applyTemplate('<yandex:like layout="icon" size="small" text="text" url="url" title="title"/>') == '<a name="ya-share" type="icon" size="small" share_text="text" share_url="url" share_title="title"></a>'
  }

  void testShareTag()
  {
    assert applyTemplate('<yandex:share/>') == "<div class=\"yashare-auto-init\" data-yashareL10n=\"${request.locale.language}\" data-yashareType=\"button\" data-yashareQuickServices=\"yaru,vkontakte,facebook,twitter,odnoklassniki,moimir,lj,friendfeed,moikrug,gplus,pinterest,surfingbird\"></div>"
    assert applyTemplate("<yandex:share services=\"yaru\" layout=\"${YandexShareButtonLayout.LINK}\" language=\"ru\"/>") == "<div class=\"yashare-auto-init\" data-yashareL10n=\"ru\" data-yashareType=\"${YandexShareButtonLayout.LINK}\" data-yashareQuickServices=\"yaru\"></div>"
  }

  void testVideoTag()
  {
    assert !applyTemplate('<yandex:video/>')
    assert !applyTemplate('<yandex:video video="video" user="user" width="width"/>')
    assert !applyTemplate('<yandex:video video="video" user="user" height="height"/>')
    assert !applyTemplate('<yandex:video video="video" width="width" height="height"/>')
    assert !applyTemplate('<yandex:video user="user" width="width" height="height"/>')

    assert applyTemplate('<yandex:video video="video" width="width" height="height" user="user"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://video.yandex.ru/iframe/user/video"></iframe>'
  }

  void testVideoLinkTag()
  {
    assert !applyTemplate('<yandex:videoLink/>')
    assert !applyTemplate('<yandex:videoLink video="video"/>')
    assert !applyTemplate('<yandex:videoLink user="user"/>')
    assert applyTemplate('<yandex:videoLink video="video" user="user"/>') == '<a href="http://video.yandex.ru/users/user/view/video"></a>'
  }

  void testVideoUrlTag()
  {
    assert !applyTemplate('<yandex:videoUrl/>')
    assert !applyTemplate('<yandex:videoUrl video="video"/>')
    assert !applyTemplate('<yandex:videoUrl user="user"/>')
    assert applyTemplate('<yandex:videoUrl video="video" user="user"/>') == 'http://video.yandex.ru/users/user/view/video'
  }
}
