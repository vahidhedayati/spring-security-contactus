spring-security-contactus 0.2
=========

Grails  spring-security-contactus is a rewrite of http://grails.org/plugin/contact-me 


Dependency :

        compile ":spring-security-contactus:0.2"





## 2.4 notes:
To get it working under grails 2.4 for now you will need to add the following to your BuildConfig.groovy, under plugins:

   runtime ":cache-headers:1.1.7"

#### Full instructions for 2.4 apps:

Tried to follow this page and found I was lost, so I have made a new page explaining from the very start on a 2.4 asset driven site, should be useful for pre 2.4 resources based. More informative than a lot of this here. (Grails 2.4 from scratch with spring-security-contactus)[https://github.com/vahidhedayati/spring-security-contactus/blob/master/grails24instructions.md]




install jquery-ui within your project 

Then enable both jquery and jquery-ui in your main.gsp

	
	<g:javascript library="jquery"/>
	<g:javascript library="jquery-ui"/>
	

Refer to your contact page via a remotefunction like below:
		
		
	 <!-- added in spring-security-contactus form here as a button for test purposes -->
	 <button id=boxbtn onclick="<g:remoteFunction controller="ContactUs" action="index" update="siteContent" />">contact</button>
	 <div id="siteContent"> </div>
		                






Ensure you have enabled and configured all of these values in your config.groovy :

	
	contactus.to.address='youremail@yourdomain.com'
	contactus.email.view='/contactUs/emailBody'
	contactus.form.view='/contactUs/index.gsp'
	contactus.thanks.view='/contactUs/thanks'
	contactus.dbtable='ContactUs'
	
	simpleCaptcha {
			// font size used in CAPTCHA images
			fontSize = 30
			height = 200
			width = 200
			// number of characters in CAPTCHA text
			length = 6
			// amount of space between the bottom of the CAPTCHA text and the bottom of the CAPTCHA image
			bottomPadding = 16
			// distance between the diagonal lines used to obfuscate the text
			lineSpacing = 10
			// the charcters shown in the CAPTCHA text must be one of the following
			chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	}
	grails.plugin.springsecurity.controllerAnnotations.staticRules = [
			'/': ['permitAll'],
			'/index': ['permitAll'],
			'/index.gsp': ['permitAll'],
			'/**/js/**': ['permitAll'],
			'/**/css/**': ['permitAll'],
			'/**/images/**': ['permitAll'],
			'/**/favicon.ico': ['permitAll'],
			'/**/simpleCaptcha/captcha': ['permitAll'],
			'/simpleCaptcha/captcha': ['permitAll'],
			'/**/contactus/**': ['permitAll'],
			'/contactus/**': ['permitAll']
	]
	




refer to :
https://github.com/vahidhedayati/spcontacttest/ for a sample site using this plugin



You will need a domain class called ContactUs or whatever you wish it to be called - just ensure the config in the config.grooy table name matches this name: (this is the sample domainClass taken from within the plugin - it uses to generate the form view:) it needs to be identical so far as fields and configuration goes.


package grails.plugins.springsecurity.contactus
	
	class ContactUsMaster {
		Date dateCreated
		String name
		String email
		String username
		String subject
		String message
		String captcha
		static constraints = {
			name(blank: false)
			email(blank: false, email: true)
			subject(blank: false)
			message(minSize: 10, blank: false)
		}
		static optionals = [ 'username'  ]
		static mapping = {
			message type: 'text'
			username  defaultValue: ''
		}
		static transients = ['captcha']
		static afterContactUs = { form, params ->
			new ContactUsMaster(name:form.name, email:form.email, username:form.username, subject:form.subject, message:form.message).save()
		}
	}
	
	
