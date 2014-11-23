package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(GravatarTagLib)
class GravatarTagLibSpec extends Specification
{
  void image_url()
  {
    when :
      String template = applyTemplate('<gravatar:image_url/>')
    then :
      template == ''

    when :
      template = applyTemplate('<gravatar:image_url hash=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<gravatar:image_url hash=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<gravatar:image_url hash="hash"/>')
    then :
      template == 'http://www.gravatar.com/avatar/hash'

    when :
      template = applyTemplate('<gravatar:image_url email=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<gravatar:image_url email=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<gravatar:image_url email="prokhor.ozornin@yandex.ru"/>')
    then :
      template == 'http://www.gravatar.com/avatar/61b98d241eaa1ce237c979e7a8181d13'

    when :
      template = applyTemplate("<gravatar:image_url hash=\"hash\" extension=\"jpg\" default=\"${GravatarDefaultImage.WAVATAR}\" force_default=\"true\" rating=\"${GravatarImageRating.G}\" size=\"300\"/>")
    then :
      template == 'http://www.gravatar.com/avatar/hash.jpg?default=wavatar&forcedefault=y&rating=g&size=300'
  }

  void profile_url()
  {
    when :
      String template = applyTemplate('<gravatar:profile_url/>')
    then :
      template == ''

    when :
      template = applyTemplate('<gravatar:profile_url hash=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<gravatar:profile_url hash=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<gravatar:profile_url hash="hash"/>')
    then :
      template == 'http://www.gravatar.com/hash'

    when :
      template = applyTemplate('<gravatar:profile_url email=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<gravatar:profile_url email=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<gravatar:profile_url email="prokhor.ozornin@yandex.ru"/>')
    then :
      template == 'http://www.gravatar.com/61b98d241eaa1ce237c979e7a8181d13'

    when :
      template = applyTemplate('<gravatar:profile_url hash="hash" format="json"/>')
    then :
      template == 'http://www.gravatar.com/hash.json'
  }
}