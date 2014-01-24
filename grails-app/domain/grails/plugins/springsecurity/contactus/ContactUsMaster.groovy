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
