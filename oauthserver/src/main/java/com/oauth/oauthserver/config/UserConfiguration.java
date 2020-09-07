package com.oauth.oauthserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfiguration  extends GlobalAuthenticationConfigurerAdapter{
@Override
public void init(AuthenticationManagerBuilder auth) throws Exception {
	// TODO Auto-generated method stub
	//super.init(auth);
	PasswordEncoder passwordEncoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
	auth.inMemoryAuthentication().withUser("prashanta").password(passwordEncoder.encode("prashanta123")).roles("USER","ADMIN","MANAGER").authorities("CAN_READ","CAN_WRITE","CAN_DELETE").and().
	withUser("mami").password(passwordEncoder.encode("prashanta123")).roles("USER","ADMIN").authorities("CAN_READ","CAN_WRITE");
}

}
