package catharsis.widgets

import grails.converters.JSON

class YandexTagLib
{
  static final String namespace = "yandex"

  /**
   * Renders Yandex.Metrika web counter's JavaScript code.
   * @see "https://metrika.yandex.ru"
   * @attr account REQUIRED Identifier Yandex.Metrica site.
   * @attr webvisor Whether to use webvisor (recording and analysis of site's visitors behaviour). Default is true.
   * @attr clickmap Whether to use click map (gathering statistics for "click map" report). Default is true.
   * @attr tracklinks Whether to track links (gathering statistics for external links, file uploads and "Share" button). Default is true.
   * @attr trackhash Whether to track address hash in URL query string. Default is true.
   * @attr accurate Whether to use accurate track bounce. Default is true.
   * @attr noindex Whether to disable indexing of site's pages. Default is false.
   * @attr language Language of visual counter's interface to use. Default is current locale's language.
   */
  def analytics = { attrs ->
    if (!attrs.account)
    {
      return
    }

    def config =
    [
      id: attrs.account,
      webvisor: attrs.webvisor != null ? attrs.webvisor.toBoolean() : true,
      clickmap: attrs.clickmap != null? attrs.clickmap.toBoolean() : true,
      trackLinks: attrs.tracklinks != null ? attrs.tracklinks.toBoolean() : true,
      accurateTrackBounce: attrs.accurate != null ? attrs.accurate.toBoolean() : true,
      trackHash: attrs.trackhash != null ? attrs.trackhash.toBoolean() : true
    ]

    if (attrs.noindex?.toBoolean())
    {
      config.ut = "noindex"
    }

    out << g.render(contextPath: pluginContextPath, template: "/yandex_analytics", model:
    [
      account: attrs.account,
      language: attrs.language ?: request.locale.language,
      config: config as JSON
    ])
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
  def like = { attrs ->
    def attributes =
    [
      name: "ya-share",
      type: (attrs.layout ?: YandexLikeButtonLayout.BUTTON).toString(),
      size: (attrs.size ?: YandexLikeButtonSize.LARGE).toString()
    ]

    if (attrs.text)
    {
      attributes.share_text = attrs.text
    }

    if (attrs.url)
    {
      attributes.share_url = attrs.url
    }

    if (attrs.title)
    {
      attributes.share_title = attrs.title
    }

    out << g.withTag(name: "a", attrs: attributes)
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
   * @attr payerFullName Whether to ask for full name of payer during transaction. Default is false.
   * @attr payerEmail Whether to ask for email address of payer during transaction. Default is false.
   * @attr payerPhone Whether to ask for payer phone number during transaction. Default is false.
   * @attr payerAddress Whether to ask for payer address during transaction. Default is false.
   */
  def moneyButton = { attrs ->
    if (!attrs.account || !attrs.sum || !attrs.description)
    {
      return
    }

    def text = attrs.text ?: YandexMoneyButtonText.PAY.toString()
    def width = 283
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
    }

    out << g.withTag(
      name: "iframe",
      attrs:
      [
        frameborder: "0",
        allowtransparency: true,
        scrolling: "no",
        width: width,
        height: 54,
        src: "https://money.yandex.ru/embed/small.xml?account=${attrs.account}&quickpay=small&${(attrs.type ?: YandexMoneyButtonType.WALLET).toString()}=on&button-text=${text}&button-size=${(attrs.size ?: YandexMoneyButtonSize.LARGE).toString()}&button-color=${(attrs.color ?: YandexMoneyButtonColor.ORANGE).toString()}&targets=${attrs.description.encodeAsURL()}&default-sum=${attrs.sum.toBigDecimal()}${attrs.payerFullName?.toBoolean() ? "&fio=on" : ""}${attrs.payerEmail?.toBoolean() ? "&mail=on" : ""}${attrs.payerPhone?.toBoolean() ? "&phone=on" : ""}${attrs.payerAddress?.toBoolean() ? "&address=on" : ""}"
      ])
  }

  /**
   * Renders donation form for Yandex.Money (http://money.yandex.ru) payment system that allows financial transactions to be performed.
   * @see "https://money.yandex.ru/embed/quickpay/donate.xml"
   * @attr account REQUIRED Identifier of account in the Yandex.Money payment system which is to receive money.
   * @attr description REQUIRED Description of payment goal/purpose.
   * @attr showDescription Whether to show description of payment goal/purpose in the form. Default is false.
   * @attr sum Monetary sum to transfer to Yandex.Money account.
   * @attr cards Whether to accept payment from Visa/Master Card cards. Default is true.
   * @attr text Text to display on button (YandexMoneyDonateFormText or integer). Default is YandexMoneyDonateFormText.DONATE.
   * @attr projectName Name of charitable project or program.
   * @attr projectSite URL address of charitable project or program website.
   * @attr payerComment Whether to allow payer add custom payment comment. Default is false.
   * @attr payerCommentHint Hint text for comment field.
   * @attr payerFullName Whether to ask for full name of payer during transaction. Default is false.
   * @attr payerEmail Whether to ask for email address of payer during transaction. Default is false.
   * @attr payerPhone Whether to ask for payer phone number during transaction. Default is false.
   */
  def moneyDonateForm = { attrs ->
    if (!attrs.account || !attrs.description)
    {
      return
    }

    def text = (attrs.text ?: YandexMoneyDonateFormText.DONATE).toString()
    def cards = attrs.cards == null || attrs.cards.toBoolean()
    def width = 523

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
    }
    if (!cards)
    {
      width -= 69
    }

    out << g.withTag(
      name: "iframe",
      attrs:
      [
        frameborder: "0",
        allowtransparency: true,
        scrolling: "no",
        width: width,
        height: attrs.payerComment?.toBoolean() ? 210 : 133,
        src: "https://money.yandex.ru/embed/donate.xml?account=${attrs.account}&quickpay=donate${cards ? "&payment-type-choice=on" : ""}&default-sum=${attrs.sum != null ? attrs.sum.toBigDecimal() : ""}&targets=${attrs.description.encodeAsURL()}${attrs.showDescription ? "&target-visibility=on" : ""}&project-name=${attrs.projectName != null ? attrs.projectName.encodeAsURL() : ""}&project-site=${attrs.projectSite != null ? attrs.projectSite.encodeAsURL() : ""}&button-text=${text}${attrs.payerComment?.toBoolean() ? "&comment=on&hint=${attrs.payerCommentHint != null ? attrs.payerCommentHint?.encodeAsURL() : ""}" : ""}${attrs.payerFullName?.toBoolean() ? "&fio=on" : ""}${attrs.payerEmail?.toBoolean() ? "&mail=on" : ""}${attrs.payerPhone?.toBoolean() ? "&phone=on" : ""}"
      ])
  }

