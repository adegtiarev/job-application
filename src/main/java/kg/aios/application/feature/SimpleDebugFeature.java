package kg.aios.application.feature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kg.aios.application.model.JobApplication;
import kg.aios.application.util.FeatureException;

@Component("SimpleDebugFeature")
public class SimpleDebugFeature implements FeatureApi {

	private static final Logger logger = LoggerFactory.getLogger(SimpleDebugFeature.class);

	@Override
	public void perform(String featureJson, JobApplication application) throws FeatureException {
		DebugDetails details = null;
		try {
			details = new ObjectMapper().readValue(featureJson, DebugDetails.class);
		} catch (JsonProcessingException e) {
			logger.error("Error deserializing debug details", e);
			throw new FeatureException("Error deserializing debug details");
		}

		switch (details.getLevel()) {
		case "debug":
			logger.debug("Simple debug feature called for applicationId: {}", application.getId());
			break;
		case "info":
			logger.info("Simple debug feature called for applicationId: {}", application.getId());
			break;
		case "warn":
			logger.warn("Simple debug feature called for applicationId: {}", application.getId());
			break;
		}
	}

	public static class DebugDetails {
		private String level;

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}
	}

}
