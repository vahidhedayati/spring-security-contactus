grails.project.work.dir = 'target'

grails.project.dependency.resolution = {

    inherits 'global'
    log 'warn'

    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
        mavenRepo "http://repo.spring.io/milestone/"
    }

    dependencies {
    }

    plugins {
        build ":release:3.0.1", ":rest-client-builder:1.0.3", {
            export = false
        }
        compile (":simple-captcha:0.9.9",  ":spring-security-core:2.0-RC2",  ":mail:1.0.1") {   export=false }
    }
}
