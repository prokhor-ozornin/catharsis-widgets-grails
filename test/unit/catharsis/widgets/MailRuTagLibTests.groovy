package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(MailRuTagLib)
class MailRuTagLibTests
{
  void testIcqTag()
  {
    assert applyTemplate('<mailru:icq/>').contains('<script src="http://c.icq.com/siteim/icqbar/js/partners/initbar_ru.js" type="text/javascript"></script>')

    def html = applyTemplate('<mailru:icq account="account" language="en"/>')
    assert html.contains('window.ICQ = {siteOwner:\'account\'};')
    assert html.contains('<script src="http://c.icq.com/siteim/icqbar/js/partners/initbar_en.js" type="text/javascript"></script>')
  }

  void testLikeTag()
  {
    assert applyTemplate('<mailru:like/>') == "<a target=\"_blank\" class=\"mrc__plugin_uber_like_button\" href=\"http://connect.mail.ru/share\" data-mrc-config=\"{${'"sz":"20","st":"1","tp":"combo","cm":"1","ck":"1"'.encodeAsHTML()}}\">Нравится</a>"
    assert applyTemplate("<mailru:like size=\"${MailRuLikeButtonSize.SIZE_30}\" layout=\"${MailRuLikeButtonLayout.SECOND}\" type=\"${MailRuLikeButtonType.MAILRU}\" hasCounter=\"true\" counterPosition=\"${MailRuLikeButtonCounterPosition.UPPER}\" hasText=\"false\" />") == "<a target=\"_blank\" class=\"mrc__plugin_uber_like_button\" href=\"http://connect.mail.ru/share\" data-mrc-config=\"{${'"sz":"30","st":"2","tp":"mm","vt":1,"nt":1'.encodeAsHTML()}}\">Нравится</a>"
  }

  void testVideoTag()
  {
    assert !applyTemplate('<mailru:video/>')
    assert !applyTemplate('<mailru:video id="id" height="height"/>')
    assert !applyTemplate('<mailru:video id="id" width="width"/>')
    assert !applyTemplate('<mailru:video height="height" width="width"/>')

    assert applyTemplate('<mailru:video video="video" width="width" height="height"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://api.video.mail.ru/videos/embed/mail/video"></iframe>'
  }

  void testVideoLinkTag()
  {
    assert !applyTemplate('<mailru:videoLink/>')
    assert applyTemplate('<mailru:videoLink video="video"/>') == '<a href="http://my.mail.ru/video/mail/video"></a>'
  }

  void testVideoUrlTag()
  {
    assert !applyTemplate('<mailru:videoUrl/>')
    assert applyTemplate('<mailru:videoUrl video="video"/>') == 'http://my.mail.ru/video/mail/video'
  }
}
