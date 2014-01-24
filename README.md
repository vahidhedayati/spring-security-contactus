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




