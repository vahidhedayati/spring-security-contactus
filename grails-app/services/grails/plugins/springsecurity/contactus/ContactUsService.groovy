package grails.plugins.springsecurity.contactus

class ContactUsService {
	def getAdminEmail(String admin) {
		if (!admin) { admin='ROLE_ADMIN' }
		def authority= Authority.findByAuthority(admin)
		def mylist=UserAuthority.findAllByAuthority(authority).collect() { it.user.email} as Set
		return mylist
	}
}