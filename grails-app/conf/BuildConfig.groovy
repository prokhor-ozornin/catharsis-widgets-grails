grails.project.class.dir = 'target/classes'
grails.project.test.class.dir = 'target/test-classes'
grails.project.test.reports.dir = 'target/test-reports'
grails.project.target.level = 1.7

grails.project.dependency.resolver = 'maven'

grails.project.dependency.resolution =
{
  inherits 'global'
  log 'warn'

  repositories
  {
    grailsCentral()
    grailsPlugins()
    mavenCentral()
  }

  dependencies
  {
    compile 'org.apache.httpcomponents:httpclient:4.3.6'
  }

  plugins
  {
    build ':release:3.0.1', ':rest-client-builder:2.0.3', ':tomcat:8.0.15', { export = false }
    runtime ':resources:1.2.14'
  }
}