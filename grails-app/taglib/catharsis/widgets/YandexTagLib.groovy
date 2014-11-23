package catharsis.widgets

import grails.converters.JSON
import org.apache.http.client.utils.URIBuilder

/**
 * Yandex tags library
 * @see "http://yandex.ru"
 */
class YandexTagLib
{
  static final String namespace = 'yandex'

  /**
   * Renders Yandex.Metrika web counter's JavaScript code.
   * @see "https://metrika.yandex.ru"
   * @attr account REQUIRED Identifier Yandex.Metrica site.
   * @attr web_visor Whether to use webvisor (recording and analysis of site's visitors behaviour). Default is true.
   * @attr click_map Whether to use click map (gathering statistics for "click map" report). Default is true.
   * @attr track_links Whether to track links (gathering statistics for external links, file uploads and "Share" button). Default is true.
   * @attr track_hash Whether to track address hash in URL query string. Default is true.
   * @attr accurate Whether to use accurate track bounce. Default is true.
   * @attr no_index Whether to disable indexing of site's pages. Default is false.
   * @attr language Language of visual counter's interface to use. Default is current locale's language.
   */
  Closure analytics = { Map attrs ->
    String account = attrs['account']?.toString()?.trim()

    if (!account)
    {
      return
    }

    Map config =
    [
      'id' : account,
      'webvisor' : attrs['web_visor'] != null ? attrs['web_visor'].toString().toBoolean() : true,
      'clickmap' : attrs['click_map'] != null? attrs['click_map'].toString().toBoolean() : true,
      'trackLinks' : attrs['track_links'] != null ? attrs['track_links'].toString().toBoolean() : true,
      'accurateTrackBounce' : attrs['accurate'] != null ? attrs['accurate'].toString().toBoolean() : true,
      'trackHash' : attrs['track_hash'] != null ? attrs['track_hash'].toString().toBoolean() : true
    ]

    if (attrs['no_index']?.toString()?.toBoolean())
    {
      config['ut'] = 'noindex'
    }

    out << g.render(
      contextPath : pluginContextPath,
      template : '/yandex_analytics',
      model :
      [
        'account' : account,
        'language' : attrs['language']?.toString() ?: request.locale.language,
        'config' : (config as JSON).toString()
      ]
    )
  }

  /**
   * Renders Yandex "Like" button.
   * Requires "yandex" module to be loaded with Resources plugin.
   * @attr size Size of the button (YandexLikeButtonSize or string).
   * @attr layout Visual layout/appearance of the button (YandexLikeButtonLayout or string).
   * @attr text Label text to draw on the button.
   * @attr url URL address of web page to share.
   * @attr title Custom title text for shared page.
   */
  Closure like_button = { Map attrs ->
    Map attributes =
    [
      'name' : 'ya-share',
      'type' : attrs['layout']?.toString() ?: YandexLikeButtonLayout.BUTTON.toString(),
      'size' : attrs['size']?.toString() ?: YandexLikeButtonSize.LARGE.toString()
    ]

    if (attrs['text'])
    {
      attributes['share_text'] = attrs['text'].toString()
    }

    if (attrs['url'])
    {
      attributes['share_url'] = attrs['url'].toString()
    }

    if (attrs['title'])
    {
      attributes['share_title'] = attrs['title'].toString()
    }

    out << g.withTag(name : 'a', attrs : attributes)
  }

