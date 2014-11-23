package catharsis.widgets

import org.apache.http.client.utils.URIBuilder

/**
 * Pinterest tags library
 * @see "http://pinterest.com"
 */
class PinterestTagLib
{
  static final String namespace = 'pinterest'

  /**
   * Renders Pinterest Board widget with board's latest pins.
   * Requires "pinterest" module to be loaded with Resources plugin.
   * @see "http://business.pinterest.com/widget-builder/#do_embed_board"
   * @attr account REQUIRED Pinterest user account.
   * @attr id REQUIRED Identifier of account's board.
   * @attr height Total height of board in pixels.
   * @attr width Total width of board in pixels.
   * @attr image Width of board's image in pixels.
   */
  Closure board = { Map attrs ->
    String account = attrs['account']?.toString()?.trim()
    String id = attrs['id']?.toString()?.trim()

    if (!account || !id)
    {
      return
    }

    out << g.withTag(
      name : 'a',
      attrs :
      [
        'data-pin-board-width' : attrs['width']?.toString(),
        'data-pin-do' : 'embedBoard',
        'data-pin-scale-height' : attrs['height']?.toString(),
        'data-pin-scale-width' : attrs['image']?.toString(),
        'href' : "http://www.pinterest.com/${account}/${id}"
      ]
    )
  }

  /**
   * Renders Pinterest "Follow Me" button.
   * Requires "pinterest" module to be loaded with Resources plugin.
   * @see "http://business.pinterest.com/widget-builder/#do_follow_me_button"
   * @attr account REQUIRED Pinterest user account.
   * @attr label Text label on the button.
   */
  Closure follow_button = { Map attrs ->
    String account = attrs['account']?.toString()?.trim()

    if (!account)
    {
      return
    }

    out << g.withTag(
      name : 'a',
      attrs :
      [
        'data-pin-do' : 'buttonFollow',
        'href' : "http://www.pinterest.com/${account}"
      ],
      attrs['label']?.toString() ?: 'Follow'
    )
  }

  /**
   * Renders Pinterest embedded pin widget.
   * Requires "pinterest" module to be loaded with Resources plugin.
   * @see "http://business.pinterest.com/widget-builder/#do_embed_pin"
   * @attr id REQUIRED Unique identifier of Pinterest Pin.
   */
  Closure pin = { Map attrs ->
    String id = attrs['id']?.toString()?.trim()

    if (!id)
    {
      return
    }

    out << g.withTag(
      name : 'a',
      attrs :
      [
        'data-pin-do' : 'embedPin',
        'href' : "http://www.pinterest.com/pin/${id}"
      ]
    )
  }

  /**
   * Renders Pinterest "Pin It" button widget.
   * Requires "pinterest" module to be loaded with Resources plugin.
   * @see "http://business.pinterest.com/widget-builder/#do_pin_it_button"
   * @attr url REQUIRED URL address of target web page for the button.
   * @attr image REQUIRED URL address of the "pinned" image.
   * @attr description REQUIRED Description of the "pinned" image.
   * @attr color Background color of the button (PinterestPinItButtonColor or string).
   * @attr counter Position of button's pin counter (PinterestPinItButtonPinCountPosition or string).
   * @attr shape Shape of the button (PinterestPinItButtonShape or string).
   * @attr size Size of the button (PinterestPinItButtonSize or string).
   * @attr language Language of button's label.
   */
  Closure pin_it_button = { Map attrs ->
    String url = attrs['url']?.toString()?.trim()
    String image = attrs['image']?.toString()?.trim()
    String description = attrs['description']?.toString()?.trim()

    if (!url || !image || !description)
    {
      return
    }

    String color = attrs['color']?.toString() ?: 'gray'
    String counterPosition = attrs['counter']?.toString() ?: PinterestPinItButtonPinCountPosition.NONE.toString()
    String language = attrs['language'] ?: 'en'
    String shape = attrs['shape']?.toString() ?: PinterestPinItButtonShape.RECTANGULAR.toString()
    String size = attrs['size']?.toString() ?: PinterestPinItButtonSize.SMALL.toString()

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

    URIBuilder uri = new URIBuilder('http://www.pinterest.com/pin/create/button')
      .addParameter('url', url)
      .addParameter('media', image)
      .addParameter('description', description)

    out << g.withTag(
      name : 'a',
      attrs :
      [
        'data-pin-color' : shape == PinterestPinItButtonShape.RECTANGULAR.toString() ? color : null,
        'data-pin-config' : shape == PinterestPinItButtonShape.RECTANGULAR.toString() ? counterPosition : null,
        'data-pin-do' : 'buttonPin',
        'data-pin-height' : height,
        'data-pin-lang' : shape == PinterestPinItButtonShape.RECTANGULAR.toString() ? language : null,
        'data-pin-shape' : shape,
        'href' : uri.toString()
      ],
      "<img src=\"http://assets.pinterest.com/images/pidgets/pinit_fg_${language}_${shape}_${color}_${height}.png\"/>"
    )
  }

  /**
   * Renders Pinterest Profile widget with user's latest pins.
   * Requires "pinterest" module to be loaded with Resources plugin.
   * @see "http://business.pinterest.com/widget-builder/#do_embed_user"
   * @attr account REQUIRED Pinterest user account.
   * @attr height Total height of profile area in pixels.
   * @attr width Total width of profile area in pixels.
   * @attr image Width of profile area's image in pixels.
   */
  Closure profile = { Map attrs ->
    String account = attrs['account']?.toString()?.trim()

    if (!account)
    {
      return
    }

    out << g.withTag(
      name : 'a',
      attrs :
      [
        'data-pin-board-width' : attrs['width']?.toString(),
        'data-pin-do' : 'embedUser',
        'data-pin-scale-height' : attrs['height']?.toString(),
        'data-pin-scale-width' : attrs['image']?.toString(),
        'href' : "http://www.pinterest.com/${account}"
      ]
    )
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

  @Override
  String toString()
  {
    this.name().toLowerCase()
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

  @Override
  String toString()
  {
    this.name().toLowerCase()
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

  @Override
  String toString()
  {
    switch (this)
    {
      case RECTANGULAR :
        return 'rect'

      case CIRCULAR :
        return 'round'
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

  @Override
  String toString()
  {
    this.name().toLowerCase()
  }
}