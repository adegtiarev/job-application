package kg.aios.application.feature;

import kg.aios.application.model.JobApplication;
import kg.aios.application.util.FeatureException;

public interface FeatureApi {

	void perform(String featureJson, JobApplication application) throws FeatureException;

}
