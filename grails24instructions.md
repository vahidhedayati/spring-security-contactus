# Grails 2.4 springsecurity-contactus
##### From scratch


```bash
grails create-app testcontact
cd testcontact
```


##### configure  grails-app/conf/BuildConfig.groovy

```groovy
    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.53"
        // plugins for the compile step
        compile ":scaffolding:2.1.0"
        compile ':cache:1.1.6'
        compile ":asset-pipeline:1.8.7"
        // plugins needed at runtime but not for compilation
        runtime ":hibernate4:4.3.5.3" // or ":hibernate:3.6.10.15"
        runtime ":database-migration:1.4.0"
        runtime ":jquery:1.11.1"
	// These are all added to make springsecurity-contactus work on grails 2.4 app
	compile ":jquery-ui:1.10.3"
	compile ":spring-security-contactus:0.2"
	compile ":spring-security-core:2.0-RC4"
	compile ":simple-captcha:0.9.9" 
	runtime ":cache-headers:1.1.7"
    }
```



##### Once that is done, run:
 
```bash
grails refresh-dependencies

grails create-controller contactMe

vi grails-app/views/contactMe/index.gsp
```



##### index.gsp:
```gsp
<!DOCTYPE html>
<html>
<head>
     <meta name='layout' content="main"/>
  <title><g:message code="testcontact.title.label" args="[entityName]" default="Test Page"/></title>
</head>
<body>
  
 <button id=boxbtn onclick="<g:remoteFunction controller="ContactUs" action="index" update="siteContent" />">contact</button>


 <div id="siteContent">
 <!-- Where out button will output its remoteCall -->
 </div>
 
</body>
</html>
```



###### Enable jquery-ui in your grails-app/assets/javascripts  
```js
//= require jquery
//= require jquery-ui/js/jquery-ui-1.10.3.custom.min
//= require_tree .
//= require_self

```    

###### 


##### Enable springsecurity within your app:   
````bash
grails s2-quickstart com.yourapp User Authority

````

##### Be careful when running above command, it will add your Config.groovy entries which if you have already copied/pasted from plugin information, a conflict will occur.


##### configure  grails-app/conf/Config.groovy
By default s2-quick start has generated some entries what you will need, is some ammended values as shown below.


```groovy


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


// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.yourapp.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.yourapp.UserAuthority'
grails.plugin.springsecurity.authority.className = 'com.yourapp.Authority'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/':                              ['permitAll'],
	'/index':                         ['permitAll'],
	'/index.gsp':                     ['permitAll'],
	'/assets/**':                     ['permitAll'],
	'/**/js/**':                      ['permitAll'],
	'/**/css/**':                     ['permitAll'],
	'/**/images/**':                  ['permitAll'],
        '/**/favicon.ico': ['permitAll'],
        '/**/simpleCaptcha/captcha': ['permitAll'],
        '/simpleCaptcha/captcha': ['permitAll'],
        '/**/contactus/**': ['permitAll'],
        '/contactus/**': ['permitAll'],
	'/test/contactMe/**': ['permitAll'],
	'/**/contactMe/**': ['permitAll']
	
]

```



For the spring security part you need:

```groovy
        '/**/simpleCaptcha/captcha': ['permitAll'],
        '/simpleCaptcha/captcha': ['permitAll'],
        '/**/contactus/**': ['permitAll'],
        '/contactus/**': ['permitAll'],
```

For this application we created a test controller called contactMe, any of them should do:

```groovy        
	'/test/contactMe/**': ['permitAll'],
	'/**/contactMe/**': ['permitAll']

```

That should cover it all.
