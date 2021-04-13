package kg.aios.application.feature;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kg.aios.application.model.JobApplication;
import kg.aios.application.util.FeatureException;

@Component("SmtpMailSenderFeature")
public class SmtpMailSenderFeature implements FeatureApi {
	
	private static final Logger logger = LoggerFactory.getLogger(SmtpMailSenderFeature.class);

	@Override
	public void perform(String featureJson, JobApplication application) throws FeatureException {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		MailDetails details = null;
		try {
			details = new ObjectMapper().readValue(featureJson, MailDetails.class);
		} catch (JsonProcessingException e) {
			logger.error("Error deserializing mail details", e);
			throw new FeatureException(e.getMessage());
		}

		mailSender.setHost(details.getHost());
		mailSender.setPort(details.getPort());
		mailSender.setUsername(details.getUsername());
		mailSender.setPassword(details.getPassword());

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", details.getTransportProtocol());
		props.put("mail.smtp.auth", details.getSmtpAuth());
		props.put("mail.smtp.starttls.enable", details.getSmtpStartTlsEnable());
		props.put("mail.debug", details.getDebug());

		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom(details.getFromMail());
        message.setTo(details.getToMail());
        message.setSubject(details.getSubject());
		message.setText(String.format("Candidate %s %s applied for position %s", application.getFields(),
				application.getLastName(), application.getPosition().getDescription()));
        
        mailSender.send(message);
	}

	public static class MailDetails {
		private String host;
		private int port;
		private String username;
		private String password;
		private String transportProtocol;
		private String smtpAuth;
		private String smtpStartTlsEnable;
		private String debug;
		
		private String fromMail;
		private String toMail;
		private String subject;

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getTransportProtocol() {
			return transportProtocol;
		}

		public void setTransportProtocol(String transportProtocol) {
			this.transportProtocol = transportProtocol;
		}

		public String getSmtpAuth() {
			return smtpAuth;
		}

		public void setSmtpAuth(String smtpAuth) {
			this.smtpAuth = smtpAuth;
		}

		public String getSmtpStartTlsEnable() {
			return smtpStartTlsEnable;
		}

		public void setSmtpStartTlsEnable(String smtpStartTlsEnable) {
			this.smtpStartTlsEnable = smtpStartTlsEnable;
		}

		public String getDebug() {
			return debug;
		}

		public void setDebug(String debug) {
			this.debug = debug;
		}
		
		public String getFromMail() {
			return fromMail;
		}
		
		public void setFromMail(String fromMail) {
			this.fromMail = fromMail;
		}

		public String getToMail() {
			return toMail;
		}
		
		public void setToMail(String toMail) {
			this.toMail = toMail;
		}
		
		public String getSubject() {
			return subject;
		}
		
		public void setSubject(String subject) {
			this.subject = subject;
		}
	}

}
