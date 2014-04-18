package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(GravatarTagLib)
class GravatarTagLibTests
{
  void test_image_url_tag()
  {
    assert !applyTemplate('<gravatar:image_url/>')
    assert applyTemplate('<gravatar:image_url hash="hash"/>') == 'http://www.gravatar.com/avatar/hash'
    assert applyTemplate('<gravatar:image_url email="prokhor.ozornin@yandex.ru"/>') == 'http://www.gravatar.com/avatar/61b98d241eaa1ce237c979e7a8181d13'
    assert applyTemplate("<gravatar:image_url hash=\"hash\" extension=\"jpg\" default=\"${GravatarDefaultImage.WAVATAR}\" force_default=\"true\" rating=\"${GravatarImageRating.G}\" size=\"300\"/>") == 'http://www.gravatar.com/avatar/hash.jpg?default=wavatar&forcedefault=y&rating=g&size=300'

/*    def parameters = ['name':'value']
    assert applyTemplate("<gravatar:image_url hash=\"hash\" parameters=\"${parameters}\"/>") == 'http://www.gravatar.com/avatar/hash?name=value'*/
  }

  void test_profile_url_tag()
  {
    assert !applyTemplate('<gravatar:profile_url/>')
    assert applyTemplate('<gravatar:profile_url hash="hash"/>') == 'http://www.gravatar.com/hash'
    assert applyTemplate('<gravatar:profile_url email="prokhor.ozornin@yandex.ru"/>') == 'http://www.gravatar.com/61b98d241eaa1ce237c979e7a8181d13'
    assert applyTemplate('<gravatar:profile_url hash="hash" format="json"/>') == 'http://www.gravatar.com/hash.json'

/*    def parameters = ['name':'value']
    assert applyTemplate("<gravatar:profile_url hash=\"hash\" parameters=\"${parameters}\"/>") == 'http://www.gravatar.com/hash?name=value'*/
  }
}