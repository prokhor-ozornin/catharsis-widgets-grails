package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(SoundCloudTagLib)
class SoundCloudTagLibSpec extends Specification
{
  void profile_icon()
  {
    when :
      String template = applyTemplate('<soundcloud:profile_icon/>')
    then :
      template == ''

    when :
      template = applyTemplate('<soundcloud:profile_icon account=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<soundcloud:profile_icon account=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<soundcloud:profile_icon account="account"/>')
    then :
      template == '<iframe allowtransparency="true" frameborder="0" scrolling="no" src="https://w.soundcloud.com/icon?url=http%3A%2F%2Fsoundcloud.com%2Faccount&color=orange_white&size=32" style="width:32px; height:32px;"></iframe>'

    when :
      template = applyTemplate('<soundcloud:profile_icon account="account" color="color" size="1"/>')
    then :
      template == '<iframe allowtransparency="true" frameborder="0" scrolling="no" src="https://w.soundcloud.com/icon?url=http%3A%2F%2Fsoundcloud.com%2Faccount&color=color&size=1" style="width:1px; height:1px;"></iframe>'
  }
}