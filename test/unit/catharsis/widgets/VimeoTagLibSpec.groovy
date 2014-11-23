package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(VimeoTagLib)
class VimeoTagLibSpec extends Specification
{
  void video()
  {
    when :
      String template = applyTemplate('<vimeo:video/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vimeo:video id="id" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vimeo:video id="id" width="width"/>')
    then :
      template == ''

    when :
      template == applyTemplate('<vimeo:video height="height" width="width"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vimeo:video video="" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vimeo:video video=" " width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vimeo:video video="video" width="" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vimeo:video video="video" width=" " height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vimeo:video video="video" width="width" height=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vimeo:video video="video" width="width" height=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vimeo:video video="video" width="width" height="height"/>')
    then :
      template == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="https://player.vimeo.com/video/video?badge=0"></iframe>'

    when :
      template = applyTemplate('<vimeo:video video="video" width="width" height="height" autoplay="true" loop="true"/>')
    then :
      template == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="https://player.vimeo.com/video/video?badge=0&autoplay=1&loop=1"></iframe>'
  }
}
