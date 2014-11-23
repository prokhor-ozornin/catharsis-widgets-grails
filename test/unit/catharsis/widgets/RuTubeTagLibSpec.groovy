package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(RuTubeTagLib)
class RuTubeTagLibSpec extends Specification
{
  void video()
  {
    when :
      String template = applyTemplate('<rutube:video/>')
    then :
      template == ''

    when :
      template = applyTemplate('<rutube:video id="id" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<rutube:video id="id" width="width"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<rutube:video height="height" width="width"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<rutube:video id="" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<rutube:video id=" " width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<rutube:video id="id" width="" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<rutube:video id="id" width=" " height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<rutube:video id="id" width="width" height=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<rutube:video id="id" width="width" height=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<rutube:video id="id" width="width" height="height"/>')
    then :
      template == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" scrolling="no" width="width" height="height" src="http://rutube.ru/embed/id"></iframe>'
  }
}