  /**
   * Renders button for Yandex.Money (http://money.yandex.ru) payment system that allows financial transactions to be performed.
   * @see "https://money.yandex.ru/embed/quickpay/small.xml"
   * @attr account REQUIRED Identifier of account in the Yandex.Money payment system which is to receive money.
   * @attr description REQUIRED Description of payment goal/purpose.
   * @attr sum REQUIRED Monetary sum to transfer to Yandex.Money account.
   * @attr type Type of payment option (YandexMoneyButtonType or string). Default is YandexMoneyButtonType.WALLET.
   * @attr text Text to display on button (YandexMoneyButtonText or integer). Default is YandexMoneyButtonText.PAY.
   * @attr size Size of button (YandexMoneyButtonSize or string). Default is YandexMoneyButtonSize.LARGE.
   * @attr color Color of button (YandexMoneyButtonColor or string). Default is YandexMoneyButtonColor.ORANGE.
   * @attr ask_payer_full_name Whether to ask for full name of payer during transaction. Default is false.
   * @attr ask_payer_email Whether to ask for email address of payer during transaction. Default is false.
   * @attr ask_payer_phone Whether to ask for payer phone number during transaction. Default is false.
   * @attr ask_payer_address Whether to ask for payer address during transaction. Default is false.
   */
  Closure money_button = { Map attrs ->
    String account = attrs['account']?.toString()?.trim()
    String sum = attrs['sum']?.toString()?.trim()
    String description = attrs['description']?.toString()?.trim()

    if (!account || !sum || !description)
    {
      return
    }

    String text = attrs['text'] ?: YandexMoneyButtonText.PAY.toString()

    int width
    switch (text)
    {
      case YandexMoneyButtonText.PAY.toString() :
        width = 229
      break

      case YandexMoneyButtonText.BUY.toString() :
        width = 197
      break

      case YandexMoneyButtonText.TRANSFER.toString() :
        width = 242
      break

      case YandexMoneyButtonText.DONATE.toString() :
        width = 283
      break

      case YandexMoneyButtonText.GIVE.toString() :
        width = 231
      break

      case YandexMoneyButtonText.SUPPORT.toString() :
        width = 262
      break

      default :
        width = 283
      break
    }

    URIBuilder uri =
    new URIBuilder('https://money.yandex.ru/embed/small.xml')
      .addParameter('account', account)
      .addParameter('quickpay', 'small')
      .addParameter(attrs['type']?.toString() ?: YandexMoneyButtonType.WALLET.toString(), 'on')
      .addParameter('button-text', text)
      .addParameter('button-size', attrs['size']?.toString() ?: YandexMoneyButtonSize.LARGE.toString())
      .addParameter('button-color', attrs['color']?.toString() ?: YandexMoneyButtonColor.ORANGE.toString())
      .addParameter('targets', description)
      .addParameter('default-sum', sum.toBigDecimal().toString())

    if (attrs['ask_payer_full_name']?.toString()?.toBoolean())
    {
      uri.addParameter('fio', 'on')
    }

    if (attrs['ask_payer_email']?.toString()?.toBoolean())
    {
      uri.addParameter('mail', 'on')
    }

    if (attrs['ask_payer_phone']?.toString()?.toBoolean())
    {
      uri.addParameter('phone', 'on')
    }

    if (attrs['ask_payer_address']?.toString()?.toBoolean())
    {
      uri.addParameter('address', 'on')
    }

    out << g.withTag(
      name : 'iframe',
      attrs :
      [
        'frameborder' : '0',
        'allowtransparency' : true,
        'scrolling' : 'no',
        'width' : width,
        'height' : 54,
        'src' : uri.toString()
      ]
    )
  }

