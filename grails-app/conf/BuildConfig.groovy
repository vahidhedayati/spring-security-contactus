grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.fork = [
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    inherits("global") {  }
    log "warn" 
    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
		mavenRepo "http://repo.spring.io/milestone/"
    }
    dependencies {}
    plugins {
        build(":release:3.0.1",
              ":rest-client-builder:1.0.3") {
            export = false
        }
		compile (":simple-captcha:0.9.9",  ":spring-security-core:2.0-RC2",  ":mail:1.0.1") {   export=false }
    }
}