  /**
   * Renders payment form for Yandex.Money (http://money.yandex.ru) payment system that allows financial transactions to be performed.
   * @see "https://money.yandex.ru/embed/quickpay/shop.xml"
   * @attr account REQUIRED Identifier of account in the Yandex.Money payment system which is to receive money.
   * @attr description REQUIRED Description of payment goal/purpose (for predefined purpose) or purpose hint (for custom purpose).
   * @attr sum Monetary sum to transfer to Yandex.Money account.
   * @attr cards Whether to accept payment from Visa/Master Card cards. Default is true.
   * @attr text Text to display on button (YandexMoneyPaymentFormText or integer). Default is YandexMoneyPaymentFormText.PAY.
   * @attr payerPurpose Whether to allow payer specify custom payment purpose text (true) or use predefined purpose text (false). Default is false.
   * @attr payerComment Whether to allow payer add custom payment comment. Default is false.
   * @attr payerFullName Whether to ask for full name of payer during transaction. Default is false.
   * @attr payerEmail Whether to ask for email address of payer during transaction. Default is false.
   * @attr payerPhone Whether to ask for payer phone number during transaction. Default is false.
   * @attr payerAddress Whether to ask for payer address during transaction. Default is false.
   */
  def moneyPaymentForm = { attrs ->
    if (!attrs.account || !attrs.description)
    {
      return
    }

    out << g.withTag(
      name: "iframe",
      attrs:
      [
        frameborder: "0",
        allowtransparency: true,
        scrolling: "no",
        width: 450,
        height: attrs.payerComment?.toBoolean() ? 255 : 200,
        src: "https://money.yandex.ru/embed/shop.xml?account=${attrs.account}&quickpay=shop${attrs.cards == null || attrs.cards.toBoolean() ? "&payment-type-choice=on" : ""}&writer=${attrs.payerPurpose?.toBoolean() ? "buyer" : "seller"}&${attrs.payerPurpose?.toBoolean() ? "targets-hint" : "targets"}=${attrs.description.encodeAsURL()}&default-sum=${attrs.sum != null ? attrs.sum.toBigDecimal() : ""}&button-text=${(attrs.text ?: YandexMoneyPaymentFormText.PAY).toString()}${attrs.payerComment?.toBoolean() ? "&comment=on" : ""}${attrs.payerFullName?.toBoolean() ? "&fio=on" : ""}${attrs.payerEmail?.toBoolean() ? "&mail=on" : ""}${attrs.payerPhone?.toBoolean() ? "&phone=on" : ""}${attrs.payerAddress?.toBoolean() ? "&address=on" : ""}"
      ])
  }

