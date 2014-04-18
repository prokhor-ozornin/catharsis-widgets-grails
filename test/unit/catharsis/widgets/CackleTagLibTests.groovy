package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(CackleTagLib)
class CackleTagLibTests
{
  void test_comments_tag()
  {
    assert !applyTemplate('<cackle:comments/>')
    def html = applyTemplate('<cackle:comments account="account"/>')
    assert html.contains('<div id="mc-container"></div>')
    assert html.contains('{"widget":"Comment","id":"account"}')
  }

  void test_comments_count_tag()
  {
    assert !applyTemplate('<cackle:comments_count/>')
    assert applyTemplate('<cackle:comments_count account="account"/>').contains('{"widget":"CommentCount","id":"account"}')
  }

  void test_latest_comments_tag()
  {
    assert !applyTemplate('<cackle:latest_comments/>')

    def html = applyTemplate('<cackle:latest_comments account="account"/>')
    assert html.contains('<div id="mc-last"></div>')
    assert html.contains('{"widget":"CommentRecent","id":"account","size":5,"avatarSize":32,"textSize":150,"titleSize":40}')

    html = applyTemplate('<cackle:latest_comments account="account" max="1" avatar_size="2" text_size="3" title_size="4"/>')
    assert html.contains('<div id="mc-last"></div>')
    assert html.contains('{"widget":"CommentRecent","id":"account","size":1,"avatarSize":2,"textSize":3,"titleSize":4}')
  }

  void test_login_tag()
  {
    assert !applyTemplate('<cackle:login/>')

    def html = applyTemplate('<cackle:login account="account"/>')
    assert html.contains('<div id="mc-login"></div>')
    assert html.contains('{"widget":"Login","id":"account"}')
  }
}