  /**
   * Renders donation form for Yandex.Money (http://money.yandex.ru) payment system that allows financial transactions to be performed.
   * @see "https://money.yandex.ru/embed/quickpay/donate.xml"
   * @attr account REQUIRED Identifier of account in the Yandex.Money payment system which is to receive money.
   * @attr description_text REQUIRED Description of payment goal/purpose.
   * @attr description Whether to show description of payment goal/purpose in the form. Default is false.
   * @attr sum Monetary sum to transfer to Yandex.Money account.
   * @attr cards Whether to accept payment from Visa/Master Card cards. Default is true.
   * @attr text Text to display on button (YandexMoneyDonateFormText or integer). Default is YandexMoneyDonateFormText.DONATE.
   * @attr project_name Name of charitable project or program.
   * @attr project_site URL address of charitable project or program website.
   * @attr ask_payer_comment Whether to allow payer add custom payment comment. Default is false.
   * @attr comment_hint Hint text for comment field.
   * @attr ask_payer_full_name Whether to ask for full name of payer during transaction. Default is false.
   * @attr ask_payer_email Whether to ask for email address of payer during transaction. Default is false.
   * @attr ask_payer_phone Whether to ask for payer phone number during transaction. Default is false.
   */
  Closure money_donate_form = { Map attrs ->
    String account = attrs['account']?.toString()?.trim()
    String descriptionText = attrs['description_text']?.toString()?.trim()

    if (!account || !descriptionText)
    {
      return
    }

    boolean cards = attrs['cards'] == null || attrs['cards'].toString().toBoolean()
    String text = (attrs['text'] ?: YandexMoneyDonateFormText.DONATE).toString()

    int width
    switch (text)
    {
      case YandexMoneyDonateFormText.DONATE.toString() :
        width = 523
      break

      case YandexMoneyDonateFormText.GIVE.toString() :
        width = 487
      break

      case YandexMoneyDonateFormText.TRANSFER.toString() :
        width = 495
      break

      case YandexMoneyDonateFormText.SEND.toString() :
        width = 494
      break

      case YandexMoneyDonateFormText.SUPPORT.toString() :
        width = 507
      break

      default :
        width = 523
      break
    }
    if (!cards)
    {
      width -= 69
    }

    URIBuilder uri = new URIBuilder('https://money.yandex.ru/embed/donate.xml')
      .addParameter('account', account)
      .addParameter('quickpay', 'donate')
      .addParameter('targets', descriptionText)
      .addParameter('button-text', text)

    if (cards)
    {
      uri.addParameter('payment-type-choice', 'on')
    }

    if (attrs['sum'])
    {
      uri.addParameter('default-sum', attrs['sum'].toString().toBigDecimal().toString())
    }

    if (attrs['description'])
    {
      uri.addParameter('target-visibility', 'on')
    }

    if (attrs['project_name'])
    {
      uri.addParameter('project-name', attrs['project_name'].toString())
    }

    if (attrs['project_site'])
    {
      uri.addParameter('project-site', attrs['project_site'].toString())
    }

    if (attrs['ask_payer_comment']?.toString()?.toBoolean())
    {
      uri.addParameter('comment', 'on')
      if (attrs['comment_hint'])
      {
        uri.addParameter('hint', attrs['comment_hint'].toString())
      }
    }

    if (attrs['ask_payer_full_name']?.toString()?.toBoolean())
    {
      uri.addParameter('fio', 'on')
    }

    if (attrs['ask_payer_email']?.toString()?.toBoolean())
    {
      uri.addParameter('mail', 'on')
    }

    if (attrs['ask_payer_phone']?.toString()?.toBoolean())
    {
      uri.addParameter('phone', 'on')
    }

    out << g.withTag(
      name : 'iframe',
      attrs :
      [
        'frameborder' : '0',
        'allowtransparency' : true,
        'scrolling' : 'no',
        'width' : width,
        'height' : attrs['ask_payer_comment']?.toString()?.toBoolean() ? 210 : 133,
        'src' : uri.toString()
      ]
    )
  }

