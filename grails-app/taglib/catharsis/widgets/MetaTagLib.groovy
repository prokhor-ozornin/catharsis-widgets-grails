package catharsis.widgets

class MetaTagLib
{
  static final String namespace = 'meta'

  /**
   * @attr content_type REQUIRED
   * @attr charset
   */
  Closure content_type = { Map attrs ->
    String contentType = attrs['content_type']?.toString()?.trim()
    String charset = attrs['charset']?.toString()?.trim()

    if (!contentType)
    {
      return
    }

    out << g.withTag(
      name : 'meta',
      attrs :
      [
        'http-equiv' : 'Content-Type',
        'content' : charset ? "${contentType}; charset=${charset}" : contentType
      ]
    )
  }

  /**
   * @attr copyright REQUIRED
   */
  Closure copyright = { Map attrs ->
    String copyright = attrs['copyright']?.toString()?.trim()

    if (!copyright)
    {
      return
    }

    out << g.withTag(
      name : 'meta',
      attrs :
      [
        'name' : 'copyright',
        'content' : copyright
      ]
    )
  }

  /**
   * @attr description REQUIRED
   */
  Closure description = { Map attrs ->
    String description = attrs['description']?.toString()?.trim()

    if (!description)
    {
      return
    }

    out << g.withTag(
      name : 'meta',
      attrs :
      [
        'name' : 'description',
        'content' : description
      ]
    )
  }

  /**
   * @attr generator REQUIRED
   */
  Closure generator = { Map attrs ->
    String generator = attrs['generator']?.toString()?.trim()

    if (!generator)
    {
      return
    }

    out << g.withTag(
      name : 'meta',
      attrs :
      [
        'name' : 'generator',
        'content' : generator
      ]
    )
  }

  /**
   * @attr keywords REQUIRED
   */
  Closure keywords = { Map attrs ->
    String keywords = attrs['keywords'] instanceof Collection ? (attrs['keywords'] as Collection).join(',') : attrs['keywords']?.toString()?.trim()

    if (!keywords)
    {
      return
    }

    out << g.withTag(
      name : 'meta',
      attrs :
      [
        'name' : 'keywords',
        'content' : keywords
      ]
    )
  }

  /**
   * @attr seconds REQUIRED
   * @attr url
   */
  Closure refresh = { Map attrs ->
    String seconds = attrs['seconds']?.toString()?.trim()
    String url = attrs['url']?.toString()?.trim()

    if (!seconds)
    {
      return
    }

    out << g.withTag(
      name : 'meta',
      attrs :
      [
        'name' : 'refresh',
        'content' : url ? "${seconds.toInteger()}; ${url}" : seconds.toInteger().toString()
      ]
    )
  }

  /**
   * @attr width REQUIRED
   * @attr initial_scale REQUIRED
   */
  Closure viewport = { Map attrs ->
    String width = attrs['width']?.toString()?.trim()
    String initialScale = attrs['initial_scale']?.toString()?.trim()

    if (!width || !initialScale)
    {
      return
    }

    out << g.withTag(
      name : 'meta',
      attrs :
      [
        'name' : 'viewport',
        'content' : "width=${width}, initial-scale=${initialScale}"
      ]
    )
  }
}