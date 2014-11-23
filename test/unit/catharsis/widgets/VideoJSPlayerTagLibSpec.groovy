package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(VideoJSPlayerTagLib)
class VideoJSPlayerTagLibSpec extends Specification
{
  void player()
  {
    when :
      String template = applyTemplate("<videojs:player width=\"width\" height=\"height\" videos=\"['http://vjs.zencdn.net/v/oceans.mp4' : 'video/mp4', 'http://vjs.zencdn.net/v/oceans.webm' : 'video/webm']\"><track kind=\"captions\" src=\"http://www.videojs.com/vtt/captions.vtt\" srclang=\"en\" label=\"English\"></track></videojs:player>")
    then :
      template == '<video width="width" height="height" class="video-js vjs-default-skin" controls="controls" preload="auto" data-setup="{}"><source src="http://vjs.zencdn.net/v/oceans.mp4" type="video/mp4"></source><source src="http://vjs.zencdn.net/v/oceans.webm" type="video/webm"></source><track kind="captions" src="http://www.videojs.com/vtt/captions.vtt" srclang="en" label="English"></track></video>'
  }
}