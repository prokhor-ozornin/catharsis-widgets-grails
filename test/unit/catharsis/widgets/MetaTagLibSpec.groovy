package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(MetaTagLib)
class MetaTagLibSpec extends Specification
{
  void content_type()
  {
    when :
      String template = applyTemplate('<meta:content_type/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:content_type content_type=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:content_type content_type=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:content_type content_type="text/html"/>')
    then :
      template == '<meta http-equiv="Content-Type" content="text/html"></meta>'

    when :
      template = applyTemplate('<meta:content_type content_type="text/html" charset="UTF-8"/>')
    then :
      template == '<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>'
  }

  void copyright()
  {
    when :
      String template = applyTemplate('<meta:copyright/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:copyright copyright=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:copyright copyright=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:copyright copyright="Prokhor Ozornin"/>')
    then :
      template == '<meta name="copyright" content="Prokhor Ozornin"></meta>'
  }

  void description()
  {
    when :
      String template = applyTemplate('<meta:description/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:description description=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:description description=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:description description="My site description"/>')
    then :
      template == '<meta name="description" content="My site description"></meta>'
  }

  void generator()
  {
    when :
      String template = applyTemplate('<meta:generator/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:generator generator=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:generator generator=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:generator generator="Grails"/>')
    then :
      template == '<meta name="generator" content="Grails"></meta>'
  }

  void keywords()
  {
    when :
      String template = applyTemplate('<meta:keywords/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:keywords keywords=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:keywords keywords=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate("<meta:keywords keywords=\"${[]}\"/>")
    then :
      template == ''

    when :
      template = applyTemplate('<meta:keywords keywords="groovy, grails"/>')
    then :
      template == '<meta name="keywords" content="groovy, grails"></meta>'

    when :
      template = applyTemplate("<meta:keywords keywords=\"['groovy', 'grails']\"/>")
    then :
      template == '<meta name="keywords" content="groovy,grails"></meta>'
  }

  void refresh()
  {
    when :
      String template = applyTemplate('<meta:refresh/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:refresh seconds=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:refresh seconds=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:refresh seconds="1"/>')
    then :
      template == '<meta name="refresh" content="1"></meta>'

    when :
      template = applyTemplate('<meta:refresh seconds="1" url="http://grails.org"/>')
    then :
      template == '<meta name="refresh" content="1; http://grails.org"></meta>'
  }

  void viewport()
  {
    when :
      String template = applyTemplate('<meta:viewport/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:viewport width="" initial_scale="100%"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:viewport width=" " initial_scale="100%"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:viewport width="device-width" initial_scale=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:viewport width="device-width" initial_scale=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<meta:refresh seconds="1"/>')
    then :
      template == '<meta name="refresh" content="1"></meta>'

    when :
      template = applyTemplate('<meta:refresh seconds="1" url="http://grails.org"/>')
    then :
      template == '<meta name="refresh" content="1; http://grails.org"></meta>'
  }
}