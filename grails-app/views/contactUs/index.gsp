
	<div id="contactUsWrapper">
		<div id="contact-area" class="myform" style="width: 29em;">

		<h1>Feedback</h1>
		
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>

			
	<g:formRemote name="ContactUss" url="[controller:'ContactUs', action:'send']" 
              update="siteContent"
              >



	<div class="fieldcontain ${hasErrors(bean: contactUs, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="name.label" default="My name is" /><br/>
		<span class="required-indicator">required</span>
	</label>
		<g:textField name="name" value="${contactUs?.name}" required="true"/>
		
	<g:if test="${hasErrors(bean: contactUs, field: 'name', 'error')}">
	<div class="errors" style="margin-left: -0.2em;  width: auto; display: inline-block;*zoom: 1;*display: inline;">
	<g:renderErrors  bean="${contactUs}" as="list" field="name"/>
	</div>
	</g:if>
		
	</div>

	
	<g:if test="${!user }">	
	<div class="fieldcontain ${hasErrors(bean: contactUs, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="name.label" default="My email is" /><br/>
		<span class="required-indicator">required</span>
	</label>
		<g:textField name="email" value="${contactUs?.email}"/>
		<g:if test="${hasErrors(bean: contactUs, field: 'email', 'error')}">
	<div class="errors" style="margin-left: -0.2em;  width: auto; display: inline-block;*zoom: 1;*display: inline;">
	<g:renderErrors  bean="${contactUs}" as="list" field="email"/>
	</div>
	</g:if>
	</div>
	
	</g:if>
	<g:else>
			<g:hiddenField name="email" value="${usersemail}"/>
	</g:else>
	
	<g:hiddenField name="username" value="${user ?: 'Guest' }"/>
	

	<div class="fieldcontain ${hasErrors(bean: contactUs, field: 'subject', 'error')} ">
	<label for="subject">
		My website <br> or subject is<br>
		<span class="required-indicator">required</span>
	</label>
	<input type=text name="subject" value="${contactUs?.subject}" size="50"/>
	<g:if test="${hasErrors(bean: contactUs, field: 'subject', 'error')}">
	<div class="errors" style="margin-left: -0.2em;  width: auto; display: inline-block;*zoom: 1;*display: inline;">
	<g:renderErrors  bean="${contactUs}" as="list" field="subject"/>

	</div>
	</g:if>
	</div>

	
	
	<div id="contact-area">

	<div class="fieldcontain ${hasErrors(bean: contactUs, field: 'message', 'error')} ">
	<label for="message">
	Question or<br> Comments<br>
	
	<span class="required-indicator">required</span>
	</label>
	<textarea class="myform" name="message" rows="10">${contactUs?.message}</textarea>
	<g:if test="${hasErrors(bean: contactUs, field: 'message', 'error')}">
	<div class="errors" style="margin-left: -0.2em;  width: auto; display: inline-block;*zoom: 1;*display: inline;">
	<g:renderErrors  bean="${contactUs}" as="list" field="message"/>
	</div>
	</g:if>
	</div> 

	
	<g:if test="${!user }">


	<div class="fieldcontain ${hasErrors(bean: contactUs, field: 'captcha', 'errro')} ">
	
	<label for="captcha"></label>
	<div class="tbutton">
	<%def rn = new Random().nextInt(2245565)%>
	<img src="${createLink(controller: 'simpleCaptcha', action: 'captcha')}?${rn}"/> <br/>
	<div class="tbutton">Human Verification:<br> Type in characters above</div></div><br/>
	<label for="captcha">Image Value<br/>
	<span class="required-indicator">required</span>
	</label>
	<g:textField name="captcha"/>
	
<g:if test="${hasErrors(bean: contactUs, field: 'captcha', 'error')}">
	<div class="errors" style="margin-left: -0.2em;  width: auto; display: inline-block;*zoom: 1;*display: inline;">
	<g:renderErrors  bean="${contactUs}" as="list" field="captcha"/>
	</div>
	</g:if>
	</div>
	</g:if>
</div>	

	<div class="fieldcontain" >
<label for="captcha"></label>

			<g:submitToRemote class="myformsubmit" url="[controller:'ContactUs', action:'send']" update="siteContent"  value="Contact us" />
</div>

</g:formRemote>
</div>

</div>
