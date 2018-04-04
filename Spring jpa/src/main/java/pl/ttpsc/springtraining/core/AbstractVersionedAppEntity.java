package pl.ttpsc.springtraining.core;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class AbstractVersionedAppEntity implements AppEntity, Serializable {

	private static final long serialVersionUID = 917908602216655268L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	@Getter
	@Setter
	private Long version;

	@Getter
	@Setter
	private UUID uuid;

	public AbstractVersionedAppEntity() {
		this(false);
	}

	public AbstractVersionedAppEntity(boolean generateUuid) {
		if (generateUuid) {
			uuid = UUID.randomUUID();
		}
	}

	@Override
	public int hashCode() {
		return uuid.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AbstractVersionedAppEntity)) {
			return false;
		}
		AbstractVersionedAppEntity other = (AbstractVersionedAppEntity) obj;
		return getUuid().equals(other.getUuid());
	}
}
