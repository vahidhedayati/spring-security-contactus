package grails.plugins.springsecurity.contactus

class ContactUsController {
	def simpleCaptchaService
	def contactUsService
	def mailService
	def springSecurityService
	
	def index() {
		def user = springSecurityService.currentUser
		def usersemail=user?.email
		def currentview=grailsApplication.config.contactus.form.view ?: "/contactUs/index"
		render (view: currentview, model:[contactUs: new ContactUsMaster(params), user:user,   usersemail:usersemail])
	}
	
	def emailBody() { }
	
	def send = { ContactUsVerify contactUs ->
		def user, usersemail
		Boolean captchaValid = 0
		if (session.SPRING_SECURITY_CONTEXT?.authentication?.authenticated!=null) {
			captchaValid = 1
			user = springSecurityService.currentUser
			usersemail=user?.email
		}else{
			captchaValid = simpleCaptchaService.validateCaptcha(params.captcha)
		}	
	
		if(!captchaValid) {
			contactUs.errors.rejectValue('captcha', null, "Invalid Captcha Response")
		}	
		
        if (captchaValid && !contactUs.hasErrors() ) {
			try {
				sendMail {
					def admingroup=grailsApplication.config.contactus.admingroup 
					if (admingroup) {
						def emails=contactUsService.getAdminEmail(admingroup)					
						if (!emails) {
							def sendto=grailsApplication.config.contactus.to.address
							if (sendto) { 
							  to sendto
							  log.info ("contactUs Email: Individual Email being sent to: ${sendto}")
							}  
						}else{
							def primary=[]
							emails.each {
								primary.add(it)
							}
							log.info ("contactUs Email: Group Email being sent to: "+primary)
							to primary
						}
					}else{
						def sendto=grailsApplication.config.contactus.to.address
						if (sendto) {
						  to sendto
						  log.info ("contactUs Email: Individual Email being sent to: ${sendto}")
						}
					}	
							
			        from contactUs.email
			        subject "${(grailsApplication.config.contactus.subject.prefix ?: "Contact Me:")} ${contactUs.subject}"
			        body( view: grailsApplication.config.contactus.email.view ?: "/emailBody", 
			        plugin: grailsApplication.config.contactus.email.view ? null : "/emailBody",
			        model:[contactUs:contactUs])
				}
				def dbtable=	grailsApplication.config.contactus.dbtable ?: "ContactUs"
				Class domainClass = grailsApplication?.domainClasses.find { it.clazz.simpleName == dbtable }?.clazz
				if (!domainClass) {
					ContactUsMaster.afterContactUs(contactUs, params)
				}else{
					domainClass.afterContactUs(contactUs, params)
				}
				
		        render( view: grailsApplication.config.contactus.thanks.view ?: "/thankYou",
	            plugin: grailsApplication.config.contactus.thanks.view ? null : "/thankYou",
	            model: [contactUs: contactUs])
		        return
			} catch (Exception e) {
				log.info e.printStackTrace()
			}
		}
		flash.error = message(code:'contactUs.email.badcaptcha', args:params.captcha)
		flash.chainedParams = params
		render( view: grailsApplication.config.contactus.form.view ?:  "/contactUs/index",
        plugin: grailsApplication.config.contactus.form.view ? null : "/contactUs/index",
        model: [contactUs: contactUs, user:user, usersemail:usersemail])
		return
    }
	
	// EmailValidator 
	static final EmailValidator = { String email, command ->
		// Check out mx records of given email address - if not valid do not proceed
		def emaildomain=email.substring(email.indexOf('@')+1, email.length())
		CheckMX cm=new CheckMX()
		int hasMx=cm.doLookup(emaildomain)
		if (hasMx==0) {
			return 'contactUs.email.mx'
		}
	}
}

class ContactUsVerify {
    String name
    String email
	String username
    String subject
    String message
	String captcha
	static optionals = [ 'username' ]
	static mapping = {
		message type: 'text'
		username  defaultValue: ''
    }	
    static constraints = {
        name(blank: false)
        email(blank: false, email: true, validator: ContactUsController.EmailValidator) 
        subject(blank: false)
        message(minSize: 10,blank: false)
    }
	static transients = ['captcha']
}
