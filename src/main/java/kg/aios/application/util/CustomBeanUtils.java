package kg.aios.application.util;

import java.util.List;

import org.springframework.beans.BeanUtils;

public class CustomBeanUtils extends BeanUtils {
	public static <S, T> void deepCopy(List<S> source, List<T> target, Class<T> classType)
			throws InstantiationException, IllegalAccessException {
		if (source != null) {
			for (S src : source) {
				T trg = classType.newInstance();
				copyProperties(src, trg);
				target.add(trg);
			}
		}
	}
}