  /**
   * Renders payment form for Yandex.Money (http://money.yandex.ru) payment system that allows financial transactions to be performed.
   * @see "https://money.yandex.ru/embed/quickpay/shop.xml"
   * @attr account REQUIRED Identifier of account in the Yandex.Money payment system which is to receive money.
   * @attr description REQUIRED Description of payment goal/purpose (for predefined purpose) or purpose hint (for custom purpose).
   * @attr sum Monetary sum to transfer to Yandex.Money account.
   * @attr cards Whether to accept payment from Visa/Master Card cards. Default is true.
   * @attr text Text to display on button (YandexMoneyPaymentFormText or integer). Default is YandexMoneyPaymentFormText.PAY.
   * @attr ask_payer_purpose Whether to allow payer specify custom payment purpose text (true) or use predefined purpose text (false). Default is false.
   * @attr ask_payer_comment Whether to allow payer add custom payment comment. Default is false.
   * @attr ask_payer_full_name Whether to ask for full name of payer during transaction. Default is false.
   * @attr ask_payer_email Whether to ask for email address of payer during transaction. Default is false.
   * @attr ask_payer_phone Whether to ask for payer phone number during transaction. Default is false.
   * @attr ask_payer_address Whether to ask for payer address during transaction. Default is false.
   */
  Closure money_payment_form = { Map attrs ->
    String account = attrs['account']?.toString()?.trim()
    String description = attrs['description']?.toString()?.trim()

    if (!account || !description)
    {
      return
    }

    URIBuilder uri = new URIBuilder('https://money.yandex.ru/embed/shop.xml')
      .addParameter('account', account)
      .addParameter('quickpay', 'shop')
      .addParameter('button-text', attrs['text']?.toString() ?: YandexMoneyPaymentFormText.PAY.toString())

    if (attrs['cards'] == null || attrs['cards'].toString().toBoolean())
    {
      uri.addParameter('payment-type-choice', 'on')
    }

    if (attrs['ask_payer_purpose']?.toString()?.toBoolean())
    {
      uri.addParameter('writer', 'buyer')
    }
    else
    {
      uri.addParameter('writer', 'seller')
    }

    if (attrs['ask_payer_purpose']?.toString()?.toBoolean())
    {
      uri.addParameter('targets-hint', description)
    }
    else
    {
      uri.addParameter('targets', description)
    }

    if (attrs['sum'])
    {
      uri.addParameter('default-sum', attrs['sum'].toString().toBigDecimal().toString())
    }

    if (attrs['ask_payer_comment']?.toString()?.toBoolean())
    {
      uri.addParameter('comment', 'on')
    }

    if (attrs['ask_payer_full_name']?.toString()?.toBoolean())
    {
      uri.addParameter('fio', 'on')
    }

    if (attrs['ask_payer_email']?.toString()?.toBoolean())
    {
      uri.addParameter('mail', 'on')
    }

    if (attrs['ask_payer_phone']?.toString()?.toBoolean())
    {
      uri.addParameter('phone', 'on')
    }

    if (attrs['ask_payer_address']?.toString()?.toBoolean())
    {
      uri.addParameter('address', 'on')
    }

    out << g.withTag(
      name : 'iframe',
      attrs :
      [
        'frameborder' : '0',
        'allowtransparency' : true,
        'scrolling' : 'no',
        'width' : 450,
        'height' : attrs['ask_payer_comment']?.toString()?.toBoolean() ? 255 : 200,
        'src' : uri.toString()
      ]
    )
  }

  /**
   * Renders Yandex social "Share" button.
   * Requires "yandex" module to be loaded with Resources plugin.
   * @attr services List of included social services as a collection or comma-separated list string. Valid names include : [yaru, vkontakte, facebook, twitter, odnoklassniki ,moimir, lj, friendfeed, moikrug, gplus, pinterest, surfingbird].
   * @attr layout Visual layout/appearance of the button (YandexShareButtonLayout or string).
   * @attr language Button's interface language.
   */
  Closure share_panel = { Map attrs ->
    String services = attrs['services'] instanceof Collection ? (attrs['services'] as Collection).join(',') : attrs['services']?.toString()

    if (!services)
    {
      services = 'yaru,vkontakte,facebook,twitter,odnoklassniki,moimir,lj,friendfeed,moikrug,gplus,pinterest,surfingbird'
    }

    out << g.withTag(
      name : 'div',
      attrs :
      [
        'class' : 'yashare-auto-init',
        'data-yashareL10n' : attrs['language']?.toString() ?: request.locale.language,
        'data-yashareType' : attrs['layout']?.toString() ?: YandexShareButtonLayout.BUTTON.toString(),
        'data-yashareQuickServices' : services
      ]
    )
  }

