package kg.aios.application.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import kg.aios.application.dao.FeatureDAO;
import kg.aios.application.feature.FeatureApi;
import kg.aios.application.model.Feature;
import kg.aios.application.model.JobApplication;
import kg.aios.application.util.FeatureException;

@Service
public class FeatureService {

	private static final Logger logger = LoggerFactory.getLogger(FeatureService.class);

	@Autowired
	private ApplicationContext context;

	@Autowired
	private FeatureDAO featureDao;

	@Transactional
	public void applyFeatures(JobApplication application) {
		List<Feature> features = featureDao.findByCompanyId(application.getCompany().getId());

		if (features != null) {
			for (Feature feature : features) {
				FeatureApi featureApi = (FeatureApi) context.getBean(feature.getBeanName());

				try {
					featureApi.perform(feature.getFeatureJson(), application);
				} catch (FeatureException e) {
					logger.error("Error performing feature", e);
				}
			}
		}
	}
}
