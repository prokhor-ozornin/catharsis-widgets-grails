package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(YouTubeTagLib)
class YouTubeTagLibSpec extends Specification
{
  void video()
  {
    when :
      String template = applyTemplate('<youtube:video/>')
    then :
      template == ''

    when :
      template = applyTemplate('<youtube:video id="id" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<youtube:video id="id" width="width"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<youtube:video height="height" width="width"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<youtube:video id="" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<youtube:video id=" " width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<youtube:video id="id" width="" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<youtube:video id="id" width=" " height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<youtube:video id="id" width="width" height=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<youtube:video id="id" width="width" height=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<youtube:video id="id" width="width" height="height"/>')
    then :
      template == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://www.youtube.com/embed/id"></iframe>'

    when :
      template = applyTemplate('<youtube:video id="id" width="width" height="height" private_mode="true" secure_mode="true"/>')
    then :
      template == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="https://www.youtube-nocookie.com/embed/id"></iframe>'
  }
}