  /**
   * Renders embedded Yandex video on web page.
   * @attr id REQUIRED Identifier of video.
   * @attr width REQUIRED Width of video in player control.
   * @attr height REQUIRED Height of video in player control.
   * @attr user REQUIRED Account identifier of video's uploader.
   */
  Closure video = { Map attrs ->
    String id = attrs['id']?.toString()?.trim()
    String width = attrs['width']?.toString()?.trim()
    String height = attrs['height']?.toString()?.trim()
    String user = attrs['user']?.toString()?.trim()

    if (!id || !width || !height || !user)
    {
      return
    }

    out << g.withTag(
      name : 'iframe',
      attrs :
      [
        'frameborder' : '0',
        'allowfullscreen' : true,
        'webkitallowfullscreen' : true,
        'mozallowfullscreen' : true,
        'width' : width,
        'height' : height,
        'src' : "http://video.yandex.ru/iframe/${user}/${id}"
      ]
    )
  }
}

enum YandexLikeButtonLayout
{
  BUTTON,
  ICON

  @Override
  String toString()
  {
    this.name().toLowerCase()
  }
}

enum YandexLikeButtonSize
{
  LARGE,
  SMALL

  @Override
  String toString()
  {
    this.name().toLowerCase()
  }
}

/**
 * Color of the button.
 */
enum YandexMoneyButtonColor
{
  /**
   * Orange
   */
  ORANGE,

  /**
   * White
   */
  WHITE,

  /**
   * Black
   */
  BLACK

  @Override
  String toString()
  {
    this.name().toLowerCase()
  }
}

/**
 * Size of the button.
 */
enum YandexMoneyButtonSize
{
  /**
   * Small
   */
  SMALL,

  /**
   * Medium
   */
  MEDIUM,

  /**
   * Large
   */
  LARGE

  @Override
  String toString()
  {
    switch (this)
    {
      case SMALL :
        return 's'

      case MEDIUM :
        return 'm'

      case LARGE :
        return 'l'
    }
  }
}

/**
 * Type of text to display on the button.
 */
enum YandexMoneyButtonText
{
  /**
   * "Pay"
   */
  PAY,

  /**
   * "Buy"
   */
  BUY,

  /**
   * "Transfer"
   */
  TRANSFER,

  /**
   * "Donate"
   */
  DONATE,

  /**
   * "Give"
   */
  GIVE,

  /**
   * "Support"
   */
  SUPPORT

  @Override
  String toString()
  {
    "0${(ordinal() + 1)}"
  }
}

/**
 * Type of payments source.
 */
enum YandexMoneyButtonType
{
  /**
   * Visa/Master Card
   */
  CARD,

  /**
   * Yandex.Money wallet.
   */
  WALLET

  @Override
  String toString()
  {
    switch (this)
    {
      case CARD :
        return 'any-card-payment-type'

      case WALLET :
        return 'yamoney-payment-type'
    }
  }
}

/**
 * Type of text to display on the button.
 */
enum YandexMoneyDonateFormText
{
  /**
   * "Donate"
   */
  DONATE,

  /**
   * "Give"
   */
  GIVE,

  /**
   * "Transfer"
   */
  TRANSFER,

  /**
   * "Send"
   */
  SEND,

  /**
   * "Support"
   */
  SUPPORT

  @Override
  String toString()
  {
    "0${(ordinal() + 1)}"
  }
}

/**
 * Type of text to display on the button.
 */
enum YandexMoneyPaymentFormText
{
  /**
   * "Pay"
   */
  PAY,

  /**
   * "Buy"
   */
  BUY,

  /**
   * "Transfer"
   */
  TRANSFER,

  /**
   * "Give"
   */
  GIVE

  @Override
  String toString()
  {
    "0${(ordinal() + 1)}"
  }
}

enum YandexShareButtonLayout
{
  BUTTON,
  LINK,
  ICON,
  NONE

  @Override
  String toString()
  {
    this.name().toLowerCase()
  }
}