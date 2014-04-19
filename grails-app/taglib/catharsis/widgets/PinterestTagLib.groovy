package catharsis.widgets

/**
 * Pinterest tags library
 * @see "http://pinterest.com"
 */
class PinterestTagLib
{
  static final String namespace = "pinterest"

  /**
   * Renders Pinterest Board widget with board's latest pins.
   * Requires "pinterest" module to be loaded with Resources plugin.
   * @see "http://business.pinterest.com/widget-builder/#do_embed_board"
   * @attr account REQUIRED Pinterest user account.
   * @attr id REQUIRED Identifier of account's board.
   * @attr height Total height of board in pixels.
   * @attr width Total width of board in pixels.
   * @attr image_width Width of board's image in pixels.
   */
  def board = { attrs ->
    if (!attrs.account || !attrs.id)
    {
      return
    }

    out << g.withTag([name: "a", attrs:
    [
      "data-pin-board-width": attrs.width,
      "data-pin-do": "embedBoard",
      "data-pin-scale-height": attrs.height,
      "data-pin-scale-width": attrs.image_width,
      "href": "http://www.pinterest.com/${attrs.account}/${attrs.id}"
    ]])
  }

  /**
   * Renders Pinterest "Follow Me" button.
   * Requires "pinterest" module to be loaded with Resources plugin.
   * @see "http://business.pinterest.com/widget-builder/#do_follow_me_button"
   * @attr account REQUIRED Pinterest user account.
   * @attr label Text label on the button.
   */
  def follow = { attrs ->
    if (!attrs.account)
    {
      return
    }

    out << g.withTag([name: "a", attrs:
    [
      "data-pin-do": "buttonFollow",
      "href": "http://www.pinterest.com/${attrs.account}"
    ]], attrs.label ?: "Follow")
  }

  /**
   * Renders Pinterest "Pin It" button widget.
   * Requires "pinterest" module to be loaded with Resources plugin.
   * @see "http://business.pinterest.com/widget-builder/#do_pin_it_button"
   * @attr url REQUIRED URL address of target web page for the button.
   * @attr image REQUIRED URL address of the "pinned" image.
   * @attr description REQUIRED Description of the "pinned" image.
   * @attr color Background color of the button (PinterestPinItButtonColor or string).
   * @attr counter_position Position of button's pin counter (PinterestPinItButtonPinCountPosition or string).
   * @attr shape Shape of the button (PinterestPinItButtonShape or string).
   * @attr size Size of the button (PinterestPinItButtonSize or string).
   * @attr language Language of button's label.
   */
  def pinit = { attrs ->
    if (!attrs.url || !attrs.image || !attrs.description)
    {
      return
    }

    def color = (attrs.color ?: "gray").toString()
    def counterPosition = (attrs.counter_position ?: PinterestPinItButtonPinCountPosition.NONE).toString()
    def language = attrs.language ?: "en"
    def shape = (attrs.shape ?: PinterestPinItButtonShape.RECTANGULAR).toString()
    def size = (attrs.size ?: PinterestPinItButtonSize.SMALL).toString()

    byte height = 0
    switch (size)
    {
      case PinterestPinItButtonSize.LARGE.toString() :
        switch (shape)
        {
          case PinterestPinItButtonShape.CIRCULAR :
            height = 32
          break;

          default:
            height = 28
          break;
        }
        break;

      case PinterestPinItButtonSize.SMALL.toString() :
        switch (shape)
        {
          case PinterestPinItButtonShape.CIRCULAR:
            height = 16
          break;

          default:
            height = 20
          break;
        }
        break;
    }

    out << g.withTag([name: "a", attrs:
    [
      "data-pin-color": shape == PinterestPinItButtonShape.RECTANGULAR.toString() ? color : null,
      "data-pin-config": shape == PinterestPinItButtonShape.RECTANGULAR.toString() ? counterPosition : null,
      "data-pin-do": "buttonPin",
      "data-pin-height": height,
      "data-pin-lang": shape == PinterestPinItButtonShape.RECTANGULAR.toString() ? language : null,
      "data-pin-shape": shape,
      "href": "http://www.pinterest.com/pin/create/button/?url=${attrs.url.encodeAsURL()}&media=${attrs.image.encodeAsURL()}&description=${attrs.description.encodeAsURL()}"
    ]],
    "<img src=\"http://assets.pinterest.com/images/pidgets/pinit_fg_${language}_${shape}_${color}_${height}.png\"/>")
  }

  /**
   * Renders Pinterest embedded pin widget.
   * Requires "pinterest" module to be loaded with Resources plugin.
   * @see "http://business.pinterest.com/widget-builder/#do_embed_pin"
   * @attr id REQUIRED Unique identifier of Pinterest Pin.
   */
  def pin = { attrs ->
    if (!attrs.id)
    {
      return
    }

    out << g.withTag([name: "a", attrs:
    [
      "data-pin-do": "embedPin",
      "href": "http://www.pinterest.com/pin/${attrs.id}"
    ]])
  }

  /**
   * Renders Pinterest Profile widget with user's latest pins.
   * Requires "pinterest" module to be loaded with Resources plugin.
   * @see "http://business.pinterest.com/widget-builder/#do_embed_user"
   * @attr account REQUIRED Pinterest user account.
   * @attr height Total height of profile area in pixels.
   * @attr width Total width of profile area in pixels.
   * @attr image_width Width of profile area's image in pixels.
   */
  def profile = { attrs ->
    if (!attrs.account)
    {
      return
    }

    out << g.withTag([name: "a", attrs:
      [
        "data-pin-board-width": attrs.width,
        "data-pin-do": "embedUser",
        "data-pin-scale-height": attrs.height,
        "data-pin-scale-width": attrs.image_width,
        "href": "http://www.pinterest.com/${attrs.account}"
      ]])
  }
}

/**
 * Position of pin counter for Pinterest "Pin It" button.
 */
enum PinterestPinItButtonPinCountPosition
{
  /**
   * Above button.
   */
  ABOVE,

  /**
   * Beside button.
   */
  BESIDE,

  /**
   * Do not show.
   */
  NONE

  String toString()
  {
    name().toLowerCase()
  }
}

/**
 * Shape of Pinterest "Pin It" button.
 */
enum PinterestPinItButtonShape
{
  /**
   * Rectangular
   */
  RECTANGULAR,

  /**
   * Circular
   */
  CIRCULAR

  String toString()
  {
    switch (this)
    {
      case RECTANGULAR :
        return "rect";

      case CIRCULAR :
        return "round";
    }
  }
}

/**
 * Size of Pinterest "Pin It" button.
 */
enum PinterestPinItButtonSize
{
  /**
   * Small.
   */
  SMALL,

  /**
   * Large.
   */
  LARGE

  String toString()
  {
    name().toLowerCase()
  }
}

/**
 * Color of Pinterest "Pin It" button
 */
enum PinterestPinItButtonColor
{
  /**
   * Gray.
   */
  GRAY,

  /**
   * Red.
   */
  RED,

  /**
   * White.
   */
  WHITE

  String toString()
  {
    name().toLowerCase()
  }
}