  /**
   * Renders Yandex social "Share" button.
   * Requires "yandex" module to be loaded with Resources plugin.
   * @attr services List of included social services as a collection or comma-separated list string. Valid names include : [yaru, vkontakte, facebook, twitter, odnoklassniki ,moimir, lj, friendfeed, moikrug, gplus, pinterest, surfingbird].
   * @attr layout Visual layout/appearance of the button (YandexShareButtonLayout or string).
   * @attr language Button's interface language.
   */
  def share = { attrs ->
    if (!attrs.services)
    {
      attrs.services = "yaru,vkontakte,facebook,twitter,odnoklassniki,moimir,lj,friendfeed,moikrug,gplus,pinterest,surfingbird"
    }

    out << g.withTag(name: "div", attrs:
    [
      class: "yashare-auto-init",
      "data-yashareL10n": attrs.language ?: request.locale.language,
      "data-yashareType": (attrs.layout ?: YandexShareButtonLayout.BUTTON).toString(),
      "data-yashareQuickServices": attrs.services instanceof Collection ? attrs.services.join(",") : attrs.services
    ])
  }

  /**
   * Renders embedded Yandex video on web page.
   * @attr video REQUIRED Identifier of video.
   * @attr width REQUIRED Width of video in player control.
   * @attr height REQUIRED Height of video in player control.
   * @attr user REQUIRED Account identifier of video's uploader.
   */
  def video = { attrs ->
    if (!attrs.video || !attrs.width || !attrs.height || !attrs.user)
    {
      return
    }

    out << g.withTag(
      name: "iframe",
      attrs:
      [
        frameborder: "0",
        allowfullscreen: true,
        webkitallowfullscreen: true,
        mozallowfullscreen: true,
        width: attrs.width,
        height: attrs.height,
        src: "http://video.yandex.ru/iframe/${attrs.user}/${attrs.video}"
      ])
  }

  /**
   * Renders hyperlink to Yandex video.
   * @attr video REQUIRED Identifier of video.
   * @attr user REQUIRED Account identifier of video's uploader.
   */
  def videoLink = { attrs, body ->
    if (!attrs.video || !attrs.user)
    {
      return
    }

    attrs.href = videoUrl([video: attrs.video, user: attrs.user])
    attrs.remove("video")
    attrs.remove("user")

    out << g.withTag(name: "a", attrs: attrs, body())
  }

  /**
   * Generates URL to Yandex video.
   * @attr video REQUIRED Identifier of video.
   * @attr user REQUIRED Account identifier of video's uploader.
   */
  def videoUrl = { attrs ->
    if (!attrs.video || !attrs.user)
    {
      return
    }

    out << "http://video.yandex.ru/users/${attrs.user}/view/${attrs.video}"
  }
}

enum YandexLikeButtonSize
{
  LARGE,
  SMALL

  String toString()
  {
    return name().toLowerCase()
  }
}

enum YandexLikeButtonLayout
{
  BUTTON,
  ICON

  String toString()
  {
    return name().toLowerCase()
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

  String toString()
  {
    switch (this)
    {
      case CARD:
        return "any-card-payment-type";

      case WALLET:
        return "yamoney-payment-type";
    }
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

  String toString()
  {
    switch (this)
    {
      case SMALL:
        return "s";

      case MEDIUM:
        return "m";

      case LARGE:
        return "l";
    }
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

  String toString()
  {
    return name().toLowerCase()
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

  String toString()
  {
    return "0${(ordinal() + 1)}"
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

  String toString()
  {
    return "0${(ordinal() + 1)}"
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

  String toString()
  {
    return "0${(ordinal() + 1)}"
  }
}

enum YandexShareButtonLayout
{
  BUTTON,
  LINK,
  ICON,
  NONE

  String toString()
  {
    return name().toLowerCase()
  }
}
