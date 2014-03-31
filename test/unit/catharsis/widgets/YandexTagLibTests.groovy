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

  void testMoneyButtonTag()
  {
    assert !applyTemplate('<yandex:moneyButton/>')
    assert !applyTemplate('<yandex:moneyButton description="description" sum="1"/>')
    assert !applyTemplate('<yandex:moneyButton account="account" sum="1"/>')
    assert !applyTemplate('<yandex:moneyButton account="account" description="description"/>')
    assert applyTemplate('<yandex:moneyButton account="account" description="description" sum="1"/>') == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="229" height="54" src="https://money.yandex.ru/embed/small.xml?account=account&quickpay=small&yamoney-payment-type=on&button-text=01&button-size=l&button-color=orange&targets=description&default-sum=1"></iframe>'
    assert applyTemplate("<yandex:moneyButton account=\"account\" description=\"description\" sum=\"1\" type=\"${YandexMoneyButtonType.CARD}\" text=\"${YandexMoneyButtonText.TRANSFER}\" size=\"${YandexMoneyButtonSize.MEDIUM}\" color=\"${YandexMoneyButtonColor.WHITE}\" payerFullName=\"true\" payerEmail=\"true\" payerPhone=\"true\" payerAddress=\"true\"/>") == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="242" height="54" src="https://money.yandex.ru/embed/small.xml?account=account&quickpay=small&any-card-payment-type=on&button-text=03&button-size=m&button-color=white&targets=description&default-sum=1&fio=on&mail=on&phone=on&address=on"></iframe>'
  }

  void testMoneyDonateFormTag()
  {
    assert !applyTemplate('<yandex:moneyDonateForm/>')
    assert !applyTemplate('<yandex:moneyDonateForm description="description"/>')
    assert !applyTemplate('<yandex:moneyDonateForm account="account"/>')
    assert applyTemplate('<yandex:moneyDonateForm account="account" description="description"/>') == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="523" height="133" src="https://money.yandex.ru/embed/donate.xml?account=account&quickpay=donate&payment-type-choice=on&default-sum=&targets=description&project-name=&project-site=&button-text=01"></iframe>'
    assert applyTemplate("<yandex:moneyDonateForm account=\"account\" description=\"description\" showDescription=\"true\" sum=\"1\" cards=\"false\" text=\"${YandexMoneyDonateFormText.TRANSFER}\" projectName=\"projectName\" projectSite=\"projectSite\" payerComment=\"true\" payerCommentHint=\"payerCommentHint\" payerFullName=\"true\" payerEmail=\"true\" payerPhone=\"true\"/>") == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="426" height="210" src="https://money.yandex.ru/embed/donate.xml?account=account&quickpay=donate&default-sum=1&targets=description&target-visibility=on&project-name=projectName&project-site=projectSite&button-text=03&comment=on&hint=payerCommentHint&fio=on&mail=on&phone=on"></iframe>'
  }

  void testMoneyPaymentFormTag()
  {
    assert !applyTemplate('<yandex:moneyPaymentForm/>')
    assert !applyTemplate('<yandex:moneyPaymentForm description="description"/>')
    assert !applyTemplate('<yandex:moneyPaymentForm account="account"/>')
    assert applyTemplate('<yandex:moneyPaymentForm account="account" description="description"/>') == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="450" height="200" src="https://money.yandex.ru/embed/shop.xml?account=account&quickpay=shop&payment-type-choice=on&writer=seller&targets=description&default-sum=&button-text=01"></iframe>'
    assert applyTemplate("<yandex:moneyPaymentForm account=\"account\" description=\"description\" sum=\"1\" cards=\"false\" text=\"${YandexMoneyPaymentFormText.TRANSFER}\" payerPurpose=\"true\" payerComment=\"true\' payerFullName=\"true\" payerEmail=\"true\" payerPhone=\"true\" payerAddress=\"true\"/>") == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="450" height="200" src="https://money.yandex.ru/embed/shop.xml?account=account&quickpay=shop&writer=buyer&targets-hint=description&default-sum=1&button-text=03&phone=on&address=on"></iframe>'
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
