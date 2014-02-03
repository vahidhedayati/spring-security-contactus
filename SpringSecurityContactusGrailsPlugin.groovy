class SpringSecurityContactusGrailsPlugin {
    def version = "0.1"
    def grailsVersion = "2.0 > *"
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]
    def title = "Spring Security Contactus Plugin" // Headline display name of the plugin
    def author = "Vahid Hedayati"
    def authorEmail = "badvad@gmail.com"
    def description = 'Contact Form for spring security - authenticated users can post feedback unauthenticated users need to validate simple-captcha'
    def documentation = "http://grails.org/plugin/spring-security-contactus"
	def license = "APACHE"
    def developers = [ [ name: "Vahid Hedayati", email: "badvad@gmail.com" ]]
	def issueManagement = [system: 'GITHUB', url: 'https://github.com/vahidhedayati/spring-security-contactus/issues']
	def scm = [url: 'https://github.com/vahidhedayati/spring-security-contactus']

}
