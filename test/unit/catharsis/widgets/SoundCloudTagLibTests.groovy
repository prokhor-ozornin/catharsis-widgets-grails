package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(SoundCloudTagLib)
class SoundCloudTagLibTests
{
  void test_profile_icon_tag()
  {
    assert !applyTemplate('<soundcloud:profile_icon/>')
    assert applyTemplate('<soundcloud:profile_icon account="account"/>') == "<iframe allowtransparency=\"true\" frameborder=\"0\" scrolling=\"no\" src=\"https://w.soundcloud.com/icon/?url=${"http://soundcloud.com/account&color=orange_white&size=32".encodeAsURL()}\" style=\"width: 32px; height: 32px;\"></iframe>"
    assert applyTemplate('<soundcloud:profile_icon account="account" color="color" size="1"/>') == "<iframe allowtransparency=\"true\" frameborder=\"0\" scrolling=\"no\" src=\"https://w.soundcloud.com/icon/?url=${"http://soundcloud.com/account&color=color&size=1".encodeAsURL()}\" style=\"width: 1px; height: 1px;\"></iframe>"
  }
}