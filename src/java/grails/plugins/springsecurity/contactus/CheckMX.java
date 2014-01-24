package grails.plugins.springsecurity.contactus;


import java.util.Hashtable;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;


public class CheckMX {

         static int doLookup( String hostName ) throws NamingException {
                 Attributes attrs;
                
                 try {
                        Hashtable<String, String> env = new Hashtable<String, String>();
                        env.put("java.naming.factory.initial",
                                        "com.sun.jndi.dns.DnsContextFactory");
                        DirContext ictx = new InitialDirContext(env);
                        attrs = ictx.getAttributes(hostName, new String[] { "MX" });
                } catch (Exception e) {

                        return( 0 );
                }
                Attribute attr = attrs.get( "MX" );
                 if( attr == null ) {
                         return( 0 );
                 }else{
                         return( attr.size() );
                 }
         }
}