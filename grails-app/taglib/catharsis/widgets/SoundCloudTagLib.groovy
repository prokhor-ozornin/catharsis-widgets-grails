package catharsis.widgets

/**
 * SoundCloud tags library
 * @see "http://soundcloud.com"
 */
class SoundCloudTagLib
{
  static final String namespace = "soundcloud"

  /**
   * Renders SoundCloud user's profile icon.
   * @see "https://soundcloud.com/pages/embed"
   * @attr account REQUIRED SoundCloud user's account name.
   * @attr color Color of profile icon (SoundCloudProfileIconColor or string).
   * @attr size Edge size of profile icon in pixels.
   */
  def profile_icon = { attrs ->
    if (!attrs.account)
    {
      return;
    }

    def size = attrs.size ?: SoundCloudProfileIconSize.SIZE_32
    def url = "http://soundcloud.com/${attrs.account}&color=${attrs.color ?: SoundCloudProfileIconColor.ORANGE_WHITE}&size=${size}"

    out << g.withTag(name: "iframe", attrs:
    [
      "allowtransparency" : true,
      "frameborder" : "0",
      "scrolling" : "no",
      "src" : "https://w.soundcloud.com/icon/?url=${url}",
      "style" : "width: ${size}px; height: ${size}px;"
    ])
  }
}

/**
 * Size of SoundCloud user's profile icon in pixels.
 */
enum SoundCloudProfileIconSize
{
  /**
   * 16x16
   */
  SIZE_16,

  /**
   * 24x24
   */
  SIZE_24,

  /**
   * 32x32
   */
  SIZE_32,

  /**
   * 40x40
   */
  SIZE_40,

  /**
   * 48x48
   */
  SIZE_48,

  /**
   * 56x56
   */
  SIZE_56,

  /**
   * 64x64
   */
  SIZE_64

  String toString()
  {
    switch (this)
    {
      case SIZE_16 :
        return "16";

      case SIZE_24 :
        return "24";

      case SIZE_32 :
        return "32";

      case SIZE_40 :
        return "40";

      case SIZE_48 :
        return "48";

      case SIZE_56 :
        return "56";

      case SIZE_64 :
        return "64";
    }
  }
}

enum SoundCloudProfileIconColor
{
  BLACK_WHITE,

  ORANGE_TRANSPARENT,

  ORANGE_WHITE,

  WHITE_ORANGE,

  WHITE_TRANSPARENT

  String toString()
  {
    name().toLowerCase()
  }
}