package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(CackleTagLib)
class CackleTagLibSpec extends Specification
{
  void comments()
  {
    when :
      String template = applyTemplate('<cackle:comments/>')
    then :
      template == ''

    when :
      template = applyTemplate('<cackle:comments account=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<cackle:comments account=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<cackle:comments account="account"/>')
    then :
      template.contains('<div id="mc-container"></div>')
      template.contains('{"widget":"Comment","id":"account"}')
  }

  void comments_count()
  {
    when :
      String template = applyTemplate('<cackle:comments_count/>')
    then :
      template == ''

    when :
      template = applyTemplate('<cackle:comments_count account=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<cackle:comments_count account=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<cackle:comments_count account="account"/>')
    then :
      template.contains('{"widget":"CommentCount","id":"account"}')
  }

  void latest_comments()
  {
    when :
      String template = applyTemplate('<cackle:latest_comments/>')
    then :
      template == ''

    when :
      template = applyTemplate('<cackle:latest_comments account=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<cackle:latest_comments account=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<cackle:latest_comments account="account"/>')
    then :
      template.contains('<div id="mc-last"></div>')
      template.contains('{"widget":"CommentRecent","id":"account","size":5,"avatarSize":32,"textSize":150,"titleSize":40}')

    when :
      template = applyTemplate('<cackle:latest_comments account="account" max="1" avatar_size="2" text_size="3" title_size="4"/>')
    then :
      template.contains('<div id="mc-last"></div>')
      template.contains('{"widget":"CommentRecent","id":"account","size":1,"avatarSize":2,"textSize":3,"titleSize":4}')
  }

  void login()
  {
    when :
      String template = applyTemplate('<cackle:login/>')
    then :
      template == ''

    when :
      template = applyTemplate('<cackle:login account=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<cackle:login account=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<cackle:login account="account"/>')
    then :
      template.contains('<div id="mc-login"></div>')
      template.contains('{"widget":"Login","id":"account"}')
  }
}