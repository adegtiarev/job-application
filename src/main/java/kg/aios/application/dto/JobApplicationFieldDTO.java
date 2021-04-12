package kg.aios.application.dto;

public class JobApplicationFieldDTO {
	private Long fieldId;
	private Object value;
	
	public Long getFieldId() {
		return fieldId;
	}
	
	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fieldId == null) ? 0 : fieldId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobApplicationFieldDTO other = (JobApplicationFieldDTO) obj;
		if (fieldId == null) {
			if (other.fieldId != null)
				return false;
		} else if (!fieldId.equals(other.fieldId))
			return false;
		return true;
	}
	
	
